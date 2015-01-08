package reseauxFerroviaire;

public class EtatSemaphoreVert extends EtatSemaphore {

	private static final EtatSemaphoreVert instance = new EtatSemaphoreVert(
			1.0);

	private EtatSemaphoreVert(double ratio) {
		super(ratio);
	}

	public static EtatSemaphoreVert getInstance() {
		return instance;
	}
}
