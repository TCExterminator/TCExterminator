import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	Font myfont = new Font("myHUD",Font.BOLD,25);
	int maxHP;
	int maxMana;
	// pour graphics créer create(int x, int y, int width, int height) dans jeu
	public void paint(Graphics g, Player p, Arme a){
		
		g.drawString(" HP : " + p.getSante() + "/" + maxHP +/* " Mana : " + p.getMana() + */"/" + maxMana + " Munition : " + a.getMunition() , 25, 25);
	}
}