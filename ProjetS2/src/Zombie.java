
public class Zombie extends Entity{
	private int degat = 20;
	
	public Zombie(int x, int y, int speed, String texture) {
		super(x, y, speed, texture);
		this.degat = this.degat * 1;
	}

	//m�thode servant � tester la collision entre un zombie et une entite
		public boolean collision(Entity entite){
			boolean res = false;
			//� coder
			return res;
		}

}
