package Test;

public class Train {
	
	private static int idGen =0;
	private int id;     /* Identifiant du train */ 
	private int taille; /* taile du train en nombre de trançon */
	private int vMax;   /* vitesse maximale du train */ 
	private EtatCourant etatTrain;
	
	public Train(int taille, int vmax){
		this.id = idGen++;		/* besoin de faire un vérification pour l'id */
		if(taille <=0 || vmax <=0){
			System.out.println("IF");
		}
		this.taille = taille;
		this.vMax =vmax;
		/* a completer */ 
		//getatTrain = new EtatCourant(taille, );  /* à l'init v=0, et positête = taille ? */
										/* on doit ajouter Rail aussi alors ? */
	}
	
	public void arret(){
		setVitesse(0);
	}
	
	public void setVitesse(int vitesse){
		this.etatTrain.setViesseCourante(0);
	}
	/* 
	 * Avancer // vitesse + temps 
	 * Arreter : OK
	 * Start  ? on a beosin ou pas ? 
	 */
	
	
}
