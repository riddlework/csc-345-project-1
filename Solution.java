import java.util.List;
//The Solution class represents an individual solution in the genetic algorithm.
public class Solution {
	
	
	private int numItems;
	
	// Array to store items in the solution.
	public Items[] solution;
	
	// Fitness value of the solution.
	public int fitness;
	
	// Constructor for creating a random solution with specified characteristics.
	public Solution(int num, String[] names, int[] value, int[] weight){
		numItems = num;
		fitness = 0;
		solution = new Items[numItems];
		
		// Initialize the solution with random expression states for each item.
		for(int j = 0; j < numItems; j++) {
			boolean expressed = Math.random() > 0.5;
			Items newItem = new Items(weight[j], value[j], names[j]);
			if(expressed) {
				newItem.changeState();
			}
			solution[j] = newItem;
		}
	}
	
	// Constructor for creating a solution based on a list of items.
	public Solution(int num, List<Item> i) {
		numItems = num;
		fitness = 0;
		solution = new Items[numItems];
		
		// Initialize the solution based on the provided list of items.
		for(int j = 0; j < numItems; j++) {
			boolean expressed = Math.random() > 0.5;
			Items newItem = new Items(i.get(j).weight, i.get(j).value, i.get(j).name);
			if(expressed) {
				newItem.changeState();
			}
			solution[j] = newItem;
		}
		
	}
	
	// Constructor for creating a solution by combining genes from two parent solutions.
	public Solution(Solution dad, Solution mom, int spliceValue, boolean whichParent){
		numItems = dad.numItems;
		fitness = 0;
		solution = new Items[numItems];
		
		// Create a new solution by combining genes from two parent solutions based on spliceValue.
		if(whichParent) {
			int i = 0;
			while(i < numItems) {
				if(i <= spliceValue) {
					solution[i] = dad.solution[i];
				}
				else {
					solution[i] = mom.solution[i];
				}
				i++;
		    }
		
		}
		else {
			int i = 0;
			while(i < numItems) {
				if(i <= spliceValue) {
					solution[i] = mom.solution[i];
				}
				else {
					solution[i] = dad.solution[i];
				}
				i++;
		    }
		}
	}
	
	
	// Getter method to retrieve the number of items in the solution.

	public int getNumSize() {
		return this.numItems;
	}
	
	// Setter method to set the fitness value of the solution.
	public void setFitness(int fit) {
		this.fitness = fit;
	}
	
	// toString method to display the solution as a string.
	public String toString() {
		String printItems = "";
		for(int j = 0; j < solution.length; j++) {
			printItems += solution[j].toString();
		}
		return printItems + " " + this.fitness;
	}
	
	// Mutate method to introduce random changes to the solution.
	public void mutate() {
		final double mutateRate = 0.1;
		for(Items i: this.solution) {
			double Mutate = Math.random();
			if(Mutate <= mutateRate) {
				i.changeState();
			}
		}
	}
	
	
	
}
