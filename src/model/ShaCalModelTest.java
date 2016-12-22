package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class ShaCalModelTest {
	ShaCalModel model = new ShaCalModel();
	
	@Test
	public void testResetAllHashMap(){
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
		assertTrue(model.allEvents.size()==1); 
	}
	
	@Test
	public void testAddGroupToHashMap(){
		model.resetAllHashMap();
		assertTrue(model.allGroups.size()==0);
		Group group = new Group();
		assertTrue(model.allGroups.size()==0);
		model.addGroupToHashMap(group);
		assertTrue(model.allGroups.size()==1);
	}
	
	@Test
	public void testDeleteGroupFromHashMap(){
		model.resetAllHashMap();
		Group group = new Group();
		model.addGroupToHashMap(group);
		assertTrue(model.allGroups.size()==1);
		model.deleteGroupFromHashMap(group.getGrId());
		assertTrue(model.allGroups.size()==0);
	}
	
	@Test
	public void testGetGroup(){
		model.resetAllHashMap();
		Group group = new Group();
		model.addGroupToHashMap(group);
		assertEquals(model.getGroup(group.getGrId()), group);
	}

	@Test
	public void testAddPersonToHashMap(){
		model.resetAllHashMap();
		assertTrue(model.allPersons.size()==0);
		Person person = new Person("name","firstName","userName",LocalDate.now());
		assertTrue(model.allPersons.size()==0);
		model.addPersonToHashMap(person);
		assertTrue(model.allPersons.size()==1);
	}
	
	@Test
	public void testDeletePersonFromHashMap(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		model.addPersonToHashMap(person);
		assertTrue(model.allPersons.size()==1);
		model.deletePersonFromHashMap(person.getUserName());
		assertTrue(model.allPersons.size()==0);
	}
	
	@Test
	public void testGetPerson(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		model.addPersonToHashMap(person);
		assertEquals(model.getPerson(person.getUserName()),person);
	}

	@Test
	public void testAddEvent(){
		model.resetAllHashMap();
		assertTrue(model.allEvents.size()==0);
		Event event = new Event("event");
		assertTrue(model.allEvents.size()==0);
		model.addEvent("creator", event);
		assertTrue(model.allEvents.size()==1);
		assertTrue(model.getEvent("creator").size()==1);
	}
	
	@Test
	public void testRemoveEvent(){
		model.resetAllHashMap();
		Event event = new Event("event");
		model.addEvent("creator", event);
		assertTrue(model.allEvents.size()==1);
		model.removeEvent("creator", event);
		assertTrue(model.allEvents.size()==1);
		assertTrue(model.getEvent("creator").size()==0);
	}
	
	@Test
	public void testGetEvent(){
		model.resetAllHashMap();
		Event event = new Event("event");
		model.addEvent("creator", event);
		assertEquals(model.getEvent("creator").get(0),event);
	}
	
	@Test
	public void testAddMemberToGroup(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==0);
		model.addMemberToGroup(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==1);
	}
	
	@Test
	public void testDeleteMemberFromGroup(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		model.addMemberToGroup(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==1);
		model.deleteMemberFromGroup(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==0);
	}
	
	@Test
	public void testGroupEquals(){
		model.resetAllHashMap();
		Group groupOrigin = new Group("group0");
		Group groupCompare = new Group("group0");
		assertTrue(model.groupEquals(groupOrigin, groupOrigin));
		assertFalse(model.groupEquals(groupOrigin, groupCompare));
		assertFalse(model.groupEquals(groupOrigin, false));
		assertFalse(model.groupEquals(groupOrigin, null));
	}
	
	@Test
	public void testAddGroupToPerson(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==0);
		model.addGroupToPerson(person.getUserName(), group.getGrId(), 0);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==1);
	}
	
	@Test
	public void testDeleteGroupFromPerson(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		model.addGroupToPerson(person.getUserName(), group.getGrId(), 0);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==1);
		model.deleteGroupFromPerson(person.getUserName(), group.getGrId());
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==0);
	}
	
	@Test
	public void testPersonEquals(){
		model.resetAllHashMap();
		Person personOrigin = new Person("name","firstName","Origin",LocalDate.now());
		Person persoCompare = new Person("name","firstName","Origin",LocalDate.now());
		Person personCompareBis = new Person("name","firstName","NotOrigin",LocalDate.now());
		assertTrue(model.personEquals(personOrigin, personOrigin));
		assertFalse(model.personEquals(personOrigin, persoCompare));
		assertFalse(model.personEquals(persoCompare, personCompareBis));
		assertFalse(model.personEquals(personOrigin, personCompareBis));
		assertFalse(model.personEquals(personOrigin, false));
		assertFalse(model.personEquals(personOrigin, null));
	}
	
	@Test
	public void testCreateGroup(){
		model.resetAllHashMap();
		
	}
	
	@Test
	public void testAddLink(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==0);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==0);
		model.addLink(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==1);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==1);
	}
	
	@Test
	public void testRemoveLink(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		model.addLink(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==1);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==1);
		model.removeLink(person.getUserName(), group.getGrId());
		assertTrue(model.getGroup(group.getGrId()).getMembers().size()==0);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==0);
	}
	
	@Test
	public void testPersonToString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		assertEquals(model.personToString(person),"firstName name - userName - " + LocalDate.now().format(formatter));
	}
	
	@Test
	public void testPersonToStringGroups(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		model.addLink(person.getUserName(),group.getGrId());
		assertEquals(model.personToStringGroups(person),"UserName : userName"
				+ "\n>[Group : UnknownGroup0 | Lvl : 0]");
	}
	
	@Test
	public void testGroupToString(){
		model.resetAllHashMap();
		Group group = new Group();
		assertEquals(model.groupToString(group),"[Group] Id : \"0\" Name : \"UnknownGroup0\" IsPublic : true"
				+ "\nNumber of members : 0 | Number of events : 0");
	}
	
	@Test
	public void testGroupToStringMembers(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		Group group = new Group();
		model.addPersonToHashMap(person);
		model.addGroupToHashMap(group);
		model.addLink(person.getUserName(),group.getGrId());
		assertEquals(model.groupToStringMembers(group),"GrName : \"UnknownGroup0\""
				+ "\n>[User : userName | Lvl : 0]");
	}
	
	@Test
	public void testChangePermission(){
		model.resetAllHashMap();
		Person person = new Person("name","firstName","userName",LocalDate.now());
		model.addPersonToHashMap(person);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==0);
		assertTrue(model.allGroups.size()==0);
		int gr0Id = model.createGroup(person.getUserName(), "group0");
		assertTrue(model.allGroups.size()==1);
		assertTrue(model.getPerson(person.getUserName()).getGroup().size()==1);
		assertTrue(model.getGroup(gr0Id).getMembers().size()==1);
	}
}
