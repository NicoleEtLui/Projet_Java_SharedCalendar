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
	
	@Test
	public void testToStringGroup(){
		ShaCalModel.resetAllHashMap();
		Person person = new Person("name","firstName","person", LocalDate.now());
		Group group1 = new Group("group1");
		Group group2 = new Group("group2");
		ShaCalModel.addLink(person.getUserName(), group1.getGrId());
		ShaCalModel.addLink(person.getUserName(), group2.getGrId());
		assertEquals(person.toStringGroup(),"UserName : person\n" + 
											">[Group : group1| Lvl : 0]\n"+
											">[Group : group2| Lvl : 0]\n");
	}
	
	@Test
	public void testCreateGroup(){
		ShaCalModel.resetAllHashMap();
		Person person = new Person("name","firstName","person", LocalDate.now());
		assertTrue(ShaCalModel.getPerson("person").getGroup().size()==0);
		person.createGroup("group");
		assertTrue(ShaCalModel.AllGroups.size()==1);
		assertTrue(ShaCalModel.getPerson("person").getGroup().size()==1);
		assertEquals(ShaCalModel.getPerson("person").toStringGroup(),"UserName : person\n" + 
																	">[Group : group| Lvl : 2]\n");
		
	}
	
	@Test
	public void testChangePermission() { //TODO After testCreateGroup();
		ShaCalModel.resetAllHashMap();
		Person person0 = new Person("name","firstName","person0", LocalDate.now());
		Person person1 = new Person("name","firstName","person1", LocalDate.now());
		int gr0Id = person0.createGroup("group0");
		Group group1 = new Group("group1");
		int gr2Id = person1.createGroup("group2");
		assertTrue(ShaCalModel.getPerson("person0").getPermission(gr0Id) == 2);
		assertTrue(ShaCalModel.getPerson("person1").getPermission(gr2Id) == 2);
		ShaCalModel.addLink("person0", group1.getGrId());
		ShaCalModel.addLink("person1", group1.getGrId());
		ShaCalModel.addLink("person0", gr2Id);
		ShaCalModel.addLink("person1", gr0Id);
		assertTrue(ShaCalModel.getPerson("person0").getPermission(group1.getGrId()) == 0);
		assertTrue(ShaCalModel.getPerson("person1").getPermission(group1.getGrId()) == 0);
		assertTrue(ShaCalModel.getPerson("person0").getPermission(gr2Id) == 0);
		assertTrue(ShaCalModel.getPerson("person1").getPermission(gr0Id) == 0);
		person1.changePermission("person0", gr2Id, 1);
		person0.changePermission("person1", gr0Id, 1);
		assertTrue(ShaCalModel.getPerson("person0").getPermission(gr2Id) == 1);
		assertTrue(ShaCalModel.getPerson("person1").getPermission(gr0Id) == 1);
	}
}
