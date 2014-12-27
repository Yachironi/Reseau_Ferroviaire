package reseauxFerroviaire;

import java.util.ArrayList;

public class Rail {
	
	private static int idGen=0;
	private int id; 
	private int nbTrancon;
	private ArrayList <Capteur> listeCapteur;
	
	public Rail(int nbTrancon){
		
		this.id = idGen++;
		this.nbTrancon = nbTrancon;
		listeCapteur = new ArrayList<Capteur>();
	}
	
	
	

}
