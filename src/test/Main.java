package test;

import reseauxFerroviaire.Train;
import exception.TrainException;

public class Main {

	public static void main(String[] args) {

		try {
			Train trainA = new Train(4, 100);
			Train trainB = new Train(4, 100);
		} catch (TrainException e) {
			System.out.println(e.getMessage());
		}

	}
}
