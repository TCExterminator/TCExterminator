import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;

public class Terrain
  extends Thread
{
  private static final String[] listMap = { "map0.txt", "map1.txt", "map2.txt", "map3.txt", "map4.txt", "map5.txt", "map6.txt" };
  private ArrayList<Zombie> lesZomb = new ArrayList<Zombie>();
  private int idTerrain;
  private Player lePerso;
  private ArrayList<Projectile> lesProj = new ArrayList<Projectile>();
  private Tiles[][] lesTiles;
  private ArrayList<Bonus> lesBonus = new ArrayList<Bonus>();
  private boolean actif = false;
  private IA iaZomb=new IA();
  
  public Terrain(int num)
  {
    this.idTerrain = num;
    
    Texture sol = Jeu.getTexture("sol");
    Texture wall = Jeu.getTexture("wall");
    Texture pika = Jeu.getTexture("barriere");
    
    char[][] map = lireTerrain(num);
    this.lesTiles = new Tiles[map.length][map[0].length];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        switch (map[i][j])
        {
        case '1': 
          this.lesTiles[i][j] = new Tiles(wall, '1');
          break;
        case '0': 
          this.lesTiles[i][j] = new Tiles(sol, '0');
          break;
        case '2': 
          this.lesTiles[i][j] = new Tiles(pika, '2');
          break;
        default: 
          this.lesTiles[i][j] = new Tiles(wall, '1');
        }
      }
    }
    IA.setlisteOuverte();
    IA.setLesNodes(num);
    spawnZombie();
  }
  
  public boolean IsActif()
  {
    return this.actif;
  }
  
  public void setActif(boolean b)
  {
    this.actif = b;
  }
  
  public char getTypeCase(int posX, int posY)
  {
    char res = this.lesTiles[(posX / 32)][(posY / 32)].getTypeTiles();
    return res;
  }
  
  public ArrayList<Zombie> getlesZomb()
  {
    return this.lesZomb;
  }
  
  public ArrayList<Projectile> getlesProj()
  {
    return this.lesProj;
  }
  
  public Player getPerso()
  {
    return this.lePerso;
  }
  
  public void setPerso(Player p)
  {
    this.lePerso = p;
  }
  
  public void run()
  {
    afficher();
    moveAll();
    supprimerMorts();
    lePerso.heatCooldown();
    lePerso.heatInvulnerable();
	  if(lePerso.sante<0){
		  try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Jeu.stop();
	  }
  }
  
  public void afficher()
  {
    this.actif = true;
    int j;
    for (int i = 0; i < this.lesTiles.length; i++) {
      for (j = 0; j < this.lesTiles[i].length; j++)
      {
        this.lesTiles[i][j].getTexture().bind();
        glBegin(GL_QUADS);
	        glTexCoord2f(0.0F, 0.0F);
	        glVertex2i((i + 1) * 32, j * 32);
	        glTexCoord2f(1.0F, 0.0F);
	        glVertex2i((i + 1) * 32, (j + 1) * 32);
	        glTexCoord2f(1.0F, 1.0F);
	        glVertex2i(i * 32, (j + 1) * 32);
	        glTexCoord2f(0.0F, 1.0F);
	        glVertex2i(i * 32, j * 32);
        glEnd();
        
      }
    }
    for (Zombie e : this.lesZomb) {
      e.afficher();
    }
    this.lePerso.afficher();
    Jeu.stats.renderHUD(lePerso);
    for (Projectile p : this.lesProj) {
      p.afficher();
      p.heatSante(1);
    }
  }
  
  public void moveAll()
  {
	  Terrain played = Jeu.getActivTerrain();
    for (Zombie e : this.lesZomb)
    {
    //System.out.println(e.getX()+" : "+ e.getY());
    	
    	/*
      Node one =iaZomb.IAZombie(e.getX(), e.getY(), this.idTerrain).get(0);
      int vouluX=one.getPosX()*32+16;
      int vouluY=one.getPosY()*32+16;
      */
      int vouluX=this.lePerso.getX();
      int vouluY=this.lePerso.getY();
      
      if (e.getX() < vouluX) {
         e.move( e.vitesse, 0.0D,played);
      }
      if (e.getX() > vouluX) {
    	  e.move( -e.vitesse, 0.0D,played);
      }
      if (e.getY() < vouluY) {
    	  e.move( 0.0D, e.vitesse,played);
      }
      if (e.getY() > vouluY) {
    	  e.move( 0.0D, -e.vitesse,played);
      }
    }
    for (Projectile p : this.lesProj) {
    	p.move( p.getDX(), p.getDY(),played);
    }
  }
  
  public void supprimerMorts()
  {
    boolean res = false;
    for (int i = 0; i < this.lesZomb.size();) {
      if (((Zombie)this.lesZomb.get(i)).getSante() <= 0) {
        this.lesZomb.remove(i);
      } else {
        i++;
      }
    }
    for (int i = 0; i < this.lesProj.size(); i++) {
      if (((Projectile)this.lesProj.get(i)).getSante() <= 0) {
        this.lesProj.remove(i);
      } else {
        i++;
      }
    }
  }
  
  private void spawnZombie()
  {
    Random r = new Random();
    int taille = 15;
    for (int i = 0; this.lesZomb.size() < 20;)
    {
      int a = r.nextInt(Jeu.winWidth - taille - 10);
      int b = r.nextInt(Jeu.winHeight - taille - 10);
      char point1 = getTypeCase(a, b + taille);
      char point12 = getTypeCase((int)(a + Math.cos(0.0D) * taille), (int)(b + Math.cos(0.0D) * taille));
      
      char point2 = getTypeCase(a + taille, b);
      char point23 = getTypeCase(a + (int)Math.cos(0.0D) * taille, b - (int)Math.cos(0.0D) * taille);
      
      char point3 = getTypeCase(a, b - taille);
      char point34 = getTypeCase((int)(a - Math.cos(0.0D) * taille), (int)(b - Math.cos(0.0D) * taille));
      
      char point4 = getTypeCase(a - taille, b);
      char point41 = getTypeCase((int)(a - Math.cos(0.0D) * taille), (int)(b + Math.cos(0.0D) * taille));
      if ((point1 != '1') && (point2 != '1') && (point3 != '1') &&( point4 != '1') && (point12 != '1') && (point23 != '1') && (point34 != '1') && (point41 != '1')) {
        this.lesZomb.add(new Zombie(a, b, r.nextInt(3)+1));
        i++;
      }
    }
  }
  
  
  private void spawnBonus()
  {
	  Random r = new Random();
	  int taille = 15;  
	  
  }
  
  public static char[][] lireTerrain(int numTerrain)
  {
    LinkedList<char[]> res = new LinkedList<char[]>();
    String fichier = listMap[numTerrain];
    try
    {
      InputStream ips = new FileInputStream(fichier);
      InputStreamReader ipsr = new InputStreamReader(ips);
      BufferedReader br = new BufferedReader(ipsr);
      String ligne;
      while ((ligne = br.readLine()) != null)
      {
        res.add(ligne.toCharArray());
      }
      br.close();
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
    char[][] resultat = new char[res.size()][((char[])res.getFirst()).length];
    for (int i = 0; i < resultat.length; i++) {
      resultat[i] = ((char[])res.get(i));
    }
    return resultat;
  }
  
}
