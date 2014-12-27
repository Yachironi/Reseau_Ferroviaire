package reseauxFerroviaire;

import java.util.ArrayList;

public class Aiguillage extends Jonction {
	
	private ArrayList<Rail> listeRail;
	
	public Aiguillage(int nombre){
		if(nombre >= 3){
			listeRail = new ArrayList<Rail>();
		}
	}
	
}
