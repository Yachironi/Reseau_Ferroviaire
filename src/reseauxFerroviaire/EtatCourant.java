package reseauxFerroviaire;

import exception.RailException;

public class EtatCourant {
	private int posiTete;
	private Direction sens;
	private int vitesseCourante;
	private Rail monRail;

	/**
	 * Constructeur 
	 * @param position : position de la tete du train 
	 * @param rail : rail sur lequel est positionne le rail 
	 * @param sens : sens direction du train
	 * @param vmax : vitesse maximale du train
	 */
	public EtatCourant(int position, Rail rail, Direction sens, int vmax) {
		this.sens = sens;
		this.vitesseCourante = vmax; 
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

	@Override
	public String toString() {
		return "EtatCourant [posiTete=" + posiTete + ", sens=" + sens
				+ ", vitesseCourante=" + vitesseCourante + ", monRail="
				+ monRail + "]";
	}

}
