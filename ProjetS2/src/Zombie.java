import java.util.ArrayList;


public class Zombie extends Entity{
	private int degat = 20;
	
	public Zombie(int x, int y, int speed, String texture) {
		super(x, y, speed, texture);
		this.degat = this.degat * 1;
	}

	//mehode servant a tester la collision entre un zombie et une entite
	public boolean collision(Entity entite){
		boolean res = false;
		//a coder
		return res;
		}
		
	public void followNodes(Node n){
		if(this.getX()<n.getPosX()){
			this.move(this.vitesse, 0);;
		}
		if(this.getX()>n.getPosX()){
			this.setPosition(-this.vitesse,0);
		}
		if(this.getY()<n.getPosY()){
			this.setPosition(0,this.vitesse);
		}
		if(this.getY()>n.getPosY()){
			this.setPosition(0,-this.vitesse);
		}
	}
}
