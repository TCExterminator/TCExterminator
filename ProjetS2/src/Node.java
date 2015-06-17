import java.util.ArrayList;




public class Node {
	private int posX;
	private int posY;	
	private int typeCase;
	private ArrayList<Node> voisins;
	// 1 pour le praticable, 2 pour les murs
	
	
	public Node(Entity e){
		this.setPosX(e.getX());
		this.setPosY(e.getY());
		this.typeCase = 1;
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
				if(this.posX==n.getPosX() && this.posY==n.getPosY() && 
						this.typeCase==n.getTypeCase())b = true;
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
				(this.posX==n.getPosX() + 1 && this.posY==n.getPosY() + 1)){
					b = true;
			
	}
		return b;
	}
	public void setVoisin(ArrayList<Node> listeVoisin){
		for(Node n:listeVoisin){
			if(this.estVoisin(n)){
				this.voisins.add(n);
			}
		}
		
	}
}	