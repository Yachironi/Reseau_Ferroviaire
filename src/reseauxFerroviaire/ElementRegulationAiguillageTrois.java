package reseauxFerroviaire;

import java.util.LinkedList;
import java.util.Queue;

public class ElementRegulationAiguillageTrois extends ElementRegulation {

	private static int priorite = -1;
	private int maPriorite = -1;
	Queue<Rail> demandes = new LinkedList<Rail>();
	boolean[] flag = new boolean[2];
	int turn;

	@Override
	protected void demandePassage(Rail rail) {
		demandes.add(rail);
		regulation();
	}

	/**
	 * Changement d'état de l'aiguillage : changment d'entree et du sortie
	 * 
	 * @param rail
	 */
	protected void changeConfiguration(Rail rail) {
		switch (aiguillage.getListeRail().indexOf(rail)) {
		case 0:
			aiguillage.setEntree(rail);
			aiguillage.setSortie(aiguillage.getListeRail().get(1));
			break;
		case 1:
			aiguillage.setEntree(rail);
			aiguillage.setSortie(aiguillage.getListeRail().get(0));
			break;
		case 2:
			aiguillage.setEntree(rail);
			aiguillage.setSortie(aiguillage.getListeRail().get(0));
		default:
			break;
		}
	}

	@Override
	protected synchronized void regulation() {

		// Test de collision !!!
		Rail railDemande = demandes.poll();
		
		changeConfiguration(railDemande);

		flag[0] = true;
		turn = 1;
		while (flag[1] && turn == 1) {

		}
		// Début de la section critique
		// ...
		// fin de la section critique
		flag[0] = false;

	}

	private boolean exclusionMutuel() {
		// TODO Stub de la méthode généré automatiquement
		return false;
	}

}
