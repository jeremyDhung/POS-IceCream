package IceCream;
/*
 * This class is used to organize the decorators
 */
public class decorator {
	private String name;
	private double price;
     
    public decorator(String name, double price){
    	this.name = name;
    	this.price = price;
    }
     
     public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
