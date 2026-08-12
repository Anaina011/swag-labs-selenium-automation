package TestNGConscepts;

import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo1 extends Base{

		
	@Test(description="Verify launching browser", priority=1, groups="Regression")
	public void launchGoogle() {
		System.out.println("Launching Google url");		
	}
	
	@Test(description="Verify math activity", priority=2, enabled=true) //enabled = false- the test case get excluded, because it is false.
	public void doMaths() {
		System.out.println(10+10);
		System.out.println(22 / 10);
		//System.out.println(22 / 0);
	}
	
	@Test(priority=3, dependsOnMethods="launchGoogle", groups= {"Regression", "Smoke"})
	public void testcase3() {
		System.out.println("close url");
	}
	
	@BeforeClass
	public void beforeclassActivities() {
		System.out.println("beforeClassActivity"); // limited to this class only. 
	}
	
	@AfterClass
	public void afterclassActivities() {
		System.out.println("afterclassActivities");
	}
}
