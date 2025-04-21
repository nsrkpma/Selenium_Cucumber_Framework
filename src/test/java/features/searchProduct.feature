Feature: Search and Place the order for Products
@OffersPage
Scenario Outline: Search experience for product search in both home and Offer Page

Given User is on Grrenkart Landing Page
When User searches with shortname <Names> and extracted actual name of product
Then User searches with same shortname <Names> on offers page
And validate product name in offers page matches with landing page

Examples:
|Names|
|Tom|
|beet|
|straw|