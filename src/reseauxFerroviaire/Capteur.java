package reseauxFerroviaire;

import java.util.Observable;

import exception.CapteurExeption;

public class Capteur extends Observable {

	private static int idGen = 0;
	private int id;
	private int position;
	private boolean trainPresent;
	private Rail monRail;

	/**
	 * Constructeur sans paramètres
	 */
	public Capteur() {
		this.id = idGen++;
		this.position = 0;
		this.monRail = null;
		this.trainPresent = false;
	}

	/**
	 * Constructeur parametré
	 * @param position Le numéro de tronçon où le capteur va être placer
	 * @param monRail le rail où le capteur va être placé
	 */
	public Capteur(int position, Rail monRail) {
		this.id = idGen++;
		this.position = position;
		this.monRail = monRail;
		this.trainPresent = false;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) throws CapteurExeption{
		if(position <=0 || position >monRail.getLongueur()) throw new CapteurExeption("Impossible de positioner à cet endroit");
		this.position = position;
	}

	public boolean isTrainPresent() {
		return trainPresent;
	}

	public void setTrainPresent(boolean trainPresent) {
		this.trainPresent = trainPresent;
		setChanged();
		notifyObservers();
	}

	public Rail getMonRail() {
		return monRail;
	}

	public void setMonRail(Rail monRail) {
		this.monRail = monRail;
	}
}
