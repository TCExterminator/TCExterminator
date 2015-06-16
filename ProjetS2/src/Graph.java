public class Graph {
	
	private int[][] poids = new int[32][32];

	public void setPoids(int numeroTerrain){
		// On récupère le tableau des cases du terrain.
		char[][] mapChar = Maping.getTerrain(numeroTerrain);
		
		// Parcours du tableau des cases.
		for(int i =0;i < mapChar.length;i++){
			for(int j=0; j<mapChar[i].length;j++){				
				//Test si le block est un mur ou non, pour définir un poids.
				if(mapChar[i][j]=='1'){
					this.poids[i][j] = 2;
				}
				else {
					this.poids[i][j] = 1;
				}
			}
		}
		
	}
	
	public int[][] getPoids(){
		return this.poids;
	}
	
	public static void main(String[] args){
		Graph g = new Graph();
		g.setPoids(0);
		int[][] i = g.getPoids();
		for(int j=0;j< i.length;j++){
			for( int k =0; k<i[j].length;k++){
				System.out.print(i[j][k]+ " ");
			}
			System.out.print("\n");
		}
	}
}




