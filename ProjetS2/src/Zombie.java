public class Zombie
  extends Entity
{
  private int degat = 20;
  
  public Zombie(int x, int y, int speed)
  {
    super(x, y, speed, "pumpkin", 15, 20);
    this.degat *= 1;
  }
  
  public boolean collision(Entity entite)
  {
    boolean res = false;
    //HI BIATCH
    return res;
  }
}
