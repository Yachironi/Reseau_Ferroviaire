package reseauxFerroviaire;

public abstract class Jonction {
	  
	private static int idGen = 0;
	private int id; 
	
	public Jonction(){
		id = idGen++;
	}
	
	public abstract Rail getSuivant(Rail rail);
}
