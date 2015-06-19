import java.util.ArrayList;

public class Player
  extends Entity
{
  @SuppressWarnings("unused")
private int armure=20;
  private int mana;
  private int caseActiveInventaire = 0;
  private ArrayList<Arme> inventaire = new ArrayList<Arme>();
  private Arme couteau = new Arme(Jeu.listeArme[0]);
  public static int poidMax = 10;
  public static int cooldown;
  public static int invulnerable;  

 /**
  * Constructeur parametre de Player
  * @param posx
  * @param posy
  * @param speed
  */
  public Player(int posx, int posy, int speed)
  {
    super(posx, posy, speed, "ninja", 15, 300);
    this.inventaire.add(Jeu.listeArme[1]);
  }
  
  /**
   * Getter de cooldown
   * @return
   */
  public int getCooldown()
  {
    return cooldown;
  }
  
  /**
   * Getter de mana
   * @return
   */
  public int getMana(){
	return this.mana;
  }
  
  /**
   * Getter de armeActive
   * @return
   */
  public Arme getArmeActive(){
	  return this.inventaire.get(this.caseActiveInventaire);
  }
  
  /**
   * Methode de reduction de la variable cooldown
   */
  public void heatCooldown()
  {
    cooldown -= 1;
  }
  
  /**
   * Methode de reduction de la variable invulnerable
   */
  public void heatInvulnerable()
  {
    invulnerable -= 1;
  }
  
  /**
   * Methode qui va Instancier un Projectile dans le Terrain actuellement actif sous certaines conditions.
   * Elle retirera des munitions a l'arme actuelle du joueur
   * @param dx vecteur de mouvement x
   * @param dy vecteur de mouvement y
   */
  public void tirer(int dx, int dy)
  {
    Arme armeActive = (Arme)this.inventaire.get(this.caseActiveInventaire);
    
    int balles = armeActive.getMunition();
    if ((balles > 0) || (armeActive.getPoid() == 0))
    {
      Jeu.getActivTerrain().getlesProj().add(new Projectile(dx, dy, getX(), getY(), 10, armeActive.getPuissance(),armeActive.getPortee()));
      if (balles != -42) {
        armeActive.redMunition();
      }
      cooldown = armeActive.getCadence();
    }
  }  
  
  /**
   * Methode qui va Instancier un Projectile de type couteau dans le Terrain actuellement actif.
   * Pas de munition contrairement a tirer
   * @param dx vecteur de mouvement x
   * @param dy vecteur de mouvement y
   */
  public void couteau(int dx, int dy)
  {
      Jeu.getActivTerrain().getlesProj().add(new Projectile(dx, dy, getX(), getY(), 10, couteau.getPuissance(),couteau.getPortee(),"couteau"));
      cooldown = couteau.getCadence();
  }
  
  
  /**
   * Methode sensee lacher l'armeActive du joueur
   */
  public void lacherArme() {}
  
  /**
   * Methode qui modifie la variable armure du joueur
   * @param ar
   */
  public void setArmure(int ar)
  {
    this.armure = ar;
  }
  
  /**
   * Methode qui modifie la variable mana du joueur
   * @param ma
   */
  public void setMana(int ma)
  {
    this.mana = ma;
  }
  
  
  /**
   * Methode qui ajoute une Arme a l'inventaire du joueur
   * @param arme
   * @return
   */
  public boolean ajouterArme(Arme arme)
  {
    boolean res = false;
    int placeRestante = poidMax;
    for (Arme a : this.inventaire) {
      placeRestante -= a.getPoid();
    }
    if (arme.getPoid() <= placeRestante)
    {
      res = true;
      this.inventaire.add(arme);
    }
    return res;
  }
  
  
  /**
   * Methode qui modifiera les variables du joueur en fonction du Bonus passe en parametre.
   * @param b
   */
  public void affecterBonus(Bonus b)
  {
    int nbBonus = b.getEffet();
    
    switch (nbBonus)
    {
    case 1: 
      this.sante += 100;
      break;
    case 2: 
      this.armure += 10;
      break;
    case 3: 
      this.mana += 20;
      break;
    case 4: 
      this.vitesse += 2;
      this.taille -= 2;
      break;
    case 5: 
      ajouterArme(b.getArme());
    }
  }
  
}
