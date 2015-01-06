package reseauxFerroviaire;

import exception.EtatSemaphoreException;

public class SemaphoreTriCouleur extends Semaphore {

	public SemaphoreTriCouleur() {
		super();
	}

	public SemaphoreTriCouleur(EtatSemaphoreBiCouleur etat, Direction sens, int position) {
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
	 * equals de EtatSemaphoreTriCouleur
	 */

}
