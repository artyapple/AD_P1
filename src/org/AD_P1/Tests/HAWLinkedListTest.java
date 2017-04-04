package org.AD_P1.Tests;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.UUID;

import org.AD_P1.Element.Element;
import org.AD_P1.HAWLinkedList.HAWLinkedList;
import org.AD_P1.HAWLinkedList.HAWNode;
import org.AD_P1.Interfaces.HAWList;
import org.junit.Before;
import org.junit.Test;

public class HAWLinkedListTest {
	
	private HAWLinkedList<String> linkedList;
	private String string1;
	private int key1;
	private String string2;
	private int key2;
	private String string3;
	private int key3;
	private String string4;
	private int key4;
	
	@Before
	public void prepare() {
		linkedList = new HAWLinkedList<String>();
		string1 = "1";
		key1 = 1;
		string2 = "2";
		key2 = 2;
		string3 = "3";
		key3 = 3;
		string4 = "4";
		key4 = 4;
	}

	@Test
	public void testInsertRetrieveFind() {
		assertEquals(0, linkedList.size());
		assertEquals(null, linkedList.find(key1));
		
		linkedList.insert(linkedList.head, new Element<String>(string1, key1));
		assertEquals(1, linkedList.size());
		assertEquals(linkedList.head, linkedList.find(key1));
		assertEquals(string1, linkedList.retrieve(linkedList.head).getValue());
		
		linkedList.insert(linkedList.find(key1), new Element<String>(string2, key2));
		linkedList.insert(linkedList.find(key1), new Element<String>(string3, key3));
		linkedList.insert(linkedList.find(key1), new Element<String>(string4, key4));
		assertEquals(4, linkedList.size());
		assertEquals(string1, linkedList.retrieve(linkedList.find(key1)).getValue());
		assertEquals(string2, linkedList.retrieve(linkedList.find(key2)).getValue());
		assertEquals(string3, linkedList.retrieve(linkedList.find(key3)).getValue());
		assertEquals(string4, linkedList.retrieve(linkedList.find(key4)).getValue());
	}
	
	@Test
	public void testDelete() {
		linkedList.insert(linkedList.head, new Element<String>(string1, key1));
		linkedList.insert(linkedList.find(key1), new Element<String>(string2, key2));
		linkedList.insert(linkedList.find(key2), new Element<String>(string3, key3));
		linkedList.insert(linkedList.find(key3), new Element<String>(string4, key4));
		linkedList.delete(linkedList.find(key3));
		assertEquals(null, linkedList.find(key3));
		linkedList.delete(linkedList.find(key4));
		assertEquals(null, linkedList.find(key4));
		assertEquals(2, linkedList.size());
	}
	
	@Test
	public void testConCat() {
		linkedList.insert(linkedList.head, new Element<String>(string1, key1));
		linkedList.insert(linkedList.find(key1), new Element<String>(string2, key2));
		HAWLinkedList<String> linkedList2 = new HAWLinkedList<String>();
		linkedList.concat(linkedList2);
		assertEquals(2, linkedList.size());
		
		linkedList2.insert(linkedList2.head, new Element<String>(string3, key3));
		linkedList2.insert(linkedList2.find(key3), new Element<String>(string4, key4));
		linkedList.concat(linkedList2);
		assertEquals(4, linkedList.size());
		assertEquals(string1, linkedList.retrieve(linkedList.find(key1)).getValue());
		assertEquals(string2, linkedList.retrieve(linkedList.find(key2)).getValue());
		assertEquals(string3, linkedList.retrieve(linkedList.find(key3)).getValue());
		assertEquals(string4, linkedList.retrieve(linkedList.find(key4)).getValue());
	}
	
	@Test
	public void testInsertInvalidPosition() {
		try {
			linkedList.insert(null, new Element<String>(string1, key1));
			fail("insert() wirft keine IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
		try {
			linkedList.insert(linkedList.head.getNextNode(), new Element<String>(string1, key1));
			fail("insert() wirft keine IllegalArgumentException");
		} catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testEffort() {
		HAWLinkedList<String> list1k = insert1kInList(1000);
		HAWLinkedList<String> cclist1k = insert1kInList(1000);
		HAWLinkedList<String> list2k = insert1kInList(2000);
		HAWLinkedList<String> cclist2k = insert1kInList(2000);
		HAWLinkedList<String> list3k = insert1kInList(3000);
		HAWLinkedList<String> cclist3k = insert1kInList(3000);
		HAWLinkedList<String> list4k = insert1kInList(4000);
		HAWLinkedList<String> cclist4k = insert1kInList(4000);
		HAWLinkedList<String> list5k = insert1kInList(5000);
		HAWLinkedList<String> cclist5k = insert1kInList(5000);
		// 1k
		System.out.println("1k: " + list1k.size());
		Object posAt80Perc = list1k.find(2);
		list1k.insert(posAt80Perc, new Element<String>("insert", -2));
		list1k.delete(-2);
		list1k.retrieve(posAt80Perc);
		list1k.concat(cclist1k);
		// 2k
		System.out.println("2k");
		posAt80Perc = list2k.find(2);
		list2k.insert(posAt80Perc, new Element<String>("insert", -2));
		list2k.delete(-2);
		list2k.retrieve(posAt80Perc);
		list2k.concat(cclist2k);
		// 3k
		System.out.println("3k");
		posAt80Perc = list3k.find(2);
		list3k.insert(posAt80Perc, new Element<String>("insert", -2));
		list3k.delete(-2);
		list3k.retrieve(posAt80Perc);
		list3k.concat(cclist3k);
		// 4k
		System.out.println("4k");
		posAt80Perc =  list4k.find(2);
		list4k.insert(posAt80Perc, new Element<String>("insert", -2));
		list4k.delete(-2);
		list4k.retrieve(posAt80Perc);
		list4k.concat(cclist4k);
		// 5k
		System.out.println("5k");
		posAt80Perc = list5k.find(2);
		list5k.insert(posAt80Perc, new Element<String>("insert", -2));
		list5k.delete(-2);
		list5k.retrieve(posAt80Perc);
		list5k.concat(cclist5k);
	}
	
	private HAWLinkedList<String> insert1kInList(int size) {
		HAWLinkedList<String> list = new HAWLinkedList<String>();
		list.insert(list.head, new Element<String>("string" + 0, 0));
		for (int i = 1; i < size; i++) {
			list.insert(list.find(i - 1), new Element<String>("string" + i, i));
		}
		return list;
	}

}
