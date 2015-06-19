public class Zombie
  extends Entity
{
  private int degat = 20;
  
  //constructeur parametre de la clase Zombie : la position ( int, int ) et la vitesse (int) sont en parametres
  public Zombie(int x, int y, int speed)
  {
    super(x, y, speed, "TCboy", 15, 20);
    this.degat *= 1;
  }
  
  // getter des degats du zombie
  public int getDegat(){
	  return this.degat;
  }
}
