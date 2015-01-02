package reseauxFerroviaire;

public class EtatSemaphoreBiCouleurRouge extends EtatSemaphoreBiCouleur {
	
	private static final EtatSemaphoreBiCouleurRouge instance = new EtatSemaphoreBiCouleurRouge(0);
	
	private EtatSemaphoreBiCouleurRouge(double ratio){
		super(ratio);
	}
	
	public static EtatSemaphoreBiCouleurRouge getInstance(){
		return instance;
	}
	
	public EtatSemaphoreBiCouleur getSuivant(){
		return EtatSemaphoreBiCouleurRouge.getInstance();
	}
	
}
