package JUnitTest;

import org.junit.Test;

import IceCream.decorator;
import junit.framework.TestCase;

public class decoratorTest extends TestCase {

	private decorator test;
	
	public void setUp(){
		 test = new decorator("test",5);
	}
	
	@Test
	public void test() {
		double expected1 = 5.0;
		assertEquals(expected1,this.test.getPrice());
		
		String expected2 = "test";
		assertEquals(expected2,this.test.getName());
		
		this.test.setPrice(6.1);
		expected1 = 6.1;
		assertEquals(expected1,this.test.getPrice());
		
		this.test.setName("modified");
		expected2 = "modified";
		assertEquals(expected2,this.test.getName());
	}

	protected void tearDown() throws Exception{
		super.tearDown();
	}
}
