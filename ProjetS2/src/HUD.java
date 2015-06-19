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
			String vie ="";
			for(int i = 0 ;i<player.getSante(); i+=5){
				vie+= "I";
			}
	        font.drawString(x, y - 10, vie , Color.red);
			

			String mana ="";
			for(int i = 0 ;i<player.getMana(); i+=5){
				vie+= "I";
			}
	        font.drawString(x, y - 30, mana , Color.blue);
	        
			String ammos ="";
			if(player.getArmeActive().getMunition()<100){
			for(int i = 0 ;i<player.getArmeActive().getMunition(); i++){
				ammos+= "I";
			}
			}else{
				ammos = "IXX";
			}
	        font.drawString(x, y - 50, ammos , Color.white);
	        
			
		}
}
	
	
	
