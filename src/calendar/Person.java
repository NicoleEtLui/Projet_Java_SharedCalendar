package calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a person.
 * UserName is the unique id for a person.
 * @author Martin
 *
 */
public class Person {
	
	private String name;
	private String firstName;
	private String userName;
	private LocalDate bDate;
	/**
	 * list of 2dimensionnal array.
	 * first array is for the groupId the person belong,
	 * second array is the level of authorization of this person in this group
	 * 0 = regular user --> access to the groupCalendar bunt cant modify it.
	 * 1 = admin --> access to groupCalendar + access to method that modify it
	 * 				setEvent, deleteEvents, ...
	 * 2 = super admin --> admin + upgrade/downgrade members
	 */
	private List<int[][]> group = new ArrayList<int[][]>();
	/**
	 * a calendar is represented by a list of event.
	 */
	private List<Event> persoClndr = new ArrayList<Event>();

	
	/**
	 * empty constructor.
	 */
	public Person(){};
	/**
	 * Constructor for a new user, with no group.
	 * an event is automatically add, his birthday.
	 * @param name
	 * @param firstName
	 * @param userName
	 * @param bDate birthDate
	 */
	public Person(String name, String firstName, String userName, LocalDate bDate) {
		this.name = name;
		this.firstName = firstName;
		this.userName = userName;
		this.bDate = bDate;
		this.persoClndr.add(new Event("Joyeux Anniversaire", "Mon anniversaire", this.bDate, this.bDate));
	}
	/**
	 * Constructor to create a person and add her to a group.
	 * @param name
	 * @param firstName
	 * @param userName
	 * @param bDate
	 * @param group
	 * @param persoClndr
	 */
	public Person(String name, String firstName, String userName, LocalDate bDate, Group group, Calendar persoClndr) {
		this.name = name;
		this.firstName = firstName;
		this.userName = userName;
		this.bDate = bDate;
		this.group.add(new int[group.getId()][0]);
		this.persoClndr.add(new Event("Joyeux Anniversaire", "Mon anniversaire", this.bDate, this.bDate));
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public LocalDate getbDate() {
		return bDate;
	}
	public void setbDate(LocalDate bDate) {
		this.bDate = bDate;
	}
	public String getUserName() {
		return userName;
	}
	public List<int[][]> getGroup() {
		return group;
	}
	public List<Event> getPersoClndr() {
		return persoClndr;
	}
	
	@Override
	public String toString(){
		return (this.firstName + " " + this.name + " - " +
				this.userName + "\n" + this.group + "\n" +
				this.persoClndr);
	}
	
	
	@Override
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
	
	/**
	 * this method ad a new event on his own calendar.
	 * @param x is the event to add
	 */
	public void addPersonalEvents(Event e) {
		this.persoClndr.add(e);
	}
	
	/**
	 * this method delete an event from the personal calendar.
	 * @param x is the event to delete
	 */
	public void deletePersonalEvent(Event e) {
		this.persoClndr.remove(e);
	}
	
	/**
	 * this method change permissions of a user p of a group grId. 
	 * @param x is the person to upgrade/downgrade
	 * @param userlvl is the new levels of permission for the person
	 * @param grId
	 * return false if the userLevelof for the group grId of the person calling this method is under 2.
	 * true otherwise and replace former permission of the person p for the group grId by userlvl
	 */
	private boolean changePermission(Person p, int userlvl, int grId) {
		return false;
	}
	
	/**
	 * this method create a new Group.
	 * @param grName is the name of the new group
	 * @return a new calendar's group
	 * add the created group to our list of group
	 * set the userLevel of the person calling this method and for the group created to 2. 
	 */
	public int createGroup(String grName) {
		this.group.add(new int[new Group(grName).getId()][2]);
		Group.addMembers(this);
		return 0;
	}
	public static void main(String [] args){
		Person p = new Person("Petit", "Martin", "NicoleEtlui", LocalDate.of(1994,9,28));
		Person p1 =  new Person("Petit", "Martin", "MartinP", LocalDate.of(1994,9,28));
		Person p2 =  new Person("Petit", "Nicole", "NicoleEtlui", LocalDate.of(1994,9,28));
		System.out.println(p.equals(p));
		System.out.println(p.equals(p1));
		System.out.println(p.equals(p2));
	}
}