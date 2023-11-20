/* Recursive solution to Knapsack with memoization to avoid repeated work.


*/


public class MemoizedRecursive  {
    public static int solve(int values[], int[] weights, int cap){
        var solver = new MemoizedRecursive(cap, weights, values);
        int solution = solver.knapSack(cap, values.length);
        //System.out.println(solver.table.solvedPath(weights, values));
        System.out.println(solver.table.toString());
        System.out.println(solution);
        return solution;
    }
    ComputationTable table;
    int capacity;
    int[] weights;
    int[] vals;
    
    MemoizedRecursive(int W, int[] wt, int[] vs){
        capacity = W;
        weights = wt;
        vals = vs;
        table = new ComputationTable(capacity, vals.length, -1);
    }
    
    private int knapSack(int W, int idx) {
        if (W <= 0 || idx <= 0){
            return 0;
        }
        int existingSol = table.get(W, idx);
        if (existingSol != -1){
            return existingSol;
        }
        if (W < weights[idx - 1]){
            int previous = knapSack(W, idx - 1);
            table.set(W, idx, previous);
            return previous;
        }
        int without = knapSack(W, idx - 1);
        int with = vals[idx - 1] + knapSack(W - weights[idx - 1], idx - 1);
        int max = (without > with)? without : with;
        table.set(W, idx, max);
        return max;
    }

}