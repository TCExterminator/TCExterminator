public class Arme
{
  private int puissance;
  private int munition;
  private int poid;
  private int portee;
  private int dispertion;
  private int cadence;
  
  /**
   * Constructeur parametré de la classe Arme
   * @param puissance
   * @param munition
   * @param poid
   * @param portee
   * @param dispertion
   * @param cadence
   */
  public Arme(int puissance, int munition, int poid, int portee, int dispertion, int cadence)
  {
    this.puissance = puissance;
    this.munition = munition;
    this.poid = poid;
    this.portee = portee;
    this.dispertion = dispertion;
    this.cadence = cadence;
  }
  
  /**
   * Constructeur par copie de la classe Arme
   * @param ar
   */
  public Arme(Arme ar)
  {
    this.puissance = ar.puissance;
    this.munition = ar.munition;
    this.poid = ar.poid;
    this.portee = ar.portee;
    this.dispertion = ar.dispertion;
    this.cadence = ar.cadence;
  }
  
  /**
   * Méthode qui est sensée affucher l'Arme
   */
  public void afficher() {}
  
  /**
   * Getter de puissance
   * @return
   */
  public int getPuissance()
  {
    return this.puissance;
  }
  
  /**
   * Getter de munition
   * @return
   */
  public int getMunition()
  {
    return this.munition;
  }
  
  /**
   * getter de poid
   * @return
   */
  public int getPoid()
  {
    return this.poid;
  }
  
  /**
   * Getter de portee 
   * @return
   */
  public int getPortee()
  {
    return this.portee;
  }
  
  /**
   * getter de dispertion
   * @return
   */
  public int getDispertion()
  {
    return this.dispertion;
  }
  
  /**
   * Getter de cadence
   * @return
   */
  public int getCadence()
  {
    return this.cadence;
  }
  
  /**
   * setter de puissance
   * @param puissance
   */
  public void setPuissance(int puissance)
  {
    this.puissance = puissance;
  }
  
  /**
   * Permet de décrémenter la variable munition
   * @param munition
   */
  public void redMunition()
  {
    this.munition--;
  }
}
