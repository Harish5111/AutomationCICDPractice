package viralbugindustries.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;

import viralbugindustries.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
		
	 WebDriver driver;
	
	 public ProductCatalogue(WebDriver driver)
	 {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	 }


//	 List <WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
	 
	  @FindBy(css=".mb-3")
	  List <WebElement> products;
	  
	  @FindBy(css=".ng-animating")
	  WebElement spinner;	  
	  
	  @FindBy(css=".cartSection h3")
	  List <WebElement> itemsListed;
	  
	  By prodsBy = By.cssSelector(".mb-3");
	  By addTocart = By.cssSelector(".card-body button:last-of-type");
	  By toastMessage = By.cssSelector("#toast-container");
	  By prodsIncart = By.cssSelector(".cartSection h3");
	  
	  public List<WebElement> getProductlist()
	  {
		 waitForElementToAppear(prodsBy);
		 return products;
	  }
	 
	  public WebElement getProductByname(String productName)
	  {
		 WebElement zara = getProductlist().stream().filter(y->
		 y.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);	
		 return zara;
	  }
	  
	  public void addProductToCart(String productName) throws InterruptedException
	  {
		 WebElement prod = getProductByname(productName);
		 prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();	
		 waitForElementToAppear(toastMessage);
		 waitForElementToDisAppear(spinner);
	  }
	  
	  
	  
	  
	 
	 
	}


