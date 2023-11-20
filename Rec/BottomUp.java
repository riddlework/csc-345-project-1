

public class BottomUp {
    public static int solve(int value[], int weight[], int W){
        BottomUp solver = new BottomUp(weight, value, W);
        solver.fillTable(W, value.length);
        System.out.println(solver.table.solvedPath(value, weight));
        return solver.table.get(W, value.length -1);
    }
    // A modified version of the other BottomUp approach
    // uses a single dimensional array.
    public static  int solveSpaceOptimized(int values[], int[] weights, int capacity){
        int[] solution = new int[capacity + 1];
        for(int i = 0; i < weights.length; i++){
            solution[i] = 0;
        }
        for(int i = 0; i < weights.length; i++){
            for(int j = capacity; j >= weights[i]; j--)
                solution[j] = max(solution[j], values[i] + solution[j - weights[i]]);
        }
        return solution[capacity];
    }
    

    // TODO use an Item array
    int capacity = 0;
    int[] weights;
    int[] values;
    ComputationTable table;
    
    
    
    BottomUp(int[] w, int[] v, int c){
        this.weights = w;
        this.values = v;
        this.capacity = c;
        this.table = new ComputationTable(c, values.length, 0);
    }
    

    // Defining a class for this to avoid dumb bugs mixing up the index
    
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    private void fillTable(int W, int n) {
        // Construct a table of values to 
        for(int idx = 0; idx < n; idx++){
            for(int w = 0; w <= W; w++){
                if(weights[idx] > w){
                    table.set(w, idx, table.get(w, idx - 1));
                }
                else {
                    int exclude = table.get(w, idx - 1);
                    int include = table.get(w - weights[idx], idx - 1) + values[idx];
                    table.set(w, idx, max(include, exclude));
                }
            }
        }
    }
    
}