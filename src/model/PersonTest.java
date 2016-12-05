package model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class PersonTest {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Test
	public void testToString() {
		Person person = new Person("name","firstName","person", LocalDate.now());
		assertEquals("firstName name - person - " + LocalDate.now().format(formatter), person.toString());
	}
	
	@Test
	public void testEquals(){
		Person p0 = new Person("Petit", "Martin", "NicoleEtlui", LocalDate.of(1994,9,28));
		Person p1 =  new Person("Petit", "Martin", "MartinP", LocalDate.of(1994,9,28));
		Person p2 =  new Person("Petit", "Nicole", "NicoleEtlui", LocalDate.of(1994,9,28));
		
		assertTrue(p0.equals(p0));
		assertFalse(p0.equals(p1));
		assertTrue(p0.equals(p2));
	}
	
	/*
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
	*/
}
