import java.util.ArrayList;

public class Node {
	private int posX;
	private int posY;	
	private int typeCase;
	private ArrayList<Node> voisins = new ArrayList<Node>();
	// 1 pour le praticable, 2 pour les murs
	
	
	public Node(Entity e){
		this.setPosX((int)e.getX()/32);
		this.setPosY((int)e.getY()/32);
	}
	
	public Node(int x,int y, int type){
		this.setPosX(x);
		this.setPosY(y);
		this.typeCase=type;
	}

	public boolean isPraticable(){
		return(this.typeCase==1);
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getTypeCase() {
		return typeCase;
	}

	public void setTypeCase(int typeCase) {
		this.typeCase = typeCase;
	}
	
	@Override
	public boolean equals(Object o){
		boolean b = false;
		if(this==o)b=true;
		else{
			if(o instanceof Node){
				Node n = (Node) o;
				if(this.posX==n.getPosX() && this.posY==n.getPosY())b = true;
				}
			}
		return b;
		}
	
	public boolean estVoisin(Node n){
		boolean b = false;
		if((this.posX==n.getPosX() + 1 && this.posY==n.getPosY() +1) || 
				(this.posX==n.getPosX() + 1 && this.posY==n.getPosY()) ||
				(this.posX==n.getPosX() + 1 && this.posY==n.getPosY() -1) ||
				(this.posX==n.getPosX() && this.posY==n.getPosY() - 1) ||
				(this.posX==n.getPosX() - 1 && this.posY==n.getPosY() - 1) ||
				(this.posX==n.getPosX() - 1 && this.posY==n.getPosY()) ||
				(this.posX==n.getPosX() - 1 && this.posY==n.getPosY() + 1) ||
				(this.posX==n.getPosX() && this.posY==n.getPosY() + 1)){
					b = true;
			
	}
		return b;
	}
	public void setVoisin(ArrayList<Node> listeVoisin){
		for(Node n:listeVoisin){
			if(this.estVoisin(n))this.voisins.add(n);			
		}	
	}
	
	public ArrayList<Node> getVoisin(){
		return this.voisins;
	}
	
	 @Override
	 public String toString(){
		 return ("Node X " + this.posX + " Y " + this.posY);
	 }
	 
	 public int getDistanceNode(Node n){
		 return ((this.posX - n.getPosX()) - (this.posY - n.getPosY()) * 
					(this.posX - n.getPosX()) - this.posY - n.getPosY());
	 }
	
	//TEST NODE
	public static void main(String[] args){
		Node nCentre = new Node(5,5,1);
		Node nDroite = new Node(6,5,1);
		Node nGauche = new Node(4,5,1);
		Node nHaut = new Node(5,4,1);
		Node nBas = new Node(5,6,1);
		Node nD1 = new Node(6,6,1);
		Node nD2 = new Node(4,4,1);
		Node nD3 = new Node(6,4,1);
		Node nD4 = new Node(4,6,1);
		Node inutile = new Node (0,0,2);
		Node nd11 = new Node(1,1,1);
		Node zombie = new Node(new Zombie(34,34,1));
		System.out.println(" Voisin du centre :\n");
		System.out.println("Droite : " + nCentre.estVoisin(nDroite));
		System.out.println("Gauche : " + nCentre.estVoisin(nGauche));
		System.out.println("Haut : " + nCentre.estVoisin(nHaut));
		System.out.println("Bas : " + nCentre.estVoisin(nBas));
		System.out.println("D1 : " + nCentre.estVoisin(nD1));
		System.out.println("D2 : " + nCentre.estVoisin(nD2));
		System.out.println("D3 : " + nCentre.estVoisin(nD3));
		System.out.println("D4 : " + nCentre.estVoisin(nD4));
		System.out.println("Inutile : " + nCentre.estVoisin(inutile));
		System.out.println("\nPraticabilit�");
		System.out.println("Centre : " + nCentre.isPraticable());
		System.out.println("Inutile : " +inutile.isPraticable());
		System.out.println(zombie.equals(nd11));
	}
}	