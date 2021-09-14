package ru.tandemservice.test.task2.solution1;

import ru.tandemservice.test.task2.IElement;

public class ElementWrapper implements Comparable<ElementWrapper> {
	/**
	 * ������������� ������� �� ��������������� ������
	 */
    final IElement element;
    /**
     * ������ �� ������������ �������� �� ���������������� � ������������������ �������.
     */
    ElementWrapper unsortedListElemRef, sortedListElemRef;
    /**
     * ����. ������� ��� ������� � ��������� � ������� ����� ���������� ������.
     */
    boolean passed = false;
    
    public ElementWrapper(IElement element) {
        this.element = element;
    }
    
    @SuppressWarnings("unused")
    private ElementWrapper() {
    	element = null;
    }

    @Override
    public int compareTo(ElementWrapper wrappedElement){
        return Integer.compare(this.element.getNumber(), wrappedElement.element.getNumber());
    }
            
    @Override
    public String toString() {
    	return "{"+this.element.getId()+", "+this.element.getNumber()+"}";
    }
}
