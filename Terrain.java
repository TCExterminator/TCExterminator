import java.util.ArrayList;


public class Terrain {
	private int idTerrain;
	private ArrayList<Zombie> lesZomb;
	private Personnage lePerso;
	private ArrayList<Projectile> lesProj;
	private Case[][] lesCases;
	private ArrayList<Bonus> lesBonus;
	private boolean isActif=false;
	
	//constructeur de la classe Terrain
	public Terrain(){
		//� coder
	} 
	
	//m�thode servant � afficher le terrain
	public void afficher(){
		//� coder
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
		for ( Entite e : lesZomb){			
			if( e.getSante()<=0){
				//supprimer e
			}	
		}	
		
		
			/*if( lePerso.getSante()<=0){
				//supprimer e
			}	
			*/
		
		
					
		for ( Entite e : lesProj){			
			if( e.getSante()<=0){
				//supprimer e
			}	
		}		
		return res;		
	}
	
	
}
