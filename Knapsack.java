import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class Knapsack {
	public static void main(String args[]) {
		
		String[] testFiles = {"Game-Data.csv", "Stock-Data.csv", "Courses-Data.csv"};
		int[] capacities = {20, 100, 10};
		
		for (int i=0; i<testFiles.length; i++) {
			List<Item> initialItems = readCSVFile(testFiles[i]);
			List<Item> selectedItems = new ArrayList<>();
			int capacity = capacities[i];
			long startTime = System.nanoTime();
			testAlgorithm(
				initialItems,
				capacity,
				RecursiveAlgorithm::solve
			);
			testAlgorithm(
				initialItems,
				capacity,
				BottomUp::solve
			);
			testAlgorithm(
				initialItems, 
				capacity, 
				MemoizedRecursive::solve
			);
			
		}
	}
	
	
	
	private static List<Item> createRandomItems(int numItems){
		List<Item> initialItems = new ArrayList<>();
		Random generator = new Random(System.nanoTime());
		for(int i = 0 ; i < numItems; i++){
			int value = generator.nextInt();
			int weight = generator.nextInt();
			String name = "(" + value + ", " + weight + ")";
			
			initialItems.add(new Item(name, weight, value));    
		}
		return initialItems;
	}
	
	// Hack to get around repeated logic because of static methods
	private interface Algorithm {
		int apply(List<Item> initial, List<Item> sol, int cap);
	}
	
	private static int testAlgorithm(List<Item> initialItems, int capacity, Algorithm algo){
			List<Item> selectedItems = new ArrayList<>();
			long startTime = System.nanoTime();
			
			int sol1 = algo.apply(initialItems, selectedItems, capacity);
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			System.out.println("\nTime taken: " + elapsedTime + " milliseconds\n");
			PrintKnapsack(selectedItems, sol1);
			return sol1;
	}

	
	private static void randomTestCases(int numCases){
		Random generator = new Random(System.nanoTime());
		for (int i=0; i < numCases; i++) {
			List<Item> initialItems = createRandomItems(generator.nextInt());
			int capacity = generator.nextInt();
			System.out.println("Test Case : " + i);
			System.out.println("Num Items : " + initialItems.size());
			PrintKnapsack(initialItems, capacity);

			
	        // Calculate and print the elapsed time
			// long elapsedTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

		}
		
	}
	
	private static void PrintKnapsack(List<Item> selectedItems, int maxValue) {
		if (!selectedItems.isEmpty()) {
			// Print the maximum value achievable using the knapsack algorithm.
			System.out.println("Maximum value: " + maxValue);
			System.out.println("Selected items:");
		} else {
			System.out.println("The capacity is less than the weight of any item");
		}
		// Print the details of the selected items.
		for (Item item : selectedItems) {
			System.out.println(item);
		}
	}

	private static List<Item> readCSVFile(String csvFile) {
		List<Item> initialItems = new ArrayList<>();
		try {
			File file = new File(csvFile);
			Scanner scanner = new Scanner(file);

			// Read the header to skip it
			if (scanner.hasNextLine()) {
				scanner.nextLine();
			}

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String parts[] = line.split(",");

				String name = parts[0];
				int weight = Integer.parseInt(parts[1].trim());
				int value = Integer.parseInt(parts[2].trim());

				initialItems.add(new Item(name, weight, value));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return initialItems;
	}

}
