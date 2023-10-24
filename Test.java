
public class Test {
	public static void main(String[] args) {
		String[] names = {"Phone", "Book", "Pen", " Bottle", "Eraser", "Jacket"};
		int[] value = {800, 30, 3, 50, 1, 70};
		int[] weight = {2, 5, 1, 3, 1, 7};
		Population p = new Population(20, 500);
		p.initialPop(6, names, value, weight);
		testFitness(p);
		System.out.println(p.toString());
		for(Solution s : p.population) {
			s.mutate();
		}
		testFitness(p);
		System.out.println("\n" + p.toString());
		
	}
	
	public static void testFitness(Population p) {
		for(Solution s: p.population) {
			int sum = 0;
			for(Items i: s.solution) {
				if(i.isExpressed()) {
					sum += i.value;
				}
			}
			if(sum < p.weightThreshold) {
				s.fitness = sum;
			}
		}
	}
}
