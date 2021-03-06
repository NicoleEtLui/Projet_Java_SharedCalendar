package test;

import DAO.EventDAO;
import DAO.GroupDAO;
import DAO.PersonDAO;
import DAO.Singleton;
import controller.ShaCalController;
import model.ShaCalModel;
import view.ShaCalViewConsol;

public class SharedCalendar {
	public SharedCalendar(){
		//cr�ation du mod�le
		ShaCalModel shacalmod = new ShaCalModel();
		PersonDAO pDAO = new PersonDAO(Singleton.getInstance());
		pDAO.findAll();
		GroupDAO gDAO = new GroupDAO(Singleton.getInstance());
		gDAO.findAll();
		gDAO.findMax();
		EventDAO eDAO = new EventDAO(Singleton.getInstance());
		eDAO.findAll();
		//cr�ation des controlleurs un pour chaque vue
		//chaque controlleur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander
		ShaCalController shacalcontrolConsol = new ShaCalController(shacalmod);
		//Cr�ation des vues
		//chaque vue doit avoir une r�f�rence a son controleur et avoir une r�f�rence vers le mod�le pour pouvoir l'observer
		ShaCalViewConsol console = new ShaCalViewConsol(shacalmod, shacalcontrolConsol);
		// on donne la r�f�rence � la vue pour chaque controlleur
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
