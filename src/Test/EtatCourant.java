package Test;

public class EtatCourant {
	private int posiTete;          
	private Direction sens;
	private int viesseCourante;
	private Rail myRail;
	
    public EtatCourant(int size, Rail rail, Direction sens) {
        this.sens = sens;
        this.viesseCourante = 0;    /* A l'initialisation la vitesse courante est à 0 */  
        this.myRail = rail;
        this.posiTete = size; 
        
        /* Dnas le site il font un truc bizare que je comprend pas trop */
   
    }

	public int getPosiTete() {
		return posiTete;
	}
	public void setPosiTete(int posiTete) {
		this.posiTete = posiTete;
	}
	public Direction getSens() {
		return sens;
	}
	public void setSens(Direction sens) {
		this.sens = sens;
	}
	public int getViesseCourante() {
		return viesseCourante;
	}
	public void setViesseCourante(int viesseCourante) {
		this.viesseCourante = viesseCourante;
	}
	

	
}
