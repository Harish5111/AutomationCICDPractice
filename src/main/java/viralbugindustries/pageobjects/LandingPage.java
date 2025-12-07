package viralbugindustries.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import viralbugindustries.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
		
	 WebDriver driver;
	
	 public LandingPage(WebDriver driver)
	 {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	 }


//	 WebElement userMail = driver.findElement(By.id("userEmail"));	 	 
	  @FindBy(id="userEmail")
	  WebElement userMails;
	  
//	  driver.findElement(By.id("userPassword")	  
	  @FindBy(id="userPassword")
	  WebElement userPasswords;
	  
//	  driver.findElement(By.id("login")
	  @FindBy(id="login")
	  WebElement userLogin;
	  
	  @FindBy(css="[class*='flyInOut']")
	  WebElement errorMessage;	  
	  
	  
	  public ProductCatalogue loginApplication(String email, String password)
	  {
		  userMails.sendKeys(email);
		  userPasswords.sendKeys(password);
		  userLogin.click();
		  ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		  return productCatalogue;		  
	  }
	  
	  public String getErrorMessage()
	  {
		  waitForWebElementToAppear(errorMessage);
		  return errorMessage.getText();
	  }
	  
	  public void goTo()
	  {
		  driver.get("https://rahulshettyacademy.com/client");
	  }
	  
	 
	 
	 
	}


