package reseauxFerroviaire;

public class JonctionSimple extends Jonction{

	private Rail amont;
	private Rail aval; 

	/**
	 * Constructeur sans parametre 
	 * 
	 */
	public JonctionSimple(){
		super();
		this.amont=null;
		this.aval=null;
	}
	
	/**
	 * Constructeur avec parametre 
	 * @param amont : rail en amont de la jonction simple
	 * @param aval : rail en aval de ja jonction simple
	 */
	public JonctionSimple(Rail amont, Rail aval){
		this.amont=amont;
		this.aval=aval;
	}

	@Override
	public Rail getSuivant(Rail rail) {
		if(rail.equals(amont)) return aval;
		else return amont;
	}

	public Rail getAmont() {
		return amont;
	}

	public void setAmont(Rail amont) {
		this.amont = amont;
	}

	public Rail getAval() {
		return aval;
	}

	public void setAval(Rail aval) {
		this.aval = aval;
	}
	
	
	
}
