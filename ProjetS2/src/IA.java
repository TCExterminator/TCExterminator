import java.util.ArrayList;

public class IA {
	
	private static ArrayList<Node> lesNodes = new ArrayList<>();
	private static ArrayList<Node> listeOuverte = new ArrayList<>();
	
	
	public void setListeOuverte(int terrainActuelle){
		//On initialise lesNodes grace a la map actuelle de l'entite
				Graph g = new Graph();
				g.setPoids(terrainActuelle);
				int[][] mapPoids = g.getPoids();		
				for(int i = 0;i<mapPoids.length;i++){
					for(int j = 0; j< mapPoids[i].length;j++){
						IA.lesNodes.add(new Node(i,j,mapPoids[i][j]));				
					}
				}
	}
				
	public void setlisteOuverte(){
		for(int i =0;i<IA.lesNodes.size();i++){
			if(IA.lesNodes.get(i).isPraticable()){
				IA.listeOuverte.add(lesNodes.get(i));
			}
		}
		for(Node n:IA.listeOuverte){
			n.setVoisin(IA.listeOuverte);
			}
	}
	
	
	public ArrayList<Node> IAZombie(Entity e,int terrainActuelle){
				
		ArrayList<Node> listeFerme = new ArrayList<>();
		Node nCourant = new Node(e);
		Node nFinal = new Node(Jeu.joueur);
		
		//Permet de definir les nodes de débuts et de fin précisement, avec leurs voisins.
		for(Node n:IA.listeOuverte){
			if(n.equals(nCourant)){
				nCourant = n;
			}
			if(n.equals(nFinal)){
				nFinal = n;
			}
		}

		//Boucle principale
		
		while(!nCourant.equals(nFinal)){
			if(!IA.listeOuverte.isEmpty()){
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
				IA.listeOuverte.remove(nIdeal);
			}
			}
			else nCourant = nFinal;
		}
		return listeFerme;
	}	
	
	/*//TEST SYSTEME NODE VOISIN 
	public static void main(String[] args){
		Graph g = new Graph();
		g.setPoids(0);
		int[][] mapPoids = g.getPoids();		
		for(int i = 0;i<mapPoids.length;i++){
			for(int j = 0; j< mapPoids[i].length;j++){
				lesNodes.add(new Node(i,j,mapPoids[i][j]));				
			}
		}
		
		Liste des nodes praticables
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
	}*/
}
