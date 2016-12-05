
package model;

import java.time.LocalDate;						//XXX : Used for tests.
import java.time.format.DateTimeFormatter;		//XXX : Used for tests.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
	
	//Adds a newly created Event to the list.
	public static void addEventToHashmap(String creator, Event event){
		AllEvents.get(creator).add(event);
	}
	
	//Deletes completely an Event.
	public static void deleteEventFromHashmap(String creator, Event event){
		AllEvents.get(creator).remove(event);
	}
	
	//Adds an already existing Event to an already existing Group.
	public static void addEventToGroup(String grId, Event event){
		AllEvents.get(grId).add(event);
	}
	
	//Adds an already existing Event to an already existing Person.
	public static void addEventToPerson(String person, Event event){
		AllEvents.get(person).add(event);
	}
	
	//Removes an Event from a Group and deletes it.
	public static void removeEventFromGroup(String grId, Event event){
		AllEvents.get(grId).remove(event);
	}
	
	//Removes an Event from a Person and deletes it.
	public static void removeEventFromPerson(String person, Event event){
		AllEvents.get(person).remove(event);
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
	
	
	/* //NEW new values and constructor for Person.
	
	 * Person's new values.
	private String name;
	private String firstName;
	Private String userName;
	private LocalDate bDate;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	 * All the groups the person belong and his level in those.
	 * <p>First number stands for the <code>grId<code> the person belong,
	 * second number stands for the lvl of authorization of this person in grp <p>
	 * 0 = regular user --> access to the groupCalendar bunt cant modify it.
	 * 1 = admin --> access to groupCalendar + access to method that modify it
	 * 				setEvent, deleteEvents, ...
	 * 2 = super admin --> admin + upgrade/downgrade members.
	private ArrayList<int[]> group = new ArrayList<int[]>();
	
	 * New Person's new constructor.
	public Person(String name, String firstName, String userName, LocalDate bDate) {
		this.name = name;
		this.firstName = firstName;
		this.userName = userName;
		this.bDate = bDate;
		this.group = new ArrayList<int[]>();
	}
	 */
	
	
	/* //NEW new addGroupToPerson, deleteGroupFromPerson and createGroup methods.
	
	 * New addGroupToPerson method.
	public void addGroupToPerson(int grId){
		int[] localInt = {grId, 2};
		group.add(localInt);
	}
	
	 * New deleteGroupFromPerson method.
	public void deleteGroupFromPerson(int grId){
		int[] localInt = {grId, 2};
		group.remove(localInt); //TEST : probably doesn't work as intended. 
	}
	
	 * New createGroup method.
	public int createGroup(String grName){
		Group group = new Group(grName, this);
		addGroupToHashMap(group);
		addGroupToPerson(group.getGrId());
		return group.getGrId();
	}
	 */
	
	
	/* //NEW new values and constructor for Group.
	
	 * Group's new values.
	private static int defaultIdNumber = 0;
	private int grId; //[ Kept or not ? ]
	private String grName;
	private boolean isPublic;
	private ArrayList<String> members;
	
	 * New Group's new constructor.
	public Group(String GrName, boolean IsPublic) {
		this.grId = getNewId();
		this.grName = "UnknownGroup" + getCurrentId();
		this.isPublic = IsPublic;
		this.members = new ArrayList<String>();
	}
	 */
	
	
	/* //NEW new addMember and deleteMember methods.
	
	 * New addMember method.
	public void addMemberToGroup(String userName){
		members.add(userName);
	}
	
	 * New deleteMember method.
	public void deleteMemberFromGroup(String userName){
		members.remove(userName);
	}
	 */
}

