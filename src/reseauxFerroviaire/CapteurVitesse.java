package reseauxFerroviaire;

import exception.CapteurExeption;

public class CapteurVitesse extends Capteur {
	private int vitesseTrain;
	/**
	 * Constructeur de CapteurVitesse sans parametre
	 */
	public CapteurVitesse() {
		super();
		vitesseTrain = 0;
	}
	
	/**
	 * Constructeur de CapteurVitesse 
	 * @param position : correspond à sa position sur le rail, le trançon sur lequel il se trouve
	 * @param monRail : le rail sur lequel il est positionne
	 * @throws CapteurExeption 
	 */
	public CapteurVitesse(int position, Rail monRail) throws CapteurExeption {
		super(position, monRail);
		vitesseTrain = 0;
	}

	public int getVitesseTrain() {
		return vitesseTrain;
	}

	public void setVitesseTrain(int vitesseTrain) {
		this.vitesseTrain = vitesseTrain;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Change la valeur du capteur lors d'un passage par dessus
	 */
	@Override
	public void update() {
		Train train = monRail.getTrainAt(position);
		if (train != null) {
			setVitesseTrain(train.getEtatTrain().getVitesseCourante());
		} else {
			setVitesseTrain(0);
		}
	}

}
