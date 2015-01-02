package reseauxFerroviaire;

import java.awt.Color;

public class EtatSemaphoreTriCouleur extends EtatSemaphore {
	
	private Color couleur;
    
	
	public EtatSemaphoreTriCouleur(Color c) {
		super();
		if(c.equals(Color.GREEN) || c.equals(Color.RED) || c.equals(Color.ORANGE)){
			this.couleur =c;
		}
		else{
			// throw exeption ?
		}
	}

	public double getRatioRalentissement(){
		if(couleur.equals(Color.GREEN)){
			return 1; 
		}
		else if(couleur.equals(Color.RED)){
			return 0;
		}
		else{
			return 0.5;
		}
	}
}
 