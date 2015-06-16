
public class Projectile extends Entity{
	private int puissanceProjectile;
	
	public Projectile(int x, int y, int speed, String texture, int puissanceProjectile){
		super(x, y, speed, texture);
		this.puissanceProjectile = puissanceProjectile;
	}
}
