package reseauxFerroviaire;

public class Semaphore {

	protected EtatSemaphore etat;
	protected int id;
	private static int idGen = -1;

	public Semaphore() {
		this.etat = null;
		this.id = idGen++;
	}

	public Semaphore(EtatSemaphore etat) {
		this();
		this.etat = etat;
	}

	public EtatSemaphore getEtat() {
		return etat;
	}

	public void setEtat(EtatSemaphore etat) {
		this.etat = etat;
	}
	
}
