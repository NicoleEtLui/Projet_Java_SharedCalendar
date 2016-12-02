package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Observable;

public class ShaCalModel extends Observable {
	public HashMap<String, Person> persList = new HashMap<String, Person>();
}
