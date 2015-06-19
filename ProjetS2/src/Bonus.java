import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Bonus
{
  private int posX;
  private int posY;
  private int effet;
  private Arme a = null;
  private Texture textureBonus;
  
  // constructeur parametre de la classe Bonus : la position ( int, int) et l'effet ( int) sont en parametres
  public Bonus(int x, int y, int effet)
  {
    this.textureBonus = Jeu.getTexture("pika");
    this.posX = x;
    this.posY = y;
    this.effet = effet;
  }
  
  // constructeur parametre de la classe Bonus : la position ( int, int ) est en prametre
  public Bonus(int x, int y, Arme a)
  {
    this.textureBonus = Jeu.getTexture("pika");
    this.posX = x;
    this.posY = y;
    this.effet = 5;
  }
  
  // m騁hode servant � afficher un Bonus selon une texture
  public void afficher()
  {
    this.textureBonus.bind();
    glBegin(GL_QUADS);
    	glTexCoord2f(0.0F, 0.0F);
    	glVertex2i(this.posX - 15, this.posY + 15);
    	glTexCoord2f(1.0F, 0.0F);
    	glVertex2i(this.posX + 15, this.posY + 15);
    	glTexCoord2f(1.0F, 1.0F);
    	glVertex2i(this.posX + 15, this.posY - 15);
    	glTexCoord2f(0.0F, 1.0F);
    	glVertex2i(this.posX - 15, this.posY - 15);
    glEnd();
  }
  
  // getter de la position en X
  public int getPosX()
  {
    return this.posX;
  }
  
  // getter de la position en Y
  public int getPosY()
  {
    return this.posY;
  }
  
  // getter de l'effet
  public int getEffet()
  {
    return this.effet;
  }
  
  // getter de l'Arme
  public Arme getArme()
  {
    return this.a;
  }
  
  
  // setter de la position en X
  public void setPosX(int x)
  {
    this.posX = x;
  }
  
  //setter de la position en Y
  public void setPosY(int y)
  {
    this.posY = y;
  }
  
  // setter de l'effet
  public void setEffet(int effet)
  {
    this.effet = effet;
  }
  
  // setter de l'Arme
  public void setArme(Arme arm)
  {
    this.a = new Arme(arm);
  }
  
}
