package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import pageObjects.CheckoutPage;
import utils.TestContextSetup;

public class CheckoutPageStepDefinitions {

	
	TestContextSetup testContextSetup;
	CheckoutPage checkoutPage;

	public CheckoutPageStepDefinitions(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.checkoutPage=testContextSetup.pageObjectManager.getcheckoutPage();
	}
	@Then("The product with shortname {string} and quantity {string} is shown correctly on the checkout page")
	public void the_product_with_shortname_and_quantity_is_shown_correctly_on_the_checkout_page(String shortname, String quantity) {
		Assert.assertTrue(checkoutPage.verifyCheckoutPageOrderDetails(shortname,quantity));
		
	}

	@Then("Apply and Place order buttons are showing on the checkout page")
	public void apply_and_place_order_buttons_are_showing_on_the_checkout_page() {
	   
		Assert.assertTrue(checkoutPage.verifyApplybutton());
		Assert.assertTrue(checkoutPage.verifyPlaceOrderbutton());
	}
}
