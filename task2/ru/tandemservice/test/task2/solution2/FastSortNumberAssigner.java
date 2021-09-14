package ru.tandemservice.test.task2.solution2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import ru.tandemservice.test.task2.errors.EmptyListException;
import ru.tandemservice.test.task2.IElement;
import ru.tandemservice.test.task2.generators.INumberGenerator;

public class FastSortNumberAssigner {

	private INumberGenerator numberGenerator;

	public void assignNumbers(final List<IElement> elements) throws EmptyListException {
		List<ElementWrapper> sortedElements = cloneAndSortList(elements);

		//Применение инверсивного генератора чисел возможна только если размер списка 
		//меньше половины диапазона целых чисел. В противном случае
		//присваиваемые номера идут последовательно
		if((Integer.MAX_VALUE-Math.abs(Integer.MIN_VALUE))/2 < sortedElements.size()) {
			numberGenerator = new SimpleGenerator(Integer.MIN_VALUE);
		}else {//TODO:
			numberGenerator = new InverseNumberGenerator(sortedElements);
		}

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
		List<ElementWrapper> sortedList = new ArrayList<ElementWrapper>(elements.size());

		for (IElement element : elements)
			sortedList.add(new ElementWrapper(element));

		Collections.sort(sortedList);

		return sortedList;
	}
}
