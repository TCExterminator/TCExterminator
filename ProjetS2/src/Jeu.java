import static org.lwjgl.opengl.GL11.*;

import java.io.*;
import java.util.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;
public class Jeu {
	
	//attributs de la classes jeu
	public static int winWidth =  1024;
	public static int winHeight =  winWidth*3/4;
	public static int mxr=0;
	public static int myr=0;
	private static String title = "Shinkuest";
	private static String version = "alpha 1.0";
	private static boolean started = false;	
	private static final ArrayList<Terrain> lesTerrains = new ArrayList<Terrain>() ;
	public static Player joueur;
	public static Arme[] listeArme;

	//methode de classe servant a stopper le jeu
	public static void stop(){
		started=false;
	}

	//methode servant a initialiser le personnage
	public static void initialiser(){
		
		listeArme = new Arme[]{
				   new Arme(25,-42,0,1,0,4),
				   new Arme(15,50,1,2,1,8),
				   new Arme(20,120,2,2,2,4),
				   new Arme(10,240,2,2,4,32),
				   new Arme(40,42,3,1,5,2),
				   new Arme(125,12,3,3,1,1),
				   new Arme(150,5,1,3,1,2),
				   new Arme(50,14,1,2,2,4),
				   new Arme(10,1024,5,3,5,60)};
		System.out.println(listeArme[0].getPuissance());
		started=true;
		joueur =new Player(500, 375,4,"perso");
		lesTerrains.add(new Terrain());
		
	}
	
	//methode servant a afficher un terrain
	public static void afficher(){
		lesTerrains.get(0).afficher();
		joueur.afficher();
	}
	
	public static void movements(){
		for(Terrain t: lesTerrains){
			if(t.isActif()){
				for (Zombie e : t.getlesZomb()){
					IA ia= new IA();
					//e.followNodes(ia.IAZombie(e.getX(), e.getY(), 0).get(0));
					e.followNodes(new Node (joueur.posX,joueur.posY));
				}
				for(Projectile p : t.getlesProj()){
					p.move();
				}
			}
		}
	}
	
	//methode servant de boucle principal au jeu
	public static void loop(){
		while(started){
			if(Display.isCloseRequested()){
				stop();
			}
			Display.update();
			Display.sync(30);
			glClear(GL_COLOR_BUFFER_BIT);
			movements();
			afficher();
			getInputs();
			glEnable(GL_BLEND);
		}
		Display.destroy();
		System.exit(0);
	}


	//methode servant a lancer le jeu
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
		initialiser();
		loop();
		
	}
	
	//methode servant a recuperer les inputs du joueur
	public static void getInputs(){
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			Display.destroy();
			System.exit(0);
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			needMove(joueur,0,joueur.getVitesse());
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			needMove(joueur,-joueur.getVitesse(),0);
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			needMove(joueur,joueur.getVitesse(),0);
			
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			needMove(joueur,0,-joueur.getVitesse());
			
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
		if(Mouse.isButtonDown(0) & joueur.getCooldown()>=20 & joueur.getArmeActive().getMunition() !=0){
			int dist = (int) Math.sqrt( ((Mouse.getX()-mxr - joueur.getX()) * (Mouse.getX()-mxr - joueur.getX())) +
										((Mouse.getY()-myr - joueur.getY()) * (Mouse.getY()-myr - joueur.getY())))/10;
			System.out.println(((Mouse.getX()-mxr)-joueur.getX())/dist);
			joueur.tirer((Mouse.getX()-mxr-joueur.getX())/dist,((Mouse.getY()-myr)-joueur.getY())/dist);
		}else{
			joueur.healCooldown();
		}
	}
	
	//methode recuperant les textures 
	public static Texture getTexture(String file){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG",new FileInputStream(new File("res/img/"+file+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texture;
	}
	
	public static ArrayList<Terrain> getLesTerrains(){
		return lesTerrains;
	}
	
	
	public static void needMove(Entity entite,int dx,int dy){
		int newX=entite.getX()+dx;
		int newY=entite.getY()+dy;
		Terrain played = null;
		for (Terrain t : lesTerrains){
			//perso = cercle 12h=point1 3h=point2 6h=point3 9h=point4
			if (t.isActif()){played=t;
				char point1 = played.getTypeCase(newX,newY+15);
				char point12 = played.getTypeCase(newX+13,newY+13);
				
				char point2 = played.getTypeCase(newX+15,newY);
				char point23 = played.getTypeCase(newX+13,newY-13);
				
				char point3 = played.getTypeCase(newX,newY-15);
				char point34 = played.getTypeCase(newX-13,newY-13);
				
				char point4 = played.getTypeCase(newX-15,newY);
				char point41 = played.getTypeCase(newX-13,newY+13);
								
				if((point1 =='1')||(point2 =='1')||(point3 =='1')||(point12=='1')||(point23=='1')||(point34=='1')||(point41=='1')){dx=0;dy=0;}
				
				glTranslated(-dx/2, -dy/2, 0);
				mxr-=dx/2;
				myr-=dy/2;
				entite.move(dx,dy);
			}
		}
	
	}
}
