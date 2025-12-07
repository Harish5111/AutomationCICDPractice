Feature: Submit Order

Background:
	Given I landed on Ecommerce Page

  Scenario Outline: Positive test of purchasing the order
    Given Logged in with username <name> and <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
    Examples:
    | name               | password	    |  productName |
    | viralact@gmail.com | Password@123 |  ZARA COAT 3 |