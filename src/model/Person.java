package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

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
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

	//-- CONSTRUCTOR -------------------------------------------------------------------------------------
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
		this.group = new HashMap<Integer,Integer>();
		ShaCalModel.allEvents.get(userName).add( 
				new Event("Joyeux Anniversaire " + userName,
						"Mon anniversaire",
						"",
						this.bDate,
						this.bDate,
						LocalTime.now().toString(),
						LocalTime.now().plusHours(1).toString(),
						userName));
	}
	
	public Person(String name, String firstName, String userName, String groupString, LocalDate bDate) {
		this.name = name;
		this.firstName = firstName;
		this.userName = userName;
		this.bDate = bDate;
		if(groupString.length()!=0){
			for(int i=0;i<groupString.split(";",0).length;i++){
				this.group.put(Integer.parseInt(groupString.split(";",0)[i].split("-")[0]),
						Integer.parseInt(groupString.split(";",0)[i].split("-")[1]));
			}
		}
		//model.addEvent(userName,new Event("Joyeux Anniversaire", "Mon anniversaire", this.bDate, this.bDate));
	}

	//-- GETTERS & SETTERS -------------------------------------------------------------------------------
	
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
	public String getbDate() {
		return this.bDate.format(formatter);
	}
	/**
	 * change the birthday
	 * @param bDate the date to set instead.
	 */
	public void setbDate(LocalDate bDate) { //XXX To be kept or not ? (Why have the possibility of changing it?)
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
	
	/**
	 * Returns the administrative level of the user in the Group.
	 * @param grId the Id of the Group as an Integer.
	 * @return The user's permission in the Group as an Integer.
	 */
	public Integer getPermission(Integer grId){
		return this.getGroup().get(grId);
	}
	/**
	 * 
	 * @param hm
	 * @return
	 */
	public String toStringGroup(HashMap<Integer,Integer> hm){
		String groups = "";
		for(int i=0;i<hm.size();i++){
			groups += hm.keySet().toArray()[i] + "-" + hm.get(hm.keySet().toArray()[i]) + ";";
		}
		return groups;
	}
	
	public String getGroupString(HashMap<Integer, Integer> groups){
		String groupString = "";
		for(int i=0;i<groups.size();i++){
			groupString += groups.keySet().toArray()[i] +
					"-" +
					groups.get(groups.keySet().toArray()[i]) +
					";";
		}
		return groupString;
	}
}
