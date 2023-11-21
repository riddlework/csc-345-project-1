import java.util.List;

/*
 * Notes Complexity:
 * Space: N = initial solutions d = items = O((700) * d) = O(n)
 * 
 * Time:
 * n = solutions in population
 * initialPop = O(n*d)
 * testFitness = O(n*d)
 * mutate pop = O(n*d)
 * testFitness = O(n*d)
 * selection = O(5* (2n) * d)
 * testFitness = O(5 * n*d)
 * findBest = O(n)
 * 
 * Total runtime = O(n*d)
 */



//The GeneticAlgorithm class is responsible for solving a problem using a genetic algorithm.
public class GeneticAlgorithm {
	// The solve method takes a list of items and uses a genetic algorithm to find a solution.
	public static Solution solve(List<Item> itemList, int threshold) {
		// Create a population with 20 individuals, each having 8 genes.
		Population p = new Population(20, threshold);
		
		// Initialize the population with random solutions based on the given item list.
		p.initialPop(itemList);
		
		// Evaluate the fitness of the initial population.
		testFitness(p);
		
		// Mutate each solution in the population.
		for(Solution s : p.population) {
			s.mutate();
		}
		// Evaluate  the fitness of the mutated population.
		testFitness(p);
		
		// Perform selection, crossover, and mutation for a few generations (5 in this case).
		for(int i = 0; i < 5; i++) {
			p.selection();
			testFitness(p);
		}
		
		// Evaluate and display the fitness of the population after selection.
		return p.findBest();
		
		
	}
	
	// The testFitness method evaluates the fitness of each solution in the population.
	public static void testFitness(Population p) {
		for(Solution s: p.population) {
			int sum = 0;
			int weightSum = 0;
			
			// Calculate the total value and weight of the expressed genes in the solution.
			for(Items i: s.solution) {
				if(i.isExpressed()) {
					sum += i.value;
					weightSum += i.weight;
				}
			}
			
			// Update the fitness of the solution if it satisfies the weight constraint.
			if(weightSum < p.weightThreshold) {
				s.fitness = sum;
			}
		}
	}
}
