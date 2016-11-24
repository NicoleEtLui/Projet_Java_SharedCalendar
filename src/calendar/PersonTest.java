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

	/*@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPersonalEvents() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePersonalEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangePermission() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateGroup() {
		fail("Not yet implemented");
	}*/

}
