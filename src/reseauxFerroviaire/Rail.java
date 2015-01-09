package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Iterator;

import exception.RailException;
import exception.TrainException;

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
	
	/**
	 * Constructeur 
	 * Ce constructeur ne prend en paramètre que la taille de la rail
	 * @param longueur
	 * 
	 */
	public Rail(int longueur)  {
		this.id = idGen++;
		this.longueur = longueur;
		capteurs = null;
		semaTete= null;
		semaQueue=null;
	}

	/**
	 * Constructeur avec des parametres
	 * @param longueur :longuer de la rail
	 * @param capteurs : liste des capteurs presents sur la rail
	 * @param jonctionTete : jonction se trouvant au niveau de la tete
	 * @param jonctionQueue : jonction se trouvant au niveau de la queue
	 * @param semaTete : semaphore se trouvant au niveau de la tete
	 * @param semaQueue : semaphore se trouvant au niveau de la queue
	 */
	public Rail(int longueur, ArrayList<Capteur> capteurs,
			Jonction jonctionTete, Jonction jonctionQueue, Semaphore semaTete,
			Semaphore semaQueue) {
		//super();
		this.id = idGen++;
		this.longueur = longueur;
		this.capteurs = capteurs;
		this.jonctionTete = jonctionTete;
		this.jonctionQueue = jonctionQueue;
		this.semaTete = semaTete;
		this.semaQueue = semaQueue;
		this.trains = new ArrayList<Train>();
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
	/**
	 * Methode retournant la longueur effective de la rail, 
	 * c'est a dire, l'espace disponible apres avoir ajouter la taille de l'ensemble des trains present sur 
	 * un rail
	 * @return un int correspondant a lespace occupe
	 */
	public int getLongeurEffective() {
		int longueurEffective = 0;
		for (Iterator iterator = trains.iterator(); iterator.hasNext();) {
			Train train = (Train) iterator.next();
			longueurEffective += train.getLongueur();
		}
		return longueurEffective;
	}
	/**
	 * Methode permettant d'ajouter un train a la liste des trains du rail 
	 * @param train
	 * @throws RailException
	 */
	public void addTrain(Train train) throws RailException {
		
		if(trains == null){
			trains = new ArrayList<Train>();
		}
		
		if (((trains.size() == 0) || ((trains.get(0).getEtatTrain().getSens() == train
				.getEtatTrain().getSens())))
				&& ((this.getLongeurEffective() + train.getLongueur()) <= this.longueur)) {
			trains.add(train);
		} else {
			throw new RailException("Longueur de rail dépassé");
		}
	}
	/**
	 * Perme de supprimer un train present sur cet rail
	 * Prend en parametre le train a supprimer
	 * @param train
	 */
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
		for (Iterator iterator = capteurs.iterator(); iterator
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
