package calendar;

/**
 * this class represents a day with a name, a number and some events
 * @author Sorn
 *
 */
public class Day {

	private String name;
	private int number;
	private Event [] event;
	
	public Day(String name, int number, Event [] event){
		this.name=name;
		this.number=number;
		this.event=event;
	}
	
	/**
	 * this method synchronize a calendar with a person
	 */
	public void synchronizeTo(){
		
	}
}
