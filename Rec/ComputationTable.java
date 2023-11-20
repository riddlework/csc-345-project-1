import java.util.HashSet;
// Helper class for DP solutions
// The DP uses a table of values to record all of the solutions
public class ComputationTable{
    int[][] storedComputation;
    int defaultValue;
    int capacity;
    int items;
    ComputationTable(int cap, int numItems, int val){
        defaultValue = val;
        capacity = cap;
        items = numItems;
        System.out.println(items);
        storedComputation = new int[capacity + 1][items + 1];
        for(int i = 0; i <= capacity; i++){
            for (int j = 0; j <= items; j++){
                // Assigning the output as -1 since 0 is a valid output
                storedComputation[i][j] = defaultValue;
            }
        }
    }
    // Defining this as
    public int get(int weight, int item){
        if (weight < 0 || item < 0){
            return defaultValue;
        }
        return storedComputation[weight][item];
    }
    public void set(int weight, int item, int value){
        if (weight < 0 || item < 0){
            return;
        }
        storedComputation[weight][item] = value;
    }
    
    public boolean isComputed(int weight, int item){
        return storedComputation[weight][item] != -1;
    }
    
    public String toString(){
        String repr = "";
        for(int i = 0; i < storedComputation.length; i++){
            for(int j = 0; j < storedComputation[i].length; j++){
                repr += storedComputation[i][j] + ", ";
            }
            repr += "\n";
        }
        return repr;
    }
    
    public String solvedPath(int[] values, int[] weights){
        HashSet<Integer> path = new HashSet();
        constructSolution(items - 1, capacity, path, values, weights);
        return path.toString();
    }
    // Construct the solt
    private void constructSolution(int idx, int weight, HashSet<Integer> path, int[] values, int[] weights){
        //if m[i, j] > m[i-1, j] then:
        if (idx  == 0){
            if(weight > weights[0])
                path.add(0);
            return;
        }
        if (get(weight, idx) > get(weight, idx-1)){
            constructSolution(idx - 1, weight - weights[idx], path, values, weights);
            path.add(idx);
        }
        else {
            constructSolution(idx - 1, weight, path, values, weights);
        }
        
    }
    
}