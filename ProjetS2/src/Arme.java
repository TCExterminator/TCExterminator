
public class Arme {
	//d
	private int puissance;
	private int munition;
	private int poid;
	private int portee;
	private int dispertion;
	private int cadence;
	
	// constructeur de la classe
	public Arme(int puissance, int munition, int poid, int portee, int dispertion, int cadence){
		this.puissance = puissance;
		this.munition = munition;
		this.poid = poid;
		this.portee = portee;
		this.dispertion = dispertion;
		this.cadence = cadence;
		// a coder
	}
	
	public Arme(Arme ar){
	this.puissance = ar.puissance;
	this.munition = ar.munition;
	this.poid = ar.poid;
	this.portee = ar.portee;
	this.dispertion = ar.dispertion;
	this.cadence = ar.cadence;
		
	}
	
	// methode servant a afficher l'arme
	public void afficher(){
		// a coder
	}
	
	public int getPuissance(){
		return this.puissance;
	}
	
	public int getMunition(){
		return this.munition;
	}
	
	public int getPoid(){
		return this.poid;
	}
	
	
	public int getPortee(){
		return this.portee;
	}
	
	public int getDispertion(){
		return this.dispertion;
	}
	
	public int getCadence(){
		return this.cadence;
	}
}
