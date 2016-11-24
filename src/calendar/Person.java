package calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	 * first array is for the groupId the person belong,
	 * second array is the level of authorization of this person in this group
	 * 0 = regular user --> access to the groupCalendar bunt cant modify it.
	 * 1 = admin --> access to groupCalendar + access to method that modify it
	 * 				setEvent, deleteEvents, ...
	 * 2 = super admin --> admin + upgrade/downgrade members
	 */
	private List<int[][]> group = new ArrayList<int[][]>();
	/**
	 * All the events the person sees.
	 */
	private List<Event> persoClndr = new ArrayList<Event>();

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
		this.persoClndr.add(new Event("Joyeux Anniversaire", "Mon anniversaire", this.bDate, this.bDate));
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
	public List<int[][]> getGroup() {
		return group;
	}
	/**
	 * get the calendar of the person
	 * @return the list of events the person sees
	 */
	public List<Event> getPersoClndr() {
		return persoClndr;
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
	
	/**
	 * this method add a new event to the personal calendar.
	 * The list <code>persoClndr<code> grow of one line
	 * @param e is the event to add
	 * 
	 */
	public void addPersonalEvents(Event e) {
		this.persoClndr.add(e);
	}
	
	/**
	 * this method delete an event from the personal calendar.
	 * The list <code>persoClndr<code> get shorter
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
	 * @return <p>false if the userLevelof for the group grId of the person 
	 * calling this method is under 2 or if the person doesn't belong 
	 * to the group<p>
	 * @treturn <p> true otherwise and replace former permission 
	 * of the person p for the group grId by userlvl
	 */
	public boolean changePermission(Person p, int userlvl, int grId) {
		for (int[][] gr : group){
			if (gr[0][0] == grId && gr[1][0] == 2){
				gr[1][0] = userlvl;
				return true;
			}
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
	public int createGroup(String grName) {
		Group group = new Group(grName, this);
		this.group.add(new int[group.getGrId()][2]);
		return group.getGrId();
	}
	
	public static void main(String [] args){
		Person p = new Person("Petit", "Martin", "NicoleEtlui", LocalDate.of(1994,9,28));
		Event e = new Event("MonEvent", "MaDescription", LocalDate.now(), LocalDate.now().plusDays(1));
		System.out.println(p.persoClndr.size());
		p.addPersonalEvents(e);
		System.out.println(p.persoClndr.size());
		p.deletePersonalEvent(e);
		System.out.println(p.persoClndr.size());
		
	}
}
