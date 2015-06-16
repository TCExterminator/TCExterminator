
public abstract class Entite {

	protected int posX;
	protected int posy;
	protected int vitesse;
	protected int sante;
	
	//constructeur de la classe
	public Entite(){
		//à coder
	}
	
	//méthode servant à déplacer une entite
	public void move(){
		//à coder
	}
	
	//méthode servant à afficher une entite
	public void afficher(){
		//à coder
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
