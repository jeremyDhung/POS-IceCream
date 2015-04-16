package JUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ decoratorTest.class, flavorTest.class, inventoryTest.class })
public class AllTests {

}
