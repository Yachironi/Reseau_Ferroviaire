package reseauxFerroviaire;

import exception.CapteurExeption;

public class CapteurPresence extends Capteur {

	private boolean trainPresent;

	public CapteurPresence(int num, Rail rail) throws CapteurExeption {
		super(num, rail);
		trainPresent = false;
	}

	public CapteurPresence() {
		super();
		trainPresent = false;
	}

	public boolean isTrainPresent() {
		return trainPresent;
	}

	public void setTrainPresent(boolean trainPresent) {
		if (trainPresent == true) {
			this.trainPresent = trainPresent;
			setChanged();
			notifyObservers(this);
		} else {
			this.trainPresent = trainPresent;
		}
	}

	/**
	 * Elle change la valeur du capteur lors du passage au dessus
	 */
	@Override
	public void update() {
		if (monRail.getTrainAt(position) != null) {
			setTrainPresent(true);
		} else {
			setTrainPresent(false);
		}
	}
}
