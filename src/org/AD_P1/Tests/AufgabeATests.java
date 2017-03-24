package org.AD_P1.Tests;

import static org.junit.Assert.*;

import org.AD_P1.ArrayList.ArrayList;
import org.AD_P1.Interfaces.HAWList;
import org.junit.Test;

public class AufgabeATests {

	@Test
	public void insertTest() throws Exception {

		ArrayList<Integer, Integer> liste = null;
		liste = new ArrayList();

		liste.insert(0, 1,0);
		assertTrue(liste.retrieve(0) == 1);
		
		for(int i=0;i<20000;i++){
			liste.insert(0,2,1);
		}
		assertTrue(liste.retrieve(20000) == 1);
		
		liste.delete(0);
		assertTrue(liste.retrieve(0) == null);
		
		liste.setKey(0, 4);
	
		
		
	}
}