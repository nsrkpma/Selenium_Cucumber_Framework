package pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {

public WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}
	By search=By.xpath("//input[@type='search']");
	By productName=By.cssSelector("h4.product-name");
	By offersPageLink=By.xpath("//a[@href='#/offers']");
	By quantityBox=By.cssSelector("input.quantity");
	By addToCart=By.xpath("//button[text()='ADD TO CART']");
	By cartIcon=By.cssSelector("a.cart-icon");
	By proceedToCheckout=By.xpath("//button[text()='PROCEED TO CHECKOUT']");
	
	public void searchItem(String shortName) {
		driver.findElement(search).sendKeys(shortName);
	} 
	
	public String getProductName() {
		return driver.findElement(productName).getText().split("-")[0].trim();
		}

	public void switchToOffersPage() {
		driver.findElement(offersPageLink).click();
		Set<String> windowHandles =driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		it.next();
		String childWindow = it.next();
		driver.switchTo().window(childWindow);
	}
	public void addToCart(String qunatity) {
		driver.findElement(quantityBox).clear();
		driver.findElement(quantityBox).sendKeys(qunatity);
		driver.findElement(addToCart).click();
	}
	public void switchToCheckoutPage() {
		driver.findElement(cartIcon).click();
		driver.findElement(proceedToCheckout).click();
	}
}
