// The Items class represents an item with weight, value, and an expression state in the genetic algorithm.
public class Items {
	// The weight of the item.
	public int weight;
	
	// The value of the item.
	public int value;
	
	// The name of the item.
	public String name;
	
	// The expression state of the item.
	private boolean expressed;
	
	// Default constructor for creating a random item with default values.
	public Items() {
		this.name = "Item";
		this.weight = 0;
		this.value = (int) (Math.random() * 15);
		this.expressed = false;
		
	}
	
	// Constructor for creating an item with specified weight, value, and name.
	public Items(int weight,int value, String name) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.expressed = false;
	}
	
	
	// Method to toggle the expression state of the item.
	public void changeState() {
		this.expressed = !this.expressed;
	}
	
	// Method to check if the item is expressed (used) in a solution.
	public boolean isExpressed()
	{
		return this.expressed;
	}
	
	// Override toString method to display the item as a string.
	public String toString() {
		//return "{Name: " + name + " (Weight: " + this.weight + ", Value: " 
				//+ this.value + ", Used: " + this.expressed + ")}";
		return "{Name: " + name + "(Used " + this.expressed +")}";
	}
}
