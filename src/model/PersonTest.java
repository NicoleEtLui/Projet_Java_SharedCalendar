package model;

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

	@Test
	public void testChangePermission() {
		Person p = new Person("Petit", "Martin", "Martin1", LocalDate.of(1994,9,28));
		Person p1 = new Person("Petit", "Martin", "Martin2", LocalDate.of(1994,9,28));
		int[] i1 = {1,2};
		int[] i2 = {2,1};
		int[] i3 = {3,0};
		p.getGroup().add(i1);
		p.getGroup().add(i2);
		p.getGroup().add(i3);
		
		int[] j1 = {1,0};
		int[] j2 = {2,1};
		int[] j3 = {3,2};

		p1.getGroup().add(j1);
		p1.getGroup().add(j2);
		p1.getGroup().add(j3);
		
		assertFalse(p.changePermission(p, 1, 1));
		assertTrue(p.changePermission(p1, 1, 1));
		assertFalse(p.changePermission(p1, 2, 0));
		assertFalse(p.changePermission(p1, 3, 1));
	}

	@Test
	public void testCreateGroup() {
		Person p = new Person("Petit", "Martin", "N", LocalDate.of(1994,9,28));
		p.createGroup("monGroupe");
		assertEquals("N[1,2]\n", p.toStringGroup(p.getGroup()));
	}

}
