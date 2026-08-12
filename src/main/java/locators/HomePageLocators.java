package locators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageLocators {
	
	WebDriver driver;
	
	public HomePageLocators(WebDriver driver) {
		this.driver = driver;
	}
	
	By jacket=By.id("item_5_title_link");
	By invItemName=By.cssSelector("div[data-test='inventory-item-name']");
	By invPrice=By.className("inventory_details_price");
	By addToCartButton = By.id("add-to-cart");
	By removeButton = By.id("remove");
	By cartLink = By.className("shopping_cart_link");
	
	By header = By.className("title");
	By checkout = By.id("checkout");
	
	public List<String> selectAnItem() {
		driver.findElement(jacket).click();
		String itemName = driver.findElement(invItemName).getText().trim();
		String itemPrice = driver.findElement(invPrice).getText().trim();
		
		String addCartText = driver.findElement(addToCartButton).getText().trim();
		driver.findElement(addToCartButton).click();
		
		String removeText = driver.findElement(removeButton).getText().trim();
		
		driver.findElement(cartLink).click();
		
		List<String> itemInfoList = List.of(itemName, itemPrice, addCartText, removeText);
		return itemInfoList;
		
	}
	
	public String[] verifyAndClickCheckOutPage() {
		String cartHeadertext = driver.findElement(header).getText().trim();
		driver.findElement(checkout).click();
		String CheckoutHeadertext = driver.findElement(header).getText().trim();
		String[] headers = {cartHeadertext, CheckoutHeadertext};
		return headers;
		
	}
	
	
	
}
