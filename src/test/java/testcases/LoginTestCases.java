package testcases;

import org.jspecify.annotations.Nullable;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import global.Browser;
import locators.LoginPageLocators;

public class LoginTestCases extends Browser{

	@Test(description = "Verify user can successfully login to Sauce Demo application by valid credentials", groups ="Sprint 1")
	public void verifySuccessfulLogin() {
		ExtentTest test = reports.createTest("verifySuccessfullLogin");
		try {			
		driver.get(prop.getProperty("url"));
		//Validate Title, expected title: Swag Labs
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle.trim(), "Swag Labs");
		test.pass("Title is validated", getScreenshot());
		
		LoginPageLocators loginPageLocators = new LoginPageLocators(driver);
		boolean[] elementsVisibilityActual = loginPageLocators.verifyLoginPageElements();
		boolean[] elementsVisibilityExpected = {true, true, true, true};
		Assert.assertEquals(elementsVisibilityActual, elementsVisibilityExpected);
		test.pass("Login page elements are  validated", getScreenshot());
		
		loginPageLocators.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(actualUrl, expectedUrl);
		test.pass("Login is successfully completed", getScreenshot());
		}
		catch(Throwable e) {
			test.fail("Test Failed" + e.getMessage(), getScreenshot());
		}
	}

	@Test(description="Verify invalid credentials", 
			dataProvider="InvalidCredentials_Dataset", 
			dataProviderClass = utility.TestData.class)
	public void verifyInvalidCredentialFunctionality(String usernameFromDataPr, String passwordFromDataPr) {
		ExtentTest test = reports.createTest("Verify invalid credentials");
		try {	
		driver.get(prop.getProperty("url"));
		LoginPageLocators loginPageLocators = new LoginPageLocators(driver);
		loginPageLocators.enterCredentials(usernameFromDataPr, passwordFromDataPr);
		test.info("Username: " + usernameFromDataPr);
		test.info("Password: " + passwordFromDataPr);
		String actErrorMsg = loginPageLocators.getErrorMessageText();
		String expErrorMsg = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(actErrorMsg, expErrorMsg);
		test.pass("Error message validated: " + actErrorMsg);
		}
		catch(Throwable e) {
			test.fail("Test Failed" + e.getMessage());
		}
	}
	/*	
	@Test(description="Verify invalid credentials")
	public void verifyInvalidCredentialFunctionality2() {
		driver.get(prop.getProperty("url"));
		LoginPageLocators loginPageLocators = new LoginPageLocators(driver);
		loginPageLocators.enterCredentials("hgdyuyh", prop.getProperty("username"));
		String actErrorMsg = loginPageLocators.getErrorMessageText();
		String expErrorMsg = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(actErrorMsg, expErrorMsg);
	}
	
	@Test(description="Verify invalid credentials")
	public void verifyInvalidCredentialFunctionality3() {
		driver.get(prop.getProperty("url"));
		LoginPageLocators loginPageLocators = new LoginPageLocators(driver);
		loginPageLocators.enterCredentials("hgdyuyh", "hgsygfh");
		String actErrorMsg = loginPageLocators.getErrorMessageText();
		String expErrorMsg = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(actErrorMsg, expErrorMsg);
	}
*/	
}
