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
  
  public void move(double dx, double dy,Terrain played)
  {
    int newX = (int)(this.getX() + dx);
    int newY = (int)(this.getY() + dy);
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
    				  System.out.println(this.sante);
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
    	  /*for(Zombie z :played.getlesZomb()){
    		  int x=z.getX()-this.getX();
    		  int y=z.getY()-this.getY();
    		  if(Math.sqrt(x*x+y*y)<this.taille+z.taille){
    			  colision=true;
    		  }
    	  }*/
      }/*else if (this.getClass() == Projectile.class){
    	  
      }*/
      
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
