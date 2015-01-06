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
		// TODO Auto-generated method stub
		return null;
	}
	
}
