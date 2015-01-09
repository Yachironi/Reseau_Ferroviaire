package reseauxFerroviaire;

import java.util.Observable;

import exception.CapteurExeption;

public abstract class Capteur extends Observable {

	private static int idGen = 0;
	protected int id;
	protected int position;
	protected Rail monRail;

	/**
	 * Constructeur sans paramètres
	 */
	public Capteur() {
		this.id = idGen++;
		this.position = 0;
		this.monRail = null;
	}

	/**
	 * Constructeur parametré
	 * 
	 * @param position
	 *            Le numéro de tronçon où le capteur va être placer
	 * @param monRail
	 *            le rail où le capteur va être placé le capteur
	 * @throws CapteurExeption
	 */
	public Capteur(int position, Rail monRail) throws CapteurExeption {
		this.id = idGen++;
		this.monRail = monRail;
		if (position <= 0 || position > monRail.getLongueur())
			throw new CapteurExeption("Impossible de positioner un capteur à cet endroit");
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) throws CapteurExeption {
		if (position <= 0 || position > monRail.getLongueur())
			throw new CapteurExeption("Impossible de positioner un capteur à cet endroit");
		this.position = position;
	}

	public Rail getMonRail() {
		return monRail;
	}

	public void setMonRail(Rail monRail) {
		this.monRail = monRail;
	}

	/**
	 * Elle change la valeur du capteur lors du passage au dessus
	 */
	abstract public void update();

	public boolean equals(Object arg) {
		if (arg == null) {
			return false;
		}
		if (arg == this) {
			return true;
		}
		try {
			Capteur capteur = (Capteur) arg;
			if (capteur.id == this.id) {
				return true;
			} else {
				return false;
			}
		} catch (ClassCastException e) {
			return false;
		}
	}
}
