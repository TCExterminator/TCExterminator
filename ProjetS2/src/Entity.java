import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public abstract class Entity
{
  protected double posX;
  protected double posY;
  protected int vitesse;
  protected int sante;
  private Texture texture;
  protected int taille;
  
  public Entity(int x, int y, int speed, String texture, int taille, int sante)
  {
    this.posX = x;
    this.posY = y;
    this.vitesse = speed;
    this.texture = Jeu.getTexture(texture);
    this.taille = taille;
    this.sante = sante;
  }
  
  public void setPosition(int x, int y)
  {
    this.posX = x;
    this.posY = y;
  }
  
  public int getX()
  {
    return (int)this.posX;
  }
  
  public int getY()
  {
    return (int)this.posY;
  }
  
  public int getVitesse()
  {
    return this.vitesse;
  }
  
  public int getSante()
  {
    return this.sante;
  }
  
  public void setPosX(int x)
  {
    this.posX = x;
  }
  
  public void setPosY(int y)
  {
    this.posY = y;
  }
  
  public void setVitesse(int vit)
  {
    this.sante = vit;
  }
  
  public void setSante(int sante)
  {
    this.sante = sante;
  }
  
  public void afficher()
  {
    this.texture.bind();
    GL11.glBegin(7);
    GL11.glTexCoord2f(0.0F, 0.0F);
    GL11.glVertex2i(getX() - 15, getY() + 15);
    GL11.glTexCoord2f(1.0F, 0.0F);
    GL11.glVertex2i(getX() + 15, getY() + 15);
    GL11.glTexCoord2f(1.0F, 1.0F);
    GL11.glVertex2i(getX() + 15, getY() - 15);
    GL11.glTexCoord2f(0.0F, 1.0F);
    GL11.glVertex2i(getX() - 15, getY() - 15);
    GL11.glEnd();
  }
  
  public boolean supprimer()
  {
    return this.sante <= 0;
  }
  
  public void move(double dx, double dy)
  {
    int newX = (int)(getX() + dx);
    int newY = (int)(getY() + dy);
    Terrain played = Jeu.getActivTerrain();
    int taille = this.taille;
    if (played.IsActif())
    {
      char point1 = played.getTypeCase(newX, newY + taille);
      char point12 = played.getTypeCase((int)(newX + Math.cos(0.0D) * taille), (int)(newY + Math.cos(0.0D) * taille));
      
      char point2 = played.getTypeCase(newX + taille, newY);
      char point23 = played.getTypeCase(newX + (int)Math.cos(0.0D) * taille, newY - (int)Math.cos(0.0D) * taille);
      
      char point3 = played.getTypeCase(newX, newY - taille);
      char point34 = played.getTypeCase((int)(newX - Math.cos(0.0D) * taille), (int)(newY - Math.cos(0.0D) * taille));
      
      char point4 = played.getTypeCase(newX - taille, newY);
      char point41 = played.getTypeCase((int)(newX - Math.cos(0.0D) * taille), (int)(newY + Math.cos(0.0D) * taille));
      if ((point1 == '1') || (point2 == '1') || (point3 == '1') || (point12 == '1') || (point23 == '1') || (point34 == '1') || (point41 == '1') || 
        (point1 == '2') || (point2 == '2') || (point3 == '2') || (point12 == '2') || (point23 == '2') || (point34 == '2') || (point41 == '2'))
      {
        if (getClass() == Projectile.class) {
          setSante(-1);
        }
      }
      else
      {
        if (getClass() == Player.class)
        {
          GL11.glTranslated(-dx / 2.0D, -dy / 2.0D, 0.0D);
          Jeu.mxr = (int)(Jeu.mxr - dx / 2.0D);
          Jeu.myr = (int)(Jeu.myr - dy / 2.0D);
        }
        this.posX += dx;
        this.posY += dy;
      }
    }
  }
}
