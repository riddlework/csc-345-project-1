import java.util.ArrayList;
import java.util.List;

public class RecursiveAlgorithm {
	
	public static void solve(List<Item> initialItems, int capacity) {
		int n = initialItems.size();
		List<Item> selectedItems = new ArrayList<>();
		int maxValue = knapSack(capacity, n, initialItems, selectedItems);
		PrintKnapsack(selectedItems, maxValue);
	}
	
	
    // A recursive function to solve the 0/1 knapsack problem.
    static int knapSack(int capacity, int n, List<Item> initialItems, List<Item> selectedItems) {
        // Base case: if there are no items left or the knapsack's capacity is 0,
        // the value of the knapsack is 0.
        if (n == 0 || capacity == 0)
            return 0;

        // If the weight of the nth item is more than the knapsack's capacity (W),
        // then this item cannot be included, so we skip it and move to the next item.
        if (initialItems.get(n-1).getweight() > capacity)
            return knapSack(capacity, n - 1, initialItems, selectedItems);

        // Try including the nth item in the knapsack and calculate its value.
        List<Item> withoutItem = new ArrayList<>();
        int withoutItemValue = knapSack(capacity, n - 1, initialItems, withoutItem);

        List<Item> withItem = new ArrayList<>();
        int withItemValue = initialItems.get(n-1).getvalue() + knapSack(capacity - initialItems.get(n-1).getweight(), n - 1, initialItems, withItem);

        // Compare the values of two options: with and without the nth item.
        if (withItemValue > withoutItemValue) {
            // If including the item leads to a higher value, update the selectedItems list.
            selectedItems.addAll(withItem);
            selectedItems.add(initialItems.get(n-1));
        } else {
            // If excluding the item leads to a higher value, update the selectedItems list accordingly.
            selectedItems.addAll(withoutItem);
        }

        // Return the maximum value among the two options.
        return max(withItemValue, withoutItemValue);
    }

    public static void main(String args[]) {
        // Test data: values and weights of items, knapsack capacity, and the number of items.
    	String names[] = new String[] {"Item1", "Item2", "Item3", "Item4", "Item5"};
    	int weight[] = new int[] {10 ,5, 30, 20, 21};
        int value[] = new int[] {6060, 60600, 100, 120, 10};
        
        int capacity = 5;
        int n = value.length;
        List<Item> initialItems = new ArrayList<>();
        for(int i = 0; i<n;i++) {
        	initialItems.add(new Item(names[i], weight[i], value[i]));
        }
        List<Item> selectedItems = new ArrayList<>();

        // Call the knapSack function to solve the problem and store the maximum value in 'maxValue'.
        int maxValue = knapSack(capacity, n, initialItems, selectedItems);
        
        if(maxValue>0) {
        // Print the maximum value achievable using the knapsack algorithm.
        System.out.println("Maximum value: " + maxValue);
        System.out.println("Selected items:");
        }
        else {
        	System.out.println("The capacity is less than the weight of any item");
        }
        // Print the details of the selected items.
        for (Item item : selectedItems) {
            System.out.println(item);
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
    
    
    // A utility function to find the maximum of two integers.
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    }
