package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * this class represents a event. 
 * which has an id, a title, a description ,a location,
 * a start/end date, a start/end hour and a group.
 * @author Sorn HULSEN
 *
 */
public class Event {

	// INSTANCES
	
	private String title;
	private String description;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startHour;
	private LocalTime endHour;
	private String creator;
	
	
	// CONSTRUCTORS -----------------------------------------------------------
	
	/**
	 * constructor with a String that represent creator.
	 */
	public Event(String title, String description, String location, LocalDate startDate, LocalDate endDate, String startHour, String endHour, String creator){
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = LocalTime.parse(startHour);
		this.endHour = LocalTime.parse(endHour);
		this.creator = creator;
	}
	
	/**
	 * constructor without the  group.
	 */
	public Event(String title, String description, String location, LocalDate startDate, LocalDate endDate, String startHour, String endHour){
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = LocalTime.parse(startHour);
		this.endHour = LocalTime.parse(endHour);
	}
	

	/**
	 * constructor without the  group and hours
	 */
	public Event(String title, String description, String location, LocalDate startDate, LocalDate endDate){
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = LocalTime.now();
		this.endHour = LocalTime.now().plusHours(1);
	}
	
	public Event(String title, String description, LocalDate startDate, LocalDate endDate){
		this.title = title;
		this.description = description;
		this.location = "";
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = LocalTime.now();
		this.endHour = LocalTime.now().plusHours(1);
	}
	
	/**
	 * constructor without date
	 * date will automatically be set to the current date
	 */
	public Event(String title, String description, String location, String startHour, String endHour){
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDate = LocalDate.now();
		this.endDate = LocalDate.now();
		this.startHour = LocalTime.parse(startHour);
		this.endHour = LocalTime.parse(endHour);
	}	
	
	/**
	 * constructor without the  group, dates and hours
	 */
	public Event(String title, String description, String location){
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDate = LocalDate.now();
		this.endDate = LocalDate.now();
		this.startHour = LocalTime.now();
		this.endHour = LocalTime.now().plusHours(1);
	}
	
	/**
	 * Constructor with a title and a description
	 */
	public Event(String title, String description){
		this.title=title;
		this.description=description;
		this.location="";
		this.startDate=LocalDate.now();
		this.endDate=LocalDate.now();
		this.startHour=LocalTime.now();
		this.endHour=LocalTime.now().plusHours(1);
	}
	
	/**
	 * Constructor with just the title, that's the minimum
	 * you can't create a event without title
	 * @param title is the name of the event
	 */
	public Event(String title){
		this.title=title;
		this.description="";
		this.location="";
		this.startDate=LocalDate.now();
		this.endDate=LocalDate.now();
		this.startHour=LocalTime.now();
		this.endHour=LocalTime.now().plusHours(1);
	}
	
	
	// METHODS ----------------------------------------------------------------
	
	/**
	 * this method gives a textual representation of a event.
	 */
	public String toString(){
		return ("\ntitre : "+this.title+
				"\ndescription : "+this.description+
				"\nlocation : "+this.location+
				"\nfrom : "+this.startDate+" at : "+this.startHour+
				"\nto : "+this.endDate+" at : "+this.endHour);	//the group doesn't have to be write here	
	}
	
	/**
	 * this method checks the equality between 2 objects
	 * the check is based on the title, the description and the location of the events
	 * @return true if all attributes are the same
	 * @return false if one attribute is different
	 */
	public boolean equals(Object obj){
		if(obj != null && obj instanceof Event){
	    	Event e = (Event) obj;
	        return ((this.title == e.title) &&
	        		(this.startDate.equals(e.startDate)));
		} else {
	        return false;
	    }
	}
	
	/**
	 * this method compares 2 events.
	 * the comparison is based on the start hour of the event.
	 * @return -1 if the calling event starts after the event it's compared to.
	 * 1 if the calling event starts before the event it's compared to.
	 * 0 if they start at the same time.
	 */
	public int compareTo(Event e){
	    if(this.startHour.isAfter(e.startHour))
			return -1;
	    if(this.startHour.isBefore(e.startHour))
	    	return 1;
	    return 0;
	}
	
	
	// GETTERS AND SETTERS ----------------------------------------------------
	
	/**
	 * this method gets the title of the event
	 * @return the title 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * this method sets the title
	 * @param id is the title of the event
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * this method gets the description of the event
	 * @return the description 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * this method sets the description
	 * @param id is the description of the event
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * this method gets the location of the event
	 * @return the location 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * this method sets the location
	 * @param id is the location of the event
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * this method gets the date of the begin of the event
	 * @return the start date 
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * this method sets the date of the begin
	 * @param id is the start date of the event
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * this method gets the date of the end of the event
	 * @return the end date 
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * this method sets the date of the end
	 * @param id is the end date of the event
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * this method gets the hour of the start of the event
	 * @return the start hour 
	 */
	public LocalTime getStartHour() {
		return startHour;
	}

	/**
	 * this method sets the hour of the begin
	 * @param id is the start hour of the event
	 */
	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	/**
	 * this method gets the hour of the end of the event
	 * @return the end hour 
	 */
	public LocalTime getEndHour() {
		return endHour;
	}

	/**
	 * this method sets the hour of the end
	 * @param id is the end hour of the event
	 */
	public void setEndHour(LocalTime endHour) {
		this.endHour = endHour;
	}

	public String getCreator() {
		return creator;
	}
	
}