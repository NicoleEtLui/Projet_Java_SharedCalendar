package controller;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
	 * @return true if it succeeded, false otherwise. It won't succeed if username already exist.
	 */
	public Person newUser(String n, String p, String u, LocalDate d){
		Person pers = new Person(n, p, u, d); 
		model.addPerson(pers.getUserName(), pers);
		return pers;
	}
	/**
	 * this method check if a username already exist.
	 * @param s username too look for ( the key in the hashmap of ShaCalModel )
	 * @return
	 */
	public boolean alreadyExist(String userName){
		if(model.persList.containsKey(userName)){
			return true;
		}
		return false;
	}
	
	public void display(String clndrOwner, String filter){
		ArrayList<Event> calList = new ArrayList<Event>(model.calendarList.get(clndrOwner));
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
	
}
