
public abstract class Entite {

	protected int posX;
	protected int posy;
	protected int vitesse;
	protected int sante;
	
	//constructeur de la classe
	public Entite(){
		//� coder
	}
	
	//m�thode servant � d�placer une entite
	public void move(){
		//� coder
	}
	
	//m�thode servant � afficher une entite
	public void afficher(){
		//� coder
	}
	

	public int getSante(){
		return this.sante;
	}
	
	public void setSante(int sante){
		this.sante=sante;
	}
	
	public void setVitesse(int vit){
		this.sante=vit;
	}
	
}
