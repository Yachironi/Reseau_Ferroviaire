package reseauxFerroviaire;

public class EtatSemaphoreTriCouleurOrange extends EtatSemaphoreTriCouleur{

	private static final EtatSemaphoreTriCouleurOrange instance = new EtatSemaphoreTriCouleurOrange(0.5);
	
	
	private EtatSemaphoreTriCouleurOrange(double ratio){
		super(ratio);
	}
	
	public static EtatSemaphoreTriCouleurOrange getInstance(){
		return instance;
	}
	
	public EtatSemaphoreTriCouleur getSuivant(){
		return EtatSemaphoreTriCouleurRouge.getInstance();
	}
	
	
}
