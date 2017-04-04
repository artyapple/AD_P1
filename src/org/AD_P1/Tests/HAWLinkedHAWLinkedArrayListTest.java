package org.AD_P1.Tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.AD_P1.DoublyLinkedList.HAWLinkedArrayList;
import org.AD_P1.Element.Element;
import org.junit.Before;
import org.junit.Test;

public class HAWLinkedHAWLinkedArrayListTest {

	@Test
	public void tests() throws Exception {

		int keyCounter = 1;
		HAWLinkedArrayList<Integer> list = new HAWLinkedArrayList<Integer>();

		// insert test
		list.insert(list.find(keyCounter), new Element<Integer>(1, keyCounter));
		assertTrue(list.retrieve(list.find(keyCounter)).getValue().intValue() == 1);

		// push check
		list.insert(list.find(keyCounter++), new Element<Integer>(2, keyCounter++));

		assertTrue(list.retrieve(list.find(1)).getValue() == 1);
		assertTrue(list.retrieve(list.find(2)).getValue() == 2);

		// keyfind check
		assertTrue(list.retrieve(list.find(1)).getKey() == 1);
		assertTrue(list.retrieve(list.find(2)).getKey() == 2);

		// delcheck
		list.delete(list.find(1)); // posKill
		assertTrue(list.retrieve(list.find(1)) == null);

		list.delete(2);
		assertTrue(list.retrieve(list.find(2)) == null);

		// concat check
		list.append(new Element<>(1, 5));
		list.append(new Element<>(2, 6));

		HAWLinkedArrayList<Integer> listToConcat = new HAWLinkedArrayList<Integer>();

		listToConcat.append(new Element<>(3, 7));
		listToConcat.append(new Element<>(4, 8));

		list.concat(listToConcat);

		assertTrue(list.retrieve(list.find(5)).getValue() == 1);
		assertTrue(list.retrieve(list.find(6)).getValue() == 2);
		assertTrue(list.retrieve(list.find(7)).getValue() == 3);
		assertTrue(list.retrieve(list.find(8)).getValue() == 4);

		// insert Aufwandsanalyse
		System.out.println("Implementation B: Doubly Linked ArrayList");
		keyCounter = 0;
		list = new HAWLinkedArrayList<Integer>();
		for (int i = 1; i < 6; i++) {
			list = new HAWLinkedArrayList<Integer>();
			keyCounter = 0;
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.insert(list.find(j), new Element<>(0, j));
			}
			System.out.println("INSERT: Operations for " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

		System.out.println();
		// retrieve Aufwandsanalyse
		list.reset();
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.retrieve(list.find(j));
			}
			System.out.println("RETRIEVE: Operations for " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

		System.out.println();
		// find Aufwandsanalyse
		list.reset();
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.find(j);
			}
			System.out.println("FIND: Operations for " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}
		HAWLinkedArrayList<Integer> copyOfList = list;

		System.out.println();
		// delete pos Aufwandsanalyse
		for (int i = 1; i < 6; i++) {
			list = new HAWLinkedArrayList<Integer>();
			keyCounter = 0;
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.append(new Element<>(0, keyCounter++));
			}
			list.reset();
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.delete(list.find(j));
			}
			System.out.println("DELPOS: Operations for  " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

		System.out.println();
		// delete Key Aufwandsanalyse
		list.reset();
		for (int i = 1; i < 6; i++) {
			list = new HAWLinkedArrayList<Integer>();
			keyCounter = 0;
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.append(new Element<>(0, keyCounter++));
			}
			list.reset();
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.delete(j);
			}
			System.out.println("DELKEY: Operations for " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

	}
}
