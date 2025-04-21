package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	public WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
	}
	By checkoutPageProductname=By.cssSelector("p.product-name");
	By checkoutPageQuantity=By.cssSelector("p.quantity");
	By applyButton=By.cssSelector(".promoBtn");
	By placeOrderButton=By.xpath("//button[text()='Place Order']");
	
	public boolean verifyApplybutton() {
		return driver.findElement(applyButton).isDisplayed();
	}

	public boolean verifyPlaceOrderbutton() {
		return driver.findElement(placeOrderButton).isDisplayed();
	}
	public boolean verifyCheckoutPageOrderDetails(String shortname, String quantity) {
		System.out.println(driver.findElement(checkoutPageProductname).getText().split("-")[0].trim());
		System.out.println(driver.findElement(checkoutPageQuantity).getText());
		if(driver.findElement(checkoutPageProductname).getText().split("-")[0].trim().contains(shortname)&&
		driver.findElement(checkoutPageQuantity).getText().contains(quantity)) {
			return true;
		}
		return false;
	}

}
