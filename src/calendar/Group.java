import java.util.ArrayList;

public class Group {
	
	private static int DefaultIdNumber = 0;
	private int GrId; /**[ Kept or not ? ]*/
	private String GrName;
	private boolean IsPublic;
	private ArrayList<Person> Members;
	private ArrayList<Event> GrCalendar;
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
		this.GrId = GetNewId();
		this.GrName = (GrName !=null && !GrName.isEmpty()) ? GrName : ("UnknownGroup" + GetCurrentId());
		this.IsPublic = (!IsPublic) ? IsPublic : true;
		this.Members = new ArrayList<Person>();
		if(Members != null)this.Members.addAll(Members);
		this.GrCalendar = new ArrayList<Event>();
		if(GrCalendar != null)this.GrCalendar.addAll(GrCalendar);
		System.out.println("New group created from no-arg builder :\n" + toString());
	}

	/**
	 * Default empty constructor for group object
	 */
	public Group() {
		this.GrId = GetNewId();
		this.GrName = "UnknownGroup" + GetCurrentId();
		this.IsPublic = true;
		this.Members = new ArrayList<Person>();
		this.GrCalendar = new ArrayList<Event>();
		System.out.println("New group created from no-arg builder :\n" + toString());
	}
	
	/**
	 * New default toString method
	 */
	@Override
	public String toString() {
		return "[Group] Id : \"" + this.GrId + "\" Name : \"" + this.GrName + "\" IsPublic : " + this.IsPublic;
	}
	
	/**
	 * Method toString extended to give an overview of GrCalendar and Members
	 */
	public void toStringMedium() {
		System.out.println(this);
		DEV.ShowEventsNum(this);
		DEV.ShowMembersNum(this);
	}
	
	/**
	 * Method toString extended to give full view of GrCalendar and Members
	 */
	public void toStringLong(){
		System.out.println(this);
		DEV.ShowEventsNum(this);
		DEV.ShowEvents(this);
		DEV.ShowMembersNum(this);
		DEV.ShowMembers(this);
	}
	/**
	 * Returns a new Id for the creation of a new Group
	 * @return Id as an int
	 */
	public int GetNewId(){
		return ++DefaultIdNumber;
	}
	
	/**
	 * Return the current Id used in the creation of a new group
	 * @return Id as an int
	 */
	public static int GetCurrentId(){
		return DefaultIdNumber;
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
		for(int i=0;i<Members.size();i++){
			if(Members.get(i).getUsername().equals(Username)){
				Members.remove(i);
			}
		}
	}
	
	/**
	 * Adds a new member
	 * @param NewMember : A Person to be added
	 */
	public void addMembers(Person NewMember){
		Members.add(NewMember);
	}
	
	/**
	 * Returns the WHOLE ArrayList of the member's Id from the group
	 * @return An ArrayList of member's Id
	 */
	public ArrayList<Person> getMembers() {
		return Members;
	}
	
	/**
	 * Returns a Person if found in the members, else return null
	 * @param Username : The Username to be looked for as a String
	 * @return A Person
	 */
	public Person getSingleMember(String Username){
		int index=-1;
		for(int i=0;i<Members.size();i++){
			if(Members.get(i).getUsername().equals(Username)){
				index = i;
			}
		}
		if(index>0){
			return Members.get(index);
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
		GrCalendar.add(NewEvent);
	}
	
	/**
	 * Deletes an event from a list of events from the group
	 * @param title : The title of the event you want to delete
	 */
	public void deleteEvent(String title){
		for(int i=0;i<GrCalendar.size();i++){
			if(GrCalendar.get(i).getTitle()==title){
				GrCalendar.remove(i);
			}
		}
	}

	/**
	 * Returns the WHOLE ArrayList of all the events from this group
	 * @return An ArrayList of events
	 */
	public ArrayList<Event> getGrCalendar() {
		return GrCalendar;
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
		return GrId;
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
		return GrName;
	}
	
	/**
	 * Set the group's name
	 * @param NewGrName : The new string used as a name
	 */
	public void setGrName(String NewGrName) {
		GrName = NewGrName;
	}
	
	/**
	 * Returns the boolean isPublic
	 * @return Boolean value
	 */
	public boolean IsPublic() {
		return IsPublic;
	}
	
	/**
	 * Change a group's visibility
	 * @param isPublic : Boolean value
	 */
	public void IsPublic(boolean isPublic) {
		IsPublic = isPublic;
	}
	
	public static void main(String[] args) {
		Group Group1 = new Group(null, true, null, null);
		ArrayList<Person> Members4G1 = new ArrayList<Person>();
		for(int i=0;i<10;i++){
			Person NewPerson = new Person("Mr." + Integer.toString(i));
			Members4G1.add(NewPerson);
		}
		Group1.Members.addAll(Members4G1);
		ArrayList<Event> Events4G1 = new ArrayList<Event>();
		for(int i=0;i<10;i++){
			Event NewEvent = new Event("Event " + Integer.toString(i));
			Events4G1.add(NewEvent);
		}
		Group1.GrCalendar.addAll(Events4G1);
		DEV.SplitLine();
		Group1.toStringMedium();
	}
}
