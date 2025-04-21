package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {
	
	public WebDriver driver;
	public OffersPage(WebDriver driver) {
		this.driver=driver;
	}
	By search=By.id("search-field");
	By productName=By.tagName("td");
	
	public void searchItem(String shortName) {
		driver.findElement(search).sendKeys(shortName);
	} 
	
	public String getProductName() {
		return driver.findElement(productName).getText();
		}

}

