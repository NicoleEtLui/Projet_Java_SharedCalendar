package model;

import java.util.ArrayList;

public class Group {
	
	private static int defaultIdNumber = -1;
	private int grId;
	private String grName;
	private boolean isPublic;
	private ArrayList<String> members;
	
	/**
	 * Default constructor for group object
	 * @param grName : New group's name as a String
	 * @param isPublic : Visibility of the new group as a boolean
	 * @param Members : All the members as an ArrayList of Person
	 */
	public Group(String grName, boolean IsPublic, ArrayList<String> Members) {
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = IsPublic;
		this.members = new ArrayList<String>();
		if(Members != null)this.members.addAll(Members);
		ShaCalModel.addEvent(String.valueOf(grId),null);
		ShaCalModel.addGroupToHashMap(this);
	}
	
	public Group(String grName, String FirstMember){
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = true;
		this.members = new ArrayList<String>();
		this.members.add(FirstMember);
		ShaCalModel.addEvent(String.valueOf(grId),null);
		ShaCalModel.addGroupToHashMap(this);
	}

	public Group(String grName){
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = true;
		this.members = new ArrayList<String>();
		ShaCalModel.addEvent(String.valueOf(grId),null);
		ShaCalModel.addGroupToHashMap(this);
	}
	
	public Group() {
		this.grId = getNewId();
		this.grName = "UnknownGroup" + getCurrentId();
		this.isPublic = true;
		this.members = new ArrayList<String>();
		ShaCalModel.addEvent(String.valueOf(grId),null);
		ShaCalModel.addGroupToHashMap(this);
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
				+ ShaCalModel.getEvent(getGrIdString()).size();
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
	
	public static void resetCurrentId(){
		defaultIdNumber = -1;
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
	public Person getSingleMember(String userName){
		return ShaCalModel.AllPersons.get(userName);
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
	 * @return The group's visibility as a boolean.
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
	
	/**
	 * Compare a Group to another on the basis of their unique grId.
	 * @param group : A Group to be compared to.
	 * @return The equality between two Groups as a boolean.
	 */
	public boolean equals(Group group){
		return (this.grId == group.grId);
	}
	/**
	 * Adds an userName to the list of members.
	 * @param userName : A String used to fetch the Person behind that userName.
	 */
	public void addMemberToGroup(String userName){
		members.add(userName);
	}
	
	/**
	 * Removes an userName from the list of members.
	 * @param userName : A String used to fetch the Person behind that userName.
	 */
	public void deleteMemberFromGroup(String userName){
		members.remove(userName);
	}
	
	/**
	 * Returns the grId of a Group as a String.
	 * @return the grId as a String.
	 */
	public String getGrIdString(){
		return String.valueOf(this.getGrId());
	}
}
