package view;

import java.util.Observer;
import java.util.Scanner;

import controller.ShaCalController;
import model.ShaCalModel;

public class ShaCalViewConsol extends ShaCalView implements Observer {
	protected Scanner sc;
	
	
	
	public ShaCalViewConsol(ShaCalModel model, ShaCalController controller) {
		super(model, controller);
	}
}
