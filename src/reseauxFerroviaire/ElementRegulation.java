package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import exception.ElementRegulationException;

public class ElementRegulation implements Observer {

	ArrayList<Capteur> capteurs;
	Aiguillage aiguillage;

	
	public ElementRegulation(Aiguillage aiguillage) {
		super();
		this.aiguillage = aiguillage;
		
	}

	@Override
	public void update(Observable o, Object arg){
		// TODO Stub de la méthode généré automatiquement
		Capteur capteur = (Capteur) arg;
		if (!aiguillage.getListeRail().contains(capteur)) {
			try {
				throw new ElementRegulationException("Le capteur n'appartient pas à l'aguillage de l'element de regulation");
			} catch (ElementRegulationException e) {
				System.out.println(e.getMessage());
			}
		} else {
			regulation();
		}
	}

	private void regulation() {
		
	}

}
