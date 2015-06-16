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
		//à coder
	} 
	
	//méthode servant à afficher le terrain
	public void afficher(){
		//à coder
	}
	
	
	//méthode servant à tester la collision entre une Entite est une case
	public void collision(){
		//à coder
	}
	
	//méthode servant à récupérer un type de case à une position donnée passé en paramêtre
	public char getTypeCase(int posX, int posY){
		char res;
		res =lesCases[(int) posX/32][(int) posY/32 ].getTypeCase();
		return res;
	}
	
	//méthode servant à supprimer une entite et retourne un boolean 
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
