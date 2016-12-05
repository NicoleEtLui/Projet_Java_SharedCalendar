package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class ShaCalModel extends Observable {
	public HashMap<String, Person> persList = new HashMap<String, Person>();
	public HashMap<String, Group> groupList = new HashMap<String, Group>();
	public HashMap<String, ArrayList<Event>> calendarList = new HashMap<String, ArrayList<Event>>();
	
	public void addPerson(String s, Person p){
		persList.putIfAbsent(p.getUserName(), p);
	}
	
	public void addEvent(String s, Event e){
		ArrayList<Event> temp = calendarList.get(s);
		temp.add(e);
		calendarList.replace(s, temp);
	}
	
}
