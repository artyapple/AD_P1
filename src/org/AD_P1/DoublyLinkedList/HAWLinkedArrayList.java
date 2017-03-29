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
		// indexFirstElement = -1;
		// lastElementIndex = -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(Object pos, HAWListElement<E> elem) {

		int arrayIndexToSave = getFreeArrayIndex();
		int prevIndex = 0;
		int nextIndex = -1;

		if (pos == null) {
			// am Anfang
			// LinkedArrayElement<E> posElement = (LinkedArrayElement<E>)pos;
			LinkedArrayElement<E> stopper = array[0];
			if (size != 0) {

				LinkedArrayElement<E> firstElem = array[stopper.getNext()];
				stopper.setNext(arrayIndexToSave);
				firstElem.setPrev(arrayIndexToSave);

				// prevIndex = 0;
				nextIndex = firstElem.getIndex();

			} else {
				stopper.setNext(arrayIndexToSave);
				lastElementIndex = arrayIndexToSave;
				// prevIndex = 0;
				// nextIndex = -1;

			}
			indexFirstElement = arrayIndexToSave;

		} else {
			LinkedArrayElement<E> posElement = (LinkedArrayElement<E>) pos;

			if (posElement.getNext() != -1) {
				LinkedArrayElement<E> nextElement = (LinkedArrayElement<E>) array[posElement.getNext()];
				posElement.setNext(arrayIndexToSave);
				nextElement.setPrev(arrayIndexToSave);
				prevIndex = posElement.getIndex();
				nextIndex = nextElement.getIndex();
			} else {
				posElement.setNext(arrayIndexToSave);
				prevIndex = posElement.getIndex();
				lastElementIndex = arrayIndexToSave;
				// nextIndex = -1;
			}
		}

		array[arrayIndexToSave] = new LinkedArrayElement<E>(arrayIndexToSave, nextIndex, prevIndex, elem);
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(int key) {
		if (key < 0) {
			throw new IndexOutOfBoundsException();
		}

		LinkedArrayElement<E> elem = (LinkedArrayElement<E>) find(key);

		int last = -1;

		if (elem != null) {

			if (elem.getNext() != -1) {
				LinkedArrayElement<E> prevElem = array[elem.getPrev()];
				LinkedArrayElement<E> nextElem = array[elem.getNext()];

				prevElem.setNext(nextElem.getIndex());
				nextElem.setPrev(prevElem.getIndex());
			} else {
				LinkedArrayElement<E> prevElem = array[elem.getPrev()];
				prevElem.setNext(last);
			}
			array[elem.getIndex()] = null;
			size--;
		}
	}

	@Override
	public HAWListElement<E> retrieve(Object pos) {
		if (pos == null) {
			throw new IndexOutOfBoundsException();
		}
		LinkedArrayElement<E> arrayElement = (LinkedArrayElement<E>) pos;
		return arrayElement.getElementWrapper();
	}

	@Override
	public void concat(HAWList<E> otherList) {
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
	public void delete(Object pos) {
		if (pos != null) {
			LinkedArrayElement<E> elem = (LinkedArrayElement<E>) pos;
			array[elem.getIndex()] = null;
			size--;
		}
	}

	@Override
	public Object find(int key) {

		if (size == 0) {
			return null;
		}

		LinkedArrayElement<E> stopper = array[0];
		stopper.setElement(new Element(null, key));

		LinkedArrayElement<E> elem = array[lastElementIndex];

		while (true) {
			if (elem.getElementKey() == key) {
				if (elem.getIndex() == 0) {
					stopper.setElement(new Element(null, -1));
					return null;
				} else {
					return elem;
				}
			}
			elem = array[elem.getPrev()];
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		HAWLinkedArrayList<String> list = new HAWLinkedArrayList<String>();

		list.insert(list.find(111), new Element<String>("a", 111));
		list.insert(list.find(111), new Element<String>("b", 222));
		list.insert(list.find(000), new Element<String>("c", 333));
		list.delete(111);
		list.insert(list.find(222), new Element<String>("f", 444));
		// c, a, b

		// list.delete(1);
		// list.insert(1, "d");

		System.out.println(list.size);
		for (int i = 1; i < list.array.length; i++) {
			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
		}
	}
}
