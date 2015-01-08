package reseauxFerroviaire;

public class EtatSemaphoreOrange extends EtatSemaphore{

	private static final EtatSemaphoreOrange instance = new EtatSemaphoreOrange(0.5);
	
	
	private EtatSemaphoreOrange(double ratio){
		super(ratio);
	}
	
	public static EtatSemaphoreOrange getInstance(){
		return instance;
	}
}
