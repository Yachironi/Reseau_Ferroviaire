package reseauxFerroviaire;

public class SemaphoreTriCouleur extends Semaphore {
	
	public SemaphoreTriCouleur(){
		super();
	}
	
	public SemaphoreTriCouleur(EtatSemaphoreTriCouleur etat){
		super(etat);		
	}
	
	public double getRatio(){
		return etat.getRatioRalentissement();
	}
	
	public void setEtat(EtatSemaphoreTriCouleur etat){
		this.etat=etat;
	}
	
	public void update(){
		
	}
	
}
