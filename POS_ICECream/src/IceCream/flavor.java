package IceCream;

/*
 * This class is used to organize the flavors
 */
public class flavor {
    private String name;
    private double price;
     
     public flavor(String name, double price){
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
