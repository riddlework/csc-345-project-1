import java.util.HashSet;
import java.util.List;
// Helper class for dynamic programming solutions
// The Knapsack dynamic programming solutions use a table of values to record all of the solutions
public class ComputationTable {
    int[][] storedComputation;
    int defaultValue;
    int capacity;
    int items;
    
    ComputationTable(int cap, int numItems, int val){
        defaultValue = val;
        capacity = cap;
        items = numItems;
        storedComputation = new int[capacity + 1][items + 1];
        for(int i = 0; i <= capacity; i++){
            for (int j = 0; j <= items; j++){
                // Assigning the output as -1 since 0 is a valid output
                storedComputation[i][j] = defaultValue;
            }
        }
    }
    
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
        return storedComputation[weight][item] != defaultValue;
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
    
    public void solvedPath(List<Item> items, List<Item> solution){
        HashSet<Item> path = new HashSet();
        
        int weight = capacity;
        for(int i = this.items; i > 0; i--){
            if(get(weight, i) > get(weight, i - 1)){
                Item cur = items.get(i - 1);
                solution.add(cur);
                weight -= cur.getweight();
            }
        }
        if(weight > items.get(0).getweight()){
            solution.add(items.get(0));
        }
    }
    
}