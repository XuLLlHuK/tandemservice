package ru.tandemservice.test.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ru.tandemservice.test.task2.errors.EmptyListException;

public class Task2Demo
{
	private final static int listSize = 100_000;
	private final static int loops = 50;
	private final static List<Long> stats = new LinkedList<Long>();

	public static void main(String[] args)
	{
		for (int i = 0; i < loops; i++)
		{
			final ElementExampleImpl.Context context = new ElementExampleImpl.Context();
			final List<IElement> elements = createList(
					listSize, context);

			long timeStart = System.currentTimeMillis();

			/////////////////
			try
			{
				Task2Impl.INSTANCE.assignNumbers(elements);
			} catch (EmptyListException e)
			{
				e.printStackTrace();
			}
			/////////////////

			long timePassed = System.currentTimeMillis()
					- timeStart;

			System.out.println(i + "-ый проход.\tВремя:\t"
					+ String.valueOf(timePassed)
					+ "\tКоличество операций:\t"
					+ String.valueOf(
							context.getOperationCount()));

			stats.add(timePassed);
		}

		// Подсчет среднего арифметического времени
		long summary = 0;
		for (long s : stats)
			summary += s;

		System.out.println(
				"Среднее время работы алгоритма за " + loops
						+ " проходов: "
						+ String.valueOf(summary / loops)
						+ "мс");
		//
	}

	/**
	 * Создает экземпляр списка и заполняет его элементы номерами.
	 * 
	 * @param size
	 * @param context
	 * @return
	 */
	private static List<IElement> createList(int size,
			ElementExampleImpl.Context context)
	{
		// Для получения более "распределенного" или "разряженного" по значениям массива
		// номеров
		int[] poolOfNumbers = new int[size];
		Set<Integer> setOfNumbers = new HashSet<Integer>(
				size);

		for (int i = 0; i < size; i++)
			poolOfNumbers[i] = Integer.MIN_VALUE + i 
					* (Integer.MAX_VALUE / size);

		// "Миксер" номеров
		Random random = new Random();

		for (int i = poolOfNumbers.length - 1; i > 0; i--)
		{
			int index = random.nextInt(i);
			int n = poolOfNumbers[index];
			poolOfNumbers[index] = poolOfNumbers[i];
			poolOfNumbers[i] = n;
			setOfNumbers.add(poolOfNumbers[i]);
		}
		//

		// Заполняет элементы номерами
		List<IElement> elements = new ArrayList<IElement>();

		Iterator<Integer> iterator = setOfNumbers
				.iterator();
		while (iterator.hasNext())
			elements.add(new ElementExampleImpl(context,
					iterator.next().intValue()));
		//

		return Collections.unmodifiableList(elements);// unmutable list
	}
}
