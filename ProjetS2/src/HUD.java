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
	
	DisplayMode mode = new DisplayMode(width * scale,height * scale);
	
	public HUD(){
		try {
		Display.setDisplayMode(mode);
		Display.setResizable(true);
		Display.setFullscreen(false);
		Display.setTitle(title);
		Display.create();
		
		initGl();
		} catch (LWJGLException e){
			e.printStackTrace();
		}
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
				
				renderHUD();
			}
			exit();
		}
		
	
		public void renderHUD() {
			
			//cr�ation d'un rectangle
			//glRectf(25,25, 20 , 100);
			
			//cr�ation d'un rectangle ou on initialise les 4 coins
			int x = 15;
			int y = 5;
			int size = 16;
			glBegin(GL_QUADS);
				glColor3f(1.0f,0.0f,0.0f); //couleur rouge
				glVertex2f(x,y);
				
				glColor3f(0.0f,1.0f,0.0f); //couleur vert
				glVertex2f(x + 100 + size,y);
				
				glColor3f(0.0f,1.0f,0.0f); //couleur vert
				glVertex2f(x + 100 + size,y + size - 10);
				
				glColor3f(1.0f,0.0f,0.0f); //couleur rouge
				glVertex2f(x,y + size - 10);
			glEnd();
			
			glBegin(GL_QUADS);
			glColor3f(0.0f,0.0f,1.0f); //couleur blue
			glVertex2f(x,y+10);
			
			glColor3f(0.0f,0.0f,1.0f); //couleur blue
			glVertex2f(x + 100 + size,y+10);
			
			glColor3f(0.0f,0.0f,1.0f); //couleur blue
			glVertex2f(x + 100 + size,y + size - 10+10);
			
			glColor3f(0.0f,0.0f,1.0f); //couleur blue
			glVertex2f(x,y + size - 10+10);
		glEnd();
			
		}
		
		
	
	public static void main(String[] args){
		HUD res = new HUD();
		
		res.startHUD();
	}
}
	
	
	
