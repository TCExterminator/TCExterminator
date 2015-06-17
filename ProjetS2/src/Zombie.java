import java.util.ArrayList;


public class Zombie extends Entity{
	private int degat = 20;
	
	public Zombie(int x, int y, int speed, String texture) {
		super(x, y, speed, texture);
		this.degat = this.degat * 1;
	}

	//m騁hode servant � tester la collision entre un zombie et une entite
	public boolean collision(Entity entite){
		boolean res = false;
		//� coder
		return res;
		}
		
	public void followNodes(ArrayList<Node> nodes){
		for(Node n : nodes){
			this.setPosition(n.getPosX()*32+16, n.getPosY()*32+16);
		}
	}
}
