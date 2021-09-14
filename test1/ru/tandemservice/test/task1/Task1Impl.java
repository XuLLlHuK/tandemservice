package ru.tandemservice.test.task1;

import java.util.Collections;
import java.util.List;

import ru.tandemservice.test.task1.errors.*;

/**
 * <h1>Задание №1</h1> Реализуйте интерфейс {@link IStringRowsListSorter}.
 *
 * <p>
 * Мы будем обращать внимание в первую очередь на структуру кода и владение
 * стандартными средствами java.
 * </p>
 */
public final class Task1Impl
		implements IStringRowsListSorter
{

	// ваша реализация должна работать, как singleton. даже при использовании из
	// нескольких потоков.
	public static final IStringRowsListSorter INSTANCE = new Task1Impl();

	private Task1Impl()
	{
	}

	@Override
	public synchronized void sort(final List<String[]> rows,
			final int columnIndex)
	{
		try
		{
			if (rows == null || rows.size() == 0)
				throw new EmptyListException(
						"Пустой список нельзя отсортировать.");

			// Исходя из равенства размеров всех записей списка (таблица).
			final int rowsSize = rows.get(0) != null
					? rows.get(0).length
					: Integer.MIN_VALUE;

			if (columnIndex < 0)
				throw new ColumnIndexOutOfBoundsException(
						columnIndex);
			if (columnIndex >= rowsSize)
				throw new ColumnIndexOutOfBoundsException(
						columnIndex, rowsSize);

			Collections.sort(rows,
					new RowsSorter(columnIndex));
		} catch (ColumnIndexOutOfBoundsException
				| EmptyListException
				| NullPointerException e1)
		{
			e1.printStackTrace();
		}
	}
}
