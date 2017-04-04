package org.AD_P1.DoublyLinkedList;

import static org.junit.Assert.assertTrue;

import org.AD_P1.Counter.Counter;
import org.AD_P1.Element.Element;
import org.AD_P1.Interfaces.HAWList;
import org.AD_P1.Interfaces.HAWListElement;

public class HAWLinkedArrayList<E> extends Counter implements HAWList<E> {

	private static final int STOPPER_PLACE = 1;

	/**
	 * size of list
	 */
	private int size;
	/**
	 * array to store elements
	 */
	private LinkedArrayElement[] array;
	/**
	 * index of last element in array
	 */
	private int lastElementIndex;

	public HAWLinkedArrayList() {
		size = 0;
		array = new LinkedArrayElement[INITIAL_ARRAY_LENGTH + STOPPER_PLACE];
		array[0] = new LinkedArrayElement<>(0, -1, -1, new Element<E>(null, -1));
		// indexFirstElement = -1;
		lastElementIndex = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(Object pos, HAWListElement<E> elem) {
		count();
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

	@Override
	public void delete(Object pos) {
		count();
		if (pos != null) {
			LinkedArrayElement<E> elem = (LinkedArrayElement<E>) pos;

			int last = -1;
			if (elem.getNext() != -1) {
				LinkedArrayElement<E> prevElem = array[elem.getPrev()];
				LinkedArrayElement<E> nextElem = array[elem.getNext()];

				prevElem.setNext(nextElem.getIndex());
				nextElem.setPrev(prevElem.getIndex());
			} else {
				LinkedArrayElement<E> prevElem = array[elem.getPrev()];
				prevElem.setNext(last);
				lastElementIndex = prevElem.getIndex();
			}

			array[elem.getIndex()] = null;
			size--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(int key) {
		if (key < 0) {
			throw new IndexOutOfBoundsException();
		}

		LinkedArrayElement<E> elem = (LinkedArrayElement<E>) find(key);

		delete(elem);
	}

	@Override
	public HAWListElement<E> retrieve(Object pos) {
		count();
		if (pos == null) {
			//throw new IndexOutOfBoundsException();
			return null;
		}
		LinkedArrayElement<E> arrayElement = (LinkedArrayElement<E>) pos;
		return arrayElement.getElementWrapper();
	}

	@Override
	public void concat(HAWList<E> otherList) {
		count();
		
		if (otherList == null) {
			return;
		}

		if (this.getClass().equals(otherList.getClass())) {
			System.out.println("equals");
		} else {
			System.out.println("not equals");
		}

		// stelle sicher, dass es genug Platz im Array zum Kopieren gibt
		if (array.length - size - STOPPER_PLACE < otherList.size()) {
			increaseArray(otherList.size());
		}
		// kopiere alle Elemente
		
		HAWLinkedArrayList<E> other = (HAWLinkedArrayList<E>) otherList;
		Element<E>[] content = other.getContainer();
		for (int i = 0; i < content.length; i++) {
			append(content[i]);
		}
	}

	@Override
	public int size() {
		count();
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
			count();
			if (array[i] == null) {
				return i;
			}
		}
		// free position not find
		// increase array
		increaseArray(INITIAL_ARRAY_LENGTH);
		return i + 1;
	}

	/**
	 * increases the length of array for 10 positions
	 */
	private void increaseArray(int incr) {
		count();
		LinkedArrayElement[] newArray = new LinkedArrayElement[array.length + incr];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}

	// @SuppressWarnings("unchecked")
	// private LinkedArrayElement<E> getElementOfPosition(int pos) {
	// LinkedArrayElement<E> arrayElement = (LinkedArrayElement<E>)
	// array[indexFirstElement];
	// for (int i = 0; i < pos; i++) {
	// arrayElement = (LinkedArrayElement<E>) array[arrayElement.getNext()];
	// }
	// return arrayElement;
	// }

	@SuppressWarnings("unchecked")
	@Override
	public Object find(int key) {

		if (size == 0) {
			return null;
		}

		LinkedArrayElement<E> stopper = array[0];
		stopper.setElement(new Element(null, key));

		LinkedArrayElement<E> elem = (LinkedArrayElement<E>) array[lastElementIndex];

		while (true) {
			count();
			if (elem.getElementKey() == key) {
				if (elem.getIndex() == 0) {
					stopper.setElement(new Element(null, -1));
					return null;
				} else {
					stopper.setElement(new Element(null, -1));
					return elem;
				}
			}
			elem = array[elem.getPrev()];
		}
	}

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param elem
	 */
	public void append(HAWListElement<E> elem) {
		insert(array[lastElementIndex], elem);
		// count();
		// int arrayIndexToSave, nextIndex, prevIndex;
		//
		// arrayIndexToSave = getFreeArrayIndex();
		// prevIndex = lastElementIndex;
		//
		// LinkedArrayElement<E> lastElem = array[lastElementIndex];
		//
		// lastElem.setNext(arrayIndexToSave);
		// prevIndex = lastElem.getIndex();
		// lastElementIndex = arrayIndexToSave;
		// nextIndex = -1;
		// array[arrayIndexToSave] = new LinkedArrayElement<E>(arrayIndexToSave,
		// nextIndex, prevIndex, elem);
		// size++;
	}

	/**
	 * Removes all of the elements from this list
	 */
	public void clear() {
		LinkedArrayElement<E> elem = array[lastElementIndex];

		int indexInArray = elem.getIndex();

		while (indexInArray != 0) {
			count();
			array[indexInArray] = null;
			elem = array[elem.getPrev()];
			indexInArray = elem.getIndex();
		}

		// stopper elem
		elem.setNext(-1);
		size = 0;
		lastElementIndex = 0;
	}

	public LinkedArrayElement[] toArray() {
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public Element<E>[] getContainer() {
		Element[] content = new Element[size]; 
		for(int i = 1; i < array.length; i++){
			if(array[i]!=null){
				Element<E> cont = (Element<E>)array[i].getElementWrapper();
				if(cont!=null){
					content[i-1] = cont;
				}
			}
			
			 
		}
		
		return content;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		HAWLinkedArrayList<Integer> list = new HAWLinkedArrayList<Integer>();
		int keyCounter = 1;
		///
		
		list.insert(list.find(keyCounter), new Element<Integer>(1, keyCounter++));
		list.retrieve(list.find(keyCounter)).getValue();

		// push check
		list.insert(list.find(keyCounter), new Element<Integer>(2, keyCounter++));
		for (int i = 0; i < list.array.length; i++) {
			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
		}
		
		list.retrieve(list.find(1));
		list.retrieve(list.find(2));
		
		for (int i = 0; i < list.array.length; i++) {
			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
		}
		
		///
		
		

//		list.insert(null, new Element<String>("a", 111));
//		String st = list.retrieve(list.find(111)).getValue();
//		System.out.println("retrieve elem 111: " + st);
//		
//		list.insert(list.find(111), new Element<String>("b", 222));
//		list.insert(list.find(000), new Element<String>("c", 333));
//		list.delete(111);
//		list.insert(list.find(222), new Element<String>("f", 444));
//		list.delete(list.find(222));
//		list.append(new Element<String>("x", 555));
//		list.append(new Element<String>("y", 666));
//		list.append(new Element<String>("z", 777));
//		String str = list.retrieve(list.find(333)).getValue();

		// c, a, b

		// list.delete(1);
		// list.insert(1, "d");
//
//		System.out.println(list.size);
//		for (int i = 0; i < list.array.length; i++) {
//			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
//		}
//		System.out.println("retrieve elem 333: " + str);
//		System.out.println("array lenght: " + list.array.length);
//
//		list.clear();
//		System.out.println("---clear list---");
//		for (int i = 0; i < list.array.length; i++) {
//			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
//		}
//		System.out.println("array lenght: " + list.array.length);
//
//		for (int i = 0; i < 25; i++) {
//			list.append(new Element<String>("test", 10000 + i));
//		}
//		System.out.println("---increase list---");
//		for (int i = 0; i < list.array.length; i++) {
//			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
//		}
//
//		System.out.println("array lenght: " + list.array.length);
//
//		HAWLinkedArrayList<String> otherList = new HAWLinkedArrayList<String>();
//		for (int i = 0; i < 16; i++) {
//			otherList.append(new Element<String>("test", 10025 + i));
//		}
//
//		System.out.println("---concat list---");
//
//		list.concat(otherList);
//		for (int i = 0; i < list.array.length; i++) {
//			System.out.println((LinkedArrayElement<String>) (list.array[i]) + ", ");
//		}
	}
}
