import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
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
  private static final ArrayList<Terrain> lesTerrains = new ArrayList();
  public static Arme[] listeArme;
  
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
      new Arme(25, -42, 0, 1, 0, 4), 
      new Arme(15, 50, 1, 2, 1, 8), 
      new Arme(20, 120, 2, 2, 2, 4), 
      new Arme(10, 240, 2, 2, 4, 32), 
      new Arme(40, 42, 3, 1, 5, 2), 
      new Arme(125, 12, 3, 3, 1, 1), 
      new Arme(150, 5, 1, 3, 1, 2), 
      new Arme(50, 14, 1, 2, 2, 4), 
      new Arme(10, 1024, 5, 3, 5, 60) };
    System.out.println(listeArme[0].getPuissance());
    started = true;
    lesTerrains.add(new Terrain(0));
    ((Terrain)lesTerrains.get(0)).setActif(true);
    getActivTerrain().setPerso(new Player(500, 375, 4));
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
      GL11.glClear(16384);
      
      getActivTerrain().run();
      GL11.glEnable(3042);
      
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
      GL11.glMatrixMode(5889);
      GL11.glOrtho(0.0D, winWidth, 0.0D, winHeight, 1.0D, -1.0D);
      GL11.glMatrixMode(5888);
      GL11.glEnable(3553);
      GL11.glEnable(2903);
      GL11.glBlendFunc(770, 771);
      GL11.glLoadIdentity();
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
    if (Keyboard.isKeyDown(18))
    {
      Display.destroy();
      System.exit(0);
    }
    if (Keyboard.isKeyDown(44)) {
      needMove(lePerso, 0.0D, lePerso.getVitesse());
    }
    if (Keyboard.isKeyDown(16)) {
      needMove(lePerso, -lePerso.getVitesse(), 0.0D);
    }
    if (Keyboard.isKeyDown(32)) {
      needMove(lePerso, lePerso.getVitesse(), 0.0D);
    }
    if (Keyboard.isKeyDown(31)) {
      needMove(lePerso, 0.0D, -lePerso.getVitesse());
    }
    if (Keyboard.isKeyDown(30))
    {
      System.out.println("P " + lePerso.getX() + " " + lePerso.getY());
      System.out.println("D " + mxr + " " + myr);
      System.out.println("M " + Mouse.getX() + " " + Mouse.getY());
      System.out.println("R " + (Mouse.getX() - lePerso.getX() - mxr) + " " + (Mouse.getY() - lePerso.getY() - myr));
      System.out.println();
    }
    if (Mouse.isButtonDown(1))
    {
      GL11.glDisable(3042);
      GL11.glBegin(1);
      GL11.glVertex2i(lePerso.getX(), lePerso.getY());
      GL11.glVertex2i(Mouse.getX() - mxr + (Mouse.getX() - mxr - lePerso.getX()) * 1000, Mouse.getY() - myr + (Mouse.getY() - myr - lePerso.getY()) * 1000);
      GL11.glEnd();
      GL11.glEnable(3042);
    }
    if ((Mouse.isButtonDown(0)) && 
      (lePerso.getCooldown() <= 0)) {
      lePerso.tirer(Mouse.getX() - mxr - lePerso.getX(), Mouse.getY() - myr - lePerso.getY());
    }
    lePerso.heatCooldown();
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
  
  public static void needMove(Entity entite, double dx, double dy)
  {
    entite.move(dx, dy);
  }
}
