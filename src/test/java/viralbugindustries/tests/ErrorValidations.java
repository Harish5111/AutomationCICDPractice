package viralbugindustries.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import viralbugindustries.TestComponents.BaseTest;
import viralbugindustries.TestComponents.Retry;
import viralbugindustries.pageobjects.CartPage;
import viralbugindustries.pageobjects.CheckoutPage;
import viralbugindustries.pageobjects.ConfirmationPage;
import viralbugindustries.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	@Test(groups = {"ErrorHandling","Regression"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		String mail = "viralactcc@gmail.com";
		String psw = "Password@23";
		String prodname = "ZARA COAT 3";
		
		landingPage.loginApplication(mail, psw);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		}


	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String mail = "viralact@gmail.com";
		String psw = "Password@123";
		String prodname = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(mail, psw);
		
		List<WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(prodname);		
		CartPage cartPage = productCatalogue.cartclick();
		
		Boolean match = cartPage.VerifyProductDisplay(prodname);		
		Assert.assertTrue(match);	
		
	}
}

