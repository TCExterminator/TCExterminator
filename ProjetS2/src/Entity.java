
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;



public abstract class Entity {
	
	
    protected int posX;
    protected int posY;
    protected int vitesse;
    protected int sante;
	private Texture texture;
    
	
    //constructeur de la classe
    public Entity(int x,int y,int speed,String texture){
    		this.posX = x;
    		this.posY = y;
    		this.vitesse = speed;
    		this.texture = Jeu.getTexture(texture);
    	}
    
    //méthode servant à repositioner une entite
    public void setPosition(int x, int y){
        this.posX = x;
        this.posY = y;
    }
    
    //getter de sante
    public int getSante(){
		return this.sante;
	}
    
    //setter de sante
    public void setSante(int sante){
		this.sante=sante;
	}
    
  //getter de x
    public int getX(){
    	return this.posX;
    }
    
    //getter de y
    public int getY(){
    	return this.posY;
    }
    
    //getter de vitesse
    public int getVitesse(){
    	return this.vitesse;
    }
    
    //setter de vitesse
    public void setVitesse(int vit){
		this.sante=vit;
	}
    
    //méthode servant à afficher une entite
    public void afficher(){
		this.texture.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2i(this.posX-16,this.posY+16);
			glTexCoord2f(1, 0);
			glVertex2i(this.posX+16,this.posY+16);
			glTexCoord2f(1, 1);
			glVertex2i(this.posX+16,this.posY-16);
			glTexCoord2f(0, 1);
			glVertex2i(this.posX-16,this.posY-16);
		glEnd();
    }
    
    //méthode servant à supprimer une entite et retourne un boolean 
    public boolean supprimer(){
        boolean res = false;

        return res;
        
    }
    
}


