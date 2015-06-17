import java.util.ArrayList;

public class IA {
	
	private static ArrayList<Node> lesNodes = new ArrayList<>();
	private static ArrayList<Node> listeOuverte = new ArrayList<>();
	
	
	public static void setLesNodes(int terrainActuel){
		//On initialise lesNodes grace a la map actuelle de l'entite
				Graph g = new Graph();
				g.setPoids(terrainActuel);
				int[][] mapPoids = g.getPoids();		
				for(int i = 0;i<mapPoids.length;i++){
					for(int j = 0; j< mapPoids[i].length;j++){
						IA.lesNodes.add(new Node(i,j,mapPoids[i][j]));				
					}
				}
	}
				
	public static void setlisteOuverte(){
		for(int i =0;i<IA.lesNodes.size();i++){
			if(IA.lesNodes.get(i).isPraticable()){
				IA.listeOuverte.add(lesNodes.get(i));
			}
		}
		for(Node n:IA.listeOuverte){
			n.setVoisin(IA.listeOuverte);
			}
	}
	
	public ArrayList<Node> IAZombie(int x, int y,int terrainActuelle){
		
			ArrayList<Node> listeOuverteCopie = new ArrayList<Node>(IA.listeOuverte);
			ArrayList<Node> listeFerme = new ArrayList<Node>();
			Node nCourant = new Node(x,y);
			
			Node nFinal = new Node(Jeu.joueur.getX(),Jeu.joueur.getY());
		
			//Permet de definir les nodes de débuts et de fin précisement, avec leurs voisins.
			for(Node n:listeOuverteCopie){
			if(n.equals(nCourant)){
				nCourant = n;
				
				}
				if(n.equals(nFinal)){
					
				nFinal = n;
				}
			}

			//Boucle principale
			System.out.println(nCourant.equals(nFinal));
			while(!nCourant.equals(nFinal)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Node nIdeal = nCourant;
				System.out.println(nCourant.getVoisin().size());
				for(Node n:nCourant.getVoisin()){
					if(nCourant.equals(nIdeal)){
						nIdeal = n;
					}
					else if(n.getDistanceNode(nFinal)<nIdeal.getDistanceNode(nFinal) 
								&& !listeFerme.contains(n)) nIdeal = n;
				}
				listeFerme.add(nIdeal);
				nCourant = nIdeal;
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
