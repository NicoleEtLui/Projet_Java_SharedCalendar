package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ShaCalModelTest {
	
	@Test
	public void addPersonToHashMap() {
		ShaCalModel.resetAllHashMap();
		assertTrue(ShaCalModel.AllPersons.size()==0);
		Person person = new Person("name","firstName","person", LocalDate.now());
		assertTrue(ShaCalModel.AllPersons.size()==1);
	}
	
	@Test
	public void deletePersonFromHashMap() {
		ShaCalModel.resetAllHashMap();
		Person person = new Person("name","firstName","person", LocalDate.now());
		assertTrue(ShaCalModel.AllPersons.size()==1);
		ShaCalModel.deletePersonFromHashMap(person.getUserName());
		assertTrue(ShaCalModel.AllPersons.size()==0);
		
	}

	@Test
	public void addGroupToHashMap() {
		ShaCalModel.resetAllHashMap();
		assertTrue(ShaCalModel.AllGroups.size()==0);
		Group group = new Group("group");
		assertTrue(ShaCalModel.AllGroups.size()==1);
	}
	
	@Test
	public void deleteGroupFromHashMap() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group1");
		assertTrue(ShaCalModel.AllGroups.size()==1);
		ShaCalModel.deleteGroupFromHashMap(group.getGrId());
		assertTrue(ShaCalModel.AllGroups.size()==0);
		
	}
	
	@Test
	public void addEvent() {
		ShaCalModel.resetAllHashMap();
		assertTrue(ShaCalModel.AllEvents.size()==0);
		Group group = new Group("group");
		assertTrue(ShaCalModel.AllEvents.size()==1); //Set to 1 because of one entity already having a link to this HashMap.
		Event event = new Event("event");
		assertTrue(ShaCalModel.getEvent(group.getGrIdString()).size()==0);
		ShaCalModel.addEvent(group.getGrIdString(),event);
		assertTrue(ShaCalModel.getEvent(group.getGrIdString()).size()==1);
	}
	
	@Test
	public void removeEvent() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group");
		Event event = new Event("event");
		ShaCalModel.addEvent(group.getGrIdString(),event);
		assertTrue(ShaCalModel.getEvent(group.getGrIdString()).size()==1);
		ShaCalModel.removeEvent(group.getGrIdString(),event);
		assertTrue(ShaCalModel.getEvent(group.getGrIdString()).size()==0);
	}
	
	@Test
	public void addLink() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group");
		Person person = new Person("name","firstName","person", LocalDate.now());
		assertTrue(ShaCalModel.getGroup(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==0);
		assertTrue(ShaCalModel.getPerson(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==0);
		ShaCalModel.addLink(person.getUserName(), group.getGrId());
		assertTrue(ShaCalModel.getGroup(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==1);
		assertTrue(ShaCalModel.getPerson(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==1);
	}
	
	@Test
	public void removeLink() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group");
		Person person = new Person("name","firstName","person", LocalDate.now());
		ShaCalModel.addLink(person.getUserName(), group.getGrId());
		assertTrue(ShaCalModel.getGroup(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==1);
		assertTrue(ShaCalModel.getPerson(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==1);
		ShaCalModel.removeLink(person.getUserName(), group.getGrId());
		assertTrue(ShaCalModel.getGroup(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==0);
		assertTrue(ShaCalModel.getPerson(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==0);
	}
	
	@Test
	public void resetAllHashMap() {
		ShaCalModel.resetAllHashMap();
		assertTrue(ShaCalModel.AllPersons.size()==0);
		assertTrue(ShaCalModel.AllGroups.size()==0);
		assertTrue(ShaCalModel.AllEvents.size()==0);
		Person person = new Person("name","firstName","person", LocalDate.now());
		Group group= new Group();
		assertTrue(ShaCalModel.AllPersons.size()==1);
		assertTrue(ShaCalModel.AllGroups.size()==1);
		assertTrue(ShaCalModel.AllEvents.size()==2); //Set to 2 because of two entities already having a link to this HashMap.
	}
}
