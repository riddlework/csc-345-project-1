import java.util.ArrayList;
import java.util.List;

public class RecursiveAlgorithm {
	// Main function to solve the 0/1 knapsack problem
	public static int solve(List<Item> initialItems, List<Item> selectedItems, int capacity) {
		int n = initialItems.size();
		// Get the maximum value using the knapsack algorithm
		int maxValue = knapSack(capacity, n, initialItems, selectedItems);
		// Print the selected items and the maximum value
		return maxValue;
	}

	// A recursive function to solve the 0/1 knapsack problem.
	static int knapSack(int capacity, int n, List<Item> initialItems, List<Item> selectedItems) {
		// Base case: if there are no items left or the knapsack's capacity is 0,
		// the value of the knapsack is 0.
		if (n == 0 || capacity == 0)
			return 0;

		// If the weight of the nth item is more than the knapsack's capacity (W),
		// then this item cannot be included, so we skip it and move to the next item.
		if (initialItems.get(n - 1).getweight() > capacity)
			return knapSack(capacity, n - 1, initialItems, selectedItems);

		// Try including the nth item in the knapsack and calculate its value.
		List<Item> withoutItem = new ArrayList<>();
		int withoutItemValue = knapSack(capacity, n - 1, initialItems, withoutItem);

		List<Item> withItem = new ArrayList<>();
		int withItemValue = initialItems.get(n - 1).getvalue()
				+ knapSack(capacity - initialItems.get(n - 1).getweight(), n - 1, initialItems, withItem);

		// Compare the values of two options: with and without the nth item.
		if (withItemValue > withoutItemValue) {
			// If including the item leads to a higher value, update the selectedItems list.
			selectedItems.addAll(withItem);
			selectedItems.add(initialItems.get(n - 1));
		} else {
			// If excluding the item leads to a higher value, update the selectedItems list
			// accordingly.
			selectedItems.addAll(withoutItem);
		}

		// Return the maximum value among the two options.
		return max(withItemValue, withoutItemValue);
	}

	// A utility function to find the maximum of two integers.
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}
}
