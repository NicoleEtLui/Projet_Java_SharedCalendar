package calendar;

import static org.junit.Assert.*;

import org.junit.Test;

public class EventTest {

	@Test
	public void testToString() {
		Event x = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		
		assertEquals("\ntitre : Soiree"
				+ "\ndescription : soiree entre amis"
				+ "\nlocation : LLN"
				+ "\nfrom : 2016-11-20 at : 10:30"
				+ "\nto : 2016-11-20 at : 11:00", x.toString());
		// test ok
		// !!!! CE TEST DOIT ETRE MODIFIE CHAQUE JOUR PUISQU IL SE REFERE A LA DATE DU JOUR QUI CHANGE LOL
	}

	@Test
	public void testEqualsPefect() {
		Event x = new Event("Soiree", "soiree entre amis", "LLN");
		Event y = new Event("McDo", "on se fait une bouffe", "LLN");
		Event z = new Event("Soiree", "soiree entre amis", "tourine-la-grosse-tmtc");
		Event a = new Event("Soiree", "", "derriere toi");
		Event b = new Event("Soiree", "", "derriere toi");
		Event c = new Event("Soiree", "soiree entre amis", "LLN");
		
		assertTrue(x.equalsPerfect(c));
		assertTrue(a.equalsPerfect(b));
		assertFalse(x.equalsPerfect(y));
		assertFalse(y.equalsPerfect(c));
		assertFalse(z.equalsPerfect(a));
		// test ok
	}

	@Test
	public void testEqualsTitle() {
		Event x = new Event("Soiree", "soiree entre amis", "LLN");
		Event y = new Event("McDo", "on se fait une bouffe", "LLN");
		Event z = new Event("Soiree", "soiree entre amis", "tourine-la-grosse-tmtc");
		
		assertTrue(x.equalsTitle(x));
		assertTrue(x.equalsTitle(z));
		assertFalse(x.equalsTitle(y));
		// test ok
	}

	@Test
	public void testCompareToStart(){
		Event x = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		Event y = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		Event z = new Event("Soiree", "soiree entre amis", "LLN", "11:30", "11:00");
		
		assertEquals(0, x.compareToStart(y), 0);
		assertEquals(1, x.compareToStart(z), 0);
		assertEquals(-1, z.compareToStart(x), 0);
		// test ok
	}
	
	@Test
	public void testCompareToEnd(){
		Event x = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		Event y = new Event("Soiree", "soiree entre amis", "LLN", "10:30", "11:00");
		Event z = new Event("Soiree", "soiree entre amis", "LLN", "11:30", "12:00");
		
		assertEquals(0, x.compareToEnd(y), 0);
		assertEquals(-1, x.compareToEnd(z), 0);
		assertEquals(1, z.compareToEnd(x), 0);
		// test ok
	}

}