package org.AD_P1.DoublyLinkedList;

import org.AD_P1.Element.Element;
import org.AD_P1.Interfaces.HAWListElement;

public class LinkedArrayElement<E> {

   
    /**
     * Position in array
     */
    private int index;
    
    /**
     * next element index in array
     * if element is last then -1
     */
    private int next;
    
    /**
     * previous element index in array
     * if element is first then -1
     */
    private int prev;
    
    /**
     * content (element)
     */
    private Element<E> element;
    
    
    public LinkedArrayElement(int index, int next, int prev, HAWListElement<E> element) {
        this.index = index;
        this.next = next;
        this.prev = prev;
        this.element = (Element<E>)element;
    }

    public String toString() {
        return "Key: "+ element.getKey() + " Element: "+ element.getValue() + " Index: " + index + " next: " + next + " prev: " + prev;
    }
    
    public int getIndex() {
        return index;
    }

    public int getNext() {
        return next;
    }

    public int getPrev() {
        return prev;
    }

    public Element<E> getElementWrapper() {
        return this.element;
    }
    
    public E getElementData(){
    	return getElementWrapper().getValue();
    }
    
    public int getElementKey(){
    	return getElementWrapper().getKey();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNext(int nextIndex) {
        this.next = nextIndex;
    }

    public void setPrev(int prevIndex) {
        this.prev = prevIndex;
    }

    public void setElement(Element<E> elem) {
        this.element = elem;
    }

    
    
}
