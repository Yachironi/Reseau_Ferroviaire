package reseauxFerroviaire;

import java.util.ArrayList;

import exception.CapteurExeption;
import exception.FactoryException;
import exception.TrainException;

public class ReseauxFerroviaireFactory {

	public static int LongeurRailParDefaut = 30;

	/**
	 * Fabrique un segement parametrée
	 * @param nombre nombre de rails du segemnt
	 * @param buteeAmont il y a une buteé au début du segment ou pas
	 * @param buteeAval il y a une buteé à la fin du segment ou pas
	 * @param taille
	 * @return 
	 */
	public static ArrayList<Rail> getSegment(int nombre, boolean buteeAmont,
			boolean buteeAval, int taille) {
		
		ArrayList<Rail> segment = new ArrayList<Rail>();
		int tailleRail = (int) taille / nombre;

		for (int i = 0; i < nombre; i++) {
			segment.add(new Rail(tailleRail));
		}

		for (int i = 0; i < nombre -1; i++) {
			JonctionSimple jonc = new JonctionSimple(segment.get(i), segment.get(i+1));
			segment.get(i+1).setJonctionTete(jonc);
			segment.get(i).setJonctionQueue(jonc);
		}

		if (buteeAmont) {
			segment.get(0).setJonctionTete(new Butee());
		}

		if (buteeAval) {
			segment.get(segment.size() - 1).setJonctionQueue(new Butee());
		}
		return segment;
	}

	/**
	 * Fabrique un aiguillage avec un nombre donnée de rails avec des semphores
	 * bicouleur au extrémité initialisé à Rouge
	 * 
	 * @param nbr
	 *            nombre de rails gérer par cette aiguillage
	 * @param longueur
	 *            longueur des rail
	 * @return Aiguillage crée selon le nombre de rail et leur longeur donnée avec deux capteur
	 * @throws FactoryException
	 * @throws CapteurExeption  
	 */
	public static Aiguillage getAiguillage(int nbr, int longueur)
			throws FactoryException, CapteurExeption{
		if (nbr > 2) {
			ArrayList<Rail> listeRail = new ArrayList<Rail>();
			Aiguillage aiguillage = new Aiguillage(listeRail, null, null);
			
			for (int i = 0; i < nbr; i++) {
				Rail rail = new Rail(longueur);
				SemaphoreBiCouleur semaTete = new SemaphoreBiCouleur(
						EtatSemaphoreRouge.getInstance(), Direction.AMONT,
						rail.getLongueur());
				SemaphoreBiCouleur semaQueue = new SemaphoreBiCouleur(
						EtatSemaphoreRouge.getInstance(), Direction.AVAL, 0);
				rail.setEtatSemaQueue(semaQueue);
				rail.setEtatSemaTete(semaTete);
				rail.setJonctionQueue(aiguillage);
				rail.setJonctionTete(new Butee(rail));
				CapteurPresence capteurPresenceTete = new CapteurPresence(1,rail);
				CapteurPresence capteurPresenceQueue = new CapteurPresence(longueur,rail);
				ArrayList<Capteur> listeCapteur = new ArrayList<>();
				listeCapteur.add(capteurPresenceQueue);
				listeCapteur.add(capteurPresenceTete);
				rail.setCapteurs(listeCapteur);
				rail.setTrains(new ArrayList<Train>());
				
				listeRail.add(rail);
			}
			aiguillage.setListeRail(listeRail);

			return aiguillage;
		} else {
			throw new FactoryException(
					"La fabriquacation de cet aiguillage est impossible");
		}
	}

	/**
	 * Fabrique un aiguillage avec Trois rails connectées
	 * 
	 * @return Aiguillage crée
	 * @throws FactoryException
	 * @throws CapteurExeption 
	 */
	public static Aiguillage getAiguillageTriRail() throws FactoryException, CapteurExeption {
		return getAiguillage(3, LongeurRailParDefaut);
	}

	/**
	 * Fabrique un aiguillage avec Quatre rails connectées
	 * @return Aiguillage crée
	 * @throws FactoryException
	 * @throws CapteurExeption 
	 */
	public static Aiguillage getAiguillageQuadRail()
			throws FactoryException, CapteurExeption {
		return getAiguillage(4, LongeurRailParDefaut);
	}

	/**
	 * Fabrique un segment avec nbr rails liée entre eux avec des jonction
	 * simple au milieux
	 * 
	 * @param nbr
	 *            nombre de rail du segment
	 * @return
	 */
	public static ArrayList<Rail> getSegment(int nbr) {
		ArrayList<Rail> listeRail = new ArrayList<Rail>();
		Rail rail = new Rail(LongeurRailParDefaut);
		rail.setJonctionQueue(new JonctionSimple());
		rail.setJonctionTete(new Butee(rail));
		listeRail.add(rail);
		for (int i = 1; i < nbr - 1; i++) {
			rail = new Rail(LongeurRailParDefaut);
			rail.setJonctionQueue(new JonctionSimple());
			rail.setJonctionTete(listeRail.get(i - 1).getJonctionQueue());
			listeRail.add(rail);
		}
		rail = new Rail(LongeurRailParDefaut);
		rail.setJonctionQueue(new Butee());
		rail.setJonctionTete(listeRail.get(listeRail.size() - 1)
				.getJonctionQueue());
		listeRail.add(rail);
		return listeRail;
	}

	/**
	 * Fabrique l'element de régulation associée à l'aiguillage donnée en parmaètre
	 * @param aiguillage aiguillage dont l'element de régulation va controlle
	 * @return Element de Regulation
	 */
	public static ElementRegulation getElementRegulation(Aiguillage aiguillage) {
		ElementRegulation elementRegulation = new ElementRegulation(aiguillage);

		// Association des capteurs de l'eguillage à l'element de regulation
		for(int i=0;i<3;i++){
			aiguillage.getListCapteurPresence().get(i).addObserver(elementRegulation);	
		}
		return elementRegulation;
	}
	
	/**
	 * Permet de faire l'union de deux segment, on leur appliquant une jonction simple 
	 * Prend en parametre les deux listes dont il faut faire l'union
	 * Return une liste regroupant les deux listes
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static ArrayList<Rail> getUnionSegment(ArrayList<Rail> list1, ArrayList<Rail> list2){
		
		ArrayList<Rail> unionSegment = new ArrayList<Rail>();
		Jonction union = new JonctionSimple(list1.get(list1.size()-1),list2.get(0));
		list1.get(list1.size()-1).setJonctionQueue(union);
		list2.get(0).setJonctionTete(union);
		unionSegment.addAll(list1);
		unionSegment.addAll(list2);
		return unionSegment;
	}
	
	
	
}
