
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class ShaCalModel extends Observable {
	/**
	 * The main "list" of all Persons.
	 */
	public HashMap<String,Person> allPersons = new HashMap<String,Person>();
	/**
	 * The main "list" of all Groups.
	 */
	public HashMap<Integer,Group> allGroups = new HashMap<Integer,Group>();
	/**
	 * The main "list" of all Events.
	 */
	public HashMap<String,ArrayList<Event>> allEvents = new HashMap<String,ArrayList<Event>>();

	
	//-- GETTERS & SETTERS -------------------------------------------------------------------------------
	
	/**
	 * Fetches a Group.
	 * @param object : The grId of the Group to fetch.
	 * @return A Group.
	 */
	public Group getGroup(Object grId){ 		//TODO Find how to force "String" input and not "Object".
		return allGroups.get(grId);
	}
	

	/**
	 * Fetches a Person.
	 * @param object : The userName of the Person to fetch.
	 * @return A Person.
	 */
	public Person getPerson(Object userName){ 	//TODO Find how to force "String" input and not "Object".
		return allPersons.get(userName);
	}
	
	/**
	 * Fetches the ArrayList of Event linked to a Person/Group.
	 * @param creator : The creator of the ArrayList of Event as a String.
	 * @return An ArrayList of Event.
	 */
	public ArrayList<Event> getEvent(Object creator){
		return allEvents.get(creator);
	}
	

	//-- METHODS -----------------------------------------------------------------------------------------
	//-- GROUPS ------------------------------------------------------------------------------------------
	
	/**
	 * Adds a newly created Group to the HashMap.
	 * @param group : The Group to be added.
	 */
	public void addGroupToHashMap(Group group){
		if(allGroups.get(group.getGrId())!=null){
			System.out.println("Key \"" + group.getGrId() + "\" already in use."); //TODO Handle exception
		}
		allGroups.putIfAbsent(Integer.valueOf(group.getGrId()), group);
	}
	
	/**
	 * Completely removes a Group from the HashMap.
	 * @param grId : The grId of the Group to delete.
	 */
	public void deleteGroupFromHashMap(int grId){
		allGroups.remove(grId);
		for(int i=0;i<allPersons.size();i++){
			deleteGroupFromPerson(getPerson(allPersons.keySet().toArray()[i]).getUserName(), grId);
		}
	}
	
	/**
	 * Adds an userName to the list of members.
	 * @param userName : A String used to fetch the Person behind that userName.
	 */
	public void addMemberToGroup(String userName, int grId){
		getGroup(grId).getMembers().add(userName);
	}
	
	/**
	 * Removes an userName from the list of members.
	 * @param userName : A String used to fetch the Person behind that userName.
	 */
	public void deleteMemberFromGroup(String userName, int grId){
		getGroup(String.valueOf(grId)).getMembers().remove(userName);
	}
	
	/**
	 * New default toString method
	 * @return The representation of a group, based on its Id, name, isPublic, calendar and members
	 */
	
	public String groupToString(Group group) {
		int nbEvent = (getEvent(group.getGrIdString())==null) ? 0 : getEvent(group.getGrIdString()).size();
		return "[Group] Id : \""
				+ group.getGrId()
				+ "\" Name : \""
				+ group.getGrName()
				+ "\" IsPublic : "
				+ group.getIsPublic()
				+ "\n" 
				+ "Number of members : "
				+ group.getMembers().size() 
				+ " | Number of events : " 
				+ nbEvent; 
	}
	
	/**
	 * Returns a String representation of a Person with its Groups and privileges.
	 */
	public String groupToStringMembers(Group group){
		String str = "UserName : " + group.getGrName() + "\n";
		for(int i=0;i<group.getMembers().size();i++){
			str += ">[User : " + allPersons.get(group.getMembers().get(i)).getUserName() + "| Lvl : " + allPersons.get(group.getMembers().get(i)).getPermission(group.getGrId()) + "]\n";
		}
		return str;
	}
	
	/**
	 * Compare the equality of two person.
	 * Two persons are equals if there userName are the same
	 */
	public boolean groupEquals(Group group, Object obj) {
		if (group == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if(group.getGrId() == other.getGrId()) return false;
		return true;
	}
	
	//-- PERSONS -----------------------------------------------------------------------------------------
	
	/**
	 * Adds a newly created Person to the HashMap.
	 * @param person : The Person to be added.
	 */
	public void addPersonToHashMap(Person person){
		if(allPersons.get(person.getUserName())!=null){
			System.out.println("Key \"" + person.getUserName() + "\" already in use."); //TODO Handle exception
		}
		allPersons.putIfAbsent(person.getUserName(), person);
	}
	
	/**
	 * Completely removes a Person from the HashMap.
	 * @param userName : The userName of the Person to delete.
	 */
	public void deletePersonFromHashMap(String userName){
		allPersons.remove(userName);
		for(int i=0;i<allGroups.size();i++){
			deleteMemberFromGroup(userName,getGroup(allGroups.keySet().toArray()[i]).getGrId());
		}
	}

	
	/**
	 * Adds a grId to the list of Groups, as a regular user.
	 * @param grId : the Group's Id as an int.
	 */
	public void addGroupToPerson(String person, int grId){
		getPerson(person).getGroup().put(grId, 0);
	}
	
	/**
	 * Removes a grId from the list of Groups.
	 * @param grId : the Group's Id as an int.
	 */
	public void deleteGroupFromPerson(String person, int grId){
		getPerson(person).getGroup().remove(grId);
	}
	/**
	 * Returns a String representation of a Person.
	 */
	public String personToString(Person person){
		return (person.getFirstName() + " " + person.getName() + " - " +
				person.getUserName() + " - " + person.getbDate() );
	}
	
	/**
	 * Returns a String representation of a Person with its Groups and privileges.
	 */
	public String personToStringGroups(Person person){
		String str = "UserName : " + person.getUserName() + "\n";
		for(int i=0;i<person.getGroup().size();i++){
			str += ">[Group : " + allGroups.get(person.getGroup().keySet().toArray()[i]).getGrName() + "| Lvl : " + person.getGroup().get(i) + "]\n";
		}
		return str;
	}

	/**
	 * Compare the equality of two person.
	 * Two persons are equals if there userName are the same
	 */
	public boolean personEquals(Person person, Object obj) {
		if (person == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (person.getUserName() == null) {
			if (other.getUserName() != null)
				return false;
		} else if (!person.getUserName().equals(other.getUserName()))
			return false;
		return true;
	}
	
	/**
	 * this method change permissions of a user p of a group grId. 
	 * <p>The person calling this method must belong to the same group the 
	 * person she want to change and have a aythorization of 2 in this group<p>
	 * 
	 * @param userName is the userName of the person to upgrade/downgrade.
	 * @param userLvl is the new levels of permission for the person
	 * @param grId is the Id of the group.
	 * @return <p>false if the userLevelof for the group grId of the person 
	 * calling this method is under 2 or if the person doesn't belong 
	 * to the group<p>
	 * @return <p>true otherwise and replace former permission 
	 * of the person p for the group grId by userlvl<p>
	 */
	public boolean changePermission(String admin, String userName, int grId, int userLvl) {
		if((getPerson(userName).getGroup().get(grId)==null) || (getPerson(admin).getGroup().get(grId) != 2)){
			return false;
		}
		if(getPerson(userName).getPermission(grId) != 2){
			getPerson(userName).getGroup().put(grId,userLvl);
			return true;
		}
		return false;
	}
	
	/**
	 * this method create a new Group.
	 * <p>the person that call this method get his list of group updated and
	 * get highest level of authorization for this group (2).<br>
	 * The group's members are updated with the person calling this method<p>
	 * 
	 * @param grName is the name of the new group
	 * @return the identifier <code> grId <code> of the group created
	 */
	public int createGroup(String person, String grName){
		Group group = new Group(grName, getPerson(person).getUserName());
		addGroupToHashMap(group);
		getPerson(person).getGroup().put(group.getGrId(), 2);
		return group.getGrId();
	}
	

	//-- EVENTS ------------------------------------------------------------------------------------------
	/**
	 * Adds a new Event to the Group/Person's list. 
	 * @param creator : The grId/userName to add the Event to.
	 * @param event : A newly created event to be associated with a creator.
	 */
	public void addEvent(String creator, Event event){
		allEvents.putIfAbsent(creator, new ArrayList<Event>());
		allEvents.get(creator).add(event);
	}
	
	/**
	 * Remove an Event from the creator's list.
	 * @param creator : The grId/userName to remove the Event from.
	 * @param event : The Event to be removed from the list of the creator.
	 */
	public void removeEvent(String creator, Event event){
		allEvents.get(creator).remove(event);
	}

	//-- GENERAL -----------------------------------------------------------------------------------------
	
	/**
	 * Adds a Person to a Group, by adding a link between them.
	 * @param userName : The userName of the Person to link.
	 * @param grId : The grId of the Group to link.
	 */
	public void addLink(String userName, int grId){
		addGroupToPerson(userName, grId);
		addMemberToGroup(userName, grId);
	}
	
	/**
	 * Remove a Person from a Group, by removing the link between them.
	 * @param userName : The userName of the Person to remove.
	 * @param grId : The grId of the Group to get removed from.
	 */
	public void removeLink(String userName, int grId){
		deleteGroupFromPerson(userName, grId);
		deleteMemberFromGroup(userName, grId);
	}
	
	/**
	 * Reset all the HashMaps and the grId, to start full fresh.
	 */
	public void resetAllHashMap() {
		allPersons.clear();
		allGroups.clear();
		allEvents.clear();
		Group.resetCurrentId();
		System.out.println("Cache vidé");
	}
	
	//-- UNCHECKED ---------------------------------------------------------------------------------------

	/** [ TODO ]
	 * Alerts a member.
	 * @param Members An ArrayList of Persons to alert.
	 */
	public boolean alertMembers(String Members){
		return false;
	}
	
}

