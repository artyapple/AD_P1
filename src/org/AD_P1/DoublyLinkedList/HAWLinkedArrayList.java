package org.AD_P1.DoublyLinkedList;

import org.AD_P1.Interfaces.HAWList;

public class HAWLinkedArrayList<K, E> implements HAWList<K, E> {
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

	public HAWLinkedArrayList() {
		size = 0;
		array = new LinkedArrayElement[INITIAL_ARRAY_LENGTH];
		indexFirstElement = -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(int pos, E content) {
		if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException();
		}

		if (size == 0) {
			array[0] = new LinkedArrayElement<K,E>(0, -1, -1, content);
			indexFirstElement = 0;
			size++;
			return;
		}

		int arrayIndexToSave = getFreeArrayIndex();
		int prevIndex = -1;
		int nextIndex = -1;

		if (pos == 0) {
			// am Anfang
			LinkedArrayElement<K,E> firstElement = getElementOfPosition(pos);
			firstElement.setPrev(arrayIndexToSave);
			nextIndex = firstElement.getIndex();
			indexFirstElement = arrayIndexToSave;
		} else if (pos == size) {
			// wenn am Ende
			LinkedArrayElement<K,E> lastElement = getElementOfPosition(pos - 1);
			lastElement.setNext(arrayIndexToSave);
			prevIndex = lastElement.getIndex();
		} else {
			// in der Mitte der Liste
			LinkedArrayElement<K,E> posElement = getElementOfPosition(pos);
			LinkedArrayElement<K,E> prevElement = (LinkedArrayElement<K,E>) array[posElement.getPrev()];
			posElement.setPrev(arrayIndexToSave);
			prevElement.setNext(arrayIndexToSave);
			prevIndex = prevElement.getIndex();
			nextIndex = posElement.getIndex();
		}

		array[arrayIndexToSave] = new LinkedArrayElement<K,E>(arrayIndexToSave, nextIndex, prevIndex, content);
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
	public E retrieve(int pos) {
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
	private LinkedArrayElement<K,E> getElementOfPosition(int pos) {
		LinkedArrayElement<K,E> arrayElement = (LinkedArrayElement<K,E>) array[indexFirstElement];
		for (int i = 0; i < pos; i++) {
			arrayElement = (LinkedArrayElement<K,E>) array[arrayElement.getNext()];
		}
		return arrayElement;
	}

	@Override
	public void delete(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public int find(K key) {
		// TODO Auto-generated method stub
		return 0;
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
