import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;



public class Terrain {

	private static final String[] listMap={"map0.txt","map1.txt","map2.txt","map3.txt","map4.txt","map5.txt","map6.txt"};
	private ArrayList<Zombie> lesZomb = new ArrayList<Zombie>();
	private int idTerrain;
	private Player lePerso;
	private ArrayList<Projectile> lesProj;
	private Case[][] lesCases;
	private ArrayList<Bonus> lesBonus;
	private boolean isActif=false;
	
	public boolean getIsActif(){
		return isActif;
	}
	public Terrain(){
		Random r = new Random();
		for(int i=0 ;i<20; i++){
			lesZomb.add(new Zombie(r.nextInt(Jeu.winWidth),r.nextInt(Jeu.winHeight),4,"pumpkin"));
		}
	}
	
	public void afficher(){
		isActif=true;
		
		Texture sol = Jeu.getTexture("sol");
		Texture wall = Jeu.getTexture("wall");
		
		char map[][]= lireTerrain(0);
		lesCases=new Case[map.length][map[0].length];
		
		for(int i=0;i<map.length;i++){
			for(int j =0;j<map[i].length;j++){
				switch(map[i][j]){
					case '1':
						lesCases[i][j]=new case()
						wall.bind();
						break;
					case '0':
						sol.bind();
						break;
					default :
						
						break;
					
				}
				glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2i(((i+1)*32),(j*32));
						glTexCoord2f(1, 0);
						glVertex2i(((i+1)*32),((j+1)*32));
						glTexCoord2f(1, 1);
						glVertex2i((i*32),((j+1)*32));
						glTexCoord2f(0, 1);
						glVertex2i((i*32),(j*32));
				glEnd();
			}
		}
		for (Zombie e : lesZomb){
			e.afficher();
		}
    }

	public static char[][] lireTerrain(int numTerrain){
		LinkedList<char[]> res = new LinkedList<char[]>();
		String fichier=listMap[numTerrain];
		
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				res.add(ligne.toCharArray());
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}	
		char[][] resultat= new char[res.size()][res.getFirst().length];
		for(int i=0;i<resultat.length;i++){
			resultat[i]=res.get(i);
		}
		return resultat;
	}
	
	//m�thode servant � tester la collision entre une Entite est une case
		public void collision(){
			//� coder
		}
		
		//m�thode servant � r�cup�rer un type de case � une position donn�e pass� en param�tre
		public char getTypeCase(int posX, int posY){
			char res;
			res =lesCases[(int) posX/32][(int) posY/32 ].getTypeCase();
			return res;
		}
		
		//m�thode servant � supprimer une entite et retourne un boolean 
		public boolean supprimer(){
			boolean res = false;		
			for ( Entity e : lesZomb){			
				if( e.getSante()<=0){
					//supprimer e
				}	
			}	
			
			
				/*if( lePerso.getSante()<=0){
					//supprimer e
				}	
				*/
			
			
						
			for ( Entity e : lesProj){			
				if( e.getSante()<=0){
					//supprimer e
				}	
			}		
			return res;		
		}
}
