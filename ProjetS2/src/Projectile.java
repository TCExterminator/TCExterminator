
public class Projectile extends Entity{
	private int puissanceProjectile;
	
	public Projectile(int dX, int dY, int x, int y, int speed, String texture, int puissanceProjectile){
		super(x, y, speed, texture);
		this.puissanceProjectile = puissanceProjectile;
	}
}
