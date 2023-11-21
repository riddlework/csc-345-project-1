import java.util.List;

public class BottomUp{
    public static int solve(List<Item> items, List<Item> sol, int capacity){
        BottomUp solver = new BottomUp(items, capacity);
        solver.fillTable(capacity, items.size());
        solver.table.solvedPath(items, sol);
        return solver.table.get(capacity, items.size() -1);
    }
    // A modified version of the other BottomUp approach
    // uses a single dimensional array. The challange with this is that it dosen't
    // tra
    public static  int solveSpaceOptimized(List<Item> items, int capacity){
        int[] solution = new int[capacity + 1];
        for(int i = 0; i <= capacity; i++){
            solution[i] = 0;
        }
        for(int i = 0; i < items.size(); i++){
            Item cur = items.get(i);
            for(int j = capacity; j >= cur.weight; j--)
                solution[j] = max(solution[j], cur.value + solution[j - cur.weight]);
        }
        return solution[capacity];
    }
    

    // TODO use an Item array
    int capacity = 0;
    List<Item> items;
    ComputationTable table;
    
    
    
    BottomUp(List<Item> items, int c){
        this.items = items;
        this.capacity = c;
        this.table = new ComputationTable(c, items.size(), 0);
    }
    

    // Defining a class for this to avoid dumb bugs mixing up the index
    
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }
    
    private void fillTable(int W, int n) {
        // Construct a table of values to 
        for(int idx = 0; idx < n; idx++){
            for(int w = 0; w <= W; w++){
                Item cur = items.get(idx);
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