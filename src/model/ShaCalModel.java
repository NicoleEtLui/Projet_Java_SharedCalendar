
package model;

import java.time.LocalDate;						//XXX : Used for tests.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Observable;

//TODO : instancier une personne et instancier un groupe en passant la personne en param�tre

public class ShaCalModel extends Observable {
	
	//The "list" of Persons.
	public static HashMap<String,Person> AllPersons;
	
	//The "list" of Groups.
	public static HashMap<Integer,Group> AllGroups;
	
	//The "list" of Groups.
	public static HashMap<String,ArrayList<Event>> AllEvents;
	
	public static void main(String[] args) {
		
		// Example of a LinkedHashMap, probably what we're going to use for Groups.
		LinkedHashMap<Integer, Group> GroupTest = new LinkedHashMap<Integer, Group>();
		GroupTest.put(0, new Group("First Group"));
		GroupTest.put(1, new Group("Second Group"));
		
		for(int i=0;i<GroupTest.size();i++){
			System.out.println(GroupTest.get(GroupTest.keySet().toArray()[i]));
		}
		
		System.out.println("----------------");
		
		// Example of a HashMap, probably what we're going to use for Persons.
		HashMap<String, Person> PersonTest = new HashMap<String, Person>();
		Person personA = new Person("jean","naymar","jean",LocalDate.now());
		PersonTest.put(personA.getUserName(), personA);
		Person personB = new Person("george","clooney","george",LocalDate.now());
		addPersonToHashmap(personB); //FIXME : Crash alpha
		
		for(int i=0;i<PersonTest.size();i++){
			System.out.println(PersonTest.get(PersonTest.keySet().toArray()[i]));
		}
	}
	
	//Adds a newly created Person to the list.
	public static void addPersonToHashmap(Person person){
		AllPersons.putIfAbsent(person.getUserName(), person);
	}
	
	//Deletes completely a Person.
	public static void deletePersonFromHashmap(String person){
		AllPersons.remove(person);
	}
	
	//Adds a newly created Group to the list.
	public static void addGroupToHashmap(Group group){
		AllGroups.putIfAbsent(Integer.valueOf(group.getGrId()), group); // FIXME : Crash alpha
	}
	
	//Deletes completely a Group.
	public static void deleteGroupFromHashmap(int grId){
		AllGroups.remove(grId);
	}
	
	//Adds an already existing Event to an already existing Group/Person.
	public static void addEvent(String creator, Event event){
		AllEvents.putIfAbsent(creator, new ArrayList<Event>());
		AllEvents.get(creator).add(event);
	}
	
	//Removes an Event from a Group/Person and deletes it.
	public static void removeEvent(String grId, Event event){
		AllEvents.get(grId).remove(event);
	}
	
	//Adds a new link between an already existing Person and an already existing Group.
	public static void addLink(String userName, int grId){
		AllPersons.get(userName).addGroupToPerson(grId);	//See new "addGroupToPerson" method below.
		AllGroups.get(grId).addMemberToGroup(userName); 	//See new "addMemberToGroup" method below.
	}
	
	//Remove a link between an already existing Person and an already existing Group.
	public static void removeLink(String userName, int grId){
		AllPersons.get(userName).deleteGroupFromPerson(grId);	//See new "deleteGroupFromPerson" method below.
		AllGroups.get(grId).deleteMemberFromGroup(userName);	//See new "deleteMemberFromGroup" method below.
	}
}

