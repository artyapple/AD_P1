package org.AD_P1.DoublyLinkedList;

import org.AD_P1.Interfaces.HAWListElement;

public class LinkedArrayElement<E> implements HAWListElement< E> {
   
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
    private E element;
    
    
    //TODO Key
    
    public LinkedArrayElement(int index, int next, int prev, E element) {
        this.index = index;
        this.next = next;
        this.prev = prev;
        this.element = element;
    }

    public String toString() {
        return element + " " + index + " " + next + " " + prev;
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

    @Override
    public E getElement() {
        return element;
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

    public void setElement(E content) {
        this.element = content;
    }

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return null;
	}

    
    
}
