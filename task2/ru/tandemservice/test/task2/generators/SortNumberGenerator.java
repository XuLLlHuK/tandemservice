package ru.tandemservice.test.task2.generators;

import java.util.List;
import ru.tandemservice.test.task2.IElement;

public class SortNumberGenerator implements INumberGenerator{
    private final List<IElement> elements;
    int number = Integer.MIN_VALUE;
    int index = 0;

    public SortNumberGenerator(List<IElement> elements){
        this.elements = elements;
    }

    @Override
    public int getNumber(){
    	number++;
        if (index >= elements.size()){
        	return number;
        }
        for (; index < elements.size(); index++) {
        	if (number != elements.get(index).getNumber())
        		break;
        	number++;
        }
        return number;
    }
}
