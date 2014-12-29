package reseauxFerroviaire;

import java.util.ArrayList;

import exception.AiguillageException;
import exception.TrainException;

public class Aiguillage extends Jonction {
	
	private static int idGen=0;
	private int id; /* identificateur de l'aiguillage */
	private ArrayList<Rail> listeRailAmont;
	private ArrayList<Rail> listeRailAval;
	private Rail sortieAmont; 
	private Rail sortieAval;
	
	/**
	 * Constructeur 
	 * @param listeRailAmont
	 * @param listeRailAval
	 * @param sortieAmont
	 * @param sortieAval
	 * @throws AiguillageException
	 */
	public Aiguillage(ArrayList<Rail> listeRailAmont,
		ArrayList<Rail> listeRailAval, Rail sortieAmont, Rail sortieAval) throws AiguillageException{
		
		if(listeRailAmont.size()+listeRailAval.size()>=3){		
		this.listeRailAmont = listeRailAmont;
		this.listeRailAval = listeRailAval;
		this.sortieAmont = sortieAmont;
		this.sortieAval = sortieAval;
		}
		else{
			throw new AiguillageException("Mauvaise instantiation du constructeur");
		}
	}
	
	
	
	
	
}
