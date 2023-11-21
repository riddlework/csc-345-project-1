/* Recursive solution to Knapsack with memoization to avoid repeated work.


*/
import java.util.List;
public class MemoizedRecursive  {
    public static int solve(List<Item> items, int cap, List<Item> solution){
        var solver = new MemoizedRecursive(cap, items);
        int sol = solver.knapSack(cap, items.size());
        //System.out.println(solver.table.solvedPath(weights, values));
        solver.table.solvedPath(items, solution);
        return sol;
    }
    ComputationTable table;
    int capacity;
    List<Item> items;
    
    MemoizedRecursive(int W, List<Item> items){
        capacity = W;
        this.items = items;
        // Rather than use a hashtable, we memoize our solutions with a 2d array
        table = new ComputationTable(capacity, items.size(), -1);
    }
    
    private int knapSack(int remainingWeight, int idx) {
        // Base Case
        if (remainingWeight <= 0 || idx <= 0){
            return 0;
        }
        // See if the value already exists in our table
        int existingSol = table.get(remainingWeight, idx);
        // If we do, just return the value (this is the memoization)
        if (existingSol != -1){
            return existingSol;
        }
        // If not, we compute the solution to the current subproblem 
        // This is the same as the normal recursive solution
        Item cur = items.get(idx - 1);
        if (W < cur.weight){
            int previous = knapSack(remainingWeight, idx - 1);
            table.set(remainingWeight, idx, previous);
            return previous;
        }
        int without = knapSack(remainingWeight, idx - 1);
        int with = cur.value + knapSack(remainingWeight - cur.weight, idx - 1);
        int max = (without > with)? without : with;
        table.set(W, idx, max);
        return max;
    }

}