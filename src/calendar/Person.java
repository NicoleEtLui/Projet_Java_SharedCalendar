package calendar;

import java.time.LocalDate;

/**
 * This class represents a person which contains a name,
 * a firstName, a userName, a birth date, a groupName and 
 * a personal calendar.
 * @author Sorn
 *
 */
public class Person {

	private String name;
	private String firstName;
	private String userName;
	private LocalDate bDate;
	private int [] [] group;
	private Calendar persoClndr;
	
	public Person(String name, String firstName, String userName, LocalDate bDate, Group group, Calendar persoClndr){
		this.name = name;
		this.firstName = firstName;
		this.bDate = bDate;
		this.group = group;
		this.persoClndr = persoClndr;
	}
	
	/**
	 * this method ad a new event on his own calendar
	 * @param x is the event to add
	 */
	public void setPersonalEvent(Event x){
		/*
		
		*/
	}
	
	/**
	 * this method delete a event on the personal calendar
	 * @param x is the event to delete
	 */
	public void deletePersonalEvent(Event x){
		/* 
		si (le titre de l'event correspond à un event du tableau){
			delete;
		}
		*/
	}
	
	/**
	 * this method add a new event in a group that the user joined
	 * @param x is the event to add
	 */
	public void setGroupEvent(Event x){
		/*
		
		*/
	}
	
	/**
	 * this method delete a event in a group that the user joined
	 * @param x is the event to delete
	 */
	public void deleteGroupEvent(Event x){
		/*
		
		*/
	}
	
	/**
	 * this method delete a member in a group that the user joined
	 * @param x is the person to delete from the group
	 */
	public void deleteMembers(Person x){
		/*
		
		*/
	}
	
	/**
	 * this method add a new member in a group that the user joined
	 * @param x is the person to add to the group
	 */
	public void addMembers(Person x){
		/*
		
		*/
	}
	
	/**
	 * this method change the permissions of a member for having some best rights in the grou
	 * @param x is the person to upgrade
	 * @param userlvl is the new levels of permission the person has
	 */
	public void upgradeMembers(Person x, int userlvl){
		/*
		x.userLevel=userlvl;
		*/
	}
	
	/**
	 * this method change the permissions of a member for having some worst rights in the grou
	 * @param x is the person to downgrade
	 * @param userlvl is the new permission the person has
	 */
	public void downgradeMembers(Person x, int userlvl){
		/*
		x.userLevel=userlvl;
		*/
	}
	
	/**
	 * this method create a new calendar for a new group
	 * @param x is the calendar
	 * @param grName is the name of the new group
	 * @return a new calendar's group
	 */
	public Calendar createGroupCalendar(Calendar x, String grName){
		/*
		
		*/
		return (new Calendar());
	}
}
