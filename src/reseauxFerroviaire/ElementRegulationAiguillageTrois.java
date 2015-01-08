package reseauxFerroviaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import exception.ElementRegulationException;
import exception.EtatSemaphoreException;

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
	 * @throws ElementRegulationException
	 * @throws EtatSemaphoreException
	 */
	protected void changeConfiguration(Rail rail)
			throws ElementRegulationException {

		try {
			switch (aiguillage.getListeRail().indexOf(rail)) {
			case 0:
				if (voieLibre(rail, 1)) {
					aiguillage.setEntree(rail);
					aiguillage.setSortie(aiguillage.getListeRail().get(1));
					aiguillage
							.setSemaphoreConfiguration(new ArrayList<EtatSemaphore>(
									Arrays.asList(
											EtatSemaphoreVert.getInstance(),
											EtatSemaphoreVert.getInstance(),
											EtatSemaphoreRouge.getInstance())));
				} else {
					throw new ElementRegulationException("Regulation bloqué");
				}
				break;
			case 1:
				if (voieLibre(rail, 0)) {
					aiguillage.setEntree(rail);
					aiguillage.setSortie(aiguillage.getListeRail().get(0));
					aiguillage
							.setSemaphoreConfiguration(new ArrayList<EtatSemaphore>(
									Arrays.asList(
											EtatSemaphoreVert.getInstance(),
											EtatSemaphoreVert.getInstance(),
											EtatSemaphoreRouge.getInstance())));
				}	 else {
					throw new ElementRegulationException("Regulation bloqué");
				}
				break;
			case 2:
				if (voieLibre(rail, 0)) {
				aiguillage.setEntree(rail);
				aiguillage.setSortie(aiguillage.getListeRail().get(0));
				aiguillage
						.setSemaphoreConfiguration(new ArrayList<EtatSemaphore>(
								Arrays.asList(EtatSemaphoreVert.getInstance(),
										EtatSemaphoreRouge.getInstance(),
										EtatSemaphoreVert.getInstance())));
				}	 else {
					throw new ElementRegulationException("Regulation bloqué");
				}
			default:
				break;
			}
		} catch (EtatSemaphoreException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}

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

	@Override
	protected synchronized void regulation() {

		// Test de collision !!!
		Rail railDemande = demandes.poll();

		try {
			changeConfiguration(railDemande);
			
		} catch (ElementRegulationException e) {
			System.out.println(e.getMessage());
		}

	}

}
