package org.AD_P1.Tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.AD_P1.DoublyLinkedList.HAWLinkedArrayList;
import org.junit.Before;
import org.junit.Test;

public class HAWLinkedArrayListTest {

	private final Map<Integer, String> notValidData = new HashMap<Integer, String>();
	private final Map<Integer, String> testData = new HashMap<Integer, String>();
	private HAWLinkedArrayList<Integer, String> list;

	@Before
	public void testData() {
		list = new HAWLinkedArrayList<Integer, String>();
		notValidData.put(1, " ");
		notValidData.put(-1, " ");

		testData.put(0, "a");
		testData.put(0, "b");
		// testData
	}

	@Test
	public void insertNotValidPosition() {
		for (Map.Entry<Integer, String> entry : notValidData.entrySet()) {
			String data = entry.getValue();
			Integer pos = entry.getKey();
			try {
				list.insert(pos, data);
				fail("insert() wirft keine IndexOutOfBoundsException");
			} catch (IndexOutOfBoundsException ex) {
			}
			// assertEquals(expected, actual);
		}
	}

	@Test
	public void insertOrder() {
		for (Map.Entry<Integer, String> entry : testData.entrySet()) {
			list.insert(entry.getKey(), entry.getValue());
			assertEquals(entry.getValue(),
					list.retrieve(entry.getKey()));
		}
		assertEquals("a", list.retrieve(1));
		assertEquals(2, list.size());
	}
}
