package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Iterator;

import exception.AiguillageException;
import exception.EtatSemaphoreException;

public class Aiguillage extends Jonction {

	private ArrayList<Rail> listeRail; /* rail possible à */
	private Rail entree;
	private Rail sortie;

	/**
	 * Constructeur
	 * 
	 * @param listeRailAval
	 * @param sortieAmont
	 * @param sortieAval
	 * @throws AiguillageException
	 */
	public Aiguillage(ArrayList<Rail> listeRail, Rail sortieAmont,
			Rail sortieAval){
			super();
			this.listeRail = listeRail;
			this.entree = sortieAmont;
			this.sortie = sortieAval;
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

	public Rail getEntree() {
		return entree;
	}

	public void setEntree(Rail sortieAmont) {
		this.entree = sortieAmont;
	}

	public Rail getSortie() {
		return sortie;
	}

	public void setSortie(Rail sortieAval) {
		this.sortie = sortieAval;
	}

	@Override
	public Rail getSuivant(Rail rail) {
		if(rail.equals(entree)){
			return sortie;
		}
		else{
			return entree;
		}
	}
	
	public ArrayList<Rail> getListeRail() {
		return listeRail;
	}

	public void setListeRail(ArrayList<Rail> listeRail) {
		this.listeRail = listeRail;
	}
	/**
	 * 
	 * @return l'ensemble des capteurs present sur la rail
	 */
	public ArrayList<CapteurPresence> getListCapteurPresence() {
		ArrayList<CapteurPresence> capteursPresence = new ArrayList<>();
		for (Iterator iterator = listeRail.iterator(); iterator.hasNext();) {
			Rail rail = (Rail) iterator.next();
			capteursPresence.add(rail.getListCapteurPresence().get(0));
		}
		return capteursPresence;
	}
	/**
	 * Permet de modifier les etats des different semaphores a l'aide d'une liste contenant divers etats possibles
	 * @param etatsSemaphore
	 * @throws EtatSemaphoreException
	 */
	protected void setSemaphoresConfiguration(ArrayList<EtatSemaphore> etatsSemaphore) throws EtatSemaphoreException{
		for (int i = 0; i< listeRail.size();i++) {
			listeRail.get(i).getSemaQueue().setEtat(etatsSemaphore.get(i));
		}
	}
	/**
	 * Modifie l'etat d'un semaphore 
	 * @param etatSemaphore : etat du sempahore que l'on veut passer
	 * @param railIndex : index de la rail sur lequel il est present
	 * @param id : identifiant du semaphore a modifier
	 * @throws EtatSemaphoreException
	 */
	protected void setSemaphoreConfiguration(EtatSemaphore etatSemaphore,int railIndex,int id) throws EtatSemaphoreException{
		listeRail.get(railIndex).getSemaQueue().setEtat(etatSemaphore);
		if(listeRail.get(railIndex).getJonctionTete().getId()==id){
			listeRail.get(railIndex).getSemaTete().setEtat(etatSemaphore);
		}else if(listeRail.get(railIndex).getJonctionQueue().getId()==id){
			listeRail.get(railIndex).getSemaQueue().setEtat(etatSemaphore);
		}
	}

	@Override
	public String toString() {
		return ("Aiguillage ["+getId()+"]");
	}
	
}
