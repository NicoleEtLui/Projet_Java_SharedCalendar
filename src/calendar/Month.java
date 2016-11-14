package calendar;

/**
 * this class represents a month with a name, a number and a array of days
 * @author Sorn
 *
 */
public class Month {

	private String name;
	private int number;
	private Day [] day;
	
	public Month(String name, int number, Day [] day){
		this.name=name;
		this.number=number;
		this.day=day;
	}
	
	/**
	 * this method synchronize this month with a personalCalendar
	 */
	public void synchronizeTo(){
		
	}
}

