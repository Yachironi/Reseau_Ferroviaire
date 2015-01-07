package reseauxFerroviaire;

public class JonctionSimple extends Jonction{

	private Rail amont;
	private Rail aval; 
	
	public JonctionSimple(){
		super();
		this.amont=null;
		this.aval=null;
	}
	
	public JonctionSimple(Rail amont, Rail aval){
		this.amont=amont;
		this.aval=aval;
	}

	@Override
	public Rail getSuivant(Rail rail) {
		if(rail.equals(amont)) return aval;
		else return amont;
	}
	
	
	
}
