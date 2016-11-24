package calendar;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class PersonTest {


	@Test
	public void testToString() {
		Person p = new Person("Petit", "Martin", "NicoleEtLui", LocalDate.of(1994,9,28));
		
		assertEquals("Martin Petit - NicoleEtLui - 28/09/1994", p.toString());
	}

	@Test
	public void testEqualsObject() {
		Person p = new Person("Petit", "Martin", "NicoleEtlui", LocalDate.of(1994,9,28));
		Person p1 =  new Person("Petit", "Martin", "MartinP", LocalDate.of(1994,9,28));
		Person p2 =  new Person("Petit", "Nicole", "NicoleEtlui", LocalDate.of(1994,9,28));
		
		assertTrue(p.equals(p));
		assertFalse(p.equals(p1));
		assertTrue(p.equals(p2));
	}
	@Test
	public void testAddPersonalEvents() {
		Person p = new Person("Petit", "Martin", "NicoleEtlui", LocalDate.of(1994,9,28));
		assertTrue(p.getPersoClndr().size() == 1);
		p.addPersonalEvents(new Event("MonEvent", "MaDescription", LocalDate.now(), LocalDate.now().plusDays(1)));
		assertTrue(p.getPersoClndr().size() == 2);
	}

	@Test
	public void testDeletePersonalEvent() {
		Event e = new Event("MonEvent", "MaDescription", LocalDate.now(), LocalDate.now().plusDays(1));
		Person p = new Person("Petit", "Martin", "NicoleEtlui", LocalDate.of(1994,9,28));
		assertTrue(p.getPersoClndr().size() == 1);
		p.addPersonalEvents(e);
		p.deletePersonalEvent(e);
		assertTrue(p.getPersoClndr().size() == 1);
		p.deletePersonalEvent(e);
		assertTrue(p.getPersoClndr().size() == 1);
	}

	/*@Test
	public void testChangePermission() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateGroup() {
		fail("Not yet implemented");
	}*/

}
