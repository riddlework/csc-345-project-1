import java.util.ArrayList;
import java.util.List;

public class RecursiveAlgorithm {
	
    static int knapSack(int W, int wt[], int val[], int n, List<Item> selectedItems) {
        if (n == 0 || W == 0)
            return 0;

        if (wt[n - 1] > W)
            return knapSack(W, wt, val, n - 1, selectedItems);

        List<Item> withoutItem = new ArrayList<>();
        int withoutItemValue = knapSack(W, wt, val, n - 1, withoutItem);

        List<Item> withItem = new ArrayList<>();
        int withItemValue = val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1, withItem);

        if (withItemValue > withoutItemValue) {
            selectedItems.addAll(withItem);
            selectedItems.add(new Item(n, wt[n - 1], val[n - 1]));
        } else {
            selectedItems.addAll(withoutItem);
        }

        return max(withItemValue, withoutItemValue);
    }

    public static void main(String args[]) {
        int value[] = new int[] {6060, 6060, 100, 120, 1000};
        int weight[] = new int[] {10 ,10, 30, 20, 21};
        int W = 60;
        int n = value.length;
        List<Item> selectedItems = new ArrayList<>();

        System.out.println("Maximum value: " + knapSack(W, weight, value, n, selectedItems));
        System.out.println("Selected items:");

        for (Item item : selectedItems) {
            System.out.println("Item " + item.index + " (Weight: " + item.weight + ", Value: " + item.value + ")");
        }
    }
    
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    
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
