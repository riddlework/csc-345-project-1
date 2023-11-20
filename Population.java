


public class Population {
	
	public Solution[] population;
	public int testSize;
	public int weightThreshold;
	public Population(int Size, int threshold) {
		population = new Solution[Size];
		testSize = Size;
		weightThreshold = threshold;
	}
	
	
	public void initialPop(int numItems, String[] names, int[] value, int[] weight){
		
		for(int i = 0; i < testSize; i++) {
			population[i] = new Solution(numItems, names, value, weight);
		}
		
	}
	
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
				
			Solution dad = compare(population[sol1], population[sol2]);
			Solution mom = compare(population[sol3], population[sol4]);
			
			int spliceVal = (int) (Math.random() * (dad.solution.length - 1));
			
			Solution firstChild = new Solution(dad, mom, spliceVal, true);
			Solution secondChild = new Solution(dad, mom, spliceVal, false);
			
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
	
	private Solution compare(Solution one, Solution two) {
		if(one.fitness > two.fitness) {
			return one;
		}
		return two;
		
	}
	
	public Solution findBest() {
		Solution best = population[0];
		for(Solution member: population) {
			if(member.fitness > best.fitness) {
				best = member;
			}
			
		}
		return best;
	}
	@Override
	public String toString() {
		String popOut = "";
		for(int i = 0; i < population.length; i++) {
			
			popOut += population[i].toString() + "\n";
		}
		return popOut;
	}

}
