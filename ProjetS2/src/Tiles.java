import org.newdawn.slick.opengl.Texture;
public class Tiles {
	private char typeTiles;
	private Texture textur;
	
	public char getTypeTiles(){
		return this.typeTiles;
	}
	
	public void setTypeTiles(char tyC){
		this.typeTiles = tyC;
	}
	public void setTexture(Texture textur){
		this.textur = textur;
	}
	
}
