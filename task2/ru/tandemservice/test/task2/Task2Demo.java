package ru.tandemservice.test.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
			
			stats.add(timePassed);
		}
	}
	
	/**
	 * Создает экземпляр списка и заполняет его элементы номерами.
	 * @param size
	 * @param context
	 * @return
	 */
	private static List<IElement> createList(int size,
			ElementExampleImpl.Context context)
	{
		int[] numbers = new int[size];
		
		for (int i = 0; i < size; i++)
			numbers[i] = Integer.MIN_VALUE + i
			* (2 * (Integer.MAX_VALUE / size - 1));

		Random random = new Random();
		
		for (int i = numbers.length - 1; i > 0; i--)
		{
			int index = random.nextInt(i + 1);
			int n = numbers[index];
			numbers[index] = numbers[i];
			numbers[i] = n;
		}

		List<IElement> elements = new ArrayList<IElement>();

		for (int i = 0; i < size; i++)
			elements.add(new ElementExampleImpl(context,
					numbers[i]));

		return Collections.unmodifiableList(elements);//unmutable list
	}
}
