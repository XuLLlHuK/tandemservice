package ru.tandemservice.test.task2.errors;

/**
 * 
 * @author Melnikov M.M. <a href="mailto:m@lmsu.ru">m@lmsu.ru</a>
 *
 */
@SuppressWarnings("serial")
public final class EmptyListException extends Exception {

	public EmptyListException()
	{
		super("Неопределенное исключение.");
	}
	
	public EmptyListException(String msg) {
		super(msg);
	}
}
