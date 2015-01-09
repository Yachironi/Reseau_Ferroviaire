package reseauxFerroviaire;

import exception.RailException;

public abstract class Jonction {
	  
	private static int idGen = 0;
	private int id; 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jonction(){
		id = idGen++;
	}
	
	public abstract Rail getSuivant(Rail rail) throws RailException;

	@Override
	public String toString() {
		return "Jonction [id=" + id + "]";
	}
	
}
