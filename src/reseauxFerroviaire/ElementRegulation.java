package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import exception.ElementRegulationException;
import exception.EtatSemaphoreException;

public class ElementRegulation implements Observer {

	ArrayList<Capteur> capteurs;
	Aiguillage aiguillage;
	Queue<Rail> demandes = new LinkedList<Rail>();

	public ElementRegulation(Aiguillage aiguillage) {
		this.aiguillage = aiguillage;
	}

	public ElementRegulation() {
		this.aiguillage = null;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
		Capteur capteur = (Capteur) arg;

		if (!aiguillage.getListeRail().contains(capteur)) {
			System.out
					.println("Le capteur n'appartient pas à l'aguillage de l'element de regulation");
		}
		/**
		 * Assure que lorsque le train sort de l'aguillage il lance pas une
		 * demande de passage
		 */
		else if (!capteur.getMonRail().equals(aiguillage.getSortie())) {
			System.out.println("capteur a detecte la presence d'un train");
			demandePassage(capteur.getMonRail());
		} else if (capteur.getMonRail().equals(aiguillage.getSortie())) {
			try {
				aiguillage.setSemaphoreConfiguration(
						EtatSemaphoreRouge.getInstance(),
						aiguillage.getListeRail().indexOf(
								aiguillage.getEntree()),aiguillage.getId());
			} catch (EtatSemaphoreException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Ajout de demande de passage venant du rail d'entrée
	 * 
	 * @param rail : Rail ou il y a une demande de passage
	 *            
	 */
	protected void demandePassage(Rail rail) {
		demandes.add(rail);
		regulation();
	}

	/**
	 * Gére les demandes de passage par ordre d'arrivé grace à un mechanisme de
	 * fille d'attente implmenté l'iterface Queue de l'api native de java. Cette
	 * methode est thread safe pour être sur si jamais il y a deux ellements de
	 * regulation sur une même aiguillage, il y aura pas de collission
	 */
	protected synchronized void regulation() {

		Rail railDemande = demandes.poll();
		try {
			if (!changeConfiguration(railDemande)) {
				demandes.add(railDemande);
			} else {
				System.out.println(railDemande.getTrains().get(0)
						+ " a passé par l'aiguillage " + aiguillage
						+ " vers le rail " + aiguillage.getSortie().getId());
			}
		} catch (ElementRegulationException | EtatSemaphoreException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Changement d'état de l'aiguillage : changment d'entree et du sortie
	 * 
	 * @param rail
	 *            entrée
	 * @throws ElementRegulationException
	 * @throws EtatSemaphoreException
	 */
	protected boolean changeConfiguration(Rail rail)
			throws ElementRegulationException, EtatSemaphoreException {
		int indexSortie = getRailSortiePolitique(aiguillage.getListeRail()
				.indexOf(rail));
		if (voieLibre(rail, indexSortie)) {
			aiguillage.setEntree(rail);
			aiguillage.setSortie(aiguillage.getListeRail().get(indexSortie));
			aiguillage.setSemaphoreConfiguration(EtatSemaphoreVert
					.getInstance(), aiguillage.getListeRail().indexOf(rail),aiguillage.getId());
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Verification de possibilité de passage sur le rail de sortie
	 * 
	 * @param rail
	 *            Rail d'entrée
	 * @param i
	 *            index du rail de sortie sur la liste des rails de l'aiguillage
	 * @return Renvoi si le train qui vient sur le rail d'entreé peut rouller
	 *         sur la rail de sortie ou non
	 */
	private boolean voieLibre(Rail rail, int i) {
		if (aiguillage.getListeRail().get(i).getTrains().size() > 0) {
			if (aiguillage.getListeRail().get(i).getTrains().get(0)
					.getEtatTrain().getSens() == rail.getTrains().get(0)
					.getEtatTrain().getSens()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * Mettre le rail de sortie selon une politique donnée, la methode par
	 * defaut implementé ici gére le rail de sortie selon un algorithme qui
	 * ajout 2 à l'index d'entree Pour changer cette politique , vous devez
	 * faire une redefinition à l'heritage
	 * 
	 * @param indexEntree
	 * @return
	 */
	private int getRailSortiePolitique(int indexEntree) {
		if (indexEntree == aiguillage.getListeRail().size() - 1) {
			return 1;
		} else if (indexEntree == aiguillage.getListeRail().size() - 2) {
			return 0;
		} else {
			return indexEntree + 2;
		}
	}
}
