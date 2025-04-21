package pageObjects;

import org.openqa.selenium.WebDriver;

//Factory design pattern
public class PageObjectManager {

	public WebDriver driver;
	public LandingPage landingPage;
	public OffersPage offersPage;
	public CheckoutPage checkoutPage;
	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public LandingPage getlandingPage() {
		landingPage=new LandingPage(driver);
		return landingPage;
	}
	
	public OffersPage getoffersPage() {
		offersPage=new OffersPage(driver);
		return offersPage;
	}
	public CheckoutPage getcheckoutPage() {
		checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
}
