package ru.tandemservice.test.task1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class RowsSorter implements Comparator<String[]>
{
	private int index = -1;

	@SuppressWarnings("unused")
	private RowsSorter()
	{
	}

	public RowsSorter(int columnIndex)
	{
		this.index = columnIndex;
	}

	@Override
	public int compare(String[] row_1, String[] row_2)
	{
		Integer compareResult = null;

		if ((row_1[index] == null || row_1[index] == ""
				|| row_1[index] == "\s")
				|| (row_2[index] == null
						|| row_2[index] == ""
						|| row_2[index] == "\s")) {
			if ((row_1[index] == null
					&& row_2[index] == null)
					|| (row_1[index] == ""
							&& row_2[index] == "")
					|| (row_1[index] == "\s"
							&& row_2[index] == "\s"))
				compareResult = 0;
			else if (row_1[index] == null)
				compareResult = -1;
			else if (row_2[index] == null)
				compareResult = 1;
			else if(row_1[index] == "" )
				compareResult = -1;
			else if(row_2[index] == "" )
				compareResult = 1;
			else if(row_1[index] == "\s" )
				compareResult = -1;
			else if(row_2[index] == "\s" )
				compareResult = 1;
			
			return compareResult;
		}

		String str_1 = row_1[this.index];
		String str_2 = row_2[this.index];
		String pattern = "((?<=\\D)(?=\\d)|(?<=\\d)(?=\\D))";

		/*
		 * разбиение на подстроки
		 */
		LinkedList<String> strList_1 = new LinkedList<String>(
				Arrays.asList(str_1.split(pattern)));
		LinkedList<String> strList_2 = new LinkedList<String>(
				Arrays.asList(str_2.split(pattern)));
		// TODO: проверка списков на наличие записей

		/*
		 * сравнение подстрок
		 */
		Iterator<String> it_1 = strList_1.iterator();
		Iterator<String> it_2 = strList_2.iterator();

		while (true)
		{
			if (it_1.hasNext() && it_2.hasNext())
			{
				String s_1 = it_1.next();
				String s_2 = it_2.next();

				if (isNumeric(s_1) && isNumeric(s_2))
				{// как числа
					Integer i_1 = Integer.valueOf(s_1);
					Integer i_2 = Integer.valueOf(s_2);

					if (i_1 == i_2)
						continue;
					else
						compareResult = i_1 - i_2;
					break;
				} else if (isNumeric(s_1) || isNumeric(s_2))
				{// как число и строка
					compareResult = isNumeric(s_1) ? -1 : 1;
					break;
				} else if (s_1 instanceof String
						&& s_2 instanceof String)
				{// как строки (лексикографически)
					if (s_1 == s_2)
						continue;
					else
						compareResult = s_1.compareTo(s_2);
				}
			} else
			{
				compareResult = it_1.hasNext() ? 1 : -1;
				break;
			}
		}

		return compareResult;
	}

	/**
	 * Проверяет, может ли строка быть числом
	 * 
	 * @param str строка
	 * @return <strong>true</strong>, если строка потенциальное число. Иначе
	 *         <strong>false</strong>
	 */
	private boolean isNumeric(String str)
	{
		if (str == null)
		{
			return false;
		}
		try
		{
			Integer.valueOf(str);
		} catch (NumberFormatException e3)
		{
			return false;
		}
		return true;
	}
}
