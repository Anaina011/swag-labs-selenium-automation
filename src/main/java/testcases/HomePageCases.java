package testcases;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import global.Browser;
import locators.CheckoutLocators;
import locators.HomePageLocators;
import locators.LoginPageLocators;
import utility.ExcelUtility;

public class HomePageCases extends Browser {

	@Test(description = "Verify E2E placement of an order")
	public void placeAnOrder() {
		driver.get(prop.getProperty("url"));
		LoginPageLocators loginPageLocators = new LoginPageLocators(driver);
		loginPageLocators.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));

		HomePageLocators homePageLocators = new HomePageLocators(driver);
		List<String> actProductInfoList = homePageLocators.selectAnItem();
		List<String> expList = List.of("Sauce Labs Fleece Jacket", "$49.99", "Add to cart", "Remove");
		Assert.assertEquals(actProductInfoList, expList);

		String[] actHeaders = homePageLocators.verifyAndClickCheckOutPage();
		Assert.assertEquals(actHeaders, new String[] { "Your Cart", "Checkout: Your Information" });

		CheckoutLocators checkoutLocators = new CheckoutLocators(driver);
		Map<String, String> excelMap = ExcelUtility.getUserAddressFromExcelSheet("User Address1");
		checkoutLocators.enterUserInfo(excelMap.get("First Name"), excelMap.get("Last Name"),
				excelMap.get("Postal code"));

	}
}
