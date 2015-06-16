
public class Node {
	private int posX;
	private int posY;	
	private int typeCase;
	// 1 pour le praticable, 2 pour les murs
	
	
	public Node(Entity e){
		this.setPosX(e.getPosX());
		this.setPosY(e.getPosY());
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
}	