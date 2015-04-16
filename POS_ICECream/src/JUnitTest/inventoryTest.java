package JUnitTest;

import org.junit.Test;

import IceCream.decorator;
import IceCream.flavor;
import IceCream.inventory;
import junit.framework.TestCase;

public class inventoryTest extends TestCase {

	private inventory test;
	
	public void setUp(){
		 test = new inventory();
	}
	
	@Test
	public void test() {
		assertEquals(20.0,this.test.getFlavors()[0].getPrice());
		assertEquals("Chocolate",this.test.getFlavors()[0].getName());
		
		assertEquals(20.0,this.test.getFlavors()[1].getPrice());
		assertEquals("Vanilla",this.test.getFlavors()[1].getName());
		
		assertEquals(5.0,this.test.getDecorators()[0].getPrice());
		assertEquals("M&M",this.test.getDecorators()[0].getName());
		
		assertEquals(4.0,this.test.getDecorators()[1].getPrice());
		assertEquals("Strawberry",this.test.getDecorators()[1].getName());
		
		flavor testf = new flavor("test",10);
		int expected1 = this.test.getFlavors().length + 1;
		this.test.addFlavors(testf);
		assertEquals(expected1,this.test.getFlavors().length);
		
		decorator testd = new decorator("test",5);
		int expected2 = this.test.getDecorators().length + 1;
		this.test.addDecorators(testd);
		assertEquals(expected2,this.test.getDecorators().length);
	}

	protected void tearDown() throws Exception{
		super.tearDown();
	}
}
