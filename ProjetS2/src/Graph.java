/**
 * Classe permettant de transformer un fichier de terrain en un tableau de int, permettant à la classe Node de créer des Nodes praticables ou non praticables
 * @author Luca PELISSERO
 *
 */
public class Graph
{
  private int[][] poids = new int[32][32];
  
  /**
   * Méthode créant le graphique de la map sous forme de tableau 2D de int. Une case à pour valeur 1 si elle est praticable, 2 sinon.
   * @param numeroTerrain
   */
  public void setPoids(int numeroTerrain)
  {
    char[][] mapChar = Terrain.lireTerrain(numeroTerrain);
    for (int i = 0; i < mapChar.length; i++) {
      for (int j = 0; j < mapChar[i].length; j++) {
        if (mapChar[i][j] == '1') {
          this.poids[i][j] = 2;
        } else {
          this.poids[i][j] = 1;
        }
      }
    }
  }
  
  /**
   * Getter sur l'attribut poids
   * @return
   */
  public int[][] getPoids()
  {
    return this.poids;
  }
  
/*
  public static void main(String[] args)
  {
    Graph g = new Graph();
    g.setPoids(0);
    int[][] i = g.getPoids();
    for (int j = 0; j < i.length; j++)
    {
      for (int k = 0; k < i[j].length; k++) {
        System.out.print(i[j][k] + " ");
      }
      System.out.print("\n");
    }
  }*/
  
}
