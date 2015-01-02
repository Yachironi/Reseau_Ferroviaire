package reseauxFerroviaire;

import java.awt.Color;

public abstract class EtatSemaphoreTriCouleur extends EtatSemaphore {
	
	protected EtatSemaphoreTriCouleur(double ratio) {
		super(ratio);
	}
	
	public abstract <T extends EtatSemaphoreTriCouleur> T getSuivant();

	
}
 