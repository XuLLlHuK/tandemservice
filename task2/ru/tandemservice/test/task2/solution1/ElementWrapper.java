package ru.tandemservice.test.task2.solution1;

import ru.tandemservice.test.task2.IElement;

public class ElementWrapper implements Comparable<ElementWrapper> {
	/**
	 * ќборачиваемый элемент из первоначального списка
	 */
    final IElement element;
    /**
     * —сылки на сравниваемые элементы из отсортированного и неотсортированного списков.
     */
    ElementWrapper unsortedListElemRef, sortedListElemRef;
    /**
     * ‘лаг. Ёлемент уже пройден и находитс€ в составе звена св€занного списка.
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
