package reseauxFerroviaire;

import java.util.ArrayList;

public class Rail {
	
	private static int idGen=0;
	private int id; 
	private int nbTrancon;
	private ArrayList <Capteur> capteurs;

	
	
	public Rail(int nbTrancon, ArrayList<Capteur> capteurs) {
		this.id = idGen++;
		this.nbTrancon = nbTrancon;
		this.capteurs = capteurs;
	}



	public static int getIdGen() {
		return idGen;
	}



	public static void setIdGen(int idGen) {
		Rail.idGen = idGen;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNbTrancon() {
		return nbTrancon;
	}



	public void setNbTrancon(int nbTrancon) {
		this.nbTrancon = nbTrancon;
	}



	public ArrayList<Capteur> getCapteurs() {
		return capteurs;
	}



	public void setCapteurs(ArrayList<Capteur> capteurs) {
		this.capteurs = capteurs;
	}
	
	
}
