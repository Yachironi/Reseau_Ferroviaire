package reseauxFerroviaire;

import java.util.Observable;

public class Capteur extends Observable{
	
	private static int idGen=0;
	private int id;
	private int numTrancon;
	private Rail monRail;
	
	public Capteur(int num, Rail rail){
		this.id = idGen++;
		this.numTrancon = num;
		this.monRail = rail;
	}
	
}
