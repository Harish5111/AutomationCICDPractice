package viralbugindustries.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import viralbugindustries.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:last-of-type")
	WebElement selectIndia;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = (By.cssSelector(".ta-results"));
	
	
	public CheckoutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	

	public void SelectCountry(String countryName) throws InterruptedException 
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectIndia.click();
	}
	
	public ConfirmationPage selection()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
