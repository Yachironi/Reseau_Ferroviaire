package reseauxFerroviaire;

public class SemaphoreTriCouleur extends Semaphore {
	
	public SemaphoreTriCouleur(){
		super();
	}
	
	public SemaphoreTriCouleur(EtatSemaphoreTriCouleur etat, Direction sens){
		super(etat, sens);		
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
