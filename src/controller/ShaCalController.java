package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Set;

import model.Event;
import model.Person;
import model.ShaCalModel;
import view.ShaCalView;
import view.ShaCalViewConsol;

public class ShaCalController {
	private ShaCalModel model;
	private ShaCalView view = null;
	
	
	public ShaCalController(ShaCalModel m) {
		model = m;
	}


	public void addView(ShaCalViewConsol view) {
		this.view = view;
	}
	
	/**
	 * This method create a new user. 
	 * @param n the name of the person, red from command line
	 * @param p the firstname of the person, red from command line
	 * @param u the username of the person , red from command line
	 * @param d the birthday of the person, red from command line
	 * @return 
	 */
	public Person newUser(String n, String p, String u, LocalDate d){
			Person pers = new Person(n, p, u, d); 
			model.addPersonToHashMap(pers);
			return pers;
	}
	/**
	 * this method check if a username already exist.
	 * @param s username too look for ( the key in the hashmap of ShaCalModel )
	 * @return
	 */
	public boolean alreadyExistP(String userName){
		if(model.allPersons.containsKey(userName.trim())){
			return true;
		}
		return false;
	}
	
	public boolean alreadyExistGr(int grId){
		if(model.allGroups.containsKey(grId)){
			return true;
		}
		return false;
	}
	public void display(String clndrOwner, String[] filter){
		ArrayList<Event> calList = new ArrayList<Event>(model.allEvents.get(clndrOwner));
		for (Event e : calList){
			System.out.println(e);
		}
	}


	public void help() {
		try {
		    BufferedReader reader =
		        new BufferedReader(new InputStreamReader(new FileInputStream("MAN_CONSOLE.txt")));
		    String line = "";
		    while((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    reader.close();
		} catch (IOException e) {
		    System.err.println("Erreur lors de la lecture du fichier");
		}
	}
	
	public int getUserLevel(String userName, int grId){
		return 0;
	}
	/**
	 * this method get all the group a person belong to.
	 * @param userName
	 * @return grId and the grName of the group of a person as an ArrayList 
	 */
	public ArrayList<String> getGroupsOfPerson(String userName){
		ArrayList<String> grList = new ArrayList<String>(); 
		Person tempP = model.getPerson(userName);
		Set<Integer> setOfGr = tempP.getGroup().keySet();
		for(int i = 0; i < setOfGr.size(); i++){
			grList.add(model.getGroup(i).getGrId() + " " + model.getGroup(i).getGrName());
		}
		return grList;
	}
	
	public ArrayList<String> getMembersOfGroup(int grId){
		return model.getGroup(grId).getMembers();
	}
	
	public ArrayList<Event> getEventsOfDay(int year, int month, int day, String filter){
		ArrayList<Event> eventsOfDay = new ArrayList<Event>();
		for (Event e : model.allEvents.get(filter)){
			if ((e.getStartDate().getDayOfMonth() == day) && (e.getStartDate().getMonthValue() == month) && (e.getStartDate().getYear() == year)){
				eventsOfDay.add(e);
			}
		}
		return eventsOfDay;
	}
	public ArrayList<Event> getEventsOfMonth(int year, int month, String filter){
		ArrayList<Event> eventsOfMonth = new ArrayList<Event>();
		for (Event e : model.allEvents.get(filter)){
			if (e.getStartDate().getMonthValue() == month && e.getStartDate().getYear() == year ){
				eventsOfMonth.add(e);
			}
		}
		return eventsOfMonth;
	}
	
	public String EventToStringBrief(ArrayList<Event> E){
		String eventBrief = "";
		for (Event e : E){
			eventBrief += e.getStartHour()+ "\t" + e.getTitle() + " " + e.getDescription() + "\n  " + e.getEndHour() + "\n";
		}
		return eventBrief;
	}
	
	public String EventToStringDetail(){
		return "not yet implemented";
	}
} // fin class
