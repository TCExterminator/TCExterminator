import java.util.ArrayList;


public class Personnage extends Entite {
	private int armure;
	private int mana;
	private int caseActiveInventaire;
	private ArrayList<Arme> inventaire;
	//personnage jouable représenté par un int
	private int[] perso;
	private static int poidMax=10;
			
	//méthode servant à faire tirer le personnage
	public void tirer(){
		//à coder
	}
	
	//méthode servant à faire lacher l'arme au personnage
	public void lacherArme(){
		//à coder
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
	
	//méthode servant à affecter un bonus à un personnage
		public void affecterBonus(){
			
		}
	
	
}
