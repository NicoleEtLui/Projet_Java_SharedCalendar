package view;

import java.time.LocalDate;
import java.util.Observer;
import java.util.Scanner;

import controller.ShaCalController;
import model.Person;
import model.ShaCalModel;

public class ShaCalViewConsol extends ShaCalView implements Observer {
	protected Scanner sc;
	private String currentUser;
	private String help = "This is the list of command. \n"
							+ "help - get the list of command";
	private String prompt = LocalDate.now().toString() + "> ";
	private String command = "";
	
	
	public ShaCalViewConsol(ShaCalModel model, ShaCalController controller) {
		super(model, controller);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true){
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
					Person nU = controller.newUser(n, p, currentUser, d);
					System.out.println(prompt + "Welcome " + nU + "\nalias " + currentUser);
				} else {
					System.out.println(prompt + "Login with your userName");
					while(!controller.alreadyExist(sc.next())){
						System.out.println(prompt + "this username doesn't exist");
					}
					currentUser = sc.next();
					System.out.println(prompt + "Welcome " + currentUser);
				}
				System.out.println("Press help to get a list of all the command");
				switch (command){
					case "help" : System.out.println(help);
					break;
					default  : System.out.println("Unrecognized command");
				}
			}
		}
	}
}
