package ru.tandemservice.test.task1;

import java.util.ArrayList;

public class Task1Demo
{
	public static void main(String[] args)
	{
		ArrayList<String[]> tableToSort = new ArrayList<String[]>();

//		String[] strArr0 = null;
//		String[] strArr1 = { "������", null, "",
//				"123������2", " " };
//		String[] strArr2 = { null, "", "������ ���1", "! ",
//				"��� 1-� ������" };
//		String[] strArr3 = { "", "��� ��� 3-� ������.",
//				null, null, " " };
//		String[] strArr4 = { null, "",
//				"�� ����������� ������!", "� �� ����...",
//				"1233211" };
//		String[] strArr5 = { "������ - ���������.",
//				"321123", "1234������_4", "�����������!",
//				"" };
//		String[] strArr6 = { "������ �����", "-1", "09214T",
//				"", null };
//		String[] strArr7 = { "������", null, "", "56123",
//				" " };
		String[] strArr1 = {"0", null};
		String[] strArr2 = {"1", " "};
		String[] strArr3 = {"2", null};
		String[] strArr4 = {"3", ""};
		String[] strArr5 = {"4", "200qweqwe"};
		String[] strArr6 = {"5", "1qweqwee"};
		String[] strArr7 = {"6", "123qwe21eew"};
		String[] strArr8 = {"7", "123q21ee"};
		String[] strArr9 = {"8", "123qwe21ee"};
		String[] strArr10 = {"9", "123qwe21eew"};
		String[] strArr11 = {"10", "123qwe21ee22"};
		String[] strArr12 = {"11", "qwe123qweq"};
		String[] strArr13 = {"12", "qwe12qwe"};
		String[] strArr14 = {"0", "1"};
		String[] strArr15 = {"10", "123q6we21ee22"};

//		tableToSort.add(strArr0);
		tableToSort.add(strArr1);
		tableToSort.add(strArr2);
		tableToSort.add(strArr3);
		tableToSort.add(strArr4);
		tableToSort.add(strArr5);
		tableToSort.add(strArr6);
		tableToSort.add(strArr7);
		tableToSort.add(strArr8);
		tableToSort.add(strArr9);
		tableToSort.add(strArr10);
		tableToSort.add(strArr11);
		tableToSort.add(strArr12);
		tableToSort.add(strArr13);
		tableToSort.add(strArr14);
		tableToSort.add(strArr15);

		Integer intVal = 1;

		try
		{
			Task1Impl.INSTANCE.sort(tableToSort, intVal);
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		}

		for (String[] row : tableToSort)
		{
			for (int i = 0; i < row.length; i++)
				System.out.print(row[i] + "\t");
			System.out.println();
		}
	}
}
