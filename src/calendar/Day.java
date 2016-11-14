package calendar;

/**
 * this class represents a day with a name, a number and composed by one or more events
 * @author Sorn, Nathan, Martin
 *
 */
public class Day implements Synchronisable{

	private String name;
	private int number;
	private Event [] events;
	
	public Day(String name, int number, Event [] events){
		this.name = name;
		this.number = number;
		this.events = events;
	}
	
	@Override
	public String toString(){
		return (name + " " + number + " " + events);
	}
	/**
	 * this method synchronize this day with a personalCalendar
	 */
	public void synchronizeTo(){
		
	}
	
	public static void main(String[] args) {
		
	}
}
