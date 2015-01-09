package reseauxFerroviaire;

import exception.RailException;
import exception.TrainException;

public class Train implements Runnable {

	private static int idGen = -1;
	private static int nbrInstance = 0;
	private int id; // Identifiant du train
	private int longueur; // Taile du train en nombre de trançon
	private int vMax; // Vitesse maximale du train
	private EtatCourant etatTrain;

	/**
	 * Constructeur de train avec un état
	 * 
	 * @param longueur
	 *            en nombre
	 * @param vMax
	 * @param etat
	 * @throws TrainException
	 */
	public Train(int longueur, int vMax, Rail rail, Direction sens)
			throws TrainException {
		nbrInstance++;
		// Géneration automatique d'identificateur
		++idGen;
		// Initialisation de l'id
		this.id = idGen;
		// Verification de la cohérence des paramètre d'initialisation
		if (longueur <= 0 || vMax <= 0) { // et on doit verifier la longeur de
											// rail !!!
			throw new TrainException("Paramètres d'inisialité");
		} else {
			this.longueur = longueur;
			this.vMax = vMax;
			if (!(longueur > rail.getLongueur())) {
				this.etatTrain = new EtatCourant(longueur, rail, sens);
			} else {
				throw new TrainException("Instanciation du train impossible");
			}

		}
	}

	/**
	 * Constructeur de train sans état
	 * 
	 * @param taille
	 * @param vMax
	 * @throws TrainException
	 */
	public Train(int longueur, int vMax) throws TrainException {
		// Géneration automatique d'identificateur
		++idGen;
		// Initialisation de l'id
		this.id = idGen;
		// Verification de la cohérence des paramètre d'initialisation
		if (longueur <= 0 || vMax <= 0) {
			throw new TrainException("Paramètres d'inisialité");
		} else {
			this.longueur = longueur;
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
			this.etatTrain.setVitesseCourante(vitesse);
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
		return "Train [id=" + id + ", longueur=" + longueur + ", vMax=" + vMax
				+ ", etatTrain=" + etatTrain + "]";
	}

	/**
	 * Permet le déplacement du train
	 */
	public void deplacer() {
		try {

			if (etatTrain.getSens() == Direction.AVAL) {
				avance();

			} else if (etatTrain.getSens() == Direction.AMONT) {
			//	recule();
			}

		} catch (RailException e) {
			System.out.println(e.getMessage());
		}

		// On aura besoin de Capteur.update() à chaque avancement du train
	}

	public static int getNbrInstance() {
		return nbrInstance;
	}

	public int getId() {
		return id;
	}

	public int getvMax() {
		return vMax;
	}

	public EtatCourant getEtatTrain() {
		return etatTrain;
	}

	public void setEtatTrain(EtatCourant etatTrain) {
		this.etatTrain = etatTrain;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	/**
	 * Permet de savoir quel est Rail suivant sur lequel le train doit passer
	 * 
	 * @return le rail
	 */
	public Rail getRailSuivant() {
		if (etatTrain.getSens().equals(Direction.AMONT)) {
			return etatTrain.getMonRail().getJonctionTete()
					.getSuivant(etatTrain.getMonRail());
		} else
			return etatTrain.getMonRail().getJonctionQueue()
					.getSuivant(etatTrain.getMonRail());
	}

	/*
	 * public boolean getTQTQ(EtatCourant t1, EtatCourant t2){
	 * t2.setMonRail(t1.getMonRail
	 * ().getJonctionQueue().getSuivant(t1.getMonRail()));
	 * if(t1.getMonRail().getJonctionQueue().getId()==
	 * t2.getMonRail().getJonctionTete().getId()) return true; else return
	 * false; }
	 * 
	 * public boolean getTQQT(EtatCourant t1, EtatCourant t2){
	 * t2.setMonRail(t1.getMonRail
	 * ().getJonctionQueue().getSuivant(t1.getMonRail()));
	 * if(t1.getMonRail().getJonctionQueue().getId()==
	 * t2.getMonRail().getJonctionQueue().getId()) return true; else return
	 * false; }
	 * 
	 * public boolean getQTQT(EtatCourant t1, EtatCourant t2){
	 * t2.setMonRail(t1.getMonRail
	 * ().getJonctionTete().getSuivant(t1.getMonRail()));
	 * if(t1.getMonRail().getJonctionTete().getId()==
	 * t2.getMonRail().getJonctionQueue().getId()) return true; else return
	 * false; }
	 * 
	 * public boolean getQTTQ(EtatCourant t1, EtatCourant t2){
	 * t2.setMonRail(t1.getMonRail
	 * ().getJonctionTete().getSuivant(t1.getMonRail()));
	 * if(t1.getMonRail().getJonctionTete().getId()==
	 * t2.getMonRail().getJonctionTete().getId()) return true; else return
	 * false; }
	 */

	public boolean getTQ(EtatCourant t1, EtatCourant t2) {
		Rail r1 = t1.getMonRail();
		t2.setMonRail(t1.getMonRail().getJonctionTete().getSuivant(r1));
		if (t1.getMonRail().getJonctionTete().getId() == t2.getMonRail()
				.getJonctionQueue().getId())
			return true;
		else
			return false;
	}

	public boolean getQT(EtatCourant t1, EtatCourant t2) {
		Rail r1 = t1.getMonRail();
		t2.setMonRail(t1.getMonRail().getJonctionQueue().getSuivant(r1));
		if (t1.getMonRail().getJonctionQueue().getId() == t2.getMonRail()
				.getJonctionTete().getId())
			return true;
		else
			return false;
	}

	public boolean getTT(EtatCourant t1, EtatCourant t2) {
		Rail r1 = t1.getMonRail();
		t2.setMonRail(t1.getMonRail().getJonctionTete().getSuivant(r1));
		if (t1.getMonRail().getJonctionTete().getId() == t2.getMonRail()
				.getJonctionTete().getId())
			return true;
		else
			return false;
	}

	public boolean getQQ(EtatCourant t1, EtatCourant t2) {
		Rail r1 = t1.getMonRail();
		t2.setMonRail(t1.getMonRail().getJonctionQueue().getSuivant(r1));
		if (t1.getMonRail().getJonctionQueue().getId() == t2.getMonRail()
				.getJonctionQueue().getId())
			return true;
		else
			return false;
	}

	public boolean getTQRail() {
		if (etatTrain.getMonRail().getJonctionQueue().getId() == etatTrain
				.getMonRail().getJonctionQueue()
				.getSuivant(etatTrain.getMonRail()).getJonctionQueue().getId()
				|| etatTrain.getMonRail().getJonctionQueue().getId() == etatTrain
						.getMonRail().getJonctionQueue()
						.getSuivant(etatTrain.getMonRail()).getJonctionTete()
						.getId())
			return true;
		else
			return false;

	}

	public boolean getQTRail() {
		if (etatTrain.getMonRail().getJonctionTete().getId() == etatTrain
				.getMonRail().getJonctionTete()
				.getSuivant(etatTrain.getMonRail()).getJonctionTete().getId()
				|| etatTrain.getMonRail().getJonctionTete().getId() == etatTrain
						.getMonRail().getJonctionTete()
						.getSuivant(etatTrain.getMonRail()).getJonctionQueue()
						.getId())
			return true;
		else
			return false;
	}

	public void avance() throws RailException {

		EtatCourant courant = etatTrain;
		EtatCourant suivant = courant;
		EtatCourant temp = courant;
		// reste à parcourir

		int restant = courant.getVitesseCourante()
				- (courant.getMonRail().getLongueur() - courant.getPosiTete());

		if (getTQRail()) {
			suivant.setMonRail(courant.getMonRail().getJonctionQueue()
					.getSuivant(courant.getMonRail()));
		} else if (getQTRail()) {
			suivant.setMonRail(courant.getMonRail().getJonctionTete()
					.getSuivant(courant.getMonRail()));
		}
		restant = restant - suivant.getMonRail().getLongueur();
		courant = suivant;
		while (restant > 0) {
			temp = suivant;
			if (getQT(courant, temp) || getTT(courant, temp)) {

				suivant.setMonRail(courant.getMonRail().getJonctionQueue()
						.getSuivant(courant.getMonRail()));
				restant = restant - suivant.getMonRail().getLongueur();
				courant = suivant;
				courant.setPosiTete(restant
						+ courant.getMonRail().getLongueur());

			} else if (getQQ(courant, temp) || getTQ(courant, temp)) {

				suivant.setMonRail(courant.getMonRail().getJonctionTete()
						.getSuivant(courant.getMonRail()));
				courant = suivant;
				courant.setPosiTete(courant.getMonRail().getLongueur()
						- restant + 1);
				restant = restant - suivant.getMonRail().getLongueur();
			}
		}
	}

	/*public void recule() throws RailException {

		EtatCourant courant = etatTrain;
		EtatCourant suivant = courant;
		EtatCourant temp = courant;
		// reste à parcourir


		
		
		
		int restant = courant.getVitesseCourante()
				- (courant.getMonRail().getLongueur() - courant.getPosiTete());
		
		if(restant <= 0){
			courant.setPosiTete(courant.getPosiTete() + courant.getVitesseCourante());
			etatTrain = courant;
		}
		
		
	/*	if(getQTRail()){
			
			int restant = courant.getVitesseCourante()
					- (courant.getMonRail().getLongueur() - courant.getPosiTete());
			restant = restant + 1;
			
		}
		/*if(restant >0){
		
		
			suivant.setMonRail(courant.getMonRail().getJonctionQueue()
					.getSuivant(courant.getMonRail()));
			restant = restant - suivant.getMonRail().getLongueur();
			courant = suivant;
			
			if(restant <= 0){
				if(restant==0){
					//courant.setPosiTete(courant.)
				}
				courant.setPosiTete(courant.getMonRail().getLongueur() + restant -1);
			}
			
			
		} else if (getQTRail()) {
			suivant.setMonRail(courant.getMonRail().getJonctionTete()
					.getSuivant(courant.getMonRail()));
			restant = restant - suivant.getMonRail().getLongueur();
			courant = suivant;
			
		}
		
		
		
		
		*/
		

/*		int restant = courant.getVitesseCourante() - courant.getPosiTete();

		/*
		 * if (courant.getMonRail().getJonctionQueue().getId() ==
		 * 
		 * 
		 * suivant .getMonRail().getJonctionTete().getId() ||
		 * courant.getMonRail().getJonctionTete().getId() == suivant
		 * .getMonRail().getJonctionTete().getId()) {
		 */

	/*	suivant.setMonRail(courant.getMonRail().getJonctionTete()
				.getSuivant(courant.getMonRail()));
		restant = restant - suivant.getMonRail().getLongueur();

		while (restant > 0) {

			temp = suivant;
			if (getQT(courant, temp)) {

			if (courant.getMonRail().getJonctionQueue().getId() == suivant
					.getMonRail().getJonctionTete().getId()
					|| courant.getMonRail().getJonctionTete().getId() == suivant
							.getMonRail().getJonctionTete().getId()) {
				// increment: continue à incrementer
				suivant.setMonRail(courant.getMonRail().getJonctionQueue()
						.getSuivant(courant.getMonRail()));
				restant = restant - suivant.getMonRail().getLongueur();
				courant = suivant;
				courant.setPosiTete(restant
						+ courant.getMonRail().getLongueur());
				
			}	
			else if(getTT(courant, temp)){
					suivant.setMonRail(courant.getMonRail().getJonctionTete()
							.getSuivant(courant.getMonRail()));
					restant = restant - suivant.getMonRail().getLongueur();
					courant = suivant;
					courant.setPosiTete(restant
							+ courant.getMonRail().getLongueur());				
				
			} else if (getQQ(courant, temp)) {

				suivant.setMonRail(courant.getMonRail().getJonctionQueue()
						.getSuivant(courant.getMonRail()));
				courant = suivant;
				courant.setPosiTete(courant.getMonRail().getLongueur()
						- restant + 1);
				restant = restant - suivant.getMonRail().getLongueur();
			}
			else if(getTQ(courant, temp)){
			} else if (courant.getMonRail().getJonctionQueue().getId() == suivant
					.getMonRail().getJonctionQueue().getId()
					|| courant.getMonRail().getJonctionTete().getId() == suivant
							.getMonRail().getJonctionQueue().getId()) {
				// decrement à partir de la position du tete du train
				suivant.setMonRail(courant.getMonRail().getJonctionTete()
						.getSuivant(courant.getMonRail()));
				courant = suivant;
				courant.setPosiTete(courant.getMonRail().getLongueur()
						- restant + 1);
				restant = restant - suivant.getMonRail().getLongueur();
			}
		}
	}

*/
/*
	public void recule() throws RailException {

		EtatCourant courant = etatTrain;
		EtatCourant suivant = courant;
		EtatCourant temp = courant;
		// reste à parcourir
		int restant = courant.getVitesseCourante() - courant.getPosiTete();

		
		if (getQTRail()) {
			suivant.setMonRail(courant.getMonRail().getJonctionQueue()
					.getSuivant(courant.getMonRail()));
		} else if (getTQRail()) {
			suivant.setMonRail(courant.getMonRail().getJonctionTete()
					.getSuivant(courant.getMonRail()));
		}
		
		restant = restant - suivant.getMonRail().getLongueur();

		while (restant < 0) {
			temp = suivant;
			if (getQT(courant, temp) || getTT(courant, temp))
			{
				// increment: continue à incrementer
				suivant.setMonRail(courant.getMonRail().getJonctionQueue()
						.getSuivant(courant.getMonRail()));
				restant = restant - suivant.getMonRail().getLongueur();
				courant = suivant;
				courant.setPosiTete(restant
						+ courant.getMonRail().getLongueur());

		} else if (courant.getMonRail().getJonctionQueue().getId() == suivant
					.getMonRail().getJonctionQueue().getId()
					|| courant.getMonRail().getJonctionTete().getId() == suivant
							.getMonRail().getJonctionQueue().getId()) {
				// decrement à partir de la position du tete du train
				suivant.setMonRail(courant.getMonRail().getJonctionTete()
						.getSuivant(courant.getMonRail()));
				courant = suivant;
				courant.setPosiTete(courant.getMonRail().getLongueur()
						- restant + 1);
				restant = restant - suivant.getMonRail().getLongueur();
			}
		}
*/

	@Override
	public boolean equals(Object arg) {
		if (arg == this)
			return true;
		if (arg == null)
			return false;
		try {
			Train train = (Train) arg;
			return this.id == train.getId();
		} catch (ClassCastException e) {
			return false;
		}
	}

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
		
				try {
					Thread.sleep(1000);
					while(true){
						deplacer();	
					}
				} catch (InterruptedException e) {
					// TODO Bloc catch généré automatiquement
					e.printStackTrace();
				}
	
	}

}
