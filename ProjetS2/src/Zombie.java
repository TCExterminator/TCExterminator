public class Zombie extends Entity{
	private int degat = 20;
	
	public Zombie(int x, int y, int speed) {
		super(x, y, speed,100, "pumpkin");
		this.degat = this.degat * 1;
	}

	//mehode servant a tester la collision entre un zombie et une entite
	public boolean collision(Entity entite){
		boolean res = false;
		//a coder
		return res;
		}
		
	public void followNodes(Node n){
		if(this.getX()<n.getPosX()*32+16){
			this.move(this.vitesse, 0);
		}
		if(this.getX()>n.getPosX()*32+16){
			this.move(-this.vitesse,0);
		}
		if(this.getY()<n.getPosY()*32+16){
			this.move(0,this.vitesse);
		}
		if(this.getY()>n.getPosY()*32+16){
			this.move(0,-this.vitesse);
		}
	}
}
