package reseauxFerroviaire;

import java.awt.Color;

public abstract class EtatSemaphore {
	
	protected double ratioRalentissement;

	public EtatSemaphore(double n) {
		this.ratioRalentissement=n;
	}

	public double getRatioRalentissement() {
		return ratioRalentissement;
	}

	public void setRatioRalentissement(double ratioRalentissement) {
		this.ratioRalentissement = ratioRalentissement;
	}
	
}
