package utility;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name = "InvalidCredentials_Dataset")
	public Object[][] createData1() {
	 return new Object[][] {
	   { "standard_user", "jgjgsvxsjh" },
	   { "kjhdjsdb", "secret_sauce"},
	   { "kjhdjsdb", "jhjgdjgdjh"},
	 };
	}
}
