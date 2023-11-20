import java.util.ArrayList;
import java.util.List;

public class TestKnapsack extends RecursiveAlgorithm{
    public static void main(String[] args){
        int value[] = new int[] {50, 300, 100, 120, 20};
        int weight[] = new int[] {10, 10, 30, 25, 21};
        int W = 60;
        int n = value.length;
        List<Item> selectedItems = new ArrayList<>();

        // Call the knapSack function to solve the problem and store the maximum value in 'maxValue'.
        // int maxValue = RecursiveAlgorithm.knapSack(W, weight, value, n, selectedItems);
        
        
        // // Print the maximum value achievable using the knapsack algorithm.
        // System.out.println("Maximum value: " + maxValue);
        // System.out.println("Selected items:");

        // // Print the details of the selected items.
        // for (Item item : selectedItems) {
        //     System.out.println("Item " + item.index + " (Weight: " + item.weight + ", Value: " + item.value + ")");
        // }
        testMemoizedRecursive(value, weight, W);
    }
    
    public static int testMemoizedRecursive(int value[], int weight[], int W){
        System.out.println(MemoizedRecursive.solve(value, weight, W));
        
        System.out.println(BottomUp.solve(value, weight, W));
        System.out.println(BottomUp.solveSpaceOptimized(value, weight, W));
        return 0;
    }


}