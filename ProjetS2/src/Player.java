import java.util.ArrayList;

public class Player
  extends Entity
{
  private int armure;
  private int mana;
  private int caseActiveInventaire = 0;
  private ArrayList<Arme> inventaire = new ArrayList();
  private int[] perso;
  private static int poidMax = 10;
  private static int cooldown;
  
  public Player(int posx, int posy, int speed)
  {
    super(posx, posy, speed, "perso", 15, 100);
    this.inventaire.add(Jeu.listeArme[0]);
  }
  
  public int getCooldown()
  {
    return cooldown;
  }
  
  public void heatCooldown()
  {
    cooldown -= 1;
  }
  
  public void tirer(int dx, int dy)
  {
    Arme armeActive = (Arme)this.inventaire.get(this.caseActiveInventaire);
    
    int balles = armeActive.getMunition();
    if ((balles > 0) || (armeActive.getPoid() == 0))
    {
      Jeu.getActivTerrain().getlesProj().add(new Projectile(dx, dy, getX(), getY(), 10, armeActive.getPuissance(),armeActive.getPortee()));
      if (balles != -42) {
        armeActive.setMunition(balles - 1);
      }
      cooldown = armeActive.getCadence();
    }
  }
  
  public void lacherArme() {}
  
  public void setArmure(int ar)
  {
    this.armure = ar;
  }
  
  public void setMana(int ma)
  {
    this.mana = ma;
  }
  
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
  
  public void affecterBonus(Bonus b)
  {
    int nbBonus = b.getEffet();
    switch (nbBonus)
    {
    case 1: 
      setSante(getSante() + 50);
      break;
    case 2: 
      setArmure(this.armure + 50);
      break;
    case 3: 
      setMana(this.mana + 20);
      break;
    case 4: 
      setVitesse(this.vitesse + 1);
      break;
    case 5: 
      ajouterArme(b.getArme());
    }
  }
  
}
