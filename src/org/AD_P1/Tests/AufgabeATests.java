package org.AD_P1.Tests;

import static org.junit.Assert.*;

import org.AD_P1.ArrayList.ArrayList;
import org.AD_P1.Interfaces.HAWList;
import org.junit.Test;

public class AufgabeATests {

	@Test
	public void tests() throws Exception {

		int keyCounter = 1;
		ArrayList<Integer, Integer> list = null;
		list = new ArrayList();

		// insert test
		list.insert(0, 1);
		list.setKey(0, keyCounter++);

		assertTrue(list.retrieve(0) == 1);

		// push check
		list.insert(0, 2);
		list.setKey(0, keyCounter++);

		assertTrue(list.retrieve(1) == 1);
		assertTrue(list.retrieve(0) == 2);

		// keyfind check
		assertTrue(list.retrieve(list.find(1)) == 1);
		assertTrue(list.retrieve(list.find(2)) == 2);

		// delcheck
		list.delete(0); // posKill
		assertTrue(list.retrieve(0) == null);

		list.delete((Integer) 1);
		assertTrue(list.retrieve(1) == null);

		// concat check
		list.insert(0, 1);
		list.setKey(0, keyCounter++);
		list.insert(1, 2);
		list.setKey(1, keyCounter++);

		ArrayList<Integer, Integer> listToConcat = null;
		listToConcat = new ArrayList();

		listToConcat.insert(0, 99);
		listToConcat.setKey(0, keyCounter++);

		listToConcat.insert(1, 100);
		listToConcat.setKey(1, keyCounter++);

		list.concat(listToConcat);

		assertTrue(list.retrieve(1) == 2);
		assertTrue(list.retrieve(0) == 1);
		assertTrue(list.retrieve(12) == null);
		assertTrue(list.retrieve(11) == 100);

		// 10 k Tests
		list = new  ArrayList<>();
		keyCounter = 0;

		for (int i = 0; i < 10000; i++) {
			list.insert(0, i);
			list.setKey(0, keyCounter++);
			assertTrue(list.retrieve(0) == i);
		}
		
		assertTrue(list.retrieve(0) == 9999);
		assertTrue(list.retrieve(9999) == 0);
	}
}
