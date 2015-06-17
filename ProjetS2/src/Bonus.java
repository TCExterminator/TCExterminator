import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;


public class Bonus {
	private int posX;
	private int posY;
	private int effet;
	private ArrayList<Arme> lesArmes;
	private Arme a = null;
	private Texture textureBonus;
	
	//constructeur de la classe Bonus
	public Bonus(int x, int y, int effet){
		this.textureBonus = Jeu.getTexture("pika");
		this.posX = x;
		this.posY = y;
		this.effet = effet;
	}
	

	public Bonus(int x, int y){
		this.textureBonus = Jeu.getTexture("pika");
		this.posX = x;
		this.posY = y;
		this.effet = 5;
	}
	
	
	
	//méthode servant à afficher le bonus
	public void afficher(){
		this.textureBonus.bind();
		glBegin(GL_QUADS);
		glEnd();		 
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