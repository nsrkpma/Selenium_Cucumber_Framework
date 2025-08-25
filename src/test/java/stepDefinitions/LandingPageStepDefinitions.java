package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContextSetup;

public class LandingPageStepDefinitions {
	TestContextSetup testContextSetup;
	LandingPage landingPage;

	public LandingPageStepDefinitions(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.landingPage = testContextSetup.pageObjectManager.getlandingPage();
	}

	@Given("User is on Grrenkart Landing Page")
	public void user_is_on_grrenkart_landing_page() {

		
	}

	@When("^User searches with shortname (.+) and extracted actual name of product$")
	public void user_searches_with_shortname_and_extracted_actual_name_of_product(String shortName)
			throws InterruptedException {
		
				//new LandingPage(testContextSetup.driver);
		landingPage.searchItem(shortName);
		Thread.sleep(3000);
		testContextSetup.landingPageProductname = landingPage.getProductName();
		
	}
	
	@When("User adds quantity {string} of the product to the cart")
	public void user_adds_quantity_of_the_product_to_the_cart(String string) {
		//LandingPage landingPage = testContextSetup.pageObjectManager.getlandingPage();
		landingPage.addToCart("4");
	}
	@When("User navigates to checkout Page")
	public void user_navigates_to_checkout_page() throws InterruptedException {
		//LandingPage landingPage = testContextSetup.pageObjectManager.getlandingPage();
		landingPage.switchToCheckoutPage();
		Thread.sleep(2000);
	}
	
	@Then("Top Deals and Flight Booking Links Shown on Homepage")
	public void top_deals_and_flight_booking_links_shown_on_homepage() {
	    Assert.assertTrue(landingPage.verifyNavbarLinks());
	}

	@Then("Footer Text is © {int} GreenKart - buy veg and fruits online")
	public void footer_text_is_green_kart_buy_veg_and_fruits_online(Integer int1) {
		Assert.assertEquals("© 2019 GreenKart - buy veg and fruits online", landingPage.getFooterText());
	}
}
