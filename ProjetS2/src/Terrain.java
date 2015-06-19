import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

public class Terrain
  extends Thread
{
  private static final String[] listMap = { "map0.txt", "map1.txt", "map2.txt", "map3.txt", "map4.txt", "map5.txt", "map6.txt" };
  private ArrayList<Zombie> lesZomb = new ArrayList<Zombie>();
  //private int idTerrain;
  private Player lePerso;
  private ArrayList<Projectile> lesProj = new ArrayList<Projectile>();
  private Tiles[][] lesTiles;
  private ArrayList<Bonus> lesBonus = new ArrayList<Bonus>();
  private boolean actif = false;
  //private IA iaZomb=new IA();
  
  /**
   * constructor Terrain
   * 
   * il s'agit du constructeur parametre de la classe Terrain avec un id en int en parametre
   * 
   * @param num l'id en int du Terrain
   */
  public Terrain(int num)
  {
    //this.idTerrain = num;
    
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
    spawnBonus();
  }
  
  /**
   * methode IsActif
   * 
   * cette methode retourne un boolean permettant de savoir si le terrain est actif ou non
   * 
   * @return le retour du boolean
   */
  public boolean IsActif()
  {
    return this.actif;
  }
  
  /**
   * methode setActif
   * 
   * il s'agit du setter permettant de determiner si un Terrain est actif ou non a l'aide d'un boolean en parametre
   * 
   * @param b un boolean
   */
  public void setActif(boolean b)
  {
    this.actif = b;
  }
  
  /**
   * methode getTypeCase
   * 
   * cette methode permet de retourner le type d'une case selon une position passe en parametre
   * 
   * @param posX la position int en x
   * @param posY la position int en y
   * @return
   */
  public char getTypeCase(int posX, int posY)
  {
    char res = this.lesTiles[(posX / 32)][(posY / 32)].getTypeTiles();
    return res;
  }
  
  
  /**
   * methode getLesZomb
   * 
   * il s'agit du getter de lesZomb retourner une ArrayList de Zombie
   * 
   * @return le retour de l'ArrayList
   */
  public ArrayList<Zombie> getlesZomb()
  {
    return this.lesZomb;
  }
  /**
   * methode getLesBonus
   * 
   * il s'agit du getter de lesBonus retourner une ArrayList de Bonus
   * 
   * @return le retour de l'ArrayList
   */
  public ArrayList<Bonus> getlesBonus()
  {
    return this.lesBonus;
  }
  /**
   * methode getlesProj
   * 
   * il s'agit du getter de lesProj retournant un ArrayList de Projectile
   * 
   * @return le retour de l'ArrayList
   */
  public ArrayList<Projectile> getlesProj()
  {
    return this.lesProj;
  }
  
  /**
   * methode getPerso
   * 
   * il s'agit du getter de lePerso retournant le Player
   * 
   * @return le retour du Player
   */
  public Player getPerso()
  {
    return this.lePerso;
  }
  
  /**
   * methode setPerso
   * 
   * il s'agit du setter de lePerso
   * 
   * @param p du type Player
   */
  public void setPerso(Player p)
  {
    this.lePerso = p;
  }
  
  /**
   * methode run
   * 
   * cette methode permet de faire fonctionner tout ce qui est contenu dans le Terrain
   * 
   */
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
	if(lesZomb.size()==0){
		Texture sol = Jeu.getTexture("sol");
		for(Tiles tiles[]: this.lesTiles)
			for(Tiles t:tiles)if(t.getTypeTiles()=='2'){t.setTexture(sol);t.setTypeTiles('0');}
	}
  }
  
  /**
   * methode afficher
   * 
   * cette methode permet d'afficher tout ce qui est contenu dans le Terrain
   */
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
	        glVertex2i(i * 32, (j + 1) * 32);
	        glTexCoord2f(1.0F, 0.0F);
	        glVertex2i((i + 1) * 32, (j + 1) * 32);
	        glTexCoord2f(1.0F, 1.0F);
	        glVertex2i((i + 1) * 32, j * 32);
	        glTexCoord2f(0.0F, 1.0F);
	        glVertex2i(i * 32, j * 32);
        glEnd();
        
      }
    }
    for (Zombie e : this.lesZomb) {
        e.afficher();
      }
    for (Bonus b : this.lesBonus) {
        b.afficher();
      }
    this.lePerso.afficher();
    Jeu.stats.renderHUD(lePerso);
    for (Projectile p : this.lesProj) {
      p.afficher();
      p.heatSante(1);
    }
  }
  
  /**
   * methode moveAll
   * 
   *cette methode permet de faire deplacer toutes les Entity contenues dans le Terrain
   */
  public void moveAll()
  {
	  Terrain played = Jeu.getActivTerrain();
    for (Zombie e : this.lesZomb)
    {
    	
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
  
  /**
   * methode supprimerMorts
   * 
   * cette methode permet de ne plus afficher sur le Terrain les Entity qui non plus de sante
   * 
   */
  public void supprimerMorts()
  {
    for (int i = 0; i < this.lesZomb.size();) {
      if (((Zombie)this.lesZomb.get(i)).getSante() <= 0) {
        this.lesZomb.remove(i);
      } else {
        i++;
      }
    }
    for (int i = 0; i < this.lesProj.size();) {
      if (((Projectile)this.lesProj.get(i)).getSante() <= 0) {
        this.lesProj.remove(i);
      } else {
        i++;
      }
    }
  }
  
  /**
   * methode spawnZombie
   * 
   * cette methode permet de faire apparaitre sur le Terrain des Zombie avec une position  aleatoire sur des cases libres
   */
  private void spawnZombie()
  {
    Random r = new Random();
    int taille = 15;
    while(this.lesZomb.size() < 20)
    {
      int a = r.nextInt(Jeu.winWidth - taille - 10);
      int b = r.nextInt(Jeu.winHeight - taille - 10);
      char[] point= calculePoint(a,b,taille);
      boolean goodCase=true;
      for (char c:point){
    	  if(c=='1'||c=='2')goodCase=false;
      }
    	  
      if (goodCase) {
        this.lesZomb.add(new Zombie(a, b, r.nextInt(3)+1));
      }
    }
  }
  private char[] calculePoint(int a,int b,int taille){
	  char [] res=new char[8];
	  res[0]= getTypeCase(a, b + taille);
	  res[1]= getTypeCase((int)(a + Math.cos(0.0D) * taille), (int)(b + Math.cos(0.0D) * taille));
      
	  res[2] = getTypeCase(a + taille, b);
	  res[3] = getTypeCase(a + (int)Math.cos(0.0D) * taille, b - (int)Math.cos(0.0D) * taille);
      
	  res[4] = getTypeCase(a, b - taille);
	  res[5] = getTypeCase((int)(a - Math.cos(0.0D) * taille), (int)(b - Math.cos(0.0D) * taille));
      
	  res[6] = getTypeCase(a - taille, b);
	  res[7] = getTypeCase((int)(a - Math.cos(0.0D) * taille), (int)(b + Math.cos(0.0D) * taille));
      return res;
 }
  /**
   * methode spawnBonus
   * 
   * cette methode permet de faire apparaitre sur le Terrain des Bonus avec une position aleatoire sur des cases libres
   * 
   */
  private void spawnBonus()
  {
	  Random r = new Random();
	  int taille = 15;  
	  while(this.lesBonus.size() < 2)
	    {
	  int a = r.nextInt(Jeu.winWidth - taille - 10);
      int b = r.nextInt(Jeu.winHeight - taille - 10);
      char[] point= calculePoint(a,b,taille);
      int typeSpawn=r.nextInt(4)+1;
      boolean goodCase=true;
      for (char c:point){
    	  if(c=='1'||c=='2')goodCase=false;
      }
      if(goodCase){
    	if(typeSpawn<5){
    	  this.lesBonus.add(new Bonus(a,b,typeSpawn));
      	}
      	else if(typeSpawn == 5){
    	 	this.lesBonus.add(new Bonus(a,b,new Arme(Jeu.listeArme[(r.nextInt(7)+1)])));	 
     	}
      }
	    }
  }
  
  /**
   * methode lireTerrain
   * 
   * cette methode permet de lire un fichier texte et retourne un tableau 2D de char (char[][]) etant l'agencement du Terrain
   * 
   * @param numTerrain le numero du Terrain en int
   * 
   * @return le retour du tableu 2D de char
   */
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
