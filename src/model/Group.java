package model;

import java.util.ArrayList;

public class Group {
	
	private static int defaultIdNumber = 0;
	private int grId; /**[ Kept or not ? ]*/
	private String grName;
	private boolean isPublic;
	private ArrayList<String> members;
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
	public Group(String grName, boolean IsPublic, ArrayList<String> Members) {
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = IsPublic;
		this.members = new ArrayList<String>();
		if(Members != null)this.members.addAll(Members);
	}
	
	public Group(String grName, String FirstMember){
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = true;
		this.members = new ArrayList<String>();
		this.members.add(FirstMember);
	}

	public Group(String grName){
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = true;
		this.members = new ArrayList<String>();
	}
	
	/**
	 * Default empty constructor for group object
	 */
	public Group() {
		this.grId = getNewId();
		this.grName = "UnknownGroup" + getCurrentId();
		this.isPublic = true;
		this.members = new ArrayList<String>();
	}
	
	/**
	 * New default toString method
	 * @return The representation of a group, based on its Id, name, isPublic, calendar and members
	 */
	@Override
	public String toString() {
		return "[Group] Id : \""
				+ this.grId
				+ "\" Name : \""
				+ this.grName
				+ "\" IsPublic : "
				+ this.isPublic
				+ "\n" 
				+ "Number of members : "
				+ this.getMembers().size() 
				+ " | Number of events : " 
				+ AllEvents.get(this.grId).size(); //TODO Find how to access AllEvents from here ?
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
	 * [ TODO ]
	 * @param Members An ArrayList of Persons to alert
	 */
	public void alertMembers(ArrayList<String> Members){
		// Handled elsewhere ?
	}
	
	/**
	 * Returns the WHOLE ArrayList of the member's Id from the group
	 * @return An ArrayList of member's Id
	 */
	public ArrayList<String> getMembers() {
		return members;
	}
	
	/**
	 * Returns a Person if found in the members, else return null
	 * @param Username : The Username to be looked for as a String
	 * @return A Person
	 */
	public Person getSingleMember(String UserName){
		AllPersons.get(userName); //TODO Find how to access AllEvents from here ?
	}
	
	/**
	 * return the current group's Id
	 * @return the group's Id as an int
	 */
	public int getGrId() {
		return grId;
	}
	
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
	public boolean getIsPublic() {
		return isPublic;
	}
	
	/**
	 * Change a group's visibility
	 * @param isPublic : Boolean value
	 */
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public boolean equals(Group group){
		return (this.grId == group.grId);
	}
}
