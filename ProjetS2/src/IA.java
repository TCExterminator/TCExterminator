import java.util.ArrayList;

public class IA {
	
	private ArrayList<Node> lesNodes = new ArrayList<>();
	
	public ArrayList<Node> IAZombie(Entity e,int terrainActuelle){
		//On initialise lesNodes grâce à la map actuelle de l'entité
		Graph g = new Graph();
		g.setPoids(terrainActuelle);
		int[][] mapPoids = g.getPoids();		
		for(int i = 0;i<mapPoids.length;i++){
			for(int j = 0; j< mapPoids[i].length;j++){
				lesNodes.add(new Node(i,j,mapPoids[i][j]));				
			}
		}
		
		//Liste des nodes praticables
		ArrayList<Node> listeOuverte = new ArrayList<>();
		for(int i =0;i<this.lesNodes.size();i++){
			if(this.lesNodes.get(i).isPraticable()){
				listeOuverte.add(lesNodes.get(i));
			}
		}
		ArrayList<Node> listeFerme = new ArrayList<>();
		Node nCourant = new Node(e);
		Node nFinal = new Node(Jeu.joueur);
		
		return null;
		
	}
}
