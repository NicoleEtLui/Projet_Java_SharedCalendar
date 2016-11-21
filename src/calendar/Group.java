package calendar;

import java.util.ArrayList;

public class Group {
	
	private static int defaultIdNumber = 0;
	private int grId; /**[ Kept or not ? ]*/
	private String grName;
	private boolean isPublic;
	private ArrayList<Person> members;
	private ArrayList<Event> grCalendar;
	/**
	 * GrId only used in constructors and toString() method(s) as of now
	 * - Mostly useful to get generic names for constructors
	 * - Easily removed
	 */
	
	/**
	 * Default constructor for group object
	 * @param grName : New group's name as a String
	 * @param isPublic : Visibility of the new group as a boolean
	 * @param Members : All the members as an ArrayList of Person
	 * @param GrCalendar : List of events as an ArrayList of Event 
	 */
	public Group(String GrName, boolean IsPublic, ArrayList<Person> Members, ArrayList<Event> GrCalendar) {
		this.grId = getNewId();
		this.grName = (GrName !=null && !GrName.isEmpty()) ? GrName : ("UnknownGroup" + getCurrentId());
		this.isPublic = (!IsPublic) ? IsPublic : true;
		this.members = new ArrayList<Person>();
		if(Members != null)this.members.addAll(Members);
		this.grCalendar = new ArrayList<Event>();
		if(GrCalendar != null)this.grCalendar.addAll(GrCalendar);
		System.out.println("New group created from no-arg builder :\n" + toString());
	}

	/**
	 * Default empty constructor for group object
	 */
	public Group() {
		this.grId = getNewId();
		this.grName = "UnknownGroup" + getCurrentId();
		this.isPublic = true;
		this.members = new ArrayList<Person>();
		this.grCalendar = new ArrayList<Event>();
		System.out.println("New group created from no-arg builder :\n" + toString());
	}
	
	/**
	 * New default toString method
	 */
	@Override
	public String toString() {
		return "[Group] Id : \"" + this.grId + "\" Name : \"" + this.grName + "\" IsPublic : " + this.isPublic;
	}
	
	/**
	 * Returns a new Id for the creation of a new Group
	 * @return Id as an int
	 */
	public int getNewId(){
		return ++defaultIdNumber;
	}
	
	/**
	 * Return the current Id used in the creation of a new group
	 * @return Id as an int
	 */
	public static int getCurrentId(){
		return defaultIdNumber;
	}
	
	/**
	 * [ TO DO ]
	 * @pram Members An ArrayList of Persons to alert
	 */
	public void alertMembers(ArrayList<Person> Members){
		// Handled elsewhere ?
	}
	
	/**
	 * Deletes a member of the group
	 * @param Username : The Username of the Person to be deleted from the group
	 */
	public void deleteMembers(String Username){
		for(int i=0;i<members.size();i++){
			if(members.get(i).getUserName().equals(Username)){
				members.remove(i);
			}
		}
	}
	
	/**
	 * Adds a new member
	 * @param NewMember : A Person to be added
	 */
	public void addMembers(Person NewMember){
		members.add(NewMember);
	}
	
	/**
	 * Returns the WHOLE ArrayList of the member's Id from the group
	 * @return An ArrayList of member's Id
	 */
	public ArrayList<Person> getMembers() {
		return members;
	}
	
	/**
	 * Returns a Person if found in the members, else return null
	 * @param Username : The Username to be looked for as a String
	 * @return A Person
	 */
	public Person getSingleMember(String Username){
		int index=-1;
		for(int i=0;i<members.size();i++){
			if(members.get(i).getUserName().equals(Username)){
				index = i;
			}
		}
		if(index>0){
			return members.get(index);
		}else{
			return null;		
		}
	}
	
	/**
	 * [ USEFULL ? ]
	 * Sets the WHOLE ArrayList of members
	 * @param Members : A ArrayList of Person
	 * 
	public void setMembers(ArrayList Members) {
		this.Members.removeAll();
		this.Members.addAll(Members);
	}
	 *
	 */
	
	/**
	 * Adds a new event to the ArrayList of event of the group
	 * @param NewEvent : A new Event
	 */
	public void addEvent(Event NewEvent){
		grCalendar.add(NewEvent);
	}
	
	/**
	 * Deletes an event from a list of events from the group
	 * @param title : The title of the event you want to delete
	 */
	public void deleteEvent(String title){
		for(int i=0;i<grCalendar.size();i++){
			if(grCalendar.get(i).getTitle()==title){
				grCalendar.remove(i);
			}
		}
	}

	/**
	 * Returns the WHOLE ArrayList of all the events from this group
	 * @return An ArrayList of events
	 */
	public ArrayList<Event> getGrCalendar() {
		return grCalendar;
	}
	
	/**
	 * [ USEFULL ? ]
	 * Set the WHOLE ArrayList of events for the group
	 * @param NewGrCalendar : A new ArrayList of events
	 * 
	public void setEvents(ArrayList NewGrCalendar) {
		this.GrCalendar = NewGrCalendar;
	}
	 *
	 */
	
	/**
	 * return the current group's Id
	 * @return the group's Id as an int
	 */
	public int getGrId() {
		return grId;
	}
	
	/**
	 * [ USEFULL ? ]
	 * Set a new Id for the group
	 * @param NewGrId : The new int for the group's Id as an int
	public void setGrID(int NewGrID) {
		GrId = NewGrId;
	}
	 *
	 */
	
	/**
	 * Returns the group's name
	 * @return The group's name as a string
	 */
	public String getGrName() {
		return grName;
	}
	
	/**
	 * Set the group's name
	 * @param NewGrName : The new string used as a name
	 */
	public void setGrName(String NewGrName) {
		grName = NewGrName;
	}
	
	/**
	 * Returns the boolean isPublic
	 * @return Boolean value
	 */
	public boolean isPublic() {
		return isPublic;
	}
	
	/**
	 * Change a group's visibility
	 * @param isPublic : Boolean value
	 */
	public void isPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public static void main(String[] args) {
		Group Group1 = new Group(null, true, null, null);
		ArrayList<Person> Members4G1 = new ArrayList<Person>();
		for(int i=0;i<10;i++){
			Person NewPerson = new Person();
			Members4G1.add(NewPerson);
		}
		Group1.members.addAll(Members4G1);
		ArrayList<Event> Events4G1 = new ArrayList<Event>();
		for(int i=0;i<10;i++){
			Event NewEvent = new Event("Event " + Integer.toString(i));
			Events4G1.add(NewEvent);
		}
		Group1.grCalendar.addAll(Events4G1);
		DEV.splitLine();
	}
}
