import java.io.PrintStream;
import java.util.ArrayList;

public class IA
{
  private static ArrayList<Node> lesNodes = new ArrayList();
  private static ArrayList<Node> listeOuverte = new ArrayList();
  
  public static void setLesNodes(int terrainActuel)
  {
    Graph g = new Graph();
    g.setPoids(terrainActuel);
    int[][] mapPoids = g.getPoids();
    for (int i = 0; i < mapPoids.length; i++) {
      for (int j = 0; j < mapPoids[i].length; j++) {
        lesNodes.add(new Node(i, j, mapPoids[i][j]));
      }
    }
  }
  
  public static void setlisteOuverte()
  {
    for (int i = 0; i < lesNodes.size(); i++) {
      if (((Node)lesNodes.get(i)).isPraticable()) {
        listeOuverte.add((Node)lesNodes.get(i));
      }
    }
    for (Node n : listeOuverte) {
      n.setVoisin(listeOuverte);
    }
  }
  
  public ArrayList<Node> IAZombie(int x, int y, int terrainActuelle)
  {
    ArrayList<Node> listeOuverteCopie = new ArrayList(listeOuverte);
    ArrayList<Node> listeFerme = new ArrayList();
    Node nCourant = new Node(x, y);
    
    Node nFinal = new Node(Jeu.getActivTerrain().getPerso().getX(), Jeu.getActivTerrain().getPerso().getY());
    for (Node n : listeOuverteCopie)
    {
      if (n.equals(nCourant)) {
        nCourant = n;
      }
      if (n.equals(nFinal)) {
        nFinal = n;
      }
    }
    System.out.println(nCourant.equals(nFinal));
    while (!nCourant.equals(nFinal))
    {
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      Node nIdeal = nCourant;
      System.out.println(nCourant.getVoisin().size());
      for (Node n : nCourant.getVoisin()) {
        if (nCourant.equals(nIdeal)) {
          nIdeal = n;
        } else if ((n.getDistanceNode(nFinal) < nIdeal.getDistanceNode(nFinal)) && 
          (!listeFerme.contains(n))) {
          nIdeal = n;
        }
      }
      listeFerme.add(nIdeal);
      nCourant = nIdeal;
    }
    return listeFerme;
  }
  
  public static void main(String[] args)
  {
    Graph g = new Graph();
    g.setPoids(0);
    int[][] mapPoids = g.getPoids();
    for (int i = 0; i < mapPoids.length; i++) {
      for (int j = 0; j < mapPoids[i].length; j++) {
        lesNodes.add(new Node(i, j, mapPoids[i][j]));
      }
    }
    ArrayList<Node> listeOuverte = new ArrayList();
    for (int i = 0; i < lesNodes.size(); i++) {
      if (((Node)lesNodes.get(i)).isPraticable()) {
        listeOuverte.add((Node)lesNodes.get(i));
      }
    }
    for (Node n : listeOuverte)
    {
      n.setVoisin(listeOuverte);
      System.out.println("Voisins du node " + n);
      for (Node nV : n.getVoisin()) {
        System.out.println(nV);
      }
      System.out.println();
    }
  }
}
