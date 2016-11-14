package calendar;

/**
 * this class represents a group of people which has an ID, a name, some members
 * a calendar and a variable which says if the group is public or not
 * @author Sorn
 *
 */
public class Group {

	private int id;
	private String name;
	private Person [] members;
	private boolean isPublic;
	private Calendar [] clndr;
	
	public Group(int id, String name, Person members, boolean isPublic,Calendar clndr){
		this.id=id;
		this.name=name;
		this.members=members;
		this.isPublic=isPublic;
		this.clndr=clndr;
	}
	
	/**
	 * this method 
	 * @param x is the person to alert
	 */
	public  void alertMembers(Person x){
		/*
		
		*/
	}
}