package reseauxFerroviaire;

import java.awt.Color;

public abstract class EtatSemaphoreBiCouleur extends EtatSemaphore {

	protected EtatSemaphoreBiCouleur(double ratio) {
		super(ratio);
	}
	
	public abstract <T extends EtatSemaphoreBiCouleur> T getSuivant();
}
