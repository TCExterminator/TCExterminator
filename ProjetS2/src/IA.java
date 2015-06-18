import java.io.PrintStream;
import java.util.ArrayList;


public class IA
{
  private static ArrayList<Node> lesNodes = new ArrayList<Node>();
  private static ArrayList<Node> listeOuverte = new ArrayList<Node>();
  
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
    ArrayList<Node> listeOuverteCopie = new ArrayList<Node>(listeOuverte);
    ArrayList<Node> listeFerme = new ArrayList<Node>();
    
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
    listeFerme.add(nFinal);
    
    System.out.println(nCourant.equals(nFinal));
    while (!nCourant.equals(nFinal))
    {
     
      Node nIdeal = new Node(10000000,10000000,1);
      System.out.println(nCourant.getVoisin().size());
      for (Node n : nCourant.getVoisinPraticable()) {
        if ((listeOuverteCopie.contains(n) && n.getDistanceNode(nFinal) <= nIdeal.getDistanceNode(nFinal)) && 
          (!listeFerme.contains(n) && !n.getVoisinPraticable().isEmpty())) {
          nIdeal = n;
        }
      }
      listeOuverteCopie.remove(nCourant);
      listeFerme.add(nIdeal);
      nCourant = nIdeal;
    }
    return listeFerme;
  }
  
  
  //TEST NODE VOISIN
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
    ArrayList<Node> listeOuverte = new ArrayList<Node>();
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
