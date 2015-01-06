package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Iterator;

import exception.AiguillageException;

public class Aiguillage extends Jonction {

	private static int idGen = 0;
	private int id; /* identificateur de l'aiguillage */
	private ArrayList<Rail> listeRail; /* rail possible à */
	private Rail sortieAmont;
	private Rail sortieAval;

	/**
	 * Constructeur
	 * 
	 * @param listeRailAval
	 * @param sortieAmont
	 * @param sortieAval
	 * @throws AiguillageException
	 */
	public Aiguillage(ArrayList<Rail> listeRail, Rail sortieAmont,
			Rail sortieAval) throws AiguillageException {

		if (listeRail.size() >= 3) {
			this.listeRail = listeRail;
			this.sortieAmont = sortieAmont;
			this.sortieAval = sortieAval;
		} else {
			throw new AiguillageException(
					"Mauvaise instantiation du constructeur");
		}
	}

	public void ajoutRail(Rail rail) throws AiguillageException {
		if (listeRail.contains(rail)) {
			throw new AiguillageException(
					"L'ajout de la rail impossible car la rail appartient deja a notre liste de possibilité");
		} else {
			listeRail.add(rail);
		}
	}

	public void remove(Rail rail) throws AiguillageException {
		if (!listeRail.contains(rail)) {
			throw new AiguillageException(
					"L'ajout de la rail impossible car la rail n'appartient pas a la liste");
		} else {
			listeRail.remove(rail);
		}
	}

	public Rail getSortieAmont() {
		return sortieAmont;
	}

	public void setSortieAmont(Rail sortieAmont) {
		this.sortieAmont = sortieAmont;
	}

	public Rail getSortieAval() {
		return sortieAval;
	}

	public void setSortieAval(Rail sortieAval) {
		this.sortieAval = sortieAval;
	}

	public ArrayList<Rail> getListeRail() {
		return listeRail;
	}

	public void setListeRail(ArrayList<Rail> listeRail) {
		this.listeRail = listeRail;
	}

	public ArrayList<CapteurPresence> getListCapteurPresence() {
		ArrayList<CapteurPresence> capteursPresence = new ArrayList<>();
		for (Iterator iterator = listeRail.iterator(); iterator.hasNext();) {
			Rail rail = (Rail) iterator.next();
			capteursPresence.add(rail.getListCapteurPresence().get(0));
		}
		return capteursPresence;
	}
}
