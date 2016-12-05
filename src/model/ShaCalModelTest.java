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
		ShaCalModel.addPersonToHashMap(person);
		assertTrue(ShaCalModel.AllPersons.size()==1);
	}
	
	@Test
	public void deletePersonFromHashMap() {
		ShaCalModel.resetAllHashMap();
		Person person = new Person("name","firstName","person", LocalDate.now());
		ShaCalModel.addPersonToHashMap(person);
		assertTrue(ShaCalModel.AllPersons.size()==1);
		ShaCalModel.deletePersonFromHashMap(person.getUserName());
		assertTrue(ShaCalModel.AllPersons.size()==0);
		
	}

	@Test
	public void addGroupToHashMap() {
		ShaCalModel.resetAllHashMap();
		assertTrue(ShaCalModel.AllGroups.size()==0);
		Group group = new Group("group");
		ShaCalModel.addGroupToHashMap(group);
		assertTrue(ShaCalModel.AllGroups.size()==1);
	}
	
	@Test
	public void deleteGroupFromHashMap() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group1");
		ShaCalModel.addGroupToHashMap(group);
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
		ShaCalModel.addGroupToHashMap(group);
		Event event = new Event("event");
		assertTrue(ShaCalModel.AllEvents.get(group.getGrIdString()).size()==0);
		ShaCalModel.addEvent(group.getGrIdString(),event);
		assertTrue(ShaCalModel.AllEvents.get(group.getGrIdString()).size()==1);
	}
	
	@Test
	public void removeEvent() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group");
		ShaCalModel.addGroupToHashMap(group);
		Event event = new Event("event");
		ShaCalModel.addEvent(group.getGrIdString(),event);
		assertTrue(ShaCalModel.AllEvents.get(group.getGrIdString()).size()==1);
		ShaCalModel.removeEvent(group.getGrIdString(),event);
		assertTrue(ShaCalModel.AllEvents.get(group.getGrIdString()).size()==0);
	}
	
	@Test
	public void addLink() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group");
		ShaCalModel.addGroupToHashMap(group);
		assertTrue(ShaCalModel.AllGroups.get(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==0);
		Person person = new Person("name","firstName","person", LocalDate.now());
		ShaCalModel.addPersonToHashMap(person);
		assertTrue(ShaCalModel.AllPersons.get(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==0);
		ShaCalModel.addLink(person.getUserName(), group.getGrId());
		assertTrue(ShaCalModel.AllGroups.get(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==1);
		assertTrue(ShaCalModel.AllPersons.get(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==1);
	}
	
	@Test
	public void removeLink() {
		ShaCalModel.resetAllHashMap();
		Group group = new Group("group");
		ShaCalModel.addGroupToHashMap(group);
		Person person = new Person("name","firstName","person", LocalDate.now());
		ShaCalModel.addPersonToHashMap(person);
		ShaCalModel.addLink(person.getUserName(), group.getGrId());
		assertTrue(ShaCalModel.AllGroups.get(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==1);
		assertTrue(ShaCalModel.AllPersons.get(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==1);
		ShaCalModel.removeLink(person.getUserName(), group.getGrId());
		assertTrue(ShaCalModel.AllGroups.get(ShaCalModel.AllGroups.keySet().toArray()[0]).getMembers().size()==0);
		assertTrue(ShaCalModel.AllPersons.get(ShaCalModel.AllPersons.keySet().toArray()[0]).getGroup().size()==0);
	}
	
	@Test
	public void resetAllHashMap() {
		ShaCalModel.resetAllHashMap();
		assertTrue(ShaCalModel.AllPersons.size()==0);
		assertTrue(ShaCalModel.AllGroups.size()==0);
		assertTrue(ShaCalModel.AllEvents.size()==0);
		Person person = new Person("name","firstName","person", LocalDate.now());
		ShaCalModel.addPersonToHashMap(person);
		Group group= new Group();
		ShaCalModel.addGroupToHashMap(group);
		assertTrue(ShaCalModel.AllPersons.size()==1);
		assertTrue(ShaCalModel.AllGroups.size()==1);
		assertTrue(ShaCalModel.AllEvents.size()==2); //Set to 2 because of two entities already having a link to this HashMap.
	}
}
