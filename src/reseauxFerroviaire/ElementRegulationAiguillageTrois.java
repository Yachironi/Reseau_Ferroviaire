package reseauxFerroviaire;

import java.util.LinkedList;
import java.util.Queue;

public class ElementRegulationAiguillageTrois extends ElementRegulation {

	private static int priorite = -1;
	private int maPriorite = -1;
	Queue<Rail> demandes = new LinkedList<Rail>();

	@Override
	protected void demandePassage(Rail rail) {
		demandes.add(rail);
		regulation(rail);
	}

	/**
	 * Changement d'état de l'aiguillage : changment d'entree et du sortie
	 * @param rail
	 */
	protected void changeConfiguration(Rail rail){
		switch (aiguillage.getListeRail().indexOf(rail)) {
		case 0:
			aiguillage.setEntree(rail);
			aiguillage.setSortie(aiguillage.getListeRail().get(1));
			//aiguillage.getEntree().getJonctionTete()
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
	protected void regulation(Rail rail) {
		
		changeConfiguration(rail);
		
	}

}
