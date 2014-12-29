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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Rail getRail() {
			return rail;
		}

		public void setRail(Rail rail) {
			this.rail = rail;
		}
	   
	    

}
