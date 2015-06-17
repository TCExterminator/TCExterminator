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
			}
		
		ArrayList<Node> listeFerme = new ArrayList<>();
		Node nCourant = new Node(e);
		Node nFinal = new Node(Jeu.joueur);
		//Permet de definir les nodes de débuts et de fin précisement, avec leurs voisins.
		for(Node n:listeOuverte){
			if(n.equals(nCourant)){
				nCourant = n;
			}
			if(n.equals(nFinal)){
				nFinal = n;
			}
		}

		//Boucle principale
		
		while(!nCourant.equals(nFinal)){
			if(!listeOuverte.isEmpty()){
			int dist = 1000000000;
			Node nIdeal = new Node(-1,-1,-1);
			for(Node n:nCourant.getVoisin()){
				int a =(n.getPosX() - nFinal.getPosX()) - (n.getPosY() - nFinal.getPosY()) * 
						(n.getPosX() - nFinal.getPosX()) - (n.getPosY() - nFinal.getPosY());
				if(a < dist && !listeFerme.contains(n)){
					dist = a;
					nIdeal = n;
				}	
				listeFerme.add(nIdeal);
				listeOuverte.remove(nIdeal);
			}
			}
			else nCourant = nFinal;
		}
		return listeFerme;
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
