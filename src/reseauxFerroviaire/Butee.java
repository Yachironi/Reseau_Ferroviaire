package reseauxFerroviaire;

import exception.RailException;

public class Butee extends Jonction {
	private Rail rail;
	/**
	 * Constructeur d'une butee
	 */
	public Butee() {
		super();
		rail = null;
	}
	/**
	 * Constructeur d'une 
	 * @param rail
	 */
	public Butee(Rail rail) {
		super();
		this.rail = rail;
	}

	public Rail getRail() {
		return rail;
	}

	public void setRail(Rail rail) {
		this.rail = rail;
	}

	@Override
	/**
	 * Le principe d'une butee etant la fin d'une rail, il n'y a pas de rail suivant
	 */
	public Rail getSuivant(Rail rail) throws RailException {
		throw new RailException("Deraillement pour cause de presence de butee");
	}

}
