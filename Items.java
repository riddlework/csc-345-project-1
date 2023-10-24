/*
 * This class represents the Items that will make up the population.
 */
public class Items {
	public int weight;
	public int value;
	public String name;
	private boolean expressed;
	
	
	public Items() {
		this.name = "Item";
		this.weight = 0;
		this.value = (int) (Math.random() * 15);
		this.expressed = false;
		
	}
	
	public Items(int weight,int value, String name) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.expressed = false;
	}
	
	public void changeState() {
		this.expressed = !this.expressed;
	}
	
	public boolean isExpressed()
	{
		return this.expressed;
	}
	public String toString() {
		//return "{Name: " + name + " (Weight: " + this.weight + ", Value: " 
				//+ this.value + ", Used: " + this.expressed + ")}";
		return "{Name: " + name + "(Used " + this.expressed +")}";
	}
}
