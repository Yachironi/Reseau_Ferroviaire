package reseauxFerroviaire;

public class EtatSemaphoreTriCouleurRouge extends EtatSemaphoreTriCouleur {
	
	private static final EtatSemaphoreTriCouleurRouge instance = new EtatSemaphoreTriCouleurRouge(0);

	private EtatSemaphoreTriCouleurRouge(double ratio){
		super(ratio);
	}
	
	public static EtatSemaphoreTriCouleurRouge getInstance(){
		return instance;
	}
	
	public EtatSemaphoreTriCouleur getSuivant(){
		return EtatSemaphoreTriCouleurVert.getInstance();
	}
}
