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
  
  /*
   * Bonus Constructor
   * constructeur parametre de la classe Bonus avec la position et une arme en parametre
   * @param x  la position en x en int
   * @param y la position en y en int
   * @param effet l'effet du bonus en int
   * 
   */
  public Bonus(int x, int y, int effet)
  {
    this.textureBonus = Jeu.getTexture("bonus");
    this.posX = x;
    this.posY = y;
    this.effet = effet;
  }
  
  /*
   * Bonus Constructor
   * constructeur parametre de la classe Bonus avec la position et une arme en parametre
   * @param x  la position en x en int
   * @param y la position en y en int
   * @param a l'Arme du bonus en Arme
   * 
   */
  public Bonus(int x, int y, Arme a)
  {
    this.textureBonus = Jeu.getTexture("pika");
    this.posX = x;
    this.posY = y;
    this.effet = 5;
  }
  
  /**
   * methode afficher
   * 
   * permet d'afficher un bonus selon ses coordonnees avec une texture.
   */
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
  
  /** 
   * methode getPosX
   * getter de la position en X
   * 
   * @return le retour de posX
   */
  public int getPosX()
  {
    return this.posX;
  }
  
  /** 
   * methode getPosX
   * getter de la position en Y
   * 
   * @return le retour de posY
   */
  public int getPosY()
  {
    return this.posY;
  }
  
  /** 
   * methode getEffet
   * 
   * getter de l'effet
   * 
   * @return le retour de effet
   */
  public int getEffet()
  {
    return this.effet;
  }
  
  /** 
   * methode getArme
   * 
   * getter de l'Arme
   * 
   * @return le retour de a
   */
  public Arme getArme()
  {
    return this.a;
  }
  
  
  /** 
   * methode setPosX
   * 
   * setter de la position en X
   * 
   * @param x la position int en x
   */
  public void setPosX(int x)
  {
    this.posX = x;
  }
  
  /**
   * methode setPosY
   * 
   * setter de la position en Y
   * 
   * @param y la position int en y
   */
  public void setPosY(int y)
  {
    this.posY = y;
  }
  
  /** 
   * methode setEffet
   * 
   * setter de l'effet
   * 
   * @param effet l'effet int du bonus
   */
  public void setEffet(int effet)
  {
    this.effet = effet;
  }
  
  /** 
   * methode setArme
   * 
   * setter de l'Arme
   * 
   * @param arm l'Arme du bonus
   */
  public void setArme(Arme arm)
  {
    this.a = new Arme(arm);
  }
  
}
