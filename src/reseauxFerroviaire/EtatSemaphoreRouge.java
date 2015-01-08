package reseauxFerroviaire;

public class EtatSemaphoreRouge extends EtatSemaphore {
	
	private static final EtatSemaphoreRouge instance = new EtatSemaphoreRouge(0);

	private EtatSemaphoreRouge(double ratio){
		super(ratio);
	}
	
	public static EtatSemaphoreRouge getInstance(){
		return instance;
	}
	
}
