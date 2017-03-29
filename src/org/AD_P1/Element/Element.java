package org.AD_P1.Element;

import org.AD_P1.Interfaces.HAWListElement;

public class Element<E> implements HAWListElement<E> {

	private E element;
	private int key;

	@Override
	public E getElement() {
		// TODO Auto-generated method stub
		return element;
	}

	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	public Element(E element, int key) {
		this.element = element;
		this.key = key;
	}

}
