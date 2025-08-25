Feature: Add items to cart and verfiy items on checkout page
@PlaceOrder @TCID_9
Scenario Outline: Verifying items added on lading page are showing on checkout page

Given User is on Grrenkart Landing Page
When User searches with shortname <Names> and extracted actual name of product
And User adds quantity "4" of the product to the cart
And User navigates to checkout Page
Then The product with shortname "Tom" and quantity "4" is shown correctly on the checkout page
And Apply and Place order buttons are showing on the checkout page

Examples:
|Names|
|Tom|