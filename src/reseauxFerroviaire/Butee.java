package reseauxFerroviaire;

public class Butee extends Jonction {
		   
	    private static int idGen = 0;
	    private int id; 
	    private Rail rail;
	   
	    public Butee() {
	        id = idGen++;
	        rail = null;
	    }

	    public Butee(Rail rail) {
	        id = idGen++;
	        this.rail = rail;
	    }
	   
	  

}
