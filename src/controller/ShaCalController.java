package controller;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
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
		if(model.AllPersons.containsKey(userName.trim())){
			return true;
		}
		return false;
	}
	
	public boolean alreadyExistGr(int grId){
		if(model.AllGroups.containsKey(grId)){
			return true;
		}
		return false;
	}
	public void display(String clndrOwner, String[] filter){
		ArrayList<Event> calList = new ArrayList<Event>(model.AllEvents.get(clndrOwner));
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
			grList.add(ShaCalModel.getGroup(i).getGrId() + " " + ShaCalModel.getGroup(i).getGrName());
		}
		return grList;
	}
	
	public ArrayList<String> getMembersOfGroup(int grId){
		return model.getGroup(grId).getMembers();
	}
	
	public ArrayList<Event> getEventPerMonth(int month, int gr){
		ArrayList<Event> eventPerMonth = new ArrayList<Event>();
		for (Event e : model.AllEvents.get(Integer.toString(gr))){
			if(e.getStartDate().getMonthValue() == month){
				eventPerMonth.add(e);
			} 
			return eventPerMonth;
		}
		return null;
	}
	
	public String sortEvent(ArrayList<Event> event){
		String s = "";
		for (Event e : event){
			s = "\t" + e.getStartDate() + " " +  e.getTitle() + "\n";
		}
		return s;
	}
} // fin class
