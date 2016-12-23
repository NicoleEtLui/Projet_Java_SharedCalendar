package view;

import java.util.Observable;
import java.util.Observer;

import controller.ShaCalController;
import model.ShaCalModel;

public class ShaCalView implements Observer {
	
	protected ShaCalModel model;
	protected ShaCalController controller;
	
	public ShaCalView(ShaCalModel model, ShaCalController controller){
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
