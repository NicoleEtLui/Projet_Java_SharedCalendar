package view;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observer;
import java.util.Scanner;

import controller.ShaCalController;
import model.ShaCalModel;

public class ShaCalViewConsol extends ShaCalView implements Observer {
	protected Scanner sc;
	
	private String currentUser;
	private int currentUserLevel = 0;
	private Integer workingGroup = null;
	private boolean admin = false;
	private boolean sadmin = false;
	
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
	
	
	
	
	private class ReadInput implements Runnable {
		public void run() {
			
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
								System.out.println(prompt + "This account doesn't exist");
								currentUser = null;
							} else {
								prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
								System.out.println(prompt + "Welcome " + currentUser);
								filter = currentUser;
								currentUserLevel = 0;
								workingGroup = null;
								admin = false;
								sadmin = false;
							}
						} else if (clLength == 3) {
							currentUser = commandLine[1];
							if(!controller.alreadyExistP(currentUser)) {
								System.out.println(prompt + "This account doesn't exist");
							} else {
							workingGroup = Integer.parseInt(commandLine[2]);
								if(!controller.alreadyExistGr(workingGroup)) {
									System.out.println(prompt + "This group doesn't exist");
									workingGroup = null;
								} else if (!controller.getMembersOfGroup(workingGroup).contains(currentUser)){
										System.out.println(prompt + "You don't have access to this group");
										workingGroup = null;
								} else {
									prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
									System.out.println(prompt + "Welcome " + currentUser);
									filter = workingGroup.toString();
									currentUserLevel = controller.getUserPermission(currentUser, workingGroup);
									admin = false;
									sadmin = false;
								}
							}
						} else {
							System.out.println(prompt + "Wrong number of argument, type help to get a list of command.");
						}
						
						break;
                    ///////////////////////////////////////////////////////////
					case "new": 
						if(clLength == 1 ) {
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
							try {
								LocalDate d = LocalDate.parse(sc.next().trim());
								controller.newUser(n, p, currentUser, d);
								prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
								System.out.println(prompt + "Welcome " + currentUser);
							} catch (DateTimeParseException e){
								System.out.println(prompt + "Wrong date format try NEW again");
							}
							workingGroup = null;
							filter = currentUser;
							currentUserLevel = 0;
							filter = currentUser;
							admin = false;
							sadmin = false;
						} else {
							try{
								String n = commandLine[1];
								String p = commandLine[2];
								currentUser = commandLine[3];
								LocalDate d = LocalDate.parse(commandLine[4]);
								while (controller.alreadyExistP(currentUser)){
									System.out.println(prompt + "this username already exist, type a unique username");
									currentUser = sc.next().trim();
								}
								controller.newUser(n, p, currentUser, d);
								prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
								System.out.println(prompt + "Welcome " + currentUser);
								filter = currentUser;
								workingGroup = null;
								currentUserLevel = 0;
								admin = false;
								sadmin = false;
							}
							catch(Exception e){
								System.out.println(prompt + "Wrong number of arguments");
							}
						}
						break;
					///////////////////////////////////////////////////////////
					case "group" :
						if (currentUser == null){
							System.out.println(prompt + "You are not allowed to run this command in this mode");
						} else if (clLength > 2){
							System.out.println(prompt + "Wrong number of arg, type help to get a list of command");
						} else if (clLength == 1){
							System.out.println(controller.getGroupsOfPerson(currentUser).toString());
						} else if (clLength == 2){
							workingGroup = Integer.parseInt(commandLine[1]);
							if(!controller.alreadyExistGr(workingGroup)) {
								System.out.println(prompt + "This group doesn't exist");
								workingGroup = null;
							} else if (!controller.getMembersOfGroup(workingGroup).contains(currentUser)){
								System.out.println(prompt + "You don't have access to this group");
								workingGroup = null;
							} else {
							prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "> ";
							System.out.println(prompt + "Welcome in group " + workingGroup);
							filter = workingGroup.toString();
							currentUserLevel = controller.getUserPermission(currentUser, workingGroup);
							admin = false;
							sadmin = false;
							}
						}
						break;
					///////////////////////////////////////////////////////////
					case "show" : 
						if (currentUser == null){
							System.out.println(prompt + "You are not allowed to run this command in this mode");
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
									System.out.println(prompt + "Wrong number of arguments");
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
									for (int i = 1; i <= 12; i++){
										if(!controller.getEventsOfMonth(Integer.parseInt(commandLine[2]), i, filter).isEmpty()){
											System.out.println(Month.of(i));
											System.out.println(controller.getEventsOfMonth(Integer.parseInt(commandLine[2]), i, filter).size() + " events");
										} 
									}
								}  else {
									System.out.println(prompt + "Wrong number of arguments");
								}
								break;
							default: 
								try {
									if(clLength == 2){//show [day]
										System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, currentMonth, Integer.parseInt(commandLine[1]), filter)));
									} else if (clLength == 3){//show [day] [month]
										System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(currentYear, Integer.parseInt(commandLine[2]), Integer.parseInt(commandLine[1]), filter)));
									} else if (clLength == 4){//show [day] [month] [year]
										System.out.println(controller.EventToStringBrief(controller.getEventsOfDay(Integer.parseInt(commandLine[3]), Integer.parseInt(commandLine[2]), Integer.parseInt(commandLine[1]), filter)));
									} else {
										System.out.println(prompt + "wrong number of arguments");
									}
								} catch(NumberFormatException e){
									System.out.println("Wrong type of arguments");
								}
							}
						}
						break;
					///////////////////////////////////////////////////////////
					case "admin" : 
						if (currentUserLevel >= 1){
							admin = true;
							prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "$ ";
							System.out.println(prompt + "Welcome in admin mode");
						} else {
							System.out.println(prompt + "You don't have the permission to run this command in that group");
						}
						break;
					///////////////////////////////////////////////////////////
					case "sadmin" : 
						if (currentUserLevel == 2){
							sadmin = true;
							prompt = LocalDate.now().toString() + " - " + currentUser + " - " + workingGroup + "# ";
							System.out.println(prompt + "Welcome in super admin mode");
						} else {
							System.out.println(prompt + "You don't have the permission to run this command in that group");
						}
						break;
					///////////////////////////////////////////////////////////
					case "add" : 
						if (admin){ 
							if(clLength == 3){
								switch(commandLine[1]){
									case "member" : 
										model.addMemberToGroup(commandLine[2], workingGroup);
									case "event" :
										model.addEvent(workingGroup.toString(), model.getSingleEvent(commandLine[2]));
								}
							} else {
								System.out.println(prompt + "Wrong number of arguments");
							}
						} else {
							System.out.println(prompt + "You don't have the permission to run this command");
						}
						break;
					case "create" : 
						if (clLength == 1) {
							System.out.println("Lack of arguments try with event or group");
						} else {
							switch(commandLine[1]){
								case "event" : 
									System.out.println("Entering event creation protocol");
									System.out.println("What is the title of your event ?");
									String t = sc.next().trim();
									System.out.println("Give a description of your event. Press enter to finish");
									String d = sc.next().trim();
									System.out.println("Where will it take part ?");
									String l = sc.next().trim();
									LocalDate sD = null;
									LocalDate eD = null;
									String sH = null;
									String eH = null;
									boolean good=true;
									while(good){
										try{
											System.out.println("When will it start ? as yyyy-mm-dd");
											sD = LocalDate.parse(sc.next().trim());
											System.out.println("when will it end ? as yyyy-mm-dd");
											eD = LocalDate.parse(sc.next().trim());
											System.out.println("At which hour will it start ? as hh:mm");
											sH = sc.next().trim();
											System.out.println("At wich hour will it end ? as hh:mm");
											eH = sc.next().trim();
											if (admin){
												controller.newEvent(t, d, l, sD, eD, sH, eH, workingGroup.toString());
												System.out.println(prompt + "You have created a group event");
											}else {
												controller.newEvent(t, d, l, sD, eD, sH, eH, currentUser);
												System.out.println(prompt + "You have create a personal event");
											}
											good = false;
										}catch(Exception e){
											System.out.println("Wrong format. Try again.");
										}
									}
									break;
								case "group" :
									String n = "";
									if(clLength == 1){
										System.out.println(prompt + "Entering group creation protocol");
										System.out.println(prompt + "What is the name of your group ?");
										n = sc.next().trim();
									} else if (clLength == 3){
										n = commandLine[2].trim();
									} else {
										System.out.println(prompt + "Wrong number of arguments");
									}
									controller.newGroup(n, currentUser);
									System.out.println(prompt + "You have created a group, you're the sadmin of this group");
									
									break;
								default : 
									System.out.println(prompt + "wrong command, try again");
							}
						}
						break;
					case "delete" : 
						if (admin){ 
							if(clLength == 3){
								switch(commandLine[1]){
									case "member" : 
										model.deleteMemberFromGroup(commandLine[2], workingGroup);
									case "event" :
										model.removeEvent(workingGroup.toString(), model.getSingleEvent(commandLine[2]));
								}
							} else {
								System.out.println(prompt + "Wrong number of arguments");
							}
						} else if (sadmin) {
							if(clLength == 3){
								switch(commandLine[1]){
									case "member" : 
										model.deleteMemberFromGroup(commandLine[2], workingGroup);
									case "event" :
										model.removeEvent(workingGroup.toString(), model.getSingleEvent(commandLine[2]));
									case "group" :
										model.deleteGroupFromHashMap(workingGroup);
										prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
										filter = currentUser;
										currentUserLevel = 0;
										admin = false;
										sadmin = false;
										
								}
							} else {
								System.out.println(prompt + "Wrong number of arguments");
							}
						} else {
							System.out.println(prompt + "You don't have the permission to run this command");
						}
						break;
					case "permission" : 
						if (clLength == 3){
							model.changePermission(commandLine[2], workingGroup, Integer.parseInt(commandLine[3]));
						} else {
							System.out.println(prompt + "Wrong number of arguments");
						}
						break;
					case "exit" : 
						prompt = LocalDate.now().toString() + " - " + currentUser + "> ";
						System.out.println(prompt + "Welcome " + currentUser);
						filter = currentUser;
						workingGroup = null;
						currentUserLevel = 0;
						admin = false;
						sadmin = false;
					case "help" : 
						System.out.println(help);
						controller.help();
						System.out.println(prompt);
						break;
					default: System.out.println(prompt + "Type help to get a list of command");
				}
			}
		}
	}
}
