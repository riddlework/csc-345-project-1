import java.util.ArrayList;
import java.util.List;

public class RecursiveAlgorithm {
    // A recursive function to solve the 0/1 knapsack problem.
    static int knapSack(int W, int wt[], int val[], int n, List<Item> selectedItems) {
        // Base case: if there are no items left or the knapsack's capacity is 0,
        // the value of the knapsack is 0.
        if (n == 0 || W == 0)
            return 0;

        // If the weight of the nth item is more than the knapsack's capacity (W),
        // then this item cannot be included, so we skip it and move to the next item.
        if (wt[n - 1] > W)
            return knapSack(W, wt, val, n - 1, selectedItems);

        // Try including the nth item in the knapsack and calculate its value.
        List<Item> withoutItem = new ArrayList<>();
        int withoutItemValue = knapSack(W, wt, val, n - 1, withoutItem);

        List<Item> withItem = new ArrayList<>();
        int withItemValue = val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1, withItem);

        // Compare the values of two options: with and without the nth item.
        if (withItemValue > withoutItemValue) {
            // If including the item leads to a higher value, update the selectedItems list.
            selectedItems.addAll(withItem);
            selectedItems.add(new Item(n, wt[n - 1], val[n - 1]));
        } else {
            // If excluding the item leads to a higher value, update the selectedItems list accordingly.
            selectedItems.addAll(withoutItem);
        }

        // Return the maximum value among the two options.
        return max(withItemValue, withoutItemValue);
    }

    public static void main(String args[]) {
        // Test data: values and weights of items, knapsack capacity, and the number of items.
        int value[] = new int[] {6060, 6060, 100, 120, 1000};
        int weight[] = new int[] {10 ,10, 30, 20, 21};
        int W = 60;
        int n = value.length;
        List<Item> selectedItems = new ArrayList<>();

        // Call the knapSack function to solve the problem and store the maximum value in 'maxValue'.
        int maxValue = knapSack(W, weight, value, n, selectedItems);

        // Print the maximum value achievable using the knapsack algorithm.
        System.out.println("Maximum value: " + maxValue);
        System.out.println("Selected items:");

        // Print the details of the selected items.
        for (Item item : selectedItems) {
            System.out.println("Item " + item.index + " (Weight: " + item.weight + ", Value: " + item.value + ")");
        }
    }
    
    // A utility function to find the maximum of two integers.
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    // A nested class to represent an item, with an index, weight, and value.
    static class Item {
        int index;
        int weight;
        int value;
        public Item(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }
    }
}
