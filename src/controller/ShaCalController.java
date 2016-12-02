package controller;

import java.time.LocalDate;

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
}
