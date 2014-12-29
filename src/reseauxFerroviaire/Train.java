package reseauxFerroviaire;

import exception.RailException;
import exception.TrainException;

public class Train {

	private static int idGen = -1;
	private static int nbrInstance = 0;
	private int id; // Identifiant du train
	private int taille; // Taile du train en nombre de trançon
	private int vMax; // Vitesse maximale du train
	private EtatCourant etatTrain;

	/**
	 * Constructeur de train avec un état
	 * 
	 * @param taille
	 * @param vMax
	 * @param etat
	 * @throws TrainException
	 */
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

	/**
	 * Constructeur de train sans état
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
	 * Modification de la vitesse du train
	 * 
	 * @param vitesse
	 *            vitesse du train
	 * @throws TrainException
	 */

	public void setVitesse(int vitesse) throws TrainException {
		if (vitesse >= 0 || vitesse <= vMax) {
			this.etatTrain.setViesseCourante(vitesse);
		} else {
			throw new TrainException("Vitesse non cohérente");
		}

	}

	/**
	 * Arrête un train
	 * 
	 * @throws TrainException
	 */

	public void arret() throws TrainException {
		this.setVitesse(0);
	}

	/**
	 * Retourn le nobmre de trains
	 * 
	 * @return Le nombre d'instance de train crée
	 */
	public int getNbrTrains() {
		return nbrInstance;
	}

	@Override
	public String toString() {
		return "Train [id=" + id + ", taille=" + taille + ", vMax=" + vMax
				+ ", etatTrain=" + etatTrain + "]";
	}

	/**
	 * Avancer le train
	 * 
	 * @throws RailException
	 * 
	 */
	public void avance() throws RailException {
		if (etatTrain.getSens() == Direction.AVAL) {
			etatTrain.incrementePos();
		} else if (etatTrain.getSens() == Direction.AMONT) {
			etatTrain.decrementePos();
		}
	}

	/*
	 * Avancer // vitesse + temps Arreter : OK Start ? on a beosin ou pas ?
	 */

}
