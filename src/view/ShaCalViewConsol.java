package view;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Observer;
import java.util.Scanner;

import controller.ShaCalController;
import model.Event;
import model.Group;
import model.Person;
import model.ShaCalModel;

public class ShaCalViewConsol extends ShaCalView implements Observer {
	protected Scanner sc;
	
	private String currentUser;
	private int currentUserLevel;
	private Integer workingGroup = null;
	
	private String arg = null;
	private String help = "This is the list of command. \n";
	
	private String prompt = LocalDate.now().toString() + "> ";
	private String prompt1 = LocalDate.now().toString() + " - " + currentUser + "> ";
	private String prompt2 = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
	private String prompt3 = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "$ ";
	private String prompt4 = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "# ";
	
	private int currentYear = LocalDate.now().getYear();
	private int currentMonth = LocalDate.now().getMonthValue();
	private int currentDay = LocalDate.now().getDayOfMonth();
	private Locale locale = new Locale("en");
	
	private String commandLine[];
	private String filter = null;
	
	public String getCurrentUser() {
		return currentUser;
	}

	public int getCurrentUserLevel() {
		return currentUserLevel;
	}

	public Integer getWorkingGroup() {
		return workingGroup;
	}

	public ShaCalViewConsol(ShaCalModel model, ShaCalController controller) {
		super(model, controller);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	
	
	
	private class ReadInput implements Runnable{
		public void run() {
			//temp: simulation de contenu existant
			Person pe = new Person("Petit", "Martin", "N", LocalDate.of(1994, 9, 28));
			Group gr = new Group("monGroupe", pe.getUserName());
			Group gr1 = new Group("monGroupe1");
			model.addPersonToHashMap(pe);
			model.addGroupToHashMap(gr);
			model.addGroupToHashMap(gr1);
			model.addLink(pe.getUserName(), gr.getGrId());
			Event ev = new Event("MyPersEvent", "Mydescription", "Ma maison", LocalDate.now(), LocalDate.now(), "11:00:00", "12:00:00");
			Event ev1 = new Event("MyPersEvent1", "Mydescription", "Ma maison", LocalDate.now(), LocalDate.now(), "12:15:00", "13:00:00");
			Event ev2 = new Event("MyGroup&PersEvent", "Mydescription", "Dans la rue",  LocalDate.of(2017, 9, 04), LocalDate.of(2017, 9, 04), "10:15:00", "19:00:00");
			Event ev3 = new Event("MyGroup&PersEvent1", "Mydescription", "Ma maison", LocalDate.now(), LocalDate.now(), "17:15:00", "18:00:00");
			model.addEvent(pe.getUserName(), ev);
			model.addEvent(pe.getUserName(), ev2);
			model.addEvent(pe.getUserName(), ev1);
			model.addEvent(Integer.toString(gr.getGrId()), ev2);
			model.addEvent(Integer.toString(gr.getGrId()), ev3);
			
			/*System.out.println(model.allEvents.toString());
			System.out.println(model.allGroups.toString());
			System.out.println(model.allPersons.toString());*/
			
			
			//Login
			while(true){
				sc.useDelimiter("\n");
				commandLine = sc.next().split(" ");
				int clLength = commandLine.length;
				for (int i = 0; i < clLength; i++){
					commandLine[i] = commandLine[i].trim();
				}
				
				switch(commandLine[0]) {
					///////////////////////////////////////////////////////////
					case "login": 
						if(clLength == 2 ) {
							currentUser = commandLine[1];
							if(!controller.alreadyExistP(currentUser)) {
								System.out.println("This account doesn't exist");
								currentUser = null;
							} else {
								prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
								System.out.println(prompt + "Welcome " + currentUser);
								filter = currentUser;
							}
						} else if (clLength == 3) {
							currentUser = commandLine[1];
							if(!controller.alreadyExistP(currentUser)) {
								System.out.println("This account doesn't exist");
							} else {
							workingGroup = Integer.parseInt(commandLine[2]);
								if(!controller.alreadyExistGr(workingGroup)) {
									System.out.println("This group doesn't exist");
									workingGroup = null;
								} else if (!controller.getMembersOfGroup(workingGroup).contains(currentUser)){
										System.out.println("You don't have access to this group");
										workingGroup = null;
								} else {
									prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
									System.out.println(prompt + "Welcome " + currentUser);
									filter = workingGroup.toString();
								}
							}
						} else {
							System.out.println(prompt + "Wrong number of argument, type help to get a list of command.");
						}
						
						break;
                    ///////////////////////////////////////////////////////////
					case "new": 
						if(clLength == 1 ){
							System.out.println(prompt + "Welcome, let's create your account !\nWhat's your name ?");
							String n = sc.next();
							System.out.println(prompt + "What's your firstname ?");
							String p = sc.next();
							System.out.println(prompt + "What's your unique username ?");
							currentUser = sc.next().trim();
							while(controller.alreadyExistP(currentUser)){
								System.out.println(prompt + "this username already exist");
								System.out.println(prompt + "What's your unique username ?");
								currentUser = sc.next().trim();
							}
							System.out.println(prompt + "What's your birthday ? as yyyy-mm-dd");
							LocalDate d = LocalDate.parse(sc.next().trim());
							controller.newUser(n, p, currentUser, d);
						} else {
							
							String n = commandLine[1];
							String p = commandLine[2];
							currentUser = commandLine[3];
							LocalDate d = LocalDate.parse(commandLine[4]);
							while (controller.alreadyExistP(currentUser)){
								System.out.println(prompt + "this username already exist, type a unique username");
								currentUser = sc.next().trim();
							}
							controller.newUser(n, p, currentUser, d);
						}
						prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
						System.out.println(prompt + "Welcome " + currentUser);
						filter = currentUser;
						break;
					///////////////////////////////////////////////////////////
					case "group" :
						if (currentUser == null){
							System.out.println("You are not allowed to run this command in this mode");
						} else if (clLength > 2){
							System.out.println("Wrong number of arg, type help to get a list of command");
						} else if (clLength == 1){
							System.out.println(controller.getGroupsOfPerson(currentUser).toString());
						} else if (clLength == 2){
							workingGroup = Integer.parseInt(commandLine[1]);
							if(!controller.alreadyExistGr(workingGroup)) {
								System.out.println("This group doesn't exist");
								workingGroup = null;
							} else if (!controller.getMembersOfGroup(workingGroup).contains(currentUser)){
								System.out.println("You don't have access to this group");
								workingGroup = null;
							} else {
							prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
							System.out.println(prompt + "Welcome in group " + workingGroup);
							filter = workingGroup.toString();
							}
						}
						break;
					///////////////////////////////////////////////////////////
					case "show" : 
						if (currentUser == null){
							System.out.println("You are not allowed to run this command in this mode");
						} else if (clLength == 1){
							System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, currentMonth, currentDay, filter)));
						} else {
							switch(commandLine[1]){
							case "month" :
								if(clLength == 2){ // show month
									LocalDate workingDate = LocalDate.of(currentYear, currentMonth, 1);
									Month workingMonth = Month.of(currentMonth);
									int nbDaysOfMonth = workingMonth.length(workingDate.isLeapYear());
									for (int i = 1; i <= nbDaysOfMonth ; i++){
										if(!controller.getEventsOfDay(currentYear,currentMonth,  i, filter).isEmpty()){
											System.out.println("DAY " + i );
											System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, currentMonth, i, filter)));
										} 
									}
								} else if (clLength == 3){ // show month [month]
									LocalDate workingDate = LocalDate.of(currentYear, 1, 1);
									Month workingMonth = Month.of(Integer.parseInt(commandLine[2]));
									int nbDaysOfMonth = workingMonth.length(workingDate.isLeapYear());
									for (int i = 1; i <= nbDaysOfMonth; i++){
										if(!controller.getEventsOfDay(currentYear,Integer.parseInt(commandLine[2]),  i, filter).isEmpty()){
											System.out.println("DAY " + i);
											System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, Integer.parseInt(commandLine[2]), i, filter)));
										} 
									}
								} else if (clLength == 4){//show month [month] [year]
									LocalDate workingDate = LocalDate.of(Integer.parseInt(commandLine[3]), 1, 1);
									Month workingMonth = Month.of(Integer.parseInt(commandLine[2]));
									int nbDaysOfMonth = workingMonth.length(workingDate.isLeapYear());
									for (int i = 1; i <= nbDaysOfMonth; i++){
										if(!controller.getEventsOfDay(Integer.parseInt(commandLine[3]), Integer.parseInt(commandLine[2]),  i, filter).isEmpty()){
											System.out.println("DAY " + i );
											System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(Integer.parseInt(commandLine[3]), Integer.parseInt(commandLine[2]), i, filter)));
										}
									}
								} else {
									System.out.println("Wrong number of arguments");
								}
								break;
							case "year" : 
								if(clLength == 2){// show year
									for (int i = 1; i <= 12; i++){
										if(!controller.getEventsOfMonth(currentYear, i, filter).isEmpty()){
											System.out.println(Month.of(i));
											System.out.println(controller.getEventsOfMonth(currentYear, i, filter).size() + " events");
										} 
									}
								} else if (clLength == 3){ // show year [year]
									System.out.println("You typed show year" + commandLine[2]);
									for (int i = 1; i <= 12; i++){
										if(!controller.getEventsOfMonth(Integer.parseInt(commandLine[2]), i, filter).isEmpty()){
											System.out.println(Month.of(i));
											System.out.println(controller.getEventsOfMonth(Integer.parseInt(commandLine[2]), i, filter).size() + " events");
										} 
									}
								}  else {
									System.out.println("Wrong number of arguments");
								}
								break;
							default: 
								if(clLength == 2){//show [day]
									System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, currentMonth, Integer.parseInt(commandLine[1]), filter)));
								} else if (clLength == 3){//show [day] [month]
									System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, Integer.parseInt(commandLine[2]), Integer.parseInt(commandLine[1]), filter)));
								} else if (clLength == 4){//show [day] [month] [year]
									System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(Integer.parseInt(commandLine[3]), Integer.parseInt(commandLine[2]), Integer.parseInt(commandLine[1]), filter)));
								} else {
									System.out.println("wrong number of arguments");
								}
							}
						}
						break;
					///////////////////////////////////////////////////////////
					case "help" : 
						System.out.println(help);
						controller.help();
						System.out.println(prompt);
						break;
					default: System.out.println("Type help to get a list of command");
				}
			}
		}
	}
}
