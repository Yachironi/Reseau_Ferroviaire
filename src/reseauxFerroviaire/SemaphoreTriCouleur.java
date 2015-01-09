package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Arrays;

import exception.EtatSemaphoreException;

public class SemaphoreTriCouleur extends Semaphore {

	public SemaphoreTriCouleur() {
		super();
		etatsPossibles = new ArrayList<EtatSemaphore>(Arrays.asList(
				EtatSemaphoreRouge.getInstance(),
				EtatSemaphoreVert.getInstance(),
				EtatSemaphoreOrange.getInstance()));
		etat = etatsPossibles.get(0);
	}

	public SemaphoreTriCouleur(EtatSemaphore etat, Direction sens, int position) {
		super(etat, sens, position);
	}

	public double getRatio() {
		return this.etat.getRatioRalentissement();
	}
	

}
