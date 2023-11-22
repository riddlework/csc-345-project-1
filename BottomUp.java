import java.util.List;

public class BottomUp{
    // static solve method that is an interface with the test code
    public static int solve(List<Item> items, List<Item> sol, int capacity){
        BottomUp solver = new BottomUp(items, capacity);
        // Filling the table creates 
        solver.fillTable(capacity, items.size());
        // Fill in the path
        solver.table.solvedPath(items, sol);
        // Get the last item to represent the heighest value
        return solver.table.get(capacity, items.size());
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