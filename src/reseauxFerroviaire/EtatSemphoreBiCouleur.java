package reseauxFerroviaire;

import java.awt.Color;

public class EtatSemphoreBiCouleur extends EtatSemaphore {
	
	private Color couleur;
	private int ratioVert;
	private int ratioRouge; 
	
	
	public EtatSemphoreBiCouleur(Color couleur){
		super();
		this.couleur = couleur;
	}
	
	/*public int getRatio(){
		//if(couleur.equals(Color.GREEN)) return ;
		
		
	}*/

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getRatioVert() {
		return ratioVert;
	}

	public void setRatioVert(int ratioVert) {
		this.ratioVert = ratioVert;
	}

	public int getRatioRouge() {
		return ratioRouge;
	}

	public void setRatioRouge(int ratioRouge) {
		this.ratioRouge = ratioRouge;
	}
	
	
	
}
