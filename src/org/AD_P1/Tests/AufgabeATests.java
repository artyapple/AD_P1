package org.AD_P1.Tests;

import static org.junit.Assert.*;

import org.AD_P1.ArrayList.ArrayList;
import org.AD_P1.Element.Element;
import org.AD_P1.Interfaces.HAWList;
import org.junit.Test;

public class AufgabeATests {

	@Test
	public void tests() throws Exception {

		int keyCounter = 1;
		ArrayList<Integer> list = null;
		list = new ArrayList<Integer>();

		// insert test
		list.insert(0, new Element<>(0, keyCounter++));
		assertTrue(list.retrieve(0).getValue() == 0);

		// push check
		list.insert(0, new Element<>(1, keyCounter++));

		assertTrue(list.retrieve(1).getValue() == 0);
		assertTrue(list.retrieve(0).getValue() == 1);

		// keyfind check
		assertTrue(list.retrieve(list.find(1)).getKey() == 1);
		assertTrue(list.retrieve(list.find(2)).getKey() == 2);

		// delcheck
		list.delete(new Integer(0)); // posKill
		assertTrue(list.retrieve(0) == null);

		list.delete(1);
		assertTrue(list.retrieve(0) == null);

		// concat check
		list.insert(0, new Element<>(1, keyCounter++));
		list.insert(1, new Element<>(2, keyCounter++));

		ArrayList<Integer> listToConcat = null;
		listToConcat = new ArrayList<Integer>();

		listToConcat.insert(0, new Element<>(3, keyCounter++));
		listToConcat.insert(1, new Element<>(4, keyCounter++));

		list.concat(listToConcat);

		assertTrue(list.retrieve(0).getValue() == 1);
		assertTrue(list.retrieve(1).getValue() == 2);
		assertTrue(list.retrieve(10).getValue() == 3);
		assertTrue(list.retrieve(11).getValue() == 4);

		// insert Aufwandsanalyse
		keyCounter = 0;
		list = new ArrayList<Integer>();
		for (int i = 1; i < 6; i++) {
			list = new ArrayList<Integer>();
			keyCounter = 0;
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.insert(j, new Element<>(0, keyCounter++));
			}
			System.out.println("INSERT: Operations for " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

		System.out.println();
		// retrieve Aufwandsanalyse
		list.reset();
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.retrieve(j);
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
		ArrayList<Integer> copyOfList = list;

		System.out.println();
		// delete Pos Aufwandsanalyse
		for (int i = 1; i < 6; i++) {
			list = new ArrayList<Integer>();
			keyCounter = 0;
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.insert(j, new Element<>(0, keyCounter++));
			}
			list.reset();
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.delete(new Integer(j));
			}
			System.out.println("DELPOS: Operations for  " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

		System.out.println();
		// delete Key Aufwandsanalyse
		list.reset();
		for (int i = 1; i < 6; i++) {
			list = new ArrayList<Integer>();
			keyCounter = 0;
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.insert(j, new Element<>(0, keyCounter++));
			}
			list.reset();
			for (int j = 0; j < Math.pow(10, i); j++) {
				list.delete(j);
			}
			System.out.println("DELKEY: Operations for " + Math.pow(10, i) + " calls: " + list.getOpCount());
		}

	}

}
