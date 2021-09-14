package ru.tandemservice.test.task2.solution2;

import ru.tandemservice.test.task2.IElement;

public class ElementWrapper implements Comparable<ElementWrapper>
{
	/**
	 * Оборачиваемый элемент из первоначального списка
	 */
	private final IElement element;
	/**
	 * Номер оборачиваемого элемента
	 */
	private int elementNumber;
	
	public ElementWrapper(IElement element){
		this.element = element;
	}
	
	@SuppressWarnings("unused")
	private ElementWrapper(){
		element = null;
	}
	
	public IElement getElement() {
		return this.element;
	}
	
	public int getNumber(){
		return this.elementNumber;
	}
	
	@Override
    public int compareTo(ElementWrapper wrappedElement){
        return Integer.compare(this.element.getNumber(), wrappedElement.element.getNumber());
    }
            
    @Override
    public String toString(){
    	return "{"+this.element.getId()+", "+this.element.getNumber()+"}";
    }
}
