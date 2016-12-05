package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a person.
 * UserName is the unique id for a person.
 * @author Martin
 */
public class Person {
	/**
	 * The name of the person as it appears on his id.
	 */
	private String name;
	/**
	 * The first name of the person as it appears on his id.
	 */
	private String firstName;
	/**
	 * The unique identifier of a person.
	 * String chosen by the user
	 */
	private String userName;
	/**
	 * Birthday of the persons.  
	 */
	private LocalDate bDate;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	/**
	 * All the groups the person belong and his level in those.
	 * <p>First number stands for the <code>grId<code> the person belong,
	 * second number stands for the lvl of authorization of this person in grp <p>
	 * 0 = regular user --> access to the groupCalendar bunt cant modify it.
	 * 1 = admin --> access to groupCalendar + access to method that modify it
	 * 				setEvent, deleteEvents, ...
	 * 2 = super admin --> admin + upgrade/downgrade members
	 */
	private HashMap<Integer,Integer> group = new HashMap<Integer,Integer>();
	
	//-- CONSTRUCTOR ----------------------------------------------------------
	/**
	 * Constructor for a new user, with no group.
	 * an event is automatically add, his birthday.
	 * @param name the name to give the person
	 * @param firstName the firstname to give the person
	 * @param userName the string the person chose
	 * @param bDate birthDate the date used to create the first even
	 */
	public Person(String name, String firstName, String userName, LocalDate bDate) {
		this.name = name;
		this.firstName = firstName;
		this.userName = userName;
		this.bDate = bDate;
		ShaCalModel.addEvent(userName,new Event("Joyeux Anniversaire", "Mon anniversaire", this.bDate, this.bDate)); ///TODO Find how to access addEvent from here ?
	}
	
	//-- GETTERS & SETTERS ----------------------------------------------------
	/**
	 * get the name of the person.
	 * @return the name of the person
	 */
	public String getName() {
		return name;
	}
	/**
	 * set the Name of the person
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the firstname the person have
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstname to set instead
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * get the birthday of the person.
	 * @return the birthday of the person
	 */
	public LocalDate getbDate() {
		return bDate;
	}
	/**
	 * change the birthday
	 * @param bDate the date to set instead.
	 */
	public void setbDate(LocalDate bDate) {
		this.bDate = bDate;
	}
	/**
	 * @return the userName the person have
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Get a list of the group the person belong.
	 * @return a 
	 */
	public HashMap<Integer,Integer> getGroup() {
		return group;
	}
	
	//-- METHODS --------------------------------------------------------------
	@Override
	/**
	 * return a string representation of an instance of person.
	 */
	public String toString(){
		return (this.firstName + " " + this.name + " - " +
				this.userName + " - " + this.bDate.format(formatter) );
	}
	
	public String toStringGroup(List<int[]> l){
		String gr = "";
		for (int i=0;i<group.size();i++){
			gr += (this.userName + "[" + i + "," + group.get(i) + "]\n");
		};
		return gr;
	}
	@Override
	/**
	 * Compare the equality of two person.
	 * Two persons are equals if there userName are the same
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	//QUESTION: wouldn't it be more simple if the group possessed a static list of it's members with their permission in him ?
	// if so, the change permission would go in group Class and only one list should be updated.
	// for now we must update two 
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
	public boolean changePermission(String userName, int grId, int userLvl) {
		if(this.group.get(grId) != 2){
			return false;
		}
		if(ShaCalModel.AllPersons.get(userName).group.get(grId) != 2){
			ShaCalModel.AllPersons.get(userName).group.put(grId,Integer.valueOf(userLvl));
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
	public int createGroup(String grName){
		Group group = new Group(grName, this.getUserName());
		ShaCalModel.addGroupToHashmap(group); //TODO Good ?
		addGroupToPerson(group.getGrId());
		return group.getGrId();
	}
	
	//TODO Add javadoc.
	public void addGroupToPerson(int grId){
		group.put(grId, 2);
	}
	
	public void deleteGroupFromPerson(int grId){
		group.remove(grId);
	}
	
} // fin class Person
