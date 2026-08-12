package TestNGConscepts;

import org.testng.annotations.Test;

public class Demo2 extends Base{

	@Test(groups="Regression")
	public void test4() {
		System.out.println("Testing test4");
	}
	
}
