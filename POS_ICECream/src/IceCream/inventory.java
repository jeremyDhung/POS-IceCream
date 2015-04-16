package IceCream;
/*
 * This class is used to organize the inventory of components 
 * including flavors and decorators
 */
public class inventory {
	private flavor []flavors;
	private decorator []decorators;
	
	/*
	 * As this is not connected to database, 
	 * and there is default flavors and decorators in inventory
	 * So, I organize the constructor like this
	 */
	public inventory(){
		  flavors = new flavor[2];
		  flavor f1 = new flavor("Chocolate",20.0);
		  flavor f2 = new flavor("Vanilla",20.0);
          this.flavors[0] = f1;
          this.flavors[1] = f2;
          
          decorators = new decorator[2];
          decorator d1 = new decorator("M&M",5.0);
          decorator d2 = new decorator("Strawberry",4.0);
          this.decorators[0] = d1;
          this.decorators[1] = d2;
	}
	
	private void setFlavors(flavor[] flavors) {
		this.flavors = flavors;
	}

	private void setDecorators(decorator[] decorators) {
		this.decorators = decorators;
	}
	
	public flavor[] getFlavors() {
		return flavors;
	}
	/*
	 * add new flavors to inventory
	 */
	public void addFlavors(flavor flavor) {
	   int length = this.flavors.length;
	   flavor []temp = new flavor[length+1];
	   for(int i = 0; i<length;i++){
		   temp[i] = this.flavors[i];
	   }
	   temp[length] = flavor;
	   this.setFlavors(temp);
	}
	
	public decorator[] getDecorators() {
		return decorators;
	}
	
	public void addDecorators(decorator decorator) {
          int length = this.decorators.length;
          decorator []temp = new decorator[length+1];
          for(int i =0;i<length;i++){
        	  temp[i] = this.decorators[i];
          }
          temp[length] = decorator;
          this.setDecorators(temp);
	}
}
