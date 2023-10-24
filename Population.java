import java.util.ArrayList;


public class Population {
	
	public Solution[] population;
	public static int testSize;
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
		Solution[] newPop = new Solution[testSize];
		ArrayList<Integer> accessed = new ArrayList<>();
		
		int i = 0;
		while(i < testSize) {
			boolean num1Val = false;
			boolean num2Val = false;
			boolean num3Val = false;
			boolean num4Val = false;
			int sol1 = 0;
			int sol2 = 0;
			int sol3 = 0;
			int sol4 = 0;
			while(!num1Val) {
			sol1 = (int) (Math.random() * (testSize - 1));
			if(!accessed.contains(sol1)) {
				accessed.add(sol1);
				
				num1Val = true;
			}
			}
			if(accessed.size() >= testSize - 1) {
				accessed.clear();
				accessed.add(sol1);
			}
			
			while(!num2Val) {
			sol2 = (int) (Math.random() * (testSize - 1));
			if(!accessed.contains(sol2)) {
				accessed.add(sol2);
				num2Val = true;
			}
			}
			
			if(accessed.size() >= testSize - 1) {
				accessed.clear();
				accessed.add(sol2);
			}
			
			
			while(!num3Val) {
				sol3 = (int) (Math.random() * (testSize - 1));
				if(!accessed.contains(sol1=3)) {
					accessed.add(sol3);
					
					num1Val = true;
				}
				}
				if(accessed.size() >= testSize - 1) {
					accessed.clear();
					accessed.add(sol3);
				}
				
				while(!num4Val) {
				sol4 = (int) (Math.random() * (testSize - 1));
				if(!accessed.contains(sol4)) {
					accessed.add(sol4);
					num2Val = true;
				}
				}
				
				if(accessed.size() >= testSize - 1) {
					accessed.clear();
					accessed.add(sol4);
				}
				
			
			
			Solution dad = compare(population[sol1], population[sol2]);
			Solution mom = compare(population[sol3], population[sol4]);
			
			
			
			
			
		}
	}
	
	private Solution compare(Solution one, Solution two) {
		if(one.fitness > two.fitness) {
			return one;
		}
		return two;
		
	}
	
	private void makeChild(Solution dad, Solution mom) {
		int geneSplit = (int) (Math.random() * (dad.solution.length -1));
		
	}
	
	public String toString() {
		String popOut = "";
		for(int i = 0; i < population.length; i++) {
			
			popOut += population[i].toString() + "\n";
		}
		return popOut;
	}

}
