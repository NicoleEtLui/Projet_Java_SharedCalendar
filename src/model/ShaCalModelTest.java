package model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ShaCalModelTest {
	ShaCalModel model = new ShaCalModel();
	@Test
	public void addPersonToHashMap() {
		model.resetAllHashMap();
		assertTrue(model.allPersons.size()==0);
		Person person = new Person("name","firstName","person", LocalDate.now());
		model.addPersonToHashMap(person);
		assertTrue(model.allPersons.size()==1);
	}
	
	@Test
	public void deletePersonFromHashMap() {
		model.resetAllHashMap();
		Person person = new Person("name","firstName","person", LocalDate.now());
		model.addPersonToHashMap(person);
		assertTrue(model.allPersons.size()==1);
		int gr0Id = model.createGroup(person.getUserName(), "group0");
		System.out.println("seges" + model.allGroups);
		assertTrue(model.getGroup(gr0Id).getMembers().size()==1);
		model.deletePersonFromHashMap(person.getUserName());
		assertTrue(model.allPersons.size()==0);
		assertTrue(model.getGroup(gr0Id).getMembers().size()==0);
		
	}

	@Test
	public void addGroupToHashMap() {
		model.resetAllHashMap();
		assertTrue(model.allGroups.size()==0);
		Group group = new Group("group");
		model.addGroupToHashMap(group);
		assertTrue(model.allGroups.size()==1);
	}
	
	@Test
	public void deleteGroupFromHashMap() {
		model.resetAllHashMap();
		Group group = new Group("group");
		model.addGroupToHashMap(group);
		assertTrue(model.allGroups.size()==1);
		model.deleteGroupFromHashMap(group.getGrId());
		assertTrue(model.allGroups.size()==0);
		
	}
	
	@Test
	public void addEvent() {
		model.resetAllHashMap();
		assertTrue(model.allEvents.size()==0);
		Group group = new Group("group");
		model.addGroupToHashMap(group);
		Event event = new Event("event");
		assertTrue(model.getEvent(group.getGrIdString())==null);
		model.addEvent(group.getGrIdString(),event);
		assertTrue(model.getEvent(group.getGrIdString()).size()==1);
	}
	
	@Test
	public void removeEvent() {
		model.resetAllHashMap();
		Group group = new Group("group");
		Event event = new Event("event");
		model.addEvent(group.getGrIdString(),event);
		assertTrue(model.getEvent(group.getGrIdString()).size()==1);
		model.removeEvent(group.getGrIdString(),event);
		assertTrue(model.getEvent(group.getGrIdString()).size()==0);
	}
	
	@Test
	public void addLink() {
		model.resetAllHashMap();
		Group group = new Group("group");
		model.addGroupToHashMap(group);
		Person person = new Person("name","firstName","person", LocalDate.now());
		model.addPersonToHashMap(person);
		assertTrue(model.getGroup(model.allGroups.keySet().toArray()[0]).getMembers().size()==0);
		assertTrue(model.getPerson(model.allPersons.keySet().toArray()[0]).getGroup().size()==0);
		model.addLink(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(model.allGroups.keySet().toArray()[0]).getMembers().size()==1);
		assertTrue(model.getPerson(model.allPersons.keySet().toArray()[0]).getGroup().size()==1);
	}
	
	@Test
	public void removeLink() {
		model.resetAllHashMap();
		Group group = new Group("group");
		model.addGroupToHashMap(group);
		Person person = new Person("name","firstName","person", LocalDate.now());
		model.addPersonToHashMap(person);
		model.addLink(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(model.allGroups.keySet().toArray()[0]).getMembers().size()==1);
		assertTrue(model.getPerson(model.allPersons.keySet().toArray()[0]).getGroup().size()==1);
		model.removeLink(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(model.allGroups.keySet().toArray()[0]).getMembers().size()==0);
		assertTrue(model.getPerson(model.allPersons.keySet().toArray()[0]).getGroup().size()==0);
	}
	
	@Test
	public void resetAllHashMap() {
		model.resetAllHashMap();
		assertTrue(model.allPersons.size()==0);
		assertTrue(model.allGroups.size()==0);
		assertTrue(model.allEvents.size()==0);
		Person person = new Person("name","firstName","person", LocalDate.now());
		Group group = new Group();
		Event event = new Event("event");
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		model.addEvent("person", event);
		assertTrue(model.allPersons.size()==1);
		assertTrue(model.allGroups.size()==1);
		assertTrue(model.allEvents.size()==1); //Set to 2 because of two entities already having a link to this HashMap.
	}
}
