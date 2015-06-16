import java.util.ArrayList;
import java.util.Random;


public class Bonus {
	private int posX;
	private int posY;
	private int effet;
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
	
	
	
	//m騁hode servant � afficher le bonus
	public void afficher(){
		//� coder
	}
	
	public int getEffet(){
		return this.effet;
	}
	
	public Arme getArme(){
		return this.a;
	}

}