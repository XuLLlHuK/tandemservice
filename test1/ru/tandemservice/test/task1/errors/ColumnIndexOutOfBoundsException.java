package ru.tandemservice.test.task1.errors;

/**
 * 
 * @author Melnikov M.M. <a href="mailto:m@lmsu.ru">m@lmsu.ru</a>
 *
 */
@SuppressWarnings("serial")
public final class ColumnIndexOutOfBoundsException
		extends Exception
{

	private static String getMessage(int index, int length)
	{
		String msgReplacementIndex = "*index*";
		String msgReplacementLength = "*lenght*";
		String msg1 = "������ ������� "
				+ msgReplacementIndex
				+ " �� ����� ���� ������ 0.";
		String msg2 = "������ " + msgReplacementIndex
				+ " ������� �� ������� �������� ������ "
				+ msgReplacementLength;
		String msg3 = "������ ������. length: "
				+ msgReplacementLength;
		String msg = "";

		if (index == 0 && length == 0)
			return msg;
		if (index <= 0 && length == -1)
			msg = msg1.replace(msgReplacementIndex,
					"(" + index + ")");
		if (index > 0)
			msg = msg2
					.replace(msgReplacementIndex,
							"(" + index + ")")
					.replace(msgReplacementLength,
							"(" + length + ")");
		if (length < 0)
			msg = msg3.replace(msgReplacementLength,
					"(" + length + ")");
		return msg;
	}

	public ColumnIndexOutOfBoundsException()
	{
		super("�������������� ����������.");
	}

	public ColumnIndexOutOfBoundsException(int colIndex)
	{
		super(getMessage(colIndex, -1));
	}

	public ColumnIndexOutOfBoundsException(int colIndex,
			int rowLength)
	{
		super(getMessage(colIndex, rowLength));
	}
}
