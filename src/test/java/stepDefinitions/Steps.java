package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import viralbugindustries.TestComponents.BaseTest;
import viralbugindustries.pageobjects.CartPage;
import viralbugindustries.pageobjects.CheckoutPage;
import viralbugindustries.pageobjects.ConfirmationPage;
import viralbugindustries.pageobjects.LandingPage;
import viralbugindustries.pageobjects.ProductCatalogue;

public class Steps extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
    @Given("^Logged in with username (.+) and (.+)$")
    public void login(String username, String password) 
    {
    	productCatalogue = landingPage.loginApplication(username, password);
        System.out.println("Login done");
    }
    
    @When("^I add the product (.+) to Cart$")
    public void I_add_the_product_to_Cart(String productName) throws InterruptedException
    {
    	List<WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(productName);		
    }
    
    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) throws InterruptedException
    {
		CartPage cartPage = productCatalogue.cartclick();		
		Boolean match = cartPage.VerifyProductDisplay(productName);		
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();	
		checkoutPage.SelectCountry("India");
		confirmationPage = checkoutPage.selection();
    }
    

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) 
    {
    	String confirmationMessage = confirmationPage.getConfirmation();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
//    @Then("{string} message is displayed on ConfirmationPage")
//    public void something_message_is_displayed(String string) 
//    {
//    	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
//    	driver.close();
//    }
}
