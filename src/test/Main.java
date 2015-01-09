package test;

import reseauxFerroviaire.Aiguillage;
import reseauxFerroviaire.Direction;
import reseauxFerroviaire.ElementRegulation;
import reseauxFerroviaire.ReseauxFerroviaireFactory;
import reseauxFerroviaire.Train;
import exception.CapteurExeption;
import exception.FactoryException;
import exception.TrainException;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			Aiguillage aiguillage = ReseauxFerroviaireFactory.getAiguillageTriRail();
			ElementRegulation elementRegulation = ReseauxFerroviaireFactory.getElementRegulation(aiguillage);
			
			Train trainA = new Train(10, 3, aiguillage.getListeRail().get(0), Direction.AVAL);
			Train trainB = new Train(10, 3, aiguillage.getListeRail().get(1), Direction.AVAL);
			
			System.out.println("Création du reseaux effectuer avec succés");
			trainA.run();
			trainB.run();
		} catch (TrainException | FactoryException | CapteurExeption e) {
			System.out.println(e.getMessage());
		}

	}
}
