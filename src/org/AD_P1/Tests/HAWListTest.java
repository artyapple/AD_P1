package org.AD_P1.Tests;
import static org.junit.Assert.*;

import java.util.UUID;

import org.AD_P1.HAWLinkedList.HAWLinkedList;
import org.AD_P1.Interfaces.HAWList;
import org.junit.Before;
import org.junit.Test;

public class HAWListTest {
	
	private HAWList<UUID, String> linkedList;
	private String string1;
	private UUID uuid1;
	private String string2;
	private UUID uuid2;
	private String string3;
	private UUID uuid3;
	private String string4;
	private UUID uuid4;
	
	@Before
	public void prepare() {
		linkedList = new HAWLinkedList<UUID, String>();
		string1 = "1";
		uuid1 = UUID.randomUUID();
		string2 = "2";
		uuid2 = UUID.randomUUID();
		string3 = "3";
		uuid3 = UUID.randomUUID();
		string4 = "4";
		uuid4 = UUID.randomUUID();
	}

	@Test
	public void testInsertRetrieveFind() {
		assertEquals(0, linkedList.size());
		assertEquals(-1, linkedList.find(uuid1));
		
		linkedList.insert(0, string1, uuid1);
		assertEquals(1, linkedList.size());
		assertEquals(0, linkedList.find(uuid1));
		assertEquals(string1, linkedList.retrieve(0));
		
		linkedList.insert(0, string2, uuid2);
		linkedList.insert(2, string3, uuid3);
		linkedList.insert(2, string4, uuid4);
		assertEquals(4, linkedList.size());
		assertEquals(1, linkedList.find(uuid1));
		assertEquals(string1, linkedList.retrieve(1));
		assertEquals(0, linkedList.find(uuid2));
		assertEquals(string2, linkedList.retrieve(0));
		assertEquals(3, linkedList.find(uuid3));
		assertEquals(string3, linkedList.retrieve(3));
		assertEquals(2, linkedList.find(uuid4));
		assertEquals(string4, linkedList.retrieve(2));
	}
	
	@Test
	public void testDelete() {
		linkedList.insert(0, string1, uuid1);
		linkedList.insert(1, string2, uuid2);
		linkedList.insert(2, string3, uuid3);
		linkedList.insert(3, string4, uuid4);
		linkedList.delete(2);
		assertEquals(-1, linkedList.find(uuid3));
		linkedList.delete(2);
		assertEquals(-1, linkedList.find(uuid4));
		assertEquals(2, linkedList.size());
	}
	
	@Test
	public void testConCat() {
		linkedList.insert(0, string1, uuid1);
		linkedList.insert(1, string2, uuid2);
		HAWList<UUID, String> linkedList2 = new HAWLinkedList<UUID, String>();
		HAWList<UUID, String> concatList = linkedList.concat(linkedList2);
		assertEquals(2, concatList.size());
		
		linkedList2.insert(0, string3, uuid3);
		linkedList2.insert(1, string4, uuid4);
		concatList = linkedList.concat(linkedList2);
		assertEquals(4, concatList.size());
		assertEquals(0, concatList.find(uuid1));
		assertEquals(1, concatList.find(uuid2));
		assertEquals(2, concatList.find(uuid3));
		assertEquals(3, concatList.find(uuid4));
	}

}
