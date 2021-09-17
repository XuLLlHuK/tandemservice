package ru.tandemservice.test.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ru.tandemservice.test.task2.annotations.NumberAssignerTypeUse;
import ru.tandemservice.test.task2.errors.EmptyListException;

public class Task2Demo
{
	private final static int listSizeLimit = 1_000_000;
	private final static int loops = 50;
	/////////////////	
	private final static int listSizeDelta = 100_000;
	/////////////////

	public static void main(String[] args)
	{
		//Устанавливает метод присвоения номеров
		try
		{
			Stats.setNumberAssignerType(Task2Impl.INSTANCE.getClass()
					.getMethod("assignNumbers", List.class)
					.getAnnotation(
							NumberAssignerTypeUse.class)
					.value());
		} catch (NoSuchMethodException
				| SecurityException e1)
		{
			e1.printStackTrace();
		}
		
		List<Stats> stats = new LinkedList<Stats>();
		/////////////////
		int currentListSize = 100_000;
		///////////////
		
		do
		{
			currentListSize += listSizeDelta;
			Stats statsElement = new Stats(loops,
					currentListSize);
			long startTimeStep = System.currentTimeMillis();
			
			for (int i = 0; i < loops; i++)
			{
				final ElementExampleImpl.Context context = new ElementExampleImpl.Context();
				final List<IElement> elements = createList(
						currentListSize, context);

				long timeStart = System.currentTimeMillis();

				/////////////////
				try
				{
					Task2Impl.INSTANCE
							.assignNumbers(elements);
				} catch (EmptyListException e2)
				{
					e2.printStackTrace();
				}
				/////////////////

				long timePassed = System.currentTimeMillis()
						- timeStart;

				statsElement.add(timePassed);
				statsElement.addContext(context);
//				statsElement.showCurrent();
			}
			
			long timeStepPassed = System.currentTimeMillis()
					- startTimeStep;
			System.out.println("Время, затраченное на "+loops+" проходов для списка из "+currentListSize+" элементов:\t"+timeStepPassed+" мс");
			
			statsElement.showSummaryStats();
			stats.add(statsElement);
			
		} while (currentListSize < listSizeLimit);
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
			poolOfNumbers[i] = Integer.MIN_VALUE
					+ i * (Integer.MAX_VALUE / size);

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
