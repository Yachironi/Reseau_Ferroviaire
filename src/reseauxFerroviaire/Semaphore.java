package reseauxFerroviaire;

import java.util.Observable;

public class Semaphore extends Observable {

	protected EtatSemaphore etat;

	public Semaphore() {
		this.etat = null;
	}
	public Semaphore(EtatSemaphore etat) {
		this.etat = etat;
	}

}
