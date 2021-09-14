package ru.tandemservice.test.task2.solution2;

import ru.tandemservice.test.task2.generators.INumberGenerator;

public class SimpleGenerator implements INumberGenerator
{
	int number = Integer.MIN_VALUE;
	
	public SimpleGenerator(int initialNumber) {
		this.number = initialNumber;
	}
	
	@SuppressWarnings("unused")
	private SimpleGenerator() {}

	@Override
	public int getNumber()
	{
		return this.number++;
	}

}
