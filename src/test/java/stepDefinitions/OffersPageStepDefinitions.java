package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import utils.TestContextSetup;

public class OffersPageStepDefinitions {

	public String offerPageProductname;

	TestContextSetup testContextSetup;

	public OffersPageStepDefinitions(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}


	@Then("^User searches with same shortname (.+) on offers page$")
	public void user_searches_with_same_shortname_on_offers_page(String shortName) throws InterruptedException {
		//LandingPage landingPage = new LandingPage(testContextSetup.driver);
		//switchToOffersPage();
		LandingPage landingPage = testContextSetup.pageObjectManager.getlandingPage();
		landingPage.switchToOffersPage();
		OffersPage offersPage=testContextSetup.pageObjectManager.getoffersPage();
		offersPage.searchItem(shortName);
		//Thread.sleep(3000);
		offerPageProductname = offersPage.getProductName();
		System.out.println(offerPageProductname);
	
	}

	@Then("validate product name in offers page matches with landing page")
	public void validate_product_name_in_offers_page_matches_with_landing_page() {
	
		Assert.assertEquals(offerPageProductname, testContextSetup.landingPageProductname);
	}

}
