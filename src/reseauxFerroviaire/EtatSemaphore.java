package reseauxFerroviaire;

import java.awt.Color;

public class EtatSemaphore {
	
	private double ratioRalentissement;

	public EtatSemaphore() {
		this.ratioRalentissement=0.0;
	}

	public double getRatioRalentissement() {
		return ratioRalentissement;
	}

	public void setRatioRalentissement(double ratioRalentissement) {
		this.ratioRalentissement = ratioRalentissement;
	}
	
}
