package viralbugindustries.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import viralbugindustries.TestComponents.BaseTest;
import viralbugindustries.pageobjects.CartPage;
import viralbugindustries.pageobjects.CheckoutPage;
import viralbugindustries.pageobjects.ConfirmationPage;
import viralbugindustries.pageobjects.OrderPage;
import viralbugindustries.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	
//	String mail = "viralact@gmail.com";
//	String psw = "Password@123";
	String prodname = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups = {"Purchase","Regression"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("mail"), input.get("psw"));
		
		List<WebElement> products = productCatalogue.getProductlist();
		productCatalogue.addProductToCart(input.get("prodname"));		
		CartPage cartPage = productCatalogue.cartclick();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("prodname"));		
		Assert.assertTrue(match);	
		CheckoutPage checkoutPage = cartPage.goToCheckout();	
		
		checkoutPage.SelectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.selection();
		
		String confirmationMessage = confirmationPage.getConfirmation();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	

	}
	
	@Test(dependsOnMethods = {"submitOrder"})	
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("viralact@gmail.com", "Password@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(prodname));		
	}

	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mail", "viralact@gmail.com");
		map.put("psw", "Password@123");
		map.put("prodname", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("mail", "comebaby@gmail.com");
		map1.put("psw", "Password@123");
		map1.put("prodname", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};		
	}

}
