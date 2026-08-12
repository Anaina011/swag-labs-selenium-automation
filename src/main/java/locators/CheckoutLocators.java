package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutLocators {
	WebDriver driver;

	public CheckoutLocators(WebDriver driver) {
		this.driver = driver;
	}

	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By postalCode = By.id("postal-code");
	By continueButton = By.id("continue");

	public void enterUserInfo(String fname, String lname, String code) {
		driver.findElement(firstName).sendKeys(fname);
		driver.findElement(lastName).sendKeys(lname);
		driver.findElement(postalCode).sendKeys(code);

		driver.findElement(continueButton).click();
		
	}

}
