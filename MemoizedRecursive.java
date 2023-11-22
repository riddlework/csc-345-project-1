/* Recursive solution to Knapsack with memoization to avoid repeated work.*/
import java.util.List;
public class MemoizedRecursive  {
    public static int solve(List<Item> items, List<Item> solution,  int cap){
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
            table.set(remainingWeight, idx, 0);
            return 0;
        }
        // See if the value already exists in our table
        
        // If we do, just return the value (this is the memoization)
        if (table.isComputed(remainingWeight, idx - 1)){
            returnknapSack(remainingWeight, idx - 1);
        }
        // If not, we compute the solution to the current subproblem 
        // This is the same as the normal recursive solution
        Item cur = items.get(idx - 1);
        if (remainingWeight < cur.weight){
            int previous = table.get(remainingWeight, idx - 1);
            table.set(remainingWeight, idx, previous);
            return previous;
        }
        int without = knapSack(remainingWeight, idx - 1);
        int with = cur.value + knapSack(remainingWeight - cur.weight, idx - 1);
        int max = (without > with)? without : with;
        table.set(remainingWeight, idx, max);
        return max;
    }

}