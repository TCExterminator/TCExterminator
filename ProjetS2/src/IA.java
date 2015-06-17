import java.util.ArrayList;

public class IA {
	
	private static ArrayList<Node> lesNodes = new ArrayList<>();
	
	public ArrayList<Node> IAZombie(Entity e,int terrainActuelle){
		//On initialise lesNodes grace a la map actuelle de l'entite
		Graph g = new Graph();
		g.setPoids(terrainActuelle);
		int[][] mapPoids = g.getPoids();		
		for(int i = 0;i<mapPoids.length;i++){
			for(int j = 0; j< mapPoids[i].length;j++){
				lesNodes.add(new Node(i,j,mapPoids[i][j]));				
			}
		}
		
		//Liste des nodes praticables
		ArrayList<Node> listeOuverte = new ArrayList<Node>();
		for(int i =0;i<lesNodes.size();i++){
			if(lesNodes.get(i).isPraticable()){
				listeOuverte.add(lesNodes.get(i));
			}
		}
		for(Node n:listeOuverte){
			n.setVoisin(listeOuverte);
			System.out.println("Voisins du node " + n);
			for(Node nV:n.getVoisin()){
				System.out.println(nV);
			}
			System.out.println();
		}
		
		ArrayList<Node> listeFerme = new ArrayList<>();
		Node nCourant = new Node(e);
		Node nFinal = new Node(Jeu.joueur);
		while(!nCourant.equals(nFinal)){
		}
		return null;
	}

	
	
	//TEST SYSTEME NODE VOISIN
	public static void main(String[] args){
		Graph g = new Graph();
		g.setPoids(0);
		int[][] mapPoids = g.getPoids();		
		for(int i = 0;i<mapPoids.length;i++){
			for(int j = 0; j< mapPoids[i].length;j++){
				lesNodes.add(new Node(i,j,mapPoids[i][j]));				
			}
		}
		
		//Liste des nodes praticables
		ArrayList<Node> listeOuverte = new ArrayList<Node>();
		for(int i =0;i<lesNodes.size();i++){
			if(lesNodes.get(i).isPraticable()){
				listeOuverte.add(lesNodes.get(i));
			}
		}
		for(Node n:listeOuverte){
			n.setVoisin(listeOuverte);
			System.out.println("Voisins du node " + n);
			for(Node nV:n.getVoisin()){
				System.out.println(nV);
			}
			System.out.println();
		}
	}
}
