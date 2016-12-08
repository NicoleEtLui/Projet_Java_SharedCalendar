package view;

import java.time.LocalDate;
import java.util.HashMap;
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
	
	private String commandLine[];
	
	
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
			model.addLink(pe.getUserName(), gr.getGrId());
			Event ev = new Event("MyPersEvent", "Mydescription", LocalDate.of(2016, 12, 04), LocalDate.of(2016, 12, 04));
			Event ev2 = new Event("MyGroupEvent", "Mydescription", LocalDate.of(2016, 9, 04), LocalDate.of(2016, 12, 04));
			model.addEvent(pe.getUserName(), ev);
			model.addEvent(Integer.toString(gr.getGrId()), ev2);
			
			
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
							}
						}
						break;
					///////////////////////////////////////////////////////////
					/*case "show" : 
						if (currentUser == null){
							System.out.println("You are not allowed to run this command in this mode");
						} else if (clLength == 1){
							System.out.println("JANVIER");
							controller.getEventPerMonth(1, workingGroup);
							System.out.println("FEVRIER");
							controller.getEventPerMonth(2, workingGroup);
							System.out.println("MARS");
							controller.getEventPerMonth(3, workingGroup);
							System.out.println("AVRIL");
							controller.getEventPerMonth(4, workingGroup);
							System.out.println("MAI");
							controller.getEventPerMonth(5, workingGroup);
							System.out.println("JUIN");
							controller.getEventPerMonth(6, workingGroup);
							System.out.println("JUILLET");
							controller.getEventPerMonth(7, workingGroup);
							System.out.println("AOUT");
							controller.getEventPerMonth(8, workingGroup);
							System.out.println("SEPTEMBRE");
							controller.getEventPerMonth(9, workingGroup);
							System.out.println(controller.getEventPerYear(LocalDate.now().getYear(), workingGroup));
							System.out.println("OCTOBRE");
							controller.getEventPerMonth(10, workingGroup);
							System.out.println("NOVEMBRE");
							controller.getEventPerMonth(11, workingGroup);
							System.out.println("DECEMBRE ----");
							System.out.println(controller.getEventPerYear(LocalDate.now().getYear(), workingGroup));
							//System.out.println(controller.getEventPerMonth(12, workingGroup).toString());
						} 
						break;*/
					///////////////////////////////////////////////////////////
					case "help" : 
						System.out.println(help);
						controller.help();
						System.out.println(prompt);
						break;
					default: System.out.println("Type help to get a list of command");
				}
			}
			
			/*
			//transition///////////////////////////////////////////////////////
			workingGroup = currentUser;
			prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
			System.out.println(prompt + "Welcome " + currentUser);
			System.out.println(prompt + "Press help to get a list of all the command");
			
			//interaction//////////////////////////////////////////////////////
			while(true){
				commandLine = sc.next().split(" ");
				switch (commandLine[0]){
					
					case "group" :
							System.out.println("Vous avez demandé la commande groupe ? Veuillez patienter ...");
						break;
						
					case "show" : System.out.println(prompt + "Your calendar: \n" );
						controller.display(workingGroup, commandLine);
						arg = sc.next();
						if(arg != null){
							System.out.println("Une liste d'évènements depending on a filter");
							controller.display(workingGroup, commandLine);
							arg = null;
						};
						break;
					default  : System.out.println(prompt + "Unrecognized command");
				}
			
			}*/
		}
	}
}
