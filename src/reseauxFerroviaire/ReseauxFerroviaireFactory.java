package reseauxFerroviaire;

import java.util.ArrayList;

public class ReseauxFerroviaireFactory {
	
	public ArrayList<Rail> getSegment(int nombre, boolean buteeAmont, boolean buteeAval){
		ArrayList <Rail> segment = new ArrayList<Rail>();
		ArrayList <JonctionSimple> jonctionSimple = new ArrayList<JonctionSimple>();
		
		for(int i=0; i<nombre;i++){
			segment.add(new Rail());
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
	
	public Aiguillage getAiguillage3Voies(ArrayList<Rail> connect){
		if(connect.size()>=3){
			
		}
	}
	
	public Aiguillage getAiguillage4Voies(){
		if() 
	}
	
	
	
	
	
}
