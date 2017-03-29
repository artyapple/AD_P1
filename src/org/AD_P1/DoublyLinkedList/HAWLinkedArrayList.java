package org.AD_P1.DoublyLinkedList;

import org.AD_P1.Element.Element;
import org.AD_P1.Interfaces.HAWList;
import org.AD_P1.Interfaces.HAWListElement;

public class HAWLinkedArrayList<E> implements HAWList<E> {
	


	/**
	 * size of list
	 */
	private int size;
	/**
	 * array to store elements
	 */
	private LinkedArrayElement[] array;
	/**
	 * index of first element in array
	 */
	private int indexFirstElement;
	
	private int lastElementIndex;

	public HAWLinkedArrayList() {
		size = 0;
		array = new LinkedArrayElement[INITIAL_ARRAY_LENGTH];
		array[0] = new LinkedArrayElement<>(0, -1, -1, new Element<E>(null, -1));
		indexFirstElement = -1;
		lastElementIndex = -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(Object pos, HAWListElement<E> elem) {
		
		LinkedArrayElement<E> container = (LinkedArrayElement<E>) pos;
		
//		if (pos < 0 || pos > size) {
//			throw new IndexOutOfBoundsException();
//		}

//		if (size == 0) {
//			array[0] = new LinkedArrayElement<E>(0, -1, -1, content);
//			indexFirstElement = 0;
//			size++;
//			return;
//		}

		int arrayIndexToSave = getFreeArrayIndex();
		int prevIndex = -1;
		int nextIndex = -1;

		if (pos == null) {
			// am Anfang
			LinkedArrayElement<E> firstElement = array[0];
			firstElement.setPrev(arrayIndexToSave);
			nextIndex = firstElement.getIndex();
			indexFirstElement = arrayIndexToSave;
		} else {
			LinkedArrayElement<E> posElement = (LinkedArrayElement<E>)pos;
			LinkedArrayElement<E> prevElement = (LinkedArrayElement<E>) array[posElement.getPrev()];
			posElement.setPrev(arrayIndexToSave);
			prevElement.setNext(arrayIndexToSave);
			prevIndex = prevElement.getIndex();
			nextIndex = posElement.getIndex();
		}

		array[arrayIndexToSave] = new LinkedArrayElement<E>(arrayIndexToSave, nextIndex, prevIndex, elem);
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(int pos) {
		if (pos < 0 || pos >= size) {
			throw new IndexOutOfBoundsException();
		}

		LinkedArrayElement<K,E> arrEl = getElementOfPosition(pos);

		if (size == 1) {
			//
			indexFirstElement = -1;
		} else if (pos == 0) {
			// wenn das Element ganz vorne steht
			LinkedArrayElement<K,E> next = (LinkedArrayElement<K,E>) array[arrEl.getNext()];
			next.setPrev(-1);
			indexFirstElement = next.getIndex();
		} else if (pos == size - 1) {
			// wenn das Element ganz am Ende steht
			LinkedArrayElement<K,E> prev = (LinkedArrayElement<K,E>) array[arrEl.getPrev()];
			prev.setNext(-1);
		} else {
			// wenn das Element in der Mitte der Liste steht
			LinkedArrayElement<K,E> next = (LinkedArrayElement<K,E>) array[arrEl.getNext()];
			LinkedArrayElement<K,E> prev = (LinkedArrayElement<K,E>) array[arrEl.getPrev()];
			prev.setNext(next.getIndex());
			next.setPrev(prev.getIndex());
		}
		array[arrEl.getIndex()] = null;
		size--;
	}

	@Override
	public HAWListElement<E> retrieve(Object pos) {
		if (pos < 0 || pos >= size) {
			throw new IndexOutOfBoundsException();
		}
		return getElementOfPosition(pos).getElement();
	}

	@Override
	public void concat(HAWList<K, E> otherList) {
		if (otherList == null) {
			return;
		}
		// stelle sicher, dass es genug Platz im Array zum Kopieren gibt
		if (array.length - size < otherList.size()) {
			increaseArray(size, otherList.size());
		}
		// kopiere alle Elemente
		for (int i = 0; i < otherList.size(); i++) {
			insert(size, otherList.retrieve(i));
		}
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the first free index in the array.
	 * 
	 * @return The first free index in the array.
	 */
	private int getFreeArrayIndex() {
		int i;
		for (i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return i;
			}
		}
		// free position not find
		// increase array
		increaseArray(array.length, INITIAL_ARRAY_LENGTH);
		return i + 1;
	}

	/**
	 * increases the length of array for 10 positions
	 */
	private void increaseArray(int base, int increase) {
		LinkedArrayElement[] newArray = new LinkedArrayElement[base + increase];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	@SuppressWarnings("unchecked")
	private LinkedArrayElement<E> getElementOfPosition(int pos) {
		LinkedArrayElement<E> arrayElement = (LinkedArrayElement<E>) array[indexFirstElement];
		for (int i = 0; i < pos; i++) {
			arrayElement = (LinkedArrayElement<E>) array[arrayElement.getNext()];
		}
		return arrayElement;
	}

	@Override
	public void delete(Object key) {
		
	}

	@Override
	public Object find(int key) {
		
		if(size == 0){
			return null;
		}
		
		
		LinkedArrayElement<E> stopper = array[0];
		stopper.setElement(new Element(null, key));
		
		LinkedArrayElement<E> elem = array[lastElementIndex];
		
		while(true){
			if(elem.getElementKey()==key){
				
				if(elem.getIndex() == 0){
					return null;
				} else {
					return elem;
				}
				
			} 
			elem = array[elem.getPrev()];			
		}
	}

//	@SuppressWarnings("unchecked")
//	public static void main(String[] args) {
//		HAWLinkedArrayList<Integer, String> list = new HAWLinkedArrayList<Integer, String>();
//		list.insert(0, "a");
//		list.insert(1, "b");
//		list.insert(0, "c");
//		list.delete(1);
//		list.insert(1, "d");
//
//		System.out.println(list.indexFirstElement);
//		for (int i = 0; i < list.array.length; i++) {
//			System.out.println((LinkedArrayElement<Integer, String>) (list.array[i]) + ", ");
//		}
//
//	}

}
