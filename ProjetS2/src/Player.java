import java.util.ArrayList;


public class Player extends Entity{

	private int armure;
	private int mana;
	private int caseActiveInventaire;
	private ArrayList<Arme> inventaire;
	//personnage jouable représenté par un int
	private int[] perso;
	private static int poidMax=10;
	
	public Player(int x, int y, int speed, String texture) {
		super(x, y, speed, texture);
	}
	
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

}
