package spanners;

import java.util.Random;
import java.util.Scanner;

/*project TODO:
1. ask Michael about:
	- experiments - how many? how to display?
	- checking the assignment - frontal or not?
	- deadline
2. check the core (partition, clusters)
3. build experiments
4. improve main (mayan)
	- decide what to do with errors
	- add input matrix from user
	- other k's?
*/

public class mainFlow {
	
	public static void main(String[] args) {

	        boolean experiments;
	        boolean graphExperiment;
	        Scanner reader = new Scanner(System.in);
	        
	        System.out.println("-PROGRAM START-");
	        //System.out.println();
	        
	        experiments = true;
			while(experiments){	        
		        /**create graph**/
				graph originalGraph = createAndPrintOriginalGraph(reader);
				if (originalGraph==null){
					reader.close();
					System.out.println("-PROGRAM DONE-");
					return;
				}		            
		        /**create graph spanner/s**/
		        graphExperiment = true;
		        while(graphExperiment){
		        	graph spanner = createAndPrintOneSpanner(originalGraph, reader);
		        	if(spanner==null){
		        		reader.close();
		        		System.out.println("-PROGRAM DONE-");
		        		return;
		        	}
		        	graphExperiment = checkIfAnotherSpannerDesired(reader);
		        }
		        /**continue/done**/
		        experiments = checkIfAnotherGraphDesired(reader);	
			}
			reader.close();
			System.out.println("-PROGRAM DONE-");
	}

	private static graph createAndPrintOriginalGraph(Scanner reader) {
			int userChoice;
			graph originalGraph;
					
			System.out.println("Choose one way to build the graph:");
			System.out.println("1. random graph");
			System.out.println("2. user graph");
			try{
				userChoice = reader.nextInt();
				//System.out.println();
			}
			catch(Exception e){
				System.out.println("ERROR: not a number");
				//System.out.println();
				return null;
			}
			if (userChoice==2){
				/*TODO* - GET INPUT to originalGraph FROM USER*/
				originalGraph = new graph();
			}
			else if(userChoice==1){
				originalGraph = new graph();
			}
			else{
				System.out.println("ERROR: not an option");
				//System.out.println();
				return null;
			}
			System.out.println("The Original Graph:");
			originalGraph.printAdjacencyMatrix();
			//System.out.println();
			return originalGraph;
		}
		  
	private static graph createAndPrintOneSpanner(graph originalGraph, Scanner reader) {
		Random rand;
		int n;
		int k;
		int userChoice;
		n = originalGraph.getGraphSize();
		
		System.out.println("Please choose one way to get a k:");
		System.out.println("1. random k (between 1 .. n)");
		System.out.println("2. user k");
		try{
			userChoice = reader.nextInt();
			//System.out.println();
		}catch(Exception e){
			System.out.println("ERROR: not a number1");
			//System.out.println();
			return null;
		}
		if(userChoice==2){
			System.out.println("Enter a k:");
			try{
		    	k = reader.nextInt();
		    	while(k>n || k<=1){
		    		System.out.println("you entered k>n or k<=1, please try again (n="+n+"):");
		    		k = reader.nextInt();
		    	}
			}catch(Exception e){
				System.out.println("ERROR: not a number");
				//System.out.println();
				return null;
			}
		}
		else if(userChoice==1){
			rand = new Random();
			k = rand.nextInt(n-2)+2; 			        	
		}
		else{
			System.out.println("ERROR: not an option");
			//System.out.println();
			return null;
		}
		
		System.out.println("Building spanner for the graph with k="+k+"...");
		graph spanner = new graph(originalGraph.buildSpanner(k));
		
		System.out.println("The Spanner:");
		spanner.printAdjacencyMatrix();
		//System.out.println();
		
		return spanner;		
	}
	
	private static boolean checkIfAnotherSpannerDesired(Scanner reader3) {
		boolean graphExperiment;
		int userChoice;
		System.out.println("Create another spanner for this graph? (1/0)");   
		try{
			userChoice= reader3.nextInt();
		}catch(Exception e){
			System.out.println("not an option -> 0");
			return false;
		}
		if(userChoice==1){
			graphExperiment = true;
		}
		else if(userChoice==0){
			graphExperiment = false;
		}
		else{
			System.out.println("not an option -> 0");
			graphExperiment = false;
		}
		return graphExperiment;
	}

	private static boolean checkIfAnotherGraphDesired(Scanner reader) {
		boolean experiments;
		int userChoice;
		System.out.println("Create another graph (new experiment)? (1/0)") ;
		try{
			userChoice= reader.nextInt();
		}catch(Exception e){
			System.out.println("not an option -> 0");
			return false;
		}
		if(userChoice==1){
			experiments = true;
		}
		else if(userChoice==0){
			experiments = false;
		}
		else{
			System.out.println("not an option -> n");
			experiments = false;
		}	
		return experiments;
	}

}//class
