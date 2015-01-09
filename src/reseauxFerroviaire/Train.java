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
	 * @throws RailException 
	 */
	public Train(int longueur, int vMax, Rail rail, Direction sens) throws TrainException {
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
				this.etatTrain = new EtatCourant(longueur, rail, sens, vMax);
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

	public void setvMax(int vMax) {
		this.vMax = vMax;
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
				if (etatTrain.getMonRail().getSemaQueue() != null) {
					if (etatTrain.getMonRail().getSemaQueue().getSens() == etatTrain
							.getSens()) {
						int vitesse = Math.round((float) (etatTrain
								.getVitesseCourante() * etatTrain
								.getMonRail().getSemaQueue().getEtat()
								.getRatioRalentissement()));
						etatTrain.setVitesseCourante(vitesse);
					}
				}
			} else {
				if (etatTrain.getMonRail().getSemaTete() != null) {
					if (etatTrain.getMonRail().getSemaTete().getSens() == etatTrain
							.getSens()) {
						int vitesse = Math.round((float) (etatTrain
								.getVitesseCourante() * etatTrain.getMonRail()
								.getSemaTete().getEtat()
								.getRatioRalentissement()));
						etatTrain.setVitesseCourante(vitesse);
					}
				}
			}

			if (etatTrain.getVitesseCourante() > 0) {
				etatTrain.setPosiTete(etatTrain.getPosiTete()
						+ etatTrain.getVitesseCourante());
				
				int valAParcourir = etatTrain.getPosiTete()
						- etatTrain.getMonRail().getLongueur();

				if (valAParcourir > 0) {
						
					if(getRailSuivant() != null)
							etatTrain.setMonRail(getRailSuivant());
					
					boolean avancer = true;
					while (avancer) {
						
						if (etatTrain.getMonRail().getLongueur() > valAParcourir) {
							etatTrain.setPosiTete(valAParcourir);
							avancer = false;
						} else {
							valAParcourir -= etatTrain.getMonRail()
									.getLongueur();
							etatTrain.getMonRail().removeTrain(this);
							etatTrain.setMonRail(getRailSuivant());
							etatTrain.getMonRail().addTrain(this);
						}
					}

				}
			}
			 System.out.println(toString());
		} catch (RailException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @throws RailException 
	 */
	public Rail getRailSuivant() throws RailException {
		if (etatTrain.getSens().equals(Direction.AMONT)) {
				
			return etatTrain.getMonRail().getJonctionTete()
					.getSuivant(etatTrain.getMonRail());
		}
		else {			
			return etatTrain.getMonRail().getJonctionQueue()
				.getSuivant(etatTrain.getMonRail());
				}
	}

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
		try {
			Thread.sleep(1000);
			while (true) {
				deplacer();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
	/*
	 * 
	 * public boolean getTQTQ(EtatCourant t1, EtatCourant t2){
	 * t2.setMonRail(t1.getMonRail
	 * ().getJonctionQueue().getSuivant(t1.getMonRail()));
	 * if(t1.getMonRail().getJonctionQueue().getId()==
	 * t2.getMonRail().getJonctionTete().getId()) return true; else return
	 * false; }
	 * 
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
     
	
	/*public boolean getTQ(EtatCourant t1, EtatCourant t2) {
	 
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
		if (etatTrain.getMonRail().getJonctionQueue()
				.getSuivant(etatTrain.getMonRail()).getJonctionQueue() != null) {
			if (etatTrain.getMonRail().getJonctionQueue().getId() == etatTrain
					.getMonRail().getJonctionQueue()
					.getSuivant(etatTrain.getMonRail()).getJonctionQueue()
					.getId())
				return true;

		} else if (etatTrain.getMonRail().getJonctionQueue()
				.getSuivant(etatTrain.getMonRail()).getJonctionTete() != null) {

			if (etatTrain.getMonRail().getJonctionQueue().getId() == etatTrain
					.getMonRail().getJonctionQueue()
					.getSuivant(etatTrain.getMonRail()).getJonctionTete()
					.getId())
				return true;

		} else {
			return false;
		}

		return false;
	}
	
	public boolean getQTRail() {

		if (etatTrain.getMonRail().getJonctionTete()
				.getSuivant(etatTrain.getMonRail()).getJonctionTete() != null
				&& etatTrain.getMonRail().getJonctionTete() != null) {
			if (etatTrain.getMonRail().getJonctionTete().getId() == etatTrain
					.getMonRail().getJonctionTete()
					.getSuivant(etatTrain.getMonRail()).getJonctionTete()
					.getId())
				return true;

		}

		else if (etatTrain.getMonRail().getJonctionTete()
				.getSuivant(etatTrain.getMonRail()).getJonctionQueue() != null
				&& etatTrain.getMonRail().getJonctionTete() != null) {
			if (etatTrain.getMonRail().getJonctionTete().getId() == etatTrain
					.getMonRail().getJonctionTete()
					.getSuivant(etatTrain.getMonRail()).getJonctionQueue()
					.getId())
				return true;
		}

		return false;
	}

*/
