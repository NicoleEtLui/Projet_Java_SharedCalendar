package view;

import java.time.LocalDate;
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
	private String workingGroup = null;
	
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
			Group gr = new Group("monGroupe", pe);
			model.addPerson(pe.getUserName(), pe);
			Event ev = new Event("MyPersEvent", "Mydescription", LocalDate.of(2016, 12, 04), LocalDate.of(2016, 12, 04));
			Event ev2 = new Event("MyGroupEvent", "Mydescription", LocalDate.of(2016, 12, 04), LocalDate.of(2016, 12, 04));
			model.addCalendar(pe.getUserName());
			model.addEvent(pe.getUserName(), ev);
			model.addCalendar(Integer.toString(gr.getGrId()));
			model.addEvent(Integer.toString(gr.getGrId()), ev2);
			
			
			//Login
			while(true){
				sc.useDelimiter("\n");
				commandLine = sc.next().split(" ");
				for (int i = 0; i < commandLine.length; i++){
					commandLine[i] = commandLine[i].trim();
				}
				
				switch(commandLine[0]){
					case "login": 
						if(commandLine.length == 2 ){
							currentUser = commandLine[1];
							if(!controller.alreadyExist(currentUser)){
								System.out.println("This account doesn't exist");
							}
							prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
							System.out.println(prompt + "Welcome " + currentUser);
						} else {
							System.out.println("wrong number of argument");
						}
						break;
					case "new": 
						if(commandLine.length == 1 ){
							System.out.println(prompt + "Welcome, let's create your account !\nWhat's your name ?");
							String n = sc.next();
							System.out.println(prompt + "What's your firstname ?");
							String p = sc.next();
							System.out.println(prompt + "What's your unique username ?");
							currentUser = sc.next();
							while(controller.alreadyExist(currentUser)){
								System.out.println(prompt + "this username already exist");
								System.out.println(prompt + "What's your unique username ?");
								currentUser = sc.next();
							}
							System.out.println(prompt + "What's your birthday ? as yyyy-mm-dd");
							LocalDate d = LocalDate.parse(sc.next());
							controller.newUser(n, p, currentUser, d);
						} else {
							
							String n = commandLine[1];
							String p = commandLine[2];
							currentUser = commandLine[3];
							LocalDate d = LocalDate.parse(commandLine[4]);
							while (controller.alreadyExist(currentUser)){
								System.out.println(prompt + "this username already exist, type a unique username");
								currentUser = sc.next().trim();
							}
							controller.newUser(n, p, currentUser, d);
						}
						prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
						System.out.println(prompt + "Welcome " + currentUser);
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
					case "help" : 
						System.out.println(help);
						controller.help();
						System.out.println(prompt);
					break;
					
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
