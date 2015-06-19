import java.util.ArrayList;

/**
 * Classe permettant de créer un système de nod epour chaque Terrain. Ces nodes permettent de calculer des chemins entre deux positions, en passant par des cases praticables.
 * @author Luca PELISSERO
 *
 */
public class Node
{
  private int posX;
  private int posY;
  private int typeCase;
  private ArrayList<Node> voisins = new ArrayList<Node>();
  
  /**
   * 
   * @param x, la position en x d'une entité
   * @param y la position en y d'une entité
   * Constructeur d'une node avec des coordonnées.
   */
  public Node(int x, int y)
  {
    this.posX = (x / 32);
    this.posY = (y / 32);
  }
  
  /**
   * 
   * @param x, la position en x de la node dans les tableaux de la classe Graph
   * @param y, la position en y de la node dans les tableaux de la classe Graph
   * @param type, le type de la Node, qui correspond au type de la case x,y d'un tableau de la classe Graph
   * Constructeur d'une node via les informations de la classe Graph
   */
  public Node(int x, int y, int type)
  {
	this.posX = (x);
	this.posY = (y);
    this.typeCase = type;
  }
  
  /**
   * Méthode permettant de savoir si une node est praticable.
   * @return true si la node est praticable, false sinon
   */
  public boolean isPraticable()
  {
    return this.typeCase == 1;
  }
  
  /**
   * Methode testant l'égalité de deux nodes
   */
  @Override
  public boolean equals(Object o)
  {
    boolean b = false;
    if (this == o)
    {
      b = true;
    }
    else if ((o instanceof Node))
    {
      Node n = (Node)o;
      if ((this.posX == n.getPosX()) && (this.posY == n.getPosY())) {
        b = true;
      }
    }
    return b;
  }
  
  /**
   * Getter sur l'attribut PosY
   */
  private int getPosY() {
	return this.posY;
}

  /**
   * Getter sur l'attribut PosX
   */
private int getPosX() {
	return this.posX;
}

/**
   * Méthode vérifiant si la node this est voisine à la node en paramètre
   * @param n la node sur laquelle le test sera effectué
   * @return true si la node en paramètre est voisine à la node, false sinon
   */
  public boolean estVoisin(Node n)
  {
    boolean b = false;
    if (((this.posX == n.getPosX() + 1) && (this.posY == n.getPosY() + 1)) || 
      ((this.posX == n.getPosX() + 1) && (this.posY == n.getPosY())) || 
      ((this.posX == n.getPosX() + 1) && (this.posY == n.getPosY() - 1)) || 
      ((this.posX == n.getPosX()) && (this.posY == n.getPosY() - 1)) || 
      ((this.posX == n.getPosX() - 1) && (this.posY == n.getPosY() - 1)) || 
      ((this.posX == n.getPosX() - 1) && (this.posY == n.getPosY())) || 
      ((this.posX == n.getPosX() - 1) && (this.posY == n.getPosY() + 1)) || (
      (this.posX == n.getPosX()) && (this.posY == n.getPosY() + 1))) {
      b = true;
    }
    return b;
  }
  
  /**
   * Methode affectant à chaque node d'une liste les nodes qui lui sont voisines dans cette même liste
   * @param listeVoisin la liste des nodes
   */
  public void setVoisin(ArrayList<Node> listeVoisin)
  {
    for (Node n : listeVoisin) {
      if (estVoisin(n)) {
        this.voisins.add(n);
      }
    }
  }
  
  /**
   * Getter sur l'attribut voisins
   * @return
   */
  public ArrayList<Node> getVoisin()
  {
    return this.voisins;
  }
  
  /**
   * Methode retournant une liste des voisins praticable de la node this.
   * @return
   */
  public ArrayList<Node> getVoisinPraticable()
  {
	ArrayList<Node> vP = new ArrayList<Node>();
	for(Node n:this.voisins)
	{
		if(n.isPraticable()){
			vP.add(n);
		}
	}
	return vP;
  }
  
  /**
   * 
   */
  @Override
  public String toString()
  {
    return "Node X " + this.posX + " Y " + this.posY;
  }
  
  /**
   * Méthode calculant la distance eucliennde entre la node this et la node en paramètre
   * @param n la node 
   * @return la distance euclienne entre les deux nodes
   */
  public int getDistanceNode(Node n)
  {
    return (int) Math.sqrt(this.posX - n.getPosX() - (this.posY - n.getPosY() ) );
  }
  
  /*
  public static void main(String[] args)
  {
    Node nCentre = new Node(5, 5, 1);
    Node nDroite = new Node(6, 5, 1);
    Node nGauche = new Node(4, 5, 1);
    Node nHaut = new Node(5, 4, 1);
    Node nBas = new Node(5, 6, 1);
    Node nD1 = new Node(6, 6, 1);
    Node nD2 = new Node(4, 4, 1);
    Node nD3 = new Node(6, 4, 1);
    Node nD4 = new Node(4, 6, 1);
    Node inutile = new Node(0, 0, 2);
    Node nd11 = new Node(1, 1, 1);
    Node zombie = new Node(34, 34);
    System.out.println(" Voisin du centre :\n");
    System.out.println("Droite : " + nCentre.estVoisin(nDroite));
    System.out.println("Gauche : " + nCentre.estVoisin(nGauche));
    System.out.println("Haut : " + nCentre.estVoisin(nHaut));
    System.out.println("Bas : " + nCentre.estVoisin(nBas));
    System.out.println("D1 : " + nCentre.estVoisin(nD1));
    System.out.println("D2 : " + nCentre.estVoisin(nD2));
    System.out.println("D3 : " + nCentre.estVoisin(nD3));
    System.out.println("D4 : " + nCentre.estVoisin(nD4));
    System.out.println("Inutile : " + nCentre.estVoisin(inutile));
    System.out.println("\nPraticabilit?");
    System.out.println("Centre : " + nCentre.isPraticable());
    System.out.println("Inutile : " + inutile.isPraticable());
    System.out.println(zombie.equals(nd11));
  }*/
  
}
