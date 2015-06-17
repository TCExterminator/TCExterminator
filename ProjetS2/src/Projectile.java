
public class Projectile extends Entity{
	private int puissanceProjectile;
	
	public Projectile(int dX, int dY, int x, int y, int speed, int puissanceProjectile){
		super(x, y, speed,1, "bullet");
		this.puissanceProjectile = puissanceProjectile;
	}
}
