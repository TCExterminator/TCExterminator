import java.awt.Font;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class HUD {
	

	public boolean running = false;
	
	public static String title = "test";
	public static int scale = 3;
	public static int width = 720 / scale;
	public static int height = 480 / scale;

	//private static final int BOLD = 0;
	//public static Font myFont = new Font("fontHUD", BOLD, 15);
	
	//DisplayMode mode = new DisplayMode(width * scale,height * scale);
	
	public HUD(){
		/*try {
		Display.setDisplayMode(mode);
		Display.setResizable(true);
		Display.setFullscreen(false);
		Display.setTitle(title);
		Display.create();
		
		initGl();
		} catch (LWJGLException e){
			e.printStackTrace();
		}*/
	}
	
	private void initGl(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0,width,height,0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	
	/*public void initText(){
		try{
			myFont = new Font(Font.createFont(TRUETYPE_FONT), null);
			
		} catch {
			
		}
	}
		*/
		public void startHUD() {
			running = true;
			loopHUD();
			
		}
		
		public void stopHUD() {
			running = false;
			
		}
		
		public void exit() {
			Display.destroy();
			System.exit(0);
		}
		
		public void loopHUD() {
			while(running){
				if(Display.isCloseRequested()) stopHUD();
				Display.update();
				
				//renderHUD();
			}
			exit();
		}
		
	
		public void renderHUD(int sante, int mana) {
		
			//création d'un rectangle ou on initialise les 4 coins
			int x = 30 - Jeu.mxr;
			int y = Jeu.winHeight - 30 - Jeu.myr;
			Jeu.getTexture("red").bind();
			glBegin(GL_QUADS);
	        	glTexCoord2f(0.0F, 0.0F);
					glVertex2f(x,y);
		        glTexCoord2f(1F, 0.0F);
					glVertex2f(x + (sante* 400/100) ,y);
		        glTexCoord2f(1F, 1F);
					glVertex2f(x + (sante* 400/100)  ,y - 40);
		        glTexCoord2f(0.0F, 1F);
					glVertex2f(x,y - 40);
			glEnd();
			
		Jeu.getTexture("BLUEOFDEATH").bind();
			glBegin(GL_QUADS);
	        	glTexCoord2f(1F, 0.0F);
					glVertex2f(x,y - 50);
		        glTexCoord2f(1F, 1F);
					glVertex2f(x + (mana/100) * 400,y - 50 );
		        glTexCoord2f(0F, 1F);
					glVertex2f(x + (mana/100) * 400 ,y - 70);
		        glTexCoord2f(0.0F, 0F);
					glVertex2f(x,y - 70);
			glEnd();
			
		}
		
		
	
	public static void main(String[] args){
		HUD res = new HUD();
		
		res.startHUD();
	}
}
	
	
	
