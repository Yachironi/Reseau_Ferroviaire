package reseauxFerroviaire;

import java.awt.Color;

public class SemaphoreTriCouleur extends Semaphore {
	
	public SemaphoreTriCouleur(){
		super();
//		this.etat = new EtatSemaphoreTriCouleur();	
	}
	
	public SemaphoreTriCouleur(Color c){
		super();
		this.etat = new EtatSemaphoreTriCouleur(c);	
	}
}
