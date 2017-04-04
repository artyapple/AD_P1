package org.AD_P1.HAWLinkedList;
import org.AD_P1.Counter.Counter;
import org.AD_P1.Interfaces.HAWList;
import org.AD_P1.Interfaces.HAWListElement;

public class HAWLinkedList<E> implements HAWList<E>{
	
	public HAWNode<E> head;
	private HAWNode<E> tail;
	private int size;
	
	public HAWLinkedList() {
		tail = new HAWNode<E>(null, null);
		head = new HAWNode<E>(null, tail);
		tail.setNextNode(head);
		size = 0;
	}

	@Override
	public void insert(Object pos, HAWListElement<E> elem) {
		HAWNode<E> prevNode = checkPos(pos);
		Counter counter = new Counter();
		HAWNode<E> newNode = new HAWNode<E>(elem, prevNode.getNextNode());
		if (prevNode.getNextNode() == tail) {
			tail.setNextNode(newNode);
		} 
		prevNode.setNextNode(newNode);
		counter.count();
		++size;
		System.out.println("HAWLinkedList insert counter: " + counter.getOpCount());
	}

	@Override
	public void delete(Object pos) {
		HAWNode<E> node = checkPos(pos);
		Counter counter = new Counter();
		HAWNode<E> newNextNode = node.getNextNode().getNextNode();
		counter.count();
		node.setNextNode(newNextNode);
		if (newNextNode == tail) {
			tail.setNextNode(node);
			counter.count();
		}
		--size;
		System.out.println("HAWLinkedList delete(pos) counter: " + counter.getOpCount());
	}

	@Override
	public void delete(int key) {
		Counter counter = new Counter();
		counter.count();
		delete(find(key));
		System.out.println("HAWLinkedList delete(key) counter: " + counter.getOpCount());
	}

	@Override
	public HAWListElement<E> find(int key) {
		Counter counter = new Counter();
		HAWNode<E> pos = head;
		while (pos != tail && pos.getNextNode().getKey() != key) {
			counter.count();
			pos = pos.getNextNode();
		}
		if (pos == tail) {
			pos = null;
		}
		System.out.println("HAWLinkedList find counter: " + counter.getOpCount());
		return pos;
	}

	@Override
	public HAWListElement<E> retrieve(Object pos) {
		Counter counter = new Counter();
		counter.count();
		HAWNode<E> node = checkPos(pos).getNextNode();
		System.out.println("HAWLinkedList retrieve counter: " + counter.getOpCount());
		return node.getElement();
	}

	@Override
	public void concat(HAWList<E> otherList) {
		Counter counter = new Counter();
		if (!(otherList instanceof HAWLinkedList)) {
			throw new IllegalArgumentException();
		}
		if (otherList.size() > 0) {
			HAWLinkedList<E> otherLinkedList = (HAWLinkedList<E>) otherList; 
			HAWNode<E> node = tail.getNextNode(); 
			counter.count();
			node.setNextNode(otherLinkedList.head.getNextNode());
			tail = otherLinkedList.tail;
			counter.count();
			size += otherLinkedList.size();
		}
		System.out.println("HAWLinkedList concat counter: " + counter.getOpCount());
//		HAWNode<E> node = tail.getNextNode(); 
//		node.setNextNode(nextNode);
//		
//		for (int i = 0; i < otherList.size(); i++) {
//			insert(node, retrieve(i));
//			node = node.getNextNode();
//		}
//		if (otherList.size() == 0) {
//			return this;
//		}
//		if (otherList instanceof LinkedList) {
//			Node otherHead = ((LinkedList<K, E>) otherList).head;
//			Node temp = tail.getNextNode();
//			temp.setNextNode(otherHead.getNextNode());
//			tail = ((LinkedList<K, E>) otherList).tail;
//			size += otherList.size();
//		} else {
//			for (int i = 0; i < otherList.size(); i++) {
//				insert(size + i, otherList.retrieve(i), retrieveKey(i));
//			} 
//		}
	}
	
	@Override
	public int size() {
		Counter counter = new Counter();
		System.out.println("HAWLinkedList size counter: " + counter.getOpCount());
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private HAWNode<E> checkPos(Object pos) {
		if (!(pos instanceof HAWNode)) {
			throw new IllegalArgumentException();
		} 
		HAWNode<E> node = (HAWNode<E>) pos; 
		if (node == tail) {
			throw new IllegalArgumentException();
		}
		return node;
	}

}
