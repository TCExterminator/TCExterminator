import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

public class HUD {
	
    private TrueTypeFont font;
    private boolean antiAlias = true;
	public boolean running = false;
	
	private Texture hp = Jeu.getTexture("red");
	private Texture mana = Jeu.getTexture("red");

	//private static final int BOLD = 0;
	//public static Font myFont = new Font("fontHUD", BOLD, 15);
	
	//DisplayMode mode = new DisplayMode(width * scale,height * scale);
	
	public HUD(){        
		try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("PopulationZeroBB.otf");
            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont2, antiAlias);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
		public void renderHUD(Player player) {
		
			//création d'un rectangle ou on initialise les 4 coins
			int x = 10 -Jeu.mxr;
			int y = Jeu.winHeight - 30 - Jeu.myr;
			hp.bind();
			glBegin(GL_QUADS);
	        	glTexCoord2f(0.0F, 0.0F);
				glVertex2f(x,y);
		        glTexCoord2f(1F, 0.0F);
				glVertex2f(x + player.sante ,y);
		        glTexCoord2f(1F, 1F);
				glVertex2f(x + player.sante ,y - 40);
		        glTexCoord2f(0.0F, 1F);
				glVertex2f(x,y - 40);
			glEnd();
			
		/*	this.mana.bind();
			glBegin(GL_QUADS);
	        	glTexCoord2f(1F, 0.0F);
					glVertex2f(x,y - 50);
		        glTexCoord2f(1F, 1F);
					glVertex2f(x + (player.getMana()/100) * 400,y - 50 );
		        glTexCoord2f(0F, 1F);
					glVertex2f(x + (player.getMana()/100) * 400 ,y - 70);
		        glTexCoord2f(0.0F, 0F);
					glVertex2f(x,y - 70);
			glEnd();    */    
			String ammos ="";
			if(player.getArmeActive().getMunition()<100){
			for(int i = 0 ;i<player.getArmeActive().getMunition(); i++){
				ammos+= "I";
			}
			}else{
				ammos = "IXX";
			}
	        font.drawString(x, y - 100, ammos , Color.white);
			
		}
}
	
	
	
