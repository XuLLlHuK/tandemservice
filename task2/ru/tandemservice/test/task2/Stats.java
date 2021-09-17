package ru.tandemservice.test.task2;

import java.util.LinkedList;
import java.util.List;

public class Stats
{
	/**
	 * ����� ���������� �������. �� ��������� @code{NumberAssignerType.SORTING}
	 */
	private static NumberAssignerType nat;
	/**
	 * 
	 */
	private ElementExampleImpl.Context context;
	/**
	 * ���������� �������
	 */
	private int loops;
	/**
	 * ������ ������
	 */
	private int elementsSize;
	/**
	 * ������ ��������� ����������
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
	 * ��������� ��������� �������� � ������
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
			System.out.println("�����:\t"
					+ String.valueOf(t)
					+ "\t���������� ��������:\t"
					+ String.valueOf(
							context.getOperationCount()));
	}

	public void showSummaryStats()
	{
		// ������� �������� ��������������� �������
		long summary = 0;
		for (long s : timeIntervals)
			summary += s;

		System.out.println("������� ����� ������ ������ "+nat+" �� "
				+ loops + " �������� �� ������ �� "
				+ elementsSize + " ���������: "
				+ summary / loops + " ��.");
	}
}
