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
	
	private String arg;
	private String help = "This is the list of command. \n";
	
	private String prompt = LocalDate.now().toString() + "> ";
	private String prompt1 = LocalDate.now().toString() + " - " + currentUser + "> ";
	private String prompt2 = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
	private String prompt3 = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "$ ";
	private String prompt4 = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "# ";
	
	private String command = null;
	
	
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
			
			while(currentUser == null){
				//Login
				System.out.println(prompt + "Are you a new User (y/n)?");
				String newU = sc.next();
				if(newU.equals("y")){
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
				} else if (newU.equals("n")){
					System.out.println(prompt + "Login with your userName");
					currentUser = sc.next();
					while(!controller.alreadyExist(currentUser)){
						System.out.println(prompt + "this username doesn't exist : try again");
						currentUser = sc.next();
					}
				} else {
					System.out.println("please answer y or n");
				}
			}
			
			//transition///////////////////////////////////////////////////////
			workingGroup = currentUser;
			prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
			System.out.println(prompt + "Welcome " + currentUser);
			System.out.println(prompt + "Press help to get a list of all the command");
			
			//interaction//////////////////////////////////////////////////////
			while(true){
				command = sc.next();
				switch (command){
					case "help" : System.out.println(help);
						controller.help();
						System.out.println(prompt);
					break;
					
					case "group" : //System.out.println(prompt + "enter a group id");
						currentUserLevel = controller.getUserLevel(currentUser, Integer.parseInt(workingGroup)); //!\ à implémenter, return 0 pour l'instant
						arg = sc.next();
						//workingGroup = arg;
						if(arg != null){
							prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
							System.out.println(prompt);
							arg = null;
						};
						break;
						
					case "show" : System.out.println(prompt + "Your calendar: \n" );
						//System.out.println("Une liste d'évènements triées par mois...");
						controller.display(workingGroup, command);
						arg = sc.next();
						//controller.display(workingGroup, filter);
						if(arg != null){
							//System.out.println("Une liste d'évènements depending on a filter");
							controller.display(workingGroup, command);
							arg = null;
						};
						break;
					default  : System.out.println(prompt + "Unrecognized command");
				}
			
			}
		}
	}
}
