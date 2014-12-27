package reseauxFerroviaire;

import exception.TrainException;

public class Train {

	private static int idGen = -1;
	private static int nbrInstance=0;
	private int id; // Identifiant du train
	private int taille; // Taile du train en nombre de trançon
	private int vMax; // Vitesse maximale du train
	private EtatCourant etatTrain;

	// Constructeur de train avec un état

	public Train(int taille, int vMax, EtatCourant etat) throws TrainException {
		nbrInstance++;
		// Géneration automatique d'identificateur
		++idGen;
		// Initialisation de l'id
		this.id = idGen;
		// Verification de la cohérence des paramètre d'initialisation
		if (taille <= 0 || vMax <= 0) {
			throw new TrainException("Paramètres d'inisialité");
		} else {
			this.taille = taille;
			this.vMax = vMax;
			this.etatTrain = etat;
		}
	}

	// Constructeur de train sans état

	
	/**
	 * 
	 * @param taille
	 * @param vMax
	 * @throws TrainException
	 */
	public Train(int taille, int vMax) throws TrainException {
		// Géneration automatique d'identificateur
		++idGen;
		// Initialisation de l'id
		this.id = idGen;
		// Verification de la cohérence des paramètre d'initialisation
		if (taille <= 0 || vMax <= 0) {
			throw new TrainException("Paramètres d'inisialité");
		} else {
			this.taille = taille;
			this.vMax = vMax;
			this.etatTrain = null;
		}
	}

	/**
	 * Arrêt immédiat du train
	 */
	public void arret() {
		setVitesse(0);
	}

	// Modification de la vitesse du train

	public void setVitesse(int vitesse) {
		this.etatTrain.setViesseCourante(0);
	}
	
	
	
	/**
	 *  Retourn le nobmre de trains
	 * @return Le nombre d'instance de train crée
	 */
	public int getNbrTrains(){
		return  nbrInstance;
	}
	/*
	 * Avancer // vitesse + temps Arreter : OK Start ? on a beosin ou pas ?
	 */

}
