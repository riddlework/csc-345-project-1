/* This class implements the bottom-up dynamic programming approach to solve 
the knapsack problem.
*/ 
import java.util.List;
public class BottomUp{
    public static int solve(List<Item> items, List<Item> sol, int capacity){
        BottomUp solver = new BottomUp(items, capacity);
        solver.fillTable(capacity, items.size());
        solver.table.solvedPath(items, sol);
        return solver.table.get(capacity, items.size());
    }
    
    int capacity = 0;
    List<Item> items;
    ComputationTable table;
    
    BottomUp(List<Item> items, int c){
        this.items = items;
        this.capacity = c;
        this.table = new ComputationTable(c, items.size(), 0);
    }
    
    // Utility method to return the maximum of two integers.
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    // This method fills the computation table with values to find the optimal solution.
    private void fillTable(int W, int n) {
        // Construct a table of values to 
        for(int idx = 1; idx <= n; idx++){
            for(int w = 1; w <= W; w++){
                Item cur = items.get(idx - 1);
                if(cur.weight > w){
                    table.set(w, idx, table.get(w, idx - 1));
                }
                else {
                    int exclude = table.get(w, idx - 1);
                    int include = table.get(w - cur.weight, idx - 1) + cur.value;
                    table.set(w, idx, max(include, exclude));
                }
            }
        }
    }
    
}