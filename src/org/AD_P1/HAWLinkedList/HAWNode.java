package org.AD_P1.HAWLinkedList;

import org.AD_P1.Interfaces.HAWListElement;

public class HAWNode<E> implements HAWListElement<E> {

	private HAWListElement<E> elem;
	private HAWNode<E> nextNode;
	
	public HAWNode(HAWListElement<E> elem, HAWNode<E> nextNode) {
		this.elem = elem;
		this.nextNode = nextNode;
	}
	
	@Override
	public E getValue() {
		E result = null;
		if (elem != null) {
			result = elem.getValue();
		}
		return result;
	}

	@Override
	public int getKey() {
		int result = -1;
		if (elem != null) {
			result = elem.getKey();
		}
		return result;
	}
	
	public HAWListElement<E> getElement() {
		return elem;
	}
	
	public HAWNode<E> getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(HAWNode<E> nextNode) {
		this.nextNode = nextNode;
	}

}
