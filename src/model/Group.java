package model;

import java.util.ArrayList;

public class Group {
	ShaCalModel model = new ShaCalModel();
	/**
	 * The value used in the Id creator.
	 */
	private static int defaultIdNumber = -1;
	/**
	 * The Id of the group.
	 */
	private int grId;
	/**
	 * The name of the group.
	 */
	private String grName;
	/**
	 * The visibility of the group.
	 */
	private boolean isPublic;
	/**
	 * The list of members as an ArrayList of String of userName.
	 */
	private ArrayList<String> members;

	//-- CONSTRUCTOR -------------------------------------------------------------------------------------
	
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
		model.addGroupToHashMap(this);
	}
	
	public Group(String grName, String FirstMember){
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = true;
		this.members = new ArrayList<String>();
		this.members.add(FirstMember);
		model.addGroupToHashMap(this);
	}

	public Group(String grName){
		this.grId = getNewId();
		this.grName = grName;
		this.isPublic = true;
		this.members = new ArrayList<String>();
	}
	
	public Group() {
		this.grId = getNewId();
		this.grName = "UnknownGroup" + getCurrentId();
		this.isPublic = true;
		this.members = new ArrayList<String>();
		model.addGroupToHashMap(this);
	}

	//-- GETTERS & SETTERS -------------------------------------------------------------------------------
	
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
	 * Reset the current Id to -1, to use for tests
	 */
	public static void resetCurrentId(){
		defaultIdNumber = -1;
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
		return model.allPersons.get(userName);
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
	 * Returns the grId of a Group as a String.
	 * @return the grId as a String.
	 */
	public String getGrIdString(){
		return String.valueOf(this.getGrId());
	}

	//-- METHODS -----------------------------------------------------------------------------------------
	
	
}
