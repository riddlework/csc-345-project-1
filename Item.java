
public class Item {
	String name;
	int weight, value;

	public Item(String name, int weight, int value) {
		this.name = name;
		this.weight = weight;
		this.value = value;
	}

	public String getname() {
		return this.name;
	}

	public int getweight() {
		return this.weight;
	}

	public int getvalue() {
		return this.value;
	}

	public String toString() {
		return this.name + " (Weight: " + this.weight + ", Value: " + this.value + ")";
	}
}
