package ru.tandemservice.test.task2;

import java.io.PrintStream;
import java.util.List;

import ru.tandemservice.test.task2.errors.*;
import ru.tandemservice.test.task2.annotations.*;
import ru.tandemservice.test.task2.solution1.SortNumberAssigner;
import ru.tandemservice.test.task2.solution2.FastSortNumberAssigner;

/**
 * <h1>Задание №2</h1> Реализуйте интерфейс {@link IElementNumberAssigner}.
 *
 * <p>
 * Помимо качества кода, мы будем обращать внимание на оптимальность
 * предложенного алгоритма по времени работы с учетом скорости выполнения
 * операции присвоения номера: большим плюсом (хотя это и не обязательно) будет
 * оценка числа операций, доказательство оптимальности или указание области, в
 * которой алгоритм будет оптимальным.
 * </p>
 */
public class Task2Impl implements IElementNumberAssigner
{
	// ваша реализация должна работать, как singleton. даже при использовании из
	// нескольких потоков.
	public static final IElementNumberAssigner INSTANCE = new Task2Impl();
	NumberAssignerType nat;

	private Task2Impl()
	{
	}

	@Override
	@NumberAssignerTypeUse(NumberAssignerType.SORTING)
	public synchronized void assignNumbers(
			final List<IElement> elements)
	{
		// напишите здесь свою реализацию. Мы ждем от вас хорошо структурированного,
		// документированного и понятного кода, работающего за разумное время.

		try
		{
			nat = INSTANCE.getClass()
					.getMethod("assignNumbers", List.class)
					.getAnnotation(
							NumberAssignerTypeUse.class)
					.value();

			if (elements == null || elements.isEmpty()
					|| elements.size() == 0)
				throw new EmptyListException(
						"Пустой список.");
			if (elements.size() == 1)
			{
				System.out.println(
						"Список состоит из одного элемента.");
				return;
			}

			switch (nat)
			{
			case FAST_SORT:
				new FastSortNumberAssigner()
						.assignNumbers(elements);
				break;
			case SORTING:
			default:
				new SortNumberAssigner()
						.assignNumbers(elements);
				break;
			}
		} catch (EmptyListException | NoSuchMethodException
				| SecurityException e2)
		{
			e2.printStackTrace();
		}
	}

}
