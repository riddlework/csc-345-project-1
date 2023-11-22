
/**
 * Knapsack.java
 * 
 * Description: This program implements various algorithms to solve the 0/1 Knapsack problem,
 * where the goal is to determine the most valuable combination of items to include in a knapsack
 * without exceeding its weight capacity. The code includes four different algorithms: Recursive,
 * Bottom-up Dynamic Programming, Memoized recursive and Genetic.
 * 
 * The program reads datasets from CSV files, performs tests with predefined and random datasets,
 * and measures the execution time of each algorithm. The results, including the selected items
 * and the maximum value, are printed to the console.
 *
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

//Main class for the Knapsack problem
public class Knapsack {

	// Main method
	public static void main(String args[]) {
		
		deleteResultsFile();
		
		// Test cases with different datasets and capacities
		String[] testFiles = { "Game-Data.csv", "Stock-Data.csv", "Courses-Data.csv" };
		int[] capacities = { 20, 100, 10 };

		// Loop through test cases
		for (int i = 0; i < testFiles.length; i++) {
			// Read items from CSV file
			List<Item> initialItems = readCSVFile(testFiles[i]);
			// Set the knapsack capacity for the current test case
			int capacity = capacities[i];
			// Test different algorithms on the current dataset
			testAlgorithm(initialItems, capacity, RecursiveAlgorithm::solve, "Recursive Algorithm"
			// some fun reading
			// https://stackoverflow.com/questions/20001427/double-colon-operator-in-java-8
			);
			testAlgorithm(initialItems, capacity, BottomUp::solve, "Bottom Up");
			testAlgorithm(initialItems, capacity, MemoizedRecursive::solve, "Memoized Recursive");
		}
		// Generate and test random test cases
		randomTestCases(5);
	}

	// Hack to get around repeated logic because of static methods
	// Interface to represent an algorithm
	private interface Algorithm {
		int apply(List<Item> initial, List<Item> sol, int cap);

	}

	// Method to test a specific algorithm on a dataset
	private static int testAlgorithm(List<Item> initialItems, int capacity, Algorithm algo, String algoName) {
		List<Item> selectedItems = new ArrayList<>();
		long startTime = System.nanoTime();
		int sol1 = algo.apply(initialItems, selectedItems, capacity);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		
		// Print the selected items and the maximum value
		PrintKnapsack(selectedItems, sol1);
		System.out.println("\nTime taken: " + elapsedTime + " nanoseconds\n");
		writeResultsToCSV(algoName, elapsedTime, initialItems.size());
		
		return sol1;
	}

	// Method to generate and test random test cases
	private static void randomTestCases(int numCases) {
		System.out.println("Random Test Cases");
		System.out.println("----------------------");
		Random generator = new Random(0);
		for (int i = 0; i < numCases; i++) {
			// Create random items and capacity for the test case
			List<Item> initialItems = createRandomItems(generator.nextInt(100));
			// Getting a random value and adding the 1st items weight to make sure it's a
			// valid capacity
			int capacity = generator.nextInt(100) + initialItems.get(0).getweight();
			System.out.println("Test Case : " + i);
			System.out.println("Num Items : " + initialItems.size());
			// PrintKnapsack(initialItems, capacity);
			// Test different algorithms on the random test case
			testAlgorithm(initialItems, capacity, RecursiveAlgorithm::solve, "Recursive Algorithm");
			testAlgorithm(initialItems, capacity, BottomUp::solve, "Bottom Up");
			testAlgorithm(initialItems, capacity, MemoizedRecursive::solve, "Memoized Recursive");
		}
	}

	// Method to create a list of random items
	private static List<Item> createRandomItems(int numItems) {
		List<Item> initialItems = new ArrayList<>();
		Random generator = new Random(0);
		for (int i = 0; i < numItems; i++) {
			int value = generator.nextInt(100);
			int weight = generator.nextInt(100);
			String name = "(" + weight + ", " + value + ")";

			initialItems.add(new Item(name, weight, value));
		}
		return initialItems;
	}

	
	
	// Print the details of the selected items and the maximum value
	private static void PrintKnapsack(List<Item> selectedItems, int maxValue) {
		if (!selectedItems.isEmpty()) {
			// Print the maximum value achievable using the knapsack algorithm.
			System.out.println("Maximum value: " + maxValue);
			System.out.println("Selected items:");
		} else {
			System.out.println("The capacity is less than the weight of any item.");
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
	
	
	private static void writeResultsToCSV(String algorithmName, long elapsedTime, int numItems) {
	    String csvFileName = "results.csv";

	    try {
	        File file = new File(csvFileName);

	        // Check if the file exists; if not, write the header
	        if (!file.exists() || file.length() == 0) {
	            try (FileWriter writer = new FileWriter(csvFileName, true)) {
	                writer.append("Algorithm,TimeElapsed,NumItems\n");
	            }
	        }

	        // Write algorithm name, time elapsed, and number of items to the CSV file
	        try (FileWriter writer = new FileWriter(csvFileName, true)) {
	            writer.append(algorithmName).append(",").append(Long.toString(elapsedTime)).append(",").append(Integer.toString(numItems)).append("\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void deleteResultsFile() {
        File file = new File("results.csv");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Existing results file deleted.");
            } else {
                System.out.println("Unable to delete the existing results file.");
            }
        }
    }
}