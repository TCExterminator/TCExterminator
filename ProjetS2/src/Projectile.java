public class Projectile extends Entity{
	private int puissanceProjectile;
	private int dx;
	private int dy;
	
	public Projectile(int dx, int dy, int x, int y, int speed, int puissanceProjectile){
		super(x, y, speed, 1, "bullet");
		this.dx=dx;
		this.dy=dy;
		this.puissanceProjectile = puissanceProjectile;
	}
	
	public void move(){
		this.posX=this.posX+(dx*this.vitesse);
		this.posY=this.posY+(dy*this.vitesse);
	}
}
