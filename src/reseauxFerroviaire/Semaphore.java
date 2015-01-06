package reseauxFerroviaire;

public class Semaphore {

	protected EtatSemaphore etat;
	protected int id;
	private static int idGen = -1;
	protected Direction sens;
	protected int position;
	
	public Semaphore() {
		this.etat = null;
		this.sens=null;
		this.position= -1;
		this.id = idGen++;
	}

	public Semaphore(EtatSemaphore etat, Direction sens, int position) {
		this();
		this.etat = etat;
		this.sens=sens;
		this.position=position;
	}

	public EtatSemaphore getEtat() {
		return etat;
	}

	public void setEtat(EtatSemaphore etat) {
		this.etat = etat;
	}
	
	
}
