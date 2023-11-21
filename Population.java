
import java.util.List;

//The Population class represents a population of solutions in the genetic algorithm.
public class Population {

	// Array to store the solutions in the population.
	public Solution[] population;
	// The size of the population.
	public int testSize;
	
	// The weight threshold for solutions in the population.
	public int weightThreshold;
	
	// Constructor for creating a population with a specified size and weight threshold.
	public Population(int Size, int threshold) {
		population = new Solution[Size];
		testSize = Size;
		weightThreshold = threshold;
	}
	
	// Initialize the population with random solutions based on provided characteristics.
	public void initialPop(int numItems, String[] names, int[] value, int[] weight){
		
		for(int i = 0; i < testSize; i++) {
			population[i] = new Solution(numItems, names, value, weight);
		}
		
	}

	// Initialize the population with solutions based on a list of items.
	public void initialPop(List<Item> popPeople) {
		for(int i = 0; i < testSize; i++) {
			population[i] = new Solution(popPeople.size(), popPeople);
		}
	}
	
	// Perform selection, crossover, and mutation to create a new generation of the population.
	public void selection() {
		//System.out.println(testSize);
		Solution[] newPop = new Solution[testSize * 2];
		newPop[0] = findBest();
		
		int i = 1;
		int sol1 = 0;
		int sol2 = 0;
		int sol3 = 0;
		int sol4 = 0;
		while(i < (testSize * 2)) {
			
			sol1 = (int) (Math.random() * (testSize));
			
			sol2 = (int) (Math.random() * (testSize));
				
			sol3 = (int) (Math.random() * (testSize - 1));
				
			sol4 = (int) (Math.random() * (testSize- 1));
			
			// Select parents and perform crossover.
			Solution dad = compare(population[sol1], population[sol2]);
			Solution mom = compare(population[sol3], population[sol4]);
			
			int spliceVal = (int) (Math.random() * (dad.solution.length - 1));
			
			Solution firstChild = new Solution(dad, mom, spliceVal, true);
			Solution secondChild = new Solution(dad, mom, spliceVal, false);
			
			// Add the new solutions to the next generation.
			newPop[i] = firstChild;
			i++;
			if(i >= testSize * 2) {
				break;
			}
			newPop[i] = secondChild;
			i++;
		}
		this.population = newPop;
		this.testSize *= 2;
	}
	
	
	// Helper method to compare two solutions and return the one with higher fitness.
	private Solution compare(Solution one, Solution two) {
		if(one.fitness > two.fitness) {
			return one;
		}
		return two;
		
	}
	
	// Find and return the best solution in the current population.
	public Solution findBest() {
		Solution best = population[0];
		for(Solution member: population) {
			if(member.fitness > best.fitness) {
				best = member;
			}
			
		}
		return best;
	}
	
	// Override toString method to display the entire population as a string.
	@Override
	public String toString() {
		String popOut = "";
		for(int i = 0; i < population.length; i++) {
			
			popOut += population[i].toString() + "\n";
		}
		return popOut;
	}

}
