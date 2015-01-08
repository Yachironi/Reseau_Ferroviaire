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
	private Semaphore semaTete;
	private Semaphore semaQueue;
	
	public Rail(int longueur) {
		this.id = idGen++;
		this.longueur = longueur;
		trains = null;
		capteurs = null;
		semaTete=null;
		semaQueue=null;
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

	public boolean isTrainPresent() {
		for (Iterator iterator = capteurs.iterator(); iterator.hasNext();) {
			try {
				CapteurPresence capteur = (CapteurPresence) iterator.next();
				if (capteur.isTrainPresent()) {
					return true;
				}
			} catch (ClassCastException e) {

			}
		}
		return false;
	}

	public CapteurPresence getCapteurPresenceAt(int index) {
		try {
			CapteurPresence capteur = (CapteurPresence) capteurs.get(index);
			return capteur;
		} catch (Exception e) {
			return null;
		}
	}

	public ArrayList<CapteurPresence> getListCapteurPresence() {
		ArrayList<CapteurPresence> capteursPresence = new ArrayList<>();
		for (Iterator iterator = capteursPresence.iterator(); iterator
				.hasNext();) {
			try {
				CapteurPresence capteurPresence = (CapteurPresence) iterator
						.next();
				capteursPresence.add(capteurPresence);
			} catch (Exception e) {
				continue;
			}
		}
		return capteursPresence;
	}

	public ArrayList<Capteur> getCapteurs() {
		return capteurs;
	}

	public void setCapteurs(ArrayList<Capteur> capteurs) {
		this.capteurs = capteurs;
	}

	public Jonction getJonctionTete() {
		return jonctionTete;
	}

	public void setJonctionTete(Jonction jonctionTete) {
		this.jonctionTete = jonctionTete;
	}

	public Jonction getJonctionQueue() {
		return jonctionQueue;
	}

	public void setJonctionQueue(Jonction jonctionQueue) {
		this.jonctionQueue = jonctionQueue;
	}
	
	public Semaphore getSemaTete() {
		return semaTete;
	}

	public void setSemaTete(Semaphore semaTete) {
		this.semaTete = semaTete;
	}
	public void setEtatSemaTete(Semaphore semaTete) {
		this.semaTete = semaTete;
	}

	public Semaphore getSemaQueue() {
		return semaQueue;
	}

	public void setSemaQueue(Semaphore semaQueue) {
		this.semaQueue = semaQueue;
	}
	public void setEtatSemaQueue(Semaphore semaQueue) {
		this.semaQueue = semaQueue;
	}

	@Override
	public boolean equals(Object arg) {
		if (arg == this) return true;
		if (arg == null) return false;
		try {
			Rail rail = (Rail) arg;
			return rail.id == this.id;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Rail [id=" + id + ", longueur=" + longueur + ", trains="
				+ trains + "]";
	}


	public boolean voieEstLibre() {
		// 
		return false;
	}
	

}
