package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageLocators {
	WebDriver driver;
	
	public LoginPageLocators(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id="user-name")    //Page Factory -  stores locator and webelement of it
	WebElement usernameElement; 
	
	By userName=By.id("user-name"); // just find the web element
	By password=By.name("password");
	By loginButton=By.id("login-button");
	By errorMsg =By.cssSelector("h3[data-test=\"error\"]");

	public boolean[] verifyLoginPageElements() {
		boolean b1 = driver.findElement(userName).isDisplayed();
		boolean b2 = driver.findElement(password).isDisplayed();
		boolean b3 = driver.findElement(loginButton).isDisplayed();
		boolean b4 = driver.findElement(loginButton).isEnabled();
		
		boolean[] elementsVisibility = {b1, b2, b3, b4};
		return elementsVisibility;
	}
	
	public void enterCredentials(String usernameCred, String passwordCred) {
		driver.findElement(userName).sendKeys(usernameCred);
		driver.findElement(password).sendKeys(passwordCred);
		driver.findElement(loginButton).click();
	}
	
	public String getErrorMessageText() {
		return driver.findElement(errorMsg).getText().trim();
		
	}
	
}
