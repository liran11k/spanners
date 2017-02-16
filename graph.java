package spanners;

import java.util.Random;
	
public class graph {
	
	int[][] adjacencyMatrix;
	int n;
	
	/*empty constructor (random)*/
	graph(){
		adjacencyMatrix = buildRandomAdjacencyMatrix();
		n = adjacencyMatrix.length;
	}

	/*non-empty constructor*/
	graph(int[][]adjacencyMatrix){
		this.adjacencyMatrix = adjacencyMatrix;
		n = adjacencyMatrix.length;
	}
	
	private static int[][] buildRandomAdjacencyMatrix() {	
		Random rand = new Random();
		int matrixSize = rand.nextInt(49) + 2;
		//matrixSize = 5;
		int[][] matrix = new int[matrixSize][matrixSize];
		int isNeighbour;

		for(int i=0; i<matrix.length; i++){
			matrix[i][i] = 0;
			
			for(int j=i+1; j<matrix.length; j++){
				isNeighbour = rand.nextInt(2);
				matrix[i][j] = isNeighbour;
				matrix[j][i] = isNeighbour;
			}
		}
		return matrix;
	}	
	
	protected int getGraphSize(){
		return n;		
	}
	
	protected void printAdjacencyMatrix(){
		System.out.println("length: "+ n);
		int i, j;
		for(i=0; i<n; i++){
			for(j=0; j<n-1; j++){
				System.out.print(adjacencyMatrix[i][j]+",");
			}
			System.out.print(adjacencyMatrix[i][j]);
			System.out.println("");
		}	
	}

	public int[][] buildSpanner(int k) {
		int[][] spanner = new int[n][n];
		
//		partition P = new partition(this, spanner, k);
			
		
		
		return spanner;
	}

}
