
public class Solution {
	
	
	private int numItems;
	public Items[] solution;
	public int fitness;
	public Solution(int num, String[] names, int[] value, int[] weight){
		numItems = num;
		fitness = 0;
		solution = new Items[numItems];
		for(int j = 0; j < numItems; j++) {
			boolean expressed = Math.random() > 0.5;
			Items newItem = new Items(weight[j], value[j], names[j]);
			if(expressed) {
				newItem.changeState();
			}
			solution[j] = newItem;
		}
	}
	
	public Solution(Solution dad, Solution mom, int spliceValue, boolean whichParent){
		numItems = dad.numItems;
		fitness = 0;
		solution = new Items[numItems];
		
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
	
	
	public int getNumSize() {
		return this.numItems;
	}
	
	public void setFitness(int fit) {
		this.fitness = fit;
	}
	
	public String toString() {
		String printItems = "";
		for(int j = 0; j < solution.length; j++) {
			printItems += solution[j].toString();
		}
		return printItems + " " + this.fitness;
	}
	
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
