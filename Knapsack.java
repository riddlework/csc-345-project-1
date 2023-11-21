import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Knapsack {
	public static void main(String args[]) {
		
		String[] testFiles = {"Game-Data.csv", "Stock-Data.csv", "Courses-Data.csv"};
		int[] capacities = {20, 100, 10};
		
		for (int i=0; i<testFiles.length; i++) {
		List<Item> initialItems = readCSVFile(testFiles[i]);
		List<Item> selectedItems = new ArrayList<>();
		int capacity = capacities[i];
		long startTime = System.nanoTime();
		
		RecursiveAlgorithm.solve(initialItems, selectedItems, capacity);
		
		long endTime = System.nanoTime();

        // Calculate and print the elapsed time
        long elapsedTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("\nTime taken: " + elapsedTime + " milliseconds\n");
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
