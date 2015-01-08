package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Arrays;

import exception.EtatSemaphoreException;

public class SemaphoreBiCouleur extends Semaphore {

	public SemaphoreBiCouleur() {
		super();
		etatsPossibles = new ArrayList<EtatSemaphore>(Arrays.asList(
				EtatSemaphoreRouge.getInstance(),
				EtatSemaphoreVert.getInstance()));
		etat = etatsPossibles.get(0);
	}

	public SemaphoreBiCouleur(EtatSemaphore etat, Direction sens,
			int position) {
		super(etat, sens, position);
	}

	public double getRatio() {
		return this.etat.getRatioRalentissement();
	}



	/*
	 * equals de EtatSemaphoreBiCouleur
	 */

}
