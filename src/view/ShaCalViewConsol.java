package view;

import java.util.Observer;
import java.util.Scanner;

import controller.ShaCalController;
import model.ShaCalModel;

public class ShaCalViewConsol extends ShaCalView implements Observer {
	protected Scanner sc;
	
	
	
	public ShaCalViewConsol(ShaCalModel model, ShaCalController controller) {
		super(model, controller);
		sc = new Scanner(System.in);
		new Thread(new ReadInput()).start();
	}
	
	
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true){
				//int newCTemp = sc.nextInt();
				//controller.fixeDegresC(newCTemp);
			}
		}
	}
}
