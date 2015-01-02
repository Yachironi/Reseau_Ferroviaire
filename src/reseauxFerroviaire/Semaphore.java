package reseauxFerroviaire;

public class Semaphore {
	
	protected EtatSemaphore etat;
	private static int idGen = 0;
	protected int id;
	
	public Semaphore(){
		id = idGen++;
		this.etat=null;
	}
	
}
