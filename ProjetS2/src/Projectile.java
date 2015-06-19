public class Projectile
  extends Entity
{
  private int puissanceProjectile;
  private double coefDir;
  private double DY;
  private double DX;
  
  /**
   * Constructeur parametré de Projectile
   * @param dx vecteur x
   * @param dy vecteur y
   * @param x position
   * @param y position
   * @param speed 
   * @param puissanceProjectile
   * @param porte
   */
  public Projectile(int dx, int dy, int x, int y, int speed, int puissanceProjectile,int porte)
  {
    super(x, y, speed, "bullet", 2, porte);
    this.puissanceProjectile = puissanceProjectile;
    
    this.coefDir = (Math.sqrt(dx * dx + dy * dy) / speed);
    this.DY = (dy / this.coefDir);
    this.DX = (dx / this.coefDir);
  }  
  
  /**
   * Constructeur parametré de Projectile
   * Une texture en plus est demandée pour les projectiles personnalisés (couteau)
   * @param dx
   * @param dy
   * @param x
   * @param y
   * @param speed
   * @param puissanceProjectile
   * @param porte
   * @param texture
   */
  public Projectile(int dx, int dy, int x, int y, int speed, int puissanceProjectile,int porte,String texture)
  {
    super(x, y, speed, texture, 1, porte);
    this.puissanceProjectile = puissanceProjectile;
    
    this.coefDir = (Math.sqrt(dx * dx + dy * dy) / speed);
    this.DY = (dy / this.coefDir);
    this.DX = (dx / this.coefDir);
  }
  
  /**
   * getter de DY
   * @return
   */
  public double getDY()
  {
    return this.DY;
  }
  
  /**
   * getter de DX
   * @return
   */
  public double getDX()
  {
    return this.DX;
  }
  
  /**
   * méthode qui décrémente la variable sante
   * @param d
   */
  public void heatSante(int d){
	  this.sante-=d;
  }
  
  /**
   * getter de puissanceProjectile
   * appelé getDegat comme puissanceProjectile est un nom à rallonge (merci Alexis).
   * @return
   */
  int getDegat(){
	  return this.puissanceProjectile;
  }
  
}
