package org.AD_P1.HAWLinkedList;
import org.AD_P1.Interfaces.HAWList;

public class HAWLinkedList< E> implements HAWList< E>{
	
	private Node head;
	private Node tail;
	private int size;
	
	public HAWLinkedList() {
		tail = new Node(null, null, null);
		head = new Node(null, null, tail);
		tail.setNextNode(head);
		size = 0;
	}

	@Override
	public void insert(int pos, E elem, K key) {
		checkPos(pos);
		checkKey(key);
		Node prevNode = getPreviousNode(pos);
		Node newNode = new Node(key, elem, prevNode.getNextNode());
		prevNode.setNextNode(newNode);
		if (pos == size) {
			tail.setNextNode(newNode);
		} 
		++size;
	}

	@Override
	public void delete(int pos) {
		checkPos(pos);
		Node prevNode = getPreviousNode(pos);
		prevNode.setNextNode(prevNode.getNextNode().getNextNode());
		--size;
	}

	@Override
	public void delete(K key) {
		checkKey(key);
		int pos = find(key);
		delete(pos);
	}

	@Override
	public int find(K key) {
		checkKey(key);
		int pos = -1;
		Node node = head;
		for (int i = 0; i < size; i++) {
			node = node.getNextNode();
			if (node.getKey().equals(key)) {
				pos = i;
				break;
			}
		}
		return pos;
	}

	@Override
	public E retrieve(int pos) {
		checkPos(pos);
		Node prevNode = getPreviousNode(pos);
		return prevNode.getNextNode().getElem();
	}
	
	@Override
	public K retrieveKey(int pos) {
		checkPos(pos);
		Node prevNode = getPreviousNode(pos);
		return prevNode.getNextNode().getKey();
	}

	@Override
	public HAWList<K, E> concat(HAWList<K, E> otherList) {
		HAWList<K, E> newList = new HAWLinkedList<K, E>();
		for (int i = 0; i < size(); i++) {
			newList.insert(i, retrieve(i), retrieveKey(i));
		}
		for (int i = 0; i < otherList.size(); i++) {
			newList.insert(size + i, otherList.retrieve(i), otherList.retrieveKey(i));
		} 
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
		return newList;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	private Node getPreviousNode(int pos) {
		if (pos == size) {
			return tail.getNextNode();
		}
		Node node = head;
		for (int i = 0; i < pos; i++) {
			node = node.getNextNode();
		}
		return node;
	}
	
	private void checkPos(int pos) {
		if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
	} 
	
	private class Node {
		
		private K key;
		private E elem;
		private Node nextNode;
		
		public Node(K key, E elem, Node nextNode) {
			this.key = key;
			this.elem = elem;
			this.nextNode = nextNode;
		}
		
		public K getKey() {
			return key;
		}
		
		public E getElem() {
			return elem;
		}
		
		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}
		
		public Node getNextNode() {
			return nextNode;
		}
		
	}

}
