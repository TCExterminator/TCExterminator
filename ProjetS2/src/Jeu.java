import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;
public class Jeu {
	
	public static int winWidth =  1024;
	public static int winHeight =  winWidth*3/4;
	public static int mxr=0;
	public static int myr=0;
	private static String title = "Shinkuest";
	private static String version = "alpha 1.0";
	private static boolean started = false;
	
	private static final ArrayList<Terrain> lesTerrains = new ArrayList<Terrain>() ;
	public static Player joueur;
	
	//liste des armes du jeu
	public static Arme[] listArme = new Arme[]{new Arme(15,50,1,2,1,4),
											   new Arme(20,120,2,2,2,4),
											   new Arme(10,240,2,2,4,32),
											   new Arme(40,42,3,1,5,2),
											   new Arme(125,12,3,3,1,1),
											   new Arme(150,5,1,3,1,2),
											   new Arme(50,14,1,2,2,4),
											   new Arme(10,1024,5,3,5,60)};

	public static void stop(){
		started=false;
	}

	public static void initialiser(){
		joueur =new Player(500, 375,4,"perso");
	}
	
	public static void afficher(){
		lesTerrains.get(0).afficher();
		joueur.afficher();
	}
	
	public static void loop(){
		while(started){
			if(Display.isCloseRequested()){
				stop();
			}
			Display.update();
			Display.sync(60);
			glClear(GL_COLOR_BUFFER_BIT);
			afficher();
			getInputs();
			glEnable(GL_BLEND);
		}
		Display.destroy();
		System.exit(0);
	}



	public static void main(String[] args) {
		
		try{
			Display.setDisplayMode(new DisplayMode(winWidth,winHeight));
			Display.setTitle(title + " - " + version);
			Display.create();
			glMatrixMode(GL_PROJECTION);
			glOrtho(0,winWidth,0,winHeight,1,-1);
			glMatrixMode(GL_MODELVIEW);
			glEnable(GL_TEXTURE_2D);  
		    glEnable (GL_COLOR_MATERIAL);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glLoadIdentity();
		}catch(LWJGLException e){
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		started=true;
		initialiser();
		lesTerrains.add(new Terrain());
		loop();
	}
	
	public static void getInputs(){
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			Display.destroy();
			System.exit(0);
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			joueur.setPosition(joueur.getX(),joueur.getY()+joueur.getVitesse());
			glTranslated(0, -joueur.getVitesse()/2, 0);
			myr-=joueur.getVitesse()/2;
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			joueur.setPosition(joueur.getX()-joueur.getVitesse(),joueur.getY());
			glTranslated(joueur.getVitesse()/2, 0, 0);
			mxr+=joueur.getVitesse()/2;
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			joueur.setPosition(joueur.getX()+joueur.getVitesse(),joueur.getY());
			glTranslated(-joueur.getVitesse()/2,0, 0);
			mxr-=joueur.getVitesse()/2;
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			joueur.setPosition(joueur.getX(),joueur.getY()-joueur.getVitesse());
			glTranslated(0, joueur.getVitesse()/2, 0);
			myr+=joueur.getVitesse()/2;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			System.out.println("P "+(joueur.getX()) + " "+ (joueur.getY()));
			System.out.println("D "+(mxr) + " " + (myr));
			System.out.println("M "+Mouse.getX()+" "+ Mouse.getY());
			System.out.println("R "+(Mouse.getX()-joueur.getX()-mxr)+" "+ (Mouse.getY()-joueur.getY()-myr));
			System.out.println();
		}
		if(Mouse.isButtonDown(1)){
			glDisable(GL_BLEND);
			glBegin(GL_LINES);
				glVertex2i(joueur.getX(), joueur.getY());
				glVertex2i((Mouse.getX()-mxr)+(((Mouse.getX()-mxr)-joueur.getX())*1000),(Mouse.getY()-myr)+(((Mouse.getY()-myr)-joueur.getY())*1000));
			glEnd();
			glEnable(GL_BLEND);
		}
	}
	
	
	public static Texture getTexture(String file){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG",new FileInputStream(new File("res/img/"+file+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texture;
	}
	

}
