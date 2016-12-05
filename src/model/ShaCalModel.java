
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class ShaCalModel extends Observable {
	
	//The "list" of Persons.
	public static HashMap<String,Person> AllPersons = new HashMap<String,Person>();
	
	//The "list" of Groups.
	public static HashMap<Integer,Group> AllGroups = new HashMap<Integer,Group>();
	
	//The "list" of Groups.
	public static HashMap<String,ArrayList<Event>> AllEvents = new HashMap<String,ArrayList<Event>>();
	
	//Adds a newly created Person to the list.
	public static void addPersonToHashMap(Person person){
		AllPersons.putIfAbsent(person.getUserName(), person);
	}
	
	//Deletes completely a Person.
	public static void deletePersonFromHashMap(String person){
		AllPersons.remove(person);
	}
	
	//Adds a newly created Group to the list.
	public static void addGroupToHashMap(Group group){
		AllGroups.putIfAbsent(Integer.valueOf(group.getGrId()), group); // FIXME : Crash alpha
	}
	
	//Deletes completely a Group.
	public static void deleteGroupFromHashMap(int grId){
		AllGroups.remove(grId);
	}
	
	//Adds an already existing Event to an already existing Group/Person.
	public static void addEvent(String creator, Event event){
		if(event==null){
			AllEvents.putIfAbsent(creator, new ArrayList<Event>());
		} else {
			AllEvents.putIfAbsent(creator, new ArrayList<Event>());
			AllEvents.get(creator).add(event);
		}
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
	
	public static void resetAllHashMap() {
		ShaCalModel.AllPersons.clear();
		ShaCalModel.AllGroups.clear();
		ShaCalModel.AllEvents.clear();
	}
}

