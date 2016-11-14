package calendar;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * this class represents a event which has a title, a description ,a location,
 * a start/end date, a start/end hour and a group
 * @author Sorn
 *
 */
public class Event {

	private String title;
	private String description;
	private String location;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startHour;
	private LocalTime endHour;
	private Group group;
	
	public Event(String title, String description, String location, LocalDate startDate, LocalDate endDate, LocalTime startHour, LocalTime endHour, Group group){
		this.title = title;
		this.description = description;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.group = group;
	}
	
	/**
	 * this method synchronize a calendar with a person
	 */
	public void synchronizeTo(){
		
	}
}

