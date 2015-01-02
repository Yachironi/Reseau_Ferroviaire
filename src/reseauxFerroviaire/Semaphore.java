package reseauxFerroviaire;

public class Semaphore {

	protected EtatSemaphore etat;
	protected int id;
	private static int idGen = -1;
	protected Direction sens;
	
	public Semaphore() {
		this.etat = null;
		this.sens=null;
		this.id = idGen++;
	}

	public Semaphore(EtatSemaphore etat, Direction sens) {
		this();
		this.etat = etat;
		this.sens=sens;
	}

	public EtatSemaphore getEtat() {
		return etat;
	}

	public void setEtat(EtatSemaphore etat) {
		this.etat = etat;
	}
	
}
