package org.AD_P1.ArrayList;

import java.util.Arrays;

import org.AD_P1.Counter.Counter;
import org.AD_P1.Element.Element;
import org.AD_P1.Interfaces.*;

public class ArrayList<E> extends Counter implements HAWList<E> {

	private static final int INITIAL_ARRAY_LENGTH = 10;

	private Element<E> arrayList[]; // list representaiting array
	private int currentAmount = 0;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList() {
		arrayList = new Element[INITIAL_ARRAY_LENGTH];
		size = INITIAL_ARRAY_LENGTH;
	}

	/**
	 * insert
	 * 
	 * insterts a further element to the array. checks about size of array.
	 * 
	 * @param int
	 *            position where element has to take in array
	 * @param elem
	 *            to be added
	 */
	@Override
	public void insert(Object pos, HAWListElement<E> elem) throws IllegalArgumentException {
		elementCheck(elem);

		Integer position = (Integer) pos;
		while (!(sizeCheck()) || !(posCheck(pos))) {
			increaseSize();
		}

		// position test
		if (arrayList[position] != null) {
			pushFiles(position);
		}

		arrayList[position] = (Element<E>) elem;
		currentAmount++;

	}

	/**
	 * delete
	 *
	 * deletes file element in arraylist if the position is not null. does
	 * nothing if position is out of range.
	 *
	 * @param pos
	 *            position of file element which should be deleted
	 **/
	@Override
	public void delete(Object pos) {
		Integer position = (Integer) pos;
		if (!(sizeCheck()) || !(posCheck(position))) {
			return;
		}
		arrayList[position] = null;
	}

	/**
	 * delete
	 * 
	 * delets all file elements with given key
	 * 
	 * @param key
	 *            key from elements which should get deleted
	 */
	@Override
	public void delete(int key) {
		for (int i = 0; i < size; i++) {
			if (arrayList[i] != null) {
				if (arrayList[i].getKey() == key) {
					arrayList[i] = null;
				}
			}
		}

	}

	/**
	 * find
	 * 
	 * gives back first position of element with given key
	 * 
	 * @param key
	 *            key from element which is requested
	 * 
	 * @return pos position of element
	 */
	@Override
	public Object find(int key) throws IllegalArgumentException {

		for (int i = 0; i < size; i++) {
			if (arrayList[i] != null) {
				if (arrayList[i].getKey() == key) {
					return i;
				}
			}
		}
		throw new IllegalArgumentException("Key not found");

	}

	/**
	 * retrieve
	 * 
	 * gives the element on pos back. if pos is not assigned, null will be given
	 * back.
	 * 
	 * @param pos
	 *            position of requested element
	 * @return retrieve requested element
	 */
	@Override
	public E retrieve(Object pos) throws IndexOutOfBoundsException {
		Integer position = (Integer) pos;

		if (position < 0 || position >= size) {
			throw new IndexOutOfBoundsException();
		}

		E retrieve = null;
		if (arrayList[position] != null) {
			retrieve = arrayList[position].getElement();
		}
		return retrieve;
	}

	/**
	 * sizeCheck
	 * 
	 * checks actual capturred saved elements with size of array a
	 * 
	 * @return true alright
	 * @return false out of bounce
	 */
	private boolean sizeCheck() {
		if (currentAmount >= size) {
			return false;
		}
		return true;
	}

	/**
	 * poscheck
	 * 
	 * checks if pos is in range
	 * 
	 * @param pos
	 *            to be cheked
	 * 
	 * @return true if pos is in range
	 * @return false if pos is out of range
	 */
	private boolean posCheck(Object pos) {
		Integer position = (Integer) pos;
		if (position >= size || position < 0) {
			return false;
		}
		return true;
	}

	/**
	 * increaseSize
	 * 
	 * increase Arrayssize size += 10
	 */
	private void increaseSize() {
		Element<E> newListArray[] = Arrays.copyOf(arrayList, arrayList.length + 10);
		arrayList = newListArray;
		size = arrayList.length;
	}

	/**
	 * elementCheck
	 * 
	 * checks element
	 * 
	 * @param elem
	 * @throws IllegalArgumentException
	 *             element null
	 */
	private void elementCheck(HAWListElement<E> elem) throws IllegalArgumentException {
		// element type and null check
		if (elem == null) {
			throw new IllegalArgumentException("element is null ");
		}

	}

	/**
	 * pushFiles
	 * 
	 * pushes Files until null field appears
	 * 
	 * @param pos
	 *            filepos to push from
	 * 
	 */
	private void pushFiles(int pos) {
		// arraysize check
		while (!(sizeCheck()) || !(posCheck(pos))) {
			increaseSize();
		}
		if (arrayList[pos + 1] != null) {
			pushFiles(pos + 1);
		}
		arrayList[pos + 1] = arrayList[pos];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * concat
	 * 
	 * concats 2 arraylists
	 * 
	 * @param otherList
	 *            other arraylist to concat
	 * @throws IllegalArgumentException
	 *             wrong type of list
	 */
	@Override
	public void concat(HAWList<E> otherList) throws IllegalArgumentException {
		ArrayList<E> castedList = (ArrayList<E>) otherList;

		// classcheck
		// if (otherList.retrieve(0) instanceof HAWListElement<E>) {
		// throw new IllegalArgumentException("wrong type of list");
		// }
		// save old container length and copy old list
		int arrayListLength = arrayList.length;
		arrayList = Arrays.copyOf(arrayList, arrayList.length + castedList.size());

		// adjust new size
		size = arrayList.length;

		Element<E>[] otherArray = (Element<E>[]) castedList.getContainer();

		for (int i = arrayList.length - otherArray.length; i < arrayList.length; i++) {
			arrayList[i] = otherArray[i - arrayListLength];
		}
		// adjusting amount of elements captured
		for (int i = 0; i < otherArray.length; i++) {
			if (otherArray[i] != null) {
				currentAmount++;
			}
		}

	}

	public Element<E>[] getContainer() {
		return arrayList;
	}

}
