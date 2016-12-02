package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class EventTest {

	@Test
	public void testToString() {
		Event x = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		
		assertEquals("\ntitre : Soiree"
				+ "\ndescription : soiree entre amis"
				+ "\nlocation : LLN"
				+ "\nfrom : "+LocalDate.now()+" at : 10:30"
				+ "\nto : "+LocalDate.now()+" at : 11:00", x.toString());
		// test ok
		// !!!! CE TEST DOIT ETRE MODIFIE CHAQUE JOUR PUISQU IL SE REFERE A LA DATE DU JOUR QUI CHANGE LOL
	}

	@Test
	public void testEquals() {
		Event x = new Event("Soiree", "soiree ami", "LLN", LocalDate.of(2016, 05, 04), LocalDate.of(2016, 5,  05));
		Event y = new Event("Soiree", "soiree ami", "LLN", LocalDate.of(2016, 05, 04), LocalDate.of(2016, 5,  05));
		Event z = new Event("coucou", "soiree ami", "LLN", LocalDate.of(2016, 05, 04), LocalDate.of(2017, 5,  05));
		
		assertTrue(x.equals(y));
		assertFalse(y.equals(z));
		assertFalse(z.equals(x));
		// test 
	}

	@Test
	public void testCompareTo(){
		Event x = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		Event y = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		Event z = new Event("Soiree", "soiree entre amis", "LLN", "11:30", "11:00");
		
		assertEquals(0, x.compareTo(y), 0);
		assertEquals(1, x.compareTo(z), 0);
		assertEquals(-1, z.compareTo(x), 0);
		// test ok
	}

}