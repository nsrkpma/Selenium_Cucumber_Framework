package stepDefinitions;

import io.cucumber.java.en.Given;
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

}
