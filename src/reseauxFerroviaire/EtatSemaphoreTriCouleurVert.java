package reseauxFerroviaire;

public class EtatSemaphoreTriCouleurVert extends EtatSemaphoreTriCouleur {

	private static final EtatSemaphoreTriCouleurVert instance = new EtatSemaphoreTriCouleurVert(
			1.0);

	private EtatSemaphoreTriCouleurVert(double ratio) {
		super(ratio);
	}

	public static EtatSemaphoreTriCouleurVert getInstance() {
		return instance;
	}

	
	public EtatSemaphoreTriCouleur getSuivant() {
		return EtatSemaphoreTriCouleurOrange.getInstance();
	}
}
