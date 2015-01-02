package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Iterator;

import exception.RailException;

public class Rail {

	private static int idGen = 0;
	private int id;
	private int longueur;
	private ArrayList<Train> trains;
	private ArrayList<Capteur> capteurs;
	private Jonction jonctionTete;
	private Jonction jonctionQueue;

	public Rail(int longueur) {
		this.id = idGen++;
		this.longueur = longueur;
		trains = null;
		capteurs = null;
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

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) throws RailException {
		if (longueur >= getLongeurEffective()) {
			this.longueur = longueur;
		} else {
			throw new RailException(
					"Impossible de réduire la taille mois que ça longueur fective");
		}

	}

	public ArrayList<Train> getTrains() {
		return trains;
	}

	public void setTrains(ArrayList<Train> trains) {
		this.trains = trains;
	}

	public int getLongeurEffective() {
		int longueurEffective = 0;
		for (Iterator iterator = trains.iterator(); iterator.hasNext();) {
			Train train = (Train) iterator.next();
			longueurEffective += train.getLongueur();
		}
		return longueurEffective;
	}

	public void addTrain(Train train) throws Exception {
		if (((trains.size() == 0) || ((trains.get(0).getEtatTrain().getSens() == train
				.getEtatTrain().getSens())))
				&& ((this.getLongeurEffective() + train.getLongueur()) <= this.longueur)) {
			trains.add(train);
		} else {
			throw new Exception("Longueur de rail dépassé");
		}
	}

	public void removeTrain(Train train) {
		if (trains.contains(train)) {
			trains.remove(train);
		}
	}

	/**
	 * Retourne s'il existe le train à la position donnée , si non elle renvoi
	 * null
	 * 
	 * @param position
	 * @return
	 */
	public Train getTrainAt(int position) {
		for (Iterator iterator = trains.iterator(); iterator.hasNext();) {
			Train train = (Train) iterator.next();
			if (train.getEtatTrain().getSens() == Direction.AVAL) {
				if (train.getEtatTrain().getPosiTete() - train.getLongueur() <= position) {
					return train;
				}
			} else {
				if (train.getEtatTrain().getPosiTete() + train.getLongueur() >= position) {
					return train;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Rail [id=" + id + ", longueur=" + longueur + ", trains="
				+ trains + "]";
	}

}
