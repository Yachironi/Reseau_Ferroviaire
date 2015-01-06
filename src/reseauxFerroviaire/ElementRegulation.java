package reseauxFerroviaire;

import java.util.Observable;
import java.util.Observer;

public class ElementRegulation implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Stub de la méthode généré automatiquement
		CapteurPresence capteurPresence = (CapteurPresence) arg;
		
	}
	

}
