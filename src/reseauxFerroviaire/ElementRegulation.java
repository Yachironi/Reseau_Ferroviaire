package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class ElementRegulation implements Observer {

	ArrayList<Capteur> capteurs;
	Aiguillage aiguillage;

	public ElementRegulation(Aiguillage aiguillage) {
		this.aiguillage = aiguillage;
	}

	public ElementRegulation() {
		this.aiguillage = null;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
		Capteur capteur = (Capteur) arg;

		if (!aiguillage.getListeRail().contains(capteur)) {
			System.out
					.println("Le capteur n'appartient pas à l'aguillage de l'element de regulation");

		} else {
			demandePassage(capteur.getMonRail());
		}
	}
	protected abstract void demandePassage(Rail rail);
	protected abstract void regulation(Rail rail);

}
