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
	private ArrayList<Projectile> lesProj = new ArrayList<Projectile>();
	private Tiles[][] lesTiles;
	private ArrayList<Bonus> lesBonus;
	private boolean actif=false;
	
	public boolean isActif(){
		return actif;
	}
	public Terrain(){
		this.idTerrain=0;
		Random r = new Random();
		char map[][]= lireTerrain(0);
		lesTiles=new Tiles[map.length][map[0].length];
		Texture sol = Jeu.getTexture("sol");
		Texture wall = Jeu.getTexture("wall");
		
		
		
		for(int i=0;i<map.length;i++){
			for(int j =0;j<map[i].length;j++){
				switch(map[i][j]){
					case 'Z':
						lesTiles[i][j]=new Tiles(sol,'0');
						lesZomb.add(new Zombie(i*32+16,j*32+16,2));
						break;
					case '1':
						lesTiles[i][j]=new Tiles(wall,'1');
						break;
					case '0':
						lesTiles[i][j]=new Tiles(sol,'0');
						break;
					default :
						lesTiles[i][j]=new Tiles(wall,'1');
						break;
				}
			}
		}
		IA.setLesNodes(this.idTerrain);
		IA.setlisteOuverte();
	}
	
	public void afficher(){
		for(Terrain t : Jeu.getLesTerrains()){
			t.actif=false;
		}
		actif=true;
		
		for(int i=0;i<lesTiles.length;i++){
			for(int j =0;j<lesTiles[i].length;j++){
				lesTiles[i][j].getTexture().bind();
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
		for(Projectile p : lesProj){
			p.afficher();
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
	
	//methode servant a tester la collision entre une Entite est une case
		public void collision(){
			//a coder
		}
		
		//methode servant a recuperer un type de case a une position donnee passe en parametre
		public char getTypeCase(int posX, int posY){
			char res;
			res =lesTiles[(int) posX/32][(int) posY/32 ].getTypeTiles();
			return res;
		}

		//methode servant a supprimer une entite et retourne un boolean 
		public void supprimerMorts(){
			boolean res = false;		
			for (int i=0 ; i<lesZomb.size();i++){			
				if(lesZomb.get(i).supprimer()){
					lesZomb.remove(i);
				}	
			}
			for (int i=0 ; i<lesProj.size();i++){			
				if(lesProj.get(i).supprimer()){
					lesProj.remove(i);
				}	
			}	
		}

		public ArrayList<Zombie> getlesZomb(){
			return this.lesZomb;
		}
		
		public ArrayList<Projectile> getlesProj(){
			return this.lesProj;
		}
}
