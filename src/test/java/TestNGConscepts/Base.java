package TestNGConscepts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Base {


	@BeforeMethod
	public void launchBrowser() {
		System.out.println("Launching the browser");
	}
	
	@AfterMethod
	public void closeDriver() {
		System.out.println("close the browser");
	}
	
	@BeforeTest
	public void beforeTestActivity() {
		System.out.println("beforeTestActivity");
	}
	
	@AfterTest
	public void afterTestActivity() {
		System.out.println("afterTestActivity");
	}
	
	@BeforeSuite
	public void beforesuitectivity() {
		System.out.println("beforesuitectivity");
	}
	
	@AfterSuite
	public void aftersuitectivity() {
		System.out.println("aftersuitectivity");
	}
}
