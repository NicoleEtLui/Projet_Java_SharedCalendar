package test;

import controller.ShaCalController;
import model.ShaCalModel;
import view.ShaCalViewConsol;

public class SharedCalendar {
	public SharedCalendar(){
		//création du modèle
		ShaCalModel shacalmod = new ShaCalModel();
		//création des controlleurs un pour chaque vue
		//chaque controlleur doit avoir une référence vers le modèle pour pouvoir le commander
		ShaCalController shacalcontrolConsol = new ShaCalController(shacalmod);
		
		//Création des vues
		//chaque vue doit avoir une référence a son controleur et avoir une référence vers le modèle pour pouvoir l'observer
		ShaCalViewConsol console = new ShaCalViewConsol(shacalmod, shacalcontrolConsol);
		// on donne la référence à la vue pour chaque controlleur
		shacalcontrolConsol.addView(console);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SharedCalendar();
			}
		});
	}
}
