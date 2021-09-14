package ru.tandemservice.test.task2.solution2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import ru.tandemservice.test.task2.generators.INumberGenerator;

public class InverseNumberGenerator
		implements INumberGenerator
{
	LinkedList<Integer> pool = new LinkedList<Integer>();

	public InverseNumberGenerator(
			List<ElementWrapper> sortedElements)
	{		
		int minNumber = Integer.MIN_VALUE;

		Iterator<ElementWrapper> iterator = sortedElements
				.iterator();
		int elementNumber = iterator.next().getNumber();

		while (minNumber < Integer.MAX_VALUE
				&& pool.size() < sortedElements.size())
		{
			if (minNumber < elementNumber)
			{
				pool.add(minNumber);
			} else if (minNumber == elementNumber)
			{
				if (iterator.hasNext())
				{
					elementNumber = iterator.next().getNumber();
				}
			} else
			{
				pool.add(minNumber);
			}
			minNumber++;
		}
	}

	@Override
	public int getNumber()
	{
		return pool.removeFirst();
	}
}
