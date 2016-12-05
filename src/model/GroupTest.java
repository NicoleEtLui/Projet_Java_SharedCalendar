package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GroupTest {
	
	@Test
	public void testToString() {
		Group testGroup = new Group();
		assertEquals(testGroup.toString(), "[Group] Id : \"" + testGroup.getGrId() + "\" Name : \"" + testGroup.getGrName() + "\" IsPublic : true"
				+ "\nNumber of members : 0 | Number of events : 0");
		Event event = new Event("event");
		ShaCalModel.addEvent(String.valueOf(testGroup.getGrId()), event);
		assertEquals(testGroup.toString(), "[Group] Id : \"" + testGroup.getGrId() + "\" Name : \"" + testGroup.getGrName() + "\" IsPublic : true"
				+ "\nNumber of members : 0 | Number of events : 1");
	}
	
	@Test
	public void equals() {
		Group testGroup = new Group();
		assertTrue(testGroup.equals(testGroup));
	}
}
