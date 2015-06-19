import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class HUD {
	
	private TrueTypeFont font;
    private boolean antiAlias = true;
	public boolean running = false;
	
	/**
	 * Constructeur par défaut de HUD
	 * Initialise les valeurs de font
	 */
	public HUD(){        

		try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("PopulationZeroBB.otf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(24f);
            font = new TrueTypeFont(awtFont, antiAlias);
             
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Méthode qui va afficher le HUD d'un player passé en paramètres
	 * Santé,Mana et Munitions du joueur
	 * L'affichage de munition est fixe si la variable est trop grande
	 * @param player
	 */
	public void renderHUD(Player player) {
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
	
	
	
