package reseauxFerroviaire;

import java.util.Observable;

public abstract class Semaphore extends Observable {

	private static int idGen = -1;
	protected EtatSemaphore etat;
	protected int id;
	
	public Semaphore() {
		this.etat = null;
		this.id=idGen++;
	}
	public Semaphore(EtatSemaphore etat) {
		this();
		this.etat = etat;
	}
	
	

}
