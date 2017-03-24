package org.AD_P1.ArrayList;

import java.util.Arrays;
import org.AD_P1.Interfaces.File;
import org.AD_P1.Interfaces.*;

public class ArrayList<K, E> implements HAWList<K, E> {

	private static final int INITIAL_ARRAY_LENGTH = 10;

	private FileA<K, E> arrayList[]; // list representaiting array
	private int currentAmount = 0;
	private int size;

	/**
	 * FileA represents key and value which have to be safed.
	 * 
	 * @author me
	 *
	 * @param <K>
	 *            key
	 * @param <E>
	 *            value
	 */
	public class FileA<T, G> implements File<T, G> {

		private T key = null;
		private G element = null;

		@Override
		public T getKey() {
			return key;
		}

		@Override
		public G getElement() {
			return element;
		}

		public void setKey(T key) {
			this.key = key;
		}

		protected FileA(T key, G element) {
			this.key = key;
			this.element = element;
		}

	}

	@SuppressWarnings("unchecked")
	public ArrayList() {
		arrayList = new FileA[INITIAL_ARRAY_LENGTH];
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
	public void insert(int pos, E elem, K key) throws IllegalArgumentException {

		elementCheck(elem);
		
		// // pos check
		// if (pos >= 10 || pos < 0) {
		// throw new IndexOutOfBoundsException("");
		// }

		// arraysize check
		while (!(sizeCheck()) || !(posCheck(pos))) {
			increaseSize();
		}

		// position test
		if (arrayList[pos] != null) {
			pushFiles(pos);
		}

		arrayList[pos] = new FileA<K, E>(null, elem);
		setKey(pos, key);
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
	public void delete(int pos) {
		if (!(sizeCheck()) || !(posCheck(pos))) {
			return;
		}
		arrayList[pos] = null;
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
	public void delete(K key) {
		for (int i = 0; i < size; i++) {
			if (arrayList[i].getKey() == key) {
				arrayList[i] = null;
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
	public int find(K key) throws IllegalArgumentException {

		for (int i = 0; i < size; i++) {
			if (arrayList[i].getKey() == key) {
				return i;
			}
		}

		return (Integer) null;
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
	public E retrieve(int pos) throws IndexOutOfBoundsException {

		if (pos < 0 || pos >= size) {
			throw new IndexOutOfBoundsException();
		}

		E retrieve = null;
		if (arrayList[pos] != null) {
			retrieve = arrayList[pos].getElement();
		}
		return retrieve;
	}

	@Override
	public HAWList<K, E> concat(HAWList<K, E> otherList) {
		// TODO Auto-generated method stub
		return null;
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
	private boolean posCheck(int pos) {
		if (pos >= size || pos < 0) {
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
		FileA<K, E> newListArray[] = Arrays.copyOf(arrayList, arrayList.length + 10);
		arrayList = newListArray;
		size = arrayList.length;
	}

	/**
	 * elementCheck
	 * 
	 * checks element
	 * 
	 * @param element
	 * @throws IllegalArgumentException
	 *             element null
	 */
	private void elementCheck(E element) throws IllegalArgumentException {
		// element type and null check
		if (element == null) {
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

	/**
	 * setKey
	 * 
	 * sets key because we dont know how to set keys
	 * 
	 * @param pos
	 *            position of file that will receive a key
	 * @param key
	 *            key from element
	 */
	public void setKey(int pos, K key) {
		if (!posCheck(pos)) {
			arrayList[pos].setKey(key);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K retrieveKey(int pos) {
		// TODO Auto-generated method stub
		return null;
	}
}
