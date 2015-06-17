import java.util.ArrayList;


public class Player extends Entity{

	private int armure;
	private int mana;
	private int caseActiveInventaire = 1;
	private ArrayList<Arme> inventaire=new ArrayList<Arme>();
	//personnage jouable represente par un int
	private int[] perso;
	private static int poidMax=10;
	private int cooldown;
	
	public Player(int posx, int posy, int speed, String texture) {
		super(posx, posy, speed, texture);
		inventaire.add(Jeu.listeArme[0]);
		inventaire.add(Jeu.listeArme[1]);
	}
	
	//methode servant a faire tirer le personnage
	public void tirer(int dx,int dy){
		Arme armeActive = inventaire.get(this.caseActiveInventaire);
		int balles = armeActive.getMunition();
		for(Terrain t : Jeu.getLesTerrains()){
			if(t.isActif())	t.getlesProj().add(new Projectile(dx,dy,this.getX(),this.getY(),1,armeActive.getPuissance()));
		}
		armeActive.setMunition(balles-1);
		this.cooldown = 0;
		System.out.println(armeActive.getMunition());
		//a coder
	}
		
	//methode servant a faire lacher l'arme au personnage
	public void lacherArme(){
		//a coder
	}
		
	public void setArmure(int ar){
		this.armure=ar;
	}
		
	public void setMana(int ma){
		this.mana=ma;
	}
		
		
	public int getCooldown() {
		return cooldown;
	}

	public void healCooldown() {
		if(this.cooldown<=20)
		this.cooldown++;
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
		
	
	//methode servant a affecter un bonus a un personnage
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
