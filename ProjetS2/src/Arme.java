public class Arme
{
  private int puissance;
  private int munition;
  private int poid;
  private int portee;
  private int dispertion;
  private int cadence;
  
  public Arme(int puissance, int munition, int poid, int portee, int dispertion, int cadence)
  {
    this.puissance = puissance;
    this.munition = munition;
    this.poid = poid;
    this.portee = portee;
    this.dispertion = dispertion;
    this.cadence = cadence;
  }
  
  public Arme(Arme ar)
  {
    this.puissance = ar.puissance;
    this.munition = ar.munition;
    this.poid = ar.poid;
    this.portee = ar.portee;
    this.dispertion = ar.dispertion;
    this.cadence = ar.cadence;
  }
  
  public void afficher() {}
  
  public int getPuissance()
  {
    return this.puissance;
  }
  
  public int getMunition()
  {
    return this.munition;
  }
  
  public int getPoid()
  {
    return this.poid;
  }
  
  public int getPortee()
  {
    return this.portee;
  }
  
  public int getDispertion()
  {
    return this.dispertion;
  }
  
  public int getCadence()
  {
    return this.cadence;
  }
  
  public void setPuissance(int puissance)
  {
    this.puissance = puissance;
  }
  
  public void setMunition(int munition)
  {
    this.munition = munition;
  }
  
  public void setPoid(int poid)
  {
    this.poid = poid;
  }
  
  public void setPortee(int portee)
  {
    this.portee = portee;
  }
  
  public void setDispertion(int dispertion)
  {
    this.dispertion = dispertion;
  }
  
  public void setCadence(int cadence)
  {
    this.cadence = cadence;
  }
  
}
