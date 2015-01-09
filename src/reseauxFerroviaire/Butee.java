package reseauxFerroviaire;

import exception.RailException;

public class Butee extends Jonction {
	private Rail rail;

	public Butee() {
		super();
		rail = null;
	}

	public Butee(Rail rail) {
		this.rail = rail;
	}

	public Rail getRail() {
		return rail;
	}

	public void setRail(Rail rail) {
		this.rail = rail;
	}

	@Override
	public Rail getSuivant(Rail rail) throws RailException {
		throw new RailException("Deraillement pour cause d'un butee presente");
	}

}
