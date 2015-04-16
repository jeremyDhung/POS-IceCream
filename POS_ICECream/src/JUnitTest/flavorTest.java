package JUnitTest;

import org.junit.Test;

import IceCream.flavor;
import junit.framework.TestCase;

public class flavorTest extends TestCase {
    
	private flavor test;
	
	public void setUp(){
		 test = new flavor("test",10);
	}
	
	@Test
	public void test() {
		
		double expected1 = 10.0;
		assertEquals(expected1,this.test.getPrice());
		
		String expected2 = "test";
		assertEquals(expected2,this.test.getName());
		
		this.test.setPrice(11.1);
		expected1 = 11.1;
		assertEquals(expected1,this.test.getPrice());
		
		this.test.setName("modified");
		expected2 = "modified";
		assertEquals(expected2,this.test.getName());
		
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
	}

}
