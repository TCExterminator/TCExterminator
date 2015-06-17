import java.util.ArrayList;
import java.util.Random;


public class Bonus {
	private int posX;
	private int posY;
	private int effet;
	private ArrayList<Arme> lesArmes;
	private Arme a = null;
	
	//constructeur de la classe Bonus
	public Bonus(int x, int y, int effet){
		this.posX = x;
		this.posY = y;
		this.effet = 0;
	}
	

	public Bonus(int x, int y){
		Random r = new Random();
		this.posX = x;
		this.posY = y;
		this.effet = 5;
	}
	
	
	
	//méthode servant à afficher le bonus
	public void afficher(){
		//à coder
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public int getEffet(){
		return this.effet;
	}
	
	public Arme getArme(){
		return this.a;
	}

	public void setPosX(int x){
		this.posX = x;
	}
	
	public void setPosY(int y){
		this.posY = y;
	}
	
	public void setEffet(int effet){
		this.effet = effet;
	}
	
	public void setArme(Arme arm){
		this.a = new Arme(arm);
	}
	
}