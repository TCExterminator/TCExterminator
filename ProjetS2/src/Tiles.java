import org.newdawn.slick.opengl.Texture;

public class Tiles
{
  private char typeTiles;
  private Texture textur;
  
  /**
   * Constructeur parametré de Tiles
   * @param tex
   * @param typeTiles
   */
  public Tiles(Texture tex, char typeTiles)
  {
    this.textur = tex;
    this.typeTiles = typeTiles;
  }
  
  /**
   * getter de typeTiles
   * @return
   */
  public char getTypeTiles()
  {
    return this.typeTiles;
  }
  
  /**
   * setter du typeTiles
   * @param tyC
   */
  public void setTypeTiles(char tyC)
  {
	  
    this.typeTiles = tyC;
  }
  
  /**
   * setter de textur
   * @param textur
   */
  public void setTexture(Texture textur)
  {
    this.textur = textur;
  }
  
  /**
   * getter de textur
   * @return
   */
  public Texture getTexture()
  {
    return this.textur;
  }
  
}

