import java.util.ArrayList;


public class Personnage extends Entite {
	private int armure;
	private int mana;
	private int caseActiveInventaire;
	private ArrayList<Arme> inventaire;
	//personnage jouable repr�sent� par un int
	private int[] perso;
	private static int poidMax=10;
			
	//m�thode servant � faire tirer le personnage
	public void tirer(){
		//� coder
	}
	
	//m�thode servant � faire lacher l'arme au personnage
	public void lacherArme(){
		//� coder
	}
	
	public void setArmure(int ar){
		this.armure=ar;
	}
	
	public void setMana(int ma){
		this.mana=ma;
}
	
	public boolean ajouterArme(Arme arme){
		boolean res = false;
		int placeRestante=poidMax;
		
		for(Arme a : inventaire){
			placeRestante-=a.getPoid();
			
		}
		if(arme.getPoid()<=placeRestante){
			res=true;
			inventaire.add(arme);
		}
		return res;
	}
	
	//m�thode servant � affecter un bonus � un personnage
		public void affecterBonus(){
			
		}
	
	
}
