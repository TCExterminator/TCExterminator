import java.util.Random;

public class Zombie
  extends Entity
{
  private int degat = 20;
  
  //constructeur parametre de la clase Zombie : la position ( int, int ) et la vitesse (int) sont en parametres
  public Zombie(int x, int y, int speed)
  {  
    super(x, y, speed, "Zombbunny", 15, 0);
    Random r=new Random();
    this.setSante(r.nextInt(65)+45);
    this.degat *= 1;
  }
  
  // getter des degats du zombie
  public int getDegat(){
	  return this.degat;
  }
}
