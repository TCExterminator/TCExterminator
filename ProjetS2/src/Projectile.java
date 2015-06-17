
public class Projectile extends Entity{
	private int puissanceProjectile;
	
	public Projectile(int dX, int dY, int x, int y, int speed, int puissanceProjectile){
		super(x, y, speed, "bullet");
		this.puissanceProjectile = puissanceProjectile;
	}
}
