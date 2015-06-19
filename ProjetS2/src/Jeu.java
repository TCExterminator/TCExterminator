import java.awt.Font;
import java.io.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Jeu
{
  public static int winWidth = 1024;
  public static int winHeight = winWidth * 3 / 4;
  public static int mxr = 0;
  public static int myr = 0;
  private static String title = "Shinkuest";
  private static String version = "alpha 1.0";
  private static boolean started = false;
  private static final ArrayList<Terrain> lesTerrains = new ArrayList<Terrain>();
  public static Arme[] listeArme;
  private static IA ia = new IA();
  public static HUD stats;
  
  public static void stop()
  {
    started = false;
  }
  
  public static Terrain getActivTerrain()
  {
    Terrain played = null;
    for (Terrain t : lesTerrains) {
      if (t.IsActif()) {
        played = t;
      }
    }
    return played;
  }
  
  public static void initialiser()
  {
    listeArme = new Arme[] {
      new Arme(35, -42, 0, 5, 0, 10), 
      new Arme(20, 50, 1, 100, 1, 8), 
      new Arme(20, 120, 2, 100, 2, 4), 
      new Arme(10, 240, 2, 1000, 4, 32), 
      new Arme(40, 42, 3, 100, 5, 2), 
      new Arme(125, 12, 3, 500, 1, 1), 
      new Arme(150, 5, 1, 50, 1, 2), 
      new Arme(50, 14, 1, 100, 2, 4), 
      new Arme(10, 1024, 5, 1000, 5, 1) };
   // Arme(int puissance, int munition, int poid, int portee, int dispertion, int cadence)
    System.out.println(listeArme[0].getPuissance());
    started = true;
    lesTerrains.add(new Terrain(0));
    ((Terrain)lesTerrains.get(0)).setActif(true);
    getActivTerrain().setPerso(new Player(500, 375, 4));
    stats = new HUD();
    Son.init();
  }
  
  public static void afficher()
  {
    ((Terrain)lesTerrains.get(0)).afficher();
  }
  
  public static void loop()
  {
    while (started)
    {
      if (Display.isCloseRequested()) {
        stop();
      }
		Display.update();
		Display.sync(60);
		glClear(GL_COLOR_BUFFER_BIT);
		getActivTerrain().run();
		glEnable(GL_BLEND);
		getInputs();
    }
    Display.destroy();
    System.exit(0);
  }
  
  public static void main(String[] args)
  {
    try
    {
      Display.setDisplayMode(new DisplayMode(winWidth, winHeight));
      Display.setTitle(title + " - " + version);
      Display.create();
		glMatrixMode(GL_PROJECTION);
		glOrtho(0,winWidth,0,winHeight,-1,1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);  
	    glEnable (GL_COLOR_MATERIAL);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glLoadIdentity();
    }
    catch (LWJGLException e)
    {
      e.printStackTrace();
      Display.destroy();
      System.exit(1);
    }
    initialiser();
    loop();
  }
  
  public static void getInputs()
  {
    Player lePerso = getActivTerrain().getPerso();

	glDisable(GL_BLEND);
	glBegin(GL_LINES);
		glVertex2i(lePerso.getX(), lePerso.getY());
		glVertex2i((Mouse.getX()-mxr)+(((Mouse.getX()-mxr)-lePerso.getX())*1000),(Mouse.getY()-myr)+(((Mouse.getY()-myr)-lePerso.getY())*1000));
	glEnd();
	glEnable(GL_BLEND);
	
    if (Keyboard.isKeyDown(Keyboard.KEY_E))
    {
      Display.destroy();
      System.exit(0);
    }
    double dx=0.0;
    double dy=0.0;
    if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
    	dy=lePerso.getVitesse();
    }
    
    if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
    	dx=-lePerso.getVitesse();
    }
    
    if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
    	dx=lePerso.getVitesse();
    }
    
    if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
    	dy=-lePerso.getVitesse();
    }
    lePerso.move( dx, dy,getActivTerrain());
    if (Keyboard.isKeyDown(Keyboard.KEY_A))
    {
      System.out.println("P " + lePerso.getX() + " " + lePerso.getY());
      System.out.println("D " + mxr + " " + myr);
      System.out.println("M " + Mouse.getX() + " " + Mouse.getY());
      System.out.println("R " + (Mouse.getX() - lePerso.getX() - mxr) + " " + (Mouse.getY() - lePerso.getY() - myr));
      System.out.println();
    }
    
    if ((Mouse.isButtonDown(1)) && 
    	      (lePerso.getCooldown() <= 0)) {
    	lePerso.couteau(Mouse.getX() - mxr - lePerso.getX(), Mouse.getY() - myr - lePerso.getY());
    }
    
    if ((Mouse.isButtonDown(0)) && 
      (lePerso.getCooldown() <= 0)) {
      lePerso.tirer(Mouse.getX() - mxr - lePerso.getX(), Mouse.getY() - myr - lePerso.getY());
    }
    
  }
  
  public static Texture getTexture(String file)
  {
    Texture texture = null;
    try
    {
      texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/img/" + file + ".png")));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return texture;
  }
  
}
