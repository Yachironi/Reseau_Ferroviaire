package reseauxFerroviaire;

import exception.CapteurExeption;

public class CapteurVitesse extends Capteur {
	private int vitesseTrain;

	public CapteurVitesse() {
		super();
		vitesseTrain = 0;
	}

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
	 * Elle change la valeur du capteur lors du passage au dessus
	 */
	@Override
	public void update() {
		Train train = monRail.getTrainAt(position);
		if (train != null) {
			setVitesseTrain(train.getEtatTrain().getViesseCourante());
		} else {
			setVitesseTrain(0);
		}
	}

}
