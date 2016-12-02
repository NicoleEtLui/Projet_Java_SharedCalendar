package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupTest {

	@Test
	public void testToString() {
		Group testGroup = new Group();
		assertEquals(testGroup.toString(), "[Group] Id : \"" + testGroup.getGrId() + "\" Name : \"" + testGroup.getGrName() + "\" IsPublic : true"
				+ "\nNumber of members : 0 | Number of events : 0");
	}
	
	@Test
	public void testEquals() {
		Group testGroup = new Group();
		assertTrue(testGroup.equals(testGroup));
	}
	
	@Test
	public void testAddMember() {
		Group testGroup = new Group();
		assertTrue(testGroup.getMembers().size() == 0);
		Person person = new Person(null, null, "person", null);
		testGroup.addMember(person);
		assertTrue(testGroup.getMembers().size() == 1);
	}
	
	@Test
	public void testDeleteMember() {
		Group testGroup = new Group();
		assertTrue(testGroup.getMembers().size() == 0);
		Person person = new Person(null, null, "person", null);
		testGroup.addMember(person);
		assertTrue(testGroup.getMembers().size() == 1);
		testGroup.deleteMember("person");
		assertTrue(testGroup.getMembers().size() == 0);
	}
	
	@Test
	public void testAddEvent() {
		Group testGroup = new Group();
		assertTrue(testGroup.getGrCalendar().size() == 0);
		Event event = new Event("event");
		testGroup.addEvent(event);
		assertTrue(testGroup.getGrCalendar().size() == 1);
	}
	
	@Test
	public void testDeleteEvent(){
		Group testGroup = new Group();
		assertTrue(testGroup.getGrCalendar().size() == 0);
		Event event = new Event("event");
		testGroup.addEvent(event);
		assertTrue(testGroup.getGrCalendar().size() == 1);
		testGroup.deleteEvent("event");
		assertTrue(testGroup.getGrCalendar().size() == 0);
	}
	
	@Test
	public void testGetSingleMember(){
		Group testGroup = new Group();
		Person person = new Person(null, null, "person", null);
		testGroup.addMember(person);
		assertEquals(person, testGroup.getSingleMember("person"));
	}

}
