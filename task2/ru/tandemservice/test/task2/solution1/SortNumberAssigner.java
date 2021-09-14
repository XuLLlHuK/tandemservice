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
		// Клонирует первоначальный список и "оборачивает" его элементы
		final List<ElementWrapper> wrappedElements = cloneAndWrapList(elements);
		// Копирует клонированный список и сортирует его по значению number из каждого
		// элемента списка elements
		final List<ElementWrapper> sortedWrappedElements = new ArrayList<ElementWrapper>(wrappedElements);
		Collections.sort(sortedWrappedElements);
		//
		Iterator<ElementWrapper> wrappedElementsIterator = wrappedElements.iterator();
		Iterator<ElementWrapper> sortedWrappedElementsIterator = sortedWrappedElements.iterator();
		while (sortedWrappedElementsIterator.hasNext() && wrappedElementsIterator.hasNext()) {
			ElementWrapper sortedElement = sortedWrappedElementsIterator.next();
			ElementWrapper wrappedElement = wrappedElementsIterator.next();
			if (sortedElement != wrappedElement) {// элементы, номера которых сохраняют свои значения в первоначальном
													// списке
				sortedElement.unsortedListElemRef = wrappedElement;
				wrappedElement.sortedListElemRef = sortedElement;
			}
		}
		// Создает связанный список упорядоченных элементов - "звеньев" или их головных
		// элементов отсортированного списка
		List<ElementWrapper> chainLinks = makeChainLinks(sortedWrappedElements);

		INumberGenerator ng = new SortNumberGenerator(elements);

		for (ElementWrapper firstChainLink : chainLinks)
			assignNumbersToChain(firstChainLink, ng.getNumber());
	}

	/**
	 * Метод клонирует список и "оборачивает" его элементы
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
	 * Создает связанный список упорядоченных звеньев цепочек из отсортированного
	 * списка List<{@link ElementWrapper}>
	 * 
	 * @param wrappedElements "оберточный" список
	 * @return LinkedList<{@link ElementWrapper}>
	 */
	private List<ElementWrapper> makeChainLinks(List<ElementWrapper> sortedWrappedElements) {
		LinkedList<ElementWrapper> chainLinks = new LinkedList<ElementWrapper>();
		for (ElementWrapper element : sortedWrappedElements) {
			if (!element.passed) {
				if (element.sortedListElemRef != null) {// Исключаются элементы, сохранившие свои номера из
														// первоначального списка
					while (!element.sortedListElemRef.passed) {// Пока цепочка элементов не замкнется...
						element.sortedListElemRef.passed = true;
						element = element.sortedListElemRef;
					}
					chainLinks.add(element);// ...добавить первый элемент цепочки
				}
			}
		}
		return chainLinks;
	}

	/**
	 * Присваивает номера элементам последовательностей цепочек
	 * 
	 * @param firstChainLink  - первый элемент цепочки
	 * @param generatedNumber - сгенерированный номер
	 */
	private void assignNumbersToChain(ElementWrapper firstChainLink, int generatedNumber) {
		ElementWrapper chainLink = firstChainLink;

		do {
			int tempNumber = chainLink.element.getNumber();
			assignNumberToElement(chainLink, generatedNumber);
			generatedNumber = tempNumber;
			chainLink = chainLink.unsortedListElemRef;
		} while (chainLink != firstChainLink);// пока цепочка не замкнулась

		assignNumberToElement(chainLink, generatedNumber);
	}

	/**
	 * С помощью метода {@link IElement#setupNumber(int)} записывает в исходный
	 * список полученные значения number
	 * 
	 * @param wrappedElement
	 * @param number
	 */
	private void assignNumberToElement(ElementWrapper wrappedElement, int number) {
		wrappedElement.element.setupNumber(number);
	}
}
