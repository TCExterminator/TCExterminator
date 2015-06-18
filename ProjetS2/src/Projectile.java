public class Projectile
  extends Entity
{
  private int puissanceProjectile;
  private double coefDir;
  private double DY;
  private double DX;
  
  public Projectile(int dx, int dy, int x, int y, int speed, int puissanceProjectile,int porte)
  {
    super(x, y, speed, "bullet", 2, porte);
    this.puissanceProjectile = puissanceProjectile;
    
    this.coefDir = (Math.sqrt(dx * dx + dy * dy) / speed);
    this.DY = (dy / this.coefDir);
    this.DX = (dx / this.coefDir);
  }
  
  public double getDY()
  {
    return this.DY;
  }
  
  public double getDX()
  {
    return this.DX;
  }
}
