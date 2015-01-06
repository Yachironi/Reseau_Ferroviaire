package reseauxFerroviaire;

import java.util.Iterator;

import exception.EtatSemaphoreException;

public class SemaphoreBiCouleur extends Semaphore {

	public SemaphoreBiCouleur() {
		super();
	}

	public SemaphoreBiCouleur(EtatSemaphoreBiCouleur etat, Direction sens, int position) {
		super(etat, sens, position);
	}

	public double getRatio() {
		return this.etat.getRatioRalentissement();
	}

	public void setEtat(EtatSemaphoreBiCouleur etat)
			throws EtatSemaphoreException {
		if (this.etat.equals(etat.getSuivant())) {
			this.etat = etat;
		} else {
			throw new EtatSemaphoreException("Changement d'etat incoherent");
		}
	}
	
	
	/*
	 * equals de EtatSemaphoreBiCouleur
	 */

}
