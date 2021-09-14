package ru.tandemservice.test.task2.solution2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ru.tandemservice.test.task2.errors.EmptyListException;
import ru.tandemservice.test.task2.IElement;
import ru.tandemservice.test.task2.generators.INumberGenerator;
import ru.tandemservice.test.task2.generators.InverseNumberGenerator;
import ru.tandemservice.test.task2.generators.SortNumberGenerator;
import ru.tandemservice.test.task2.solution2.ElementWrapper;

public class FastSortNumberAssigner {

	private INumberGenerator numberGenerator;

	public FastSortNumberAssigner() {
	}

	public void assignNumbers(final List<IElement> elements) throws EmptyListException {
		if (elements == null || elements.isEmpty()) {
			throw new EmptyListException();
		}

		List<ElementWrapper> sortedElements = cloneAndSortList(elements);

		numberGenerator = new InverseNumberGenerator();

		Iterator<ElementWrapper> iterator = sortedElements.iterator();

		for (IElement acceptor : elements) {
			ElementWrapper donor = iterator.next();
			
			if (donor.getElement() != acceptor) {
				int donorNumber = donor.getNumber();
				int number = numberGenerator.getNumber();

				if (donor.getElement().getNumber() == donor.getNumber())
					donor.getElement().setupNumber(number);

				acceptor.setupNumber(donorNumber);
			}
		}
	}

	/**
	 * 
	 * @param elements
	 * @return
	 */
	private static List<ElementWrapper> cloneAndSortList(List<IElement> elements) {
		List<ElementWrapper> sortedElements = new ArrayList<ElementWrapper>(elements.size());

		for (IElement element : elements)
			sortedElements.add(new ElementWrapper(element));

		Collections.sort(sortedElements);

		return sortedElements;
	}

	// TODO: переложить условие на выбор
	private static FreeNumbersFinder getFinder(List<ElementWrapper> sortedElements) {
		int number = sortedElements.get(sortedElements.size() - 1).initialNumber;
		if (number < Integer.MAX_VALUE - sortedElements.size()) {
			return new SimpleFinder(number + 1);
		}

		number = Integer.MIN_VALUE;

		for (ElementWrapper element : sortedElements) {
			if (element.initialNumber - number > sortedElements.size() + 1)
				return new SimpleFinder(number + 1);
			number = element.initialNumber;
		}

		return new DifficultFinder(sortedElements);
	}
}
