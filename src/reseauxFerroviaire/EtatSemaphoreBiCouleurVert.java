package reseauxFerroviaire;

public class EtatSemaphoreBiCouleurVert extends EtatSemaphoreBiCouleur{
	
	private static final EtatSemaphoreBiCouleurVert instance = new EtatSemaphoreBiCouleurVert(1.0);
	
	private EtatSemaphoreBiCouleurVert(double ratio){
		super(ratio);
	}
	
	public static EtatSemaphoreBiCouleurVert getInstance(){
		return instance;
	}
	
	public EtatSemaphoreBiCouleur getSuivant(){
		return EtatSemaphoreBiCouleurRouge.getInstance();
	}
}
