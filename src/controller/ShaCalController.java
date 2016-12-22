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
import model.Group;
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
	
	public Event newEvent(String t, String d, String l, LocalDate sD, LocalDate eD, String sH, String eH, String c){
		Event e = new Event(t, d, l, sD, eD, sH, eH, c);
		model.addEvent(c, e);
		return e;
	}
	
	public Group newGroup(String n, String p){
		Group gr = new Group(n, p);
		model.addGroupToHashMap(gr);
		model.addGroupToPerson(p, gr.getGrId(), 2);
		return gr;
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
	
	/**
	 * this method get all the group a person belong to.
	 * @param userName
	 * @return grId and the grName of the group of a person as an ArrayList 
	 */
	public ArrayList<String> getGroupsOfPerson(String userName){
		ArrayList<String> grList = new ArrayList<String>(); 
		try {
			Person tempP = model.getPerson(userName);
			for(int i = 0; i < tempP.getGroup().keySet().toArray().length; i++){
				grList.add(tempP.getGroup().keySet().toArray()[i] + " " + model.getGroup(tempP.getGroup().keySet().toArray()[i]).getGrName());
			}
		} catch (Exception e) {
		}
		return grList;
		
	}
	
	public ArrayList<String> getMembersOfGroup(int grId){
		return model.getGroup(grId).getMembers();
	}
	
	public ArrayList<Event> getEventsOfDay(int year, int month, int day, String filter){
		ArrayList<Event> eventsOfDay = new ArrayList<Event>();
		try {
			for (Event e : model.allEvents.get(filter)){
				if ((e.getStartDate().getDayOfMonth() == day) && (e.getStartDate().getMonthValue() == month) && (e.getStartDate().getYear() == year)){
					eventsOfDay.add(e);
				}
			}
				Person tempP = model.getPerson(filter);
				if(tempP != null){
					System.out.println(tempP.getGroup().keySet());
					for(Integer i : tempP.getGroup().keySet()){
						for (Event ev : model.allEvents.get(Integer.toString(i))){
							if ((ev.getStartDate().getDayOfMonth() == day) && (ev.getStartDate().getMonthValue() == month) && (ev.getStartDate().getYear() == year)){
								eventsOfDay.add(ev);
							}
						}
					}
				}
		} catch(Exception e){
			return null;
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
		try {
			for (Event e : E){
				eventBrief += e.getCreator() + "\n" + e.getStartHour()+ "\t" + e.getTitle() + " " + e.getDescription() + "\n  " + e.getEndHour() + "\n";
			}
		} catch(NullPointerException e){
			eventBrief = "No event yet.";
		}
		return eventBrief;
	}
	
	public String EventToStringDetail(){
		return "not yet implemented";
	}
	
	public int getUserPermission(String username, int grId){
		Person tempPers = model.allPersons.get(username);
		return tempPers.getPermission(grId);
	}
} // fin class
