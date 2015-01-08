package reseauxFerroviaire;

import java.util.ArrayList;



public class ReseauxFerroviaireFactory {
	
	final int minimum=10;
	
	public ArrayList<Rail> getSegment(int nombre, boolean buteeAmont, boolean buteeAval, int taille){
		ArrayList <Rail> segment = new ArrayList<Rail>();
		int tailleRail = (int) taille/nombre;
		
		for(int i=0; i<nombre;i++){
			segment.add(new Rail(tailleRail));
		}
		
		for(int i=0; i<nombre-1; i++){
			JonctionSimple jonc = new JonctionSimple();
			segment.get(i).setJonctionQueue(jonc);
			segment.get(i+1).setJonctionTete(jonc);
		}
		
		if(buteeAmont){
			segment.get(0).setJonctionTete(new Butee());
		}
		
		if(buteeAval){
			segment.get(segment.size()-1).setJonctionQueue(new Butee());
		}
		return segment;
	}
	
/*	public Aiguillage getAiguillage3Voies(){
		ArrayList <Rail> connect = new ArrayList <Rail>();
		connect.add(new Rail())
		
			
		}
		return new Aiguillage(listeRail, sortieAmont, sortieAval)
	}
	
	public Aiguillage getAiguillage4Voies(){
		if() 
	}
*/
	
}
