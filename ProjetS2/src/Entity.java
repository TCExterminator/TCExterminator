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
  
  /**
   * Constructor Entity
   * 
   * il s'agit du constructeur parametre de la classe Entity 
   * @param x la position en int
   * @param y la position en int
   * @param speed la vitesse en int 
   * @param texture la texture en String
   * @param taille la taille en int 
   * @param sante la sante en int
   */
  public Entity(int x, int y, int speed, String texture, int taille, int sante)
  {
    this.posX = x;
    this.posY = y;
    this.vitesse = speed;
    this.texture = Jeu.getTexture(texture);
    this.taille = taille;
    this.sante = sante;
  }
  
  /**
   * methode getX
   * 
   * il s'agit du getter de la position en X
   * 
   * @return le retour de la position int en x
   */
  public int getX()
  {
    return (int)this.posX;
  }
  
  /**
   * methode getY
   * 
   * il s'agit du getter de la position en Y
   * 
   * @return le retour de la position int en y
   */
  public int getY()
  {
    return (int)this.posY;
  }
  
  /**
   * methode getVitesse
   * 
   * il s'agit du getter de la vitesse de l'Entity
   * 
   * @return le retour de la vitesse en int
   */
  public int getVitesse()
  {
    return this.vitesse;
  }
  
  /**
   * methode getSante
   * 
   * il s'agit du getter de la sante de l'Entity
   * 
   * @return le retour de la sante en int
   */
  public int getSante()
  {
    return this.sante;
  }
  
  /**
   * methode setVitesse
   * 
   * il s'agit du setter de la vitesse de l'Entity
   * 
   * @param vit la vitesse en int 
   */
  public void setVitesse(int vit)
  {
    this.sante = vit;
  }
  
  /**
   * methode setSante
   * 
   * il s'agit du setter de la sante de l'Entity
   * 
   * @param sante la sante en int
   */
  public void setSante(int sante)
  {
    this.sante = sante;
  }
  
  /**
   * methode afficher
   * 
   * cette methode permet d'afficher sur la fenetre une Entity avec une texture particuliere.
   * 
   */
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
  
  /**
   * methode supprimer
   * 
   * cette methode permet de verifier si l'Entity peut etre supprime
   * 
   * @return le retour du boolean
   */
  public boolean supprimer()
  {
    return this.sante <= 0;
  }
  
  /**
   * methode allPoint
   * 
   * cette methode retourne un tableau de char (char[]) contenant toutes les cases que l'Entity touchera en position newX newY
   * 
   * @param played le Terrain actif
   * @param newY la position y en int
   * @param newX la position x en int
   * 
   * @return le retour du tableau char[]
   */
  public char[] allPoint(Terrain played,int newY,int newX){
	  char[] res=new char[8];
	  res[0]= played.getTypeCase(newX, newY + taille);
	  res[1]= played.getTypeCase((int)(newX + Math.cos(0.0D) * taille), (int)(newY + Math.cos(0.0D) * taille));
      
	  res[2]= played.getTypeCase(newX + taille, newY);
	  res[3]= played.getTypeCase(newX + (int)Math.cos(0.0D) * taille, newY - (int)Math.cos(0.0D) * taille);
      
	  res[4]= played.getTypeCase(newX, newY - taille);
	  res[5]= played.getTypeCase((int)(newX - Math.cos(0.0D) * taille), (int)(newY - Math.cos(0.0D) * taille));
      
	  res[6]= played.getTypeCase(newX - taille, newY);
	  res[7]= played.getTypeCase((int)(newX - Math.cos(0.0D) * taille), (int)(newY + Math.cos(0.0D) * taille));
      
      return res;
  }
  
  /**
   * methode move
   * 
   * cette methode permet de deplacer l'Entity selon deux coefficients directeurs dx dy dans un terrain actif
   * 
   * @param dx le coefficient directeur en double 
   * @param dy le coefficient directeur en double 
   * @param played le terrain actif
   */
  public void move(double dx, double dy,Terrain played)
  {
    int taille = this.taille;
    
    if (played.IsActif())
    {
      boolean colision=false;
      char[]cas1=allPoint(played,(int)(this.getY()),(int)(this.getX()+dx));
      char[]cas2=allPoint(played,(int)(this.getY()+dy),(int)(this.getX()));
      for(int i=0;i<cas1.length;i++)if(cas1[i]=='1'||cas1[i]=='2'){colision=true;dx=0.0;}
      for(int i=0;i<cas2.length;i++)if(cas2[i]=='1'||cas2[i]=='2'){colision=true;dy=0.0;}
      
      if (this.getClass() == Player.class){
    	  for(Zombie z :played.getlesZomb()){
    		  int x=z.getX()-this.getX();
    		  int y=z.getY()-this.getY();
    		  if(Math.sqrt(x*x+y*y)<taille+z.taille){
    			  if(Player.invulnerable<0){
    				  this.sante-=z.getDegat();
    				  Player.invulnerable=z.getDegat();
    			  }
    			  
    		  }
    	  }
      }else if (this.getClass() == Zombie.class){
    	  
    	  for (int i=0 ;i<played.getlesProj().size();i++){
    		  Projectile p=played.getlesProj().get(i);
    		  int x=p.getX()-this.getX();
    		  int y=p.getY()-this.getY();
    		  if(Math.sqrt(x*x+y*y)<taille+p.taille){
    			  this.sante-=p.getDegat();
    			  played.getlesProj().remove(p);
    		  }
    		  
    	  }
    	
      }
      
      if ((colision)&&(this.getClass() == Projectile.class)){
          setSante(-1);
      }
      else
      {
        if (this.getClass() == Player.class)
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
