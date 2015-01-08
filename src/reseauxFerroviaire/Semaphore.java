package reseauxFerroviaire;

import java.util.ArrayList;

import exception.EtatSemaphoreException;

public abstract class Semaphore {

	protected EtatSemaphore etat;
	protected ArrayList<EtatSemaphore> etatsPossibles;
	protected int id;
	private static int idGen = -1;
	protected Direction sens;
	protected int position;

	public Semaphore() {
		this.etat = null;
		this.sens = null;
		this.position = -1;
		this.id = idGen++;
	}

	public Semaphore(EtatSemaphore etat, Direction sens, int position) {
		this();
		this.etat = etat;
		this.sens = sens;
		this.position = position;
	}

	public EtatSemaphore getEtat() {
		return etat;
	}

	public EtatSemaphore getEtatSuivant() {
		int rang = etatsPossibles.indexOf(etat);
		if (rang < etatsPossibles.size() - 1) {
			return etatsPossibles.get(rang + 1);
		} else {
			return etatsPossibles.get(0);
		}
	}
	public void setEtat(EtatSemaphore etat)
			throws EtatSemaphoreException {
		if (!this.etat.equals(etat)&&this.etat.equals(getEtatSuivant())) {
			this.etat = etat;
		} else if(!this.etat.equals(getEtatSuivant())) {
			throw new EtatSemaphoreException("Changement d'etat incoherent");
		}
	}

}
