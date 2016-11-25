package calendar;

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
		Group testGroup1 = new Group();
		Group testGroup2 = new Group();
		Person person = new Person(null, null, null, null);
		testGroup1.addMember(person);
		assertEquals((testGroup2.getMembers().size()+1),testGroup1.getMembers().size());
	}
	
	@Test
	public void testDeleteMember() {
		Group testGroup1 = new Group();
		Group testGroup2 = new Group();
		Person person = new Person(null, null, "person", null);
		testGroup1.addMember(person);
		assertEquals((testGroup2.getMembers().size()+1),testGroup1.getMembers().size());
		testGroup1.deleteMember("person");
		assertEquals(testGroup2.getMembers().size(),testGroup1.getMembers().size());
	}
	
	@Test
	public void testAddEvent() {
		Group testGroup1 = new Group();
		Group testGroup2 = new Group();
		Event event = new Event("event");
		testGroup1.addEvent(event);
		assertEquals((testGroup2.getGrCalendar().size()+1),testGroup1.getGrCalendar().size());
	}
	
	@Test
	public void testDeleteEvent(){
		Group testGroup1 = new Group();
		Group testGroup2 = new Group();
		Event event = new Event("event");
		testGroup1.addEvent(event);
		assertEquals((testGroup2.getGrCalendar().size()+1),testGroup1.getGrCalendar().size());
		testGroup1.deleteEvent("event");
		assertEquals(testGroup2.getGrCalendar().size(),testGroup1.getGrCalendar().size());
	}
	
	@Test
	public void testGetSingleMember(){
		Group testGroup = new Group();
		Person person = new Person(null, null, "person", null);
		testGroup.addMember(person);
		assertEquals(person, testGroup.getSingleMember("person"));
	}

}
