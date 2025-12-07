package viralbugindustries.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import viralbugindustries.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
//	WebElement checkoutEle;
	private List <WebElement> productTitles;
	@FindBy(css=".totalRow button")
//	private List <WebElement> productTitles;
	WebElement checkoutEle;
			
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productName) 
	{
		Boolean match = productTitles.stream().anyMatch(z-> z.getText().equalsIgnoreCase(productName)); 
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
