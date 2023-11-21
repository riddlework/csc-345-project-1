
public class Item {
	// Instance variables to store item details
	String name;
	int weight, value;

	// Constructor to initialize an Item with a name, weight, and value
	public Item(String name, int weight, int value) {
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	// Getter method to retrieve the name of the item
	public String getname() {
		return this.name;
	}

	// Getter method to retrieve the weight of the item
	public int getweight() {
		return this.weight;
	}

	// Getter method to retrieve the value of the item
	public int getvalue() {
		return this.value;
	}

	// Override the toString method to provide a string representation of the item
	public String toString() {
		// Return a formatted string containing the name, weight, and value of the item
		return this.name + " (Weight: " + this.weight + ", Value: " + this.value + ")";
	}

}
