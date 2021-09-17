package ru.tandemservice.test.task2;

import java.util.LinkedList;
import java.util.List;

public class Stats
{
	/**
	 * Метод присвоения номеров. По умолчанию @code{NumberAssignerType.SORTING}
	 */
	private static NumberAssignerType nat;
	/**
	 * 
	 */
	private ElementExampleImpl.Context context;
	/**
	 * Количество обходов
	 */
	private int loops;
	/**
	 * Размер списка
	 */
	private int elementsSize;
	/**
	 * Список временных интервалов
	 */
	private final List<Long> timeIntervals = new LinkedList<Long>();

	@SuppressWarnings("unused")
	private Stats()
	{
	}

	public Stats(int loops, int size)
	{
		this.loops = loops;
		this.elementsSize = size;
	}

	/**
	 * 
	 * @param nat
	 */
	public static void setNumberAssignerType(
			NumberAssignerType nat)
	{
		Stats.nat = nat;
	}

	/**
	 * Добавляет временной интервал в список
	 * 
	 * @param l
	 */
	public void add(Long l)
	{
		timeIntervals.add(l);
	}

	public void addContext(
			ElementExampleImpl.Context context)
	{
		this.context = context;
	}
	
	public void showCurrent() {
		for (Long t : timeIntervals)
			System.out.println("Время:\t"
					+ String.valueOf(t)
					+ "\tКоличество операций:\t"
					+ String.valueOf(
							context.getOperationCount()));
	}

	public void showSummaryStats()
	{
		// Подсчет среднего арифметического времени
		long summary = 0;
		for (long s : timeIntervals)
			summary += s;

		System.out.println("Среднее время работы метода "+nat+" за "
				+ loops + " проходов по списку из "
				+ elementsSize + " элементов: "
				+ summary / loops + " мс.");
	}
}
