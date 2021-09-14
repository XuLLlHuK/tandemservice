package ru.tandemservice.test.task2.solution1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ru.tandemservice.test.task2.*;
import ru.tandemservice.test.task2.errors.EmptyListException;
import ru.tandemservice.test.task2.generators.*;

public class SortNumberAssigner implements IElementNumberAssigner {
	@Override
	public void assignNumbers(final List<IElement> elements) throws EmptyListException {
		// ��������� �������������� ������ � "�����������" ��� ��������
		final List<ElementWrapper> wrappedElements = cloneAndWrapList(elements);
		// �������� ������������� ������ � ��������� ��� �� �������� number �� �������
		// �������� ������ elements
		final List<ElementWrapper> sortedWrappedElements = new ArrayList<ElementWrapper>(wrappedElements);
		Collections.sort(sortedWrappedElements);
		//
		Iterator<ElementWrapper> wrappedElementsIterator = wrappedElements.iterator();
		Iterator<ElementWrapper> sortedWrappedElementsIterator = sortedWrappedElements.iterator();
		while (sortedWrappedElementsIterator.hasNext() && wrappedElementsIterator.hasNext()) {
			ElementWrapper sortedElement = sortedWrappedElementsIterator.next();
			ElementWrapper wrappedElement = wrappedElementsIterator.next();
			if (sortedElement != wrappedElement) {// ��������, ������ ������� ��������� ���� �������� � ��������������
													// ������
				sortedElement.unsortedListElemRef = wrappedElement;
				wrappedElement.sortedListElemRef = sortedElement;
			}
		}
		// ������� ��������� ������ ������������� ��������� - "�������" ��� �� ��������
		// ��������� ���������������� ������
		List<ElementWrapper> chainLinks = makeChainLinks(sortedWrappedElements);

		INumberGenerator ng = new SortNumberGenerator(elements);

		for (ElementWrapper firstChainLink : chainLinks)
			assignNumbersToChain(firstChainLink, ng.getNumber());
	}

	/**
	 * ����� ��������� ������ � "�����������" ��� ��������
	 * 
	 * @param elements - List<{@link IElement}>
	 * @return ArrayList<{@link ElementWrapper}>
	 */
	private ArrayList<ElementWrapper> cloneAndWrapList(List<IElement> elements) {
		ArrayList<ElementWrapper> wrappedElementsList = new ArrayList<ElementWrapper>(elements.size());
		for (IElement element : elements) {
			wrappedElementsList.add(new ElementWrapper(element));
		}
		return wrappedElementsList;
	}

	/**
	 * ������� ��������� ������ ������������� ������� ������� �� ����������������
	 * ������ List<{@link ElementWrapper}>
	 * 
	 * @param wrappedElements "����������" ������
	 * @return LinkedList<{@link ElementWrapper}>
	 */
	private List<ElementWrapper> makeChainLinks(List<ElementWrapper> sortedWrappedElements) {
		LinkedList<ElementWrapper> chainLinks = new LinkedList<ElementWrapper>();
		for (ElementWrapper element : sortedWrappedElements) {
			if (!element.passed) {
				if (element.sortedListElemRef != null) {// ����������� ��������, ����������� ���� ������ ��
														// ��������������� ������
					while (!element.sortedListElemRef.passed) {// ���� ������� ��������� �� ���������...
						element.sortedListElemRef.passed = true;
						element = element.sortedListElemRef;
					}
					chainLinks.add(element);// ...�������� ������ ������� �������
				}
			}
		}
		return chainLinks;
	}

	/**
	 * ����������� ������ ��������� ������������������� �������
	 * 
	 * @param firstChainLink  - ������ ������� �������
	 * @param generatedNumber - ��������������� �����
	 */
	private void assignNumbersToChain(ElementWrapper firstChainLink, int generatedNumber) {
		ElementWrapper chainLink = firstChainLink;

		do {
			int tempNumber = chainLink.element.getNumber();
			assignNumberToElement(chainLink, generatedNumber);
			generatedNumber = tempNumber;
			chainLink = chainLink.unsortedListElemRef;
		} while (chainLink != firstChainLink);// ���� ������� �� ����������

		assignNumberToElement(chainLink, generatedNumber);
	}

	/**
	 * � ������� ������ {@link IElement#setupNumber(int)} ���������� � ��������
	 * ������ ���������� �������� number
	 * 
	 * @param wrappedElement
	 * @param number
	 */
	private void assignNumberToElement(ElementWrapper wrappedElement, int number) {
		wrappedElement.element.setupNumber(number);
	}
}
