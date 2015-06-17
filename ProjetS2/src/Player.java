import java.util.ArrayList;


public class Player extends Entity{

	private int armure;
	private int mana;
	private int caseActiveInventaire;
	private ArrayList<Arme> inventaire;
	//personnage jouable repr�sent� par un int
	private int[] perso;
	private static int poidMax=10;
	
	public Player(int x, int y, int speed, String texture) {
		super(x, y, speed, texture);
	}
	
	//m�thode servant � faire tirer le personnage
	public void tirer(){
		Arme armeActive = inventaire.get(this.caseActiveInventaire);
		int balles = armeActive.getMunition();
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
	public void affecterBonus(Bonus b){
		int nbBonus=b.getEffet();
		/*effet 1 : sante
		*effet 2 : armure
		*effet 3 : mana
		*effet 4 : vitesse
		*effet 5 : Arme 
		*/
			
		switch (nbBonus) {
		case 1 : this.setSante(this.getSante() + 50);
		break;
		case 2 : this.setArmure(this.armure + 50);
		break;
		case 3 : this.setMana(this.mana + 20);
		break;
		case 4 : this.setVitesse(this.vitesse + 1);
		break;
		case 5 : this.ajouterArme(b.getArme());
		}
	}
	public void move(int dx,int dy){
		this.posX=this.posX+dx;
		this.posY=this.posY+dy;
	}		 
}
