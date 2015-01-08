package reseauxFerroviaire;

import exception.RailException;

public class EtatCourant {
	private int posiTete;
	private Direction sens;
	private int vitesseCourante;
	private Rail monRail;

	public EtatCourant(int position, Rail rail, Direction sens) {
		this.sens = sens;
		this.vitesseCourante = 0; /*
								 * A l'initialisation la vitesse courante est à
								 * 0
								 */
		this.monRail = rail;
		this.posiTete = position;
	}

	public int getPosiTete() {
		return posiTete;
	}

	public void setPosiTete(int posiTete) {
		this.posiTete = posiTete;
	}

	public Direction getSens() {
		return sens;
	}

	public void setSens(Direction sens) {
		this.sens = sens;
	}

	public int getVitesseCourante() {
		return vitesseCourante;
	}

	public void setVitesseCourante(int vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
	}

	public Rail getMonRail() {
		return monRail;
	}

	public void setMonRail(Rail monRail) {
		posiTete = 0;
		this.monRail = monRail;
	}

	

	public void decrementePos() throws RailException {
		if (posiTete - vitesseCourante > 0) {
			posiTete -= vitesseCourante;
		} else {
			throw new RailException("Depassement de rail");
		}

	}

	@Override
	public String toString() {
		return "EtatCourant [posiTete=" + posiTete + ", sens=" + sens
				+ ", vitesseCourante=" + vitesseCourante + ", monRail="
				+ monRail + "]";
	}

}
