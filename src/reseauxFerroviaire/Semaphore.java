package reseauxFerroviaire;

import java.util.Observable;

public class Semaphore extends Observable {

	protected EtatSemaphore etat;
	protected int id;
	private static int idGen = -1;
	

	public Semaphore() {
		this.etat = null;
		this.id=idGen++;
	}
	public Semaphore(EtatSemaphore etat) {
		this();
		this.etat = etat;
	}

}
