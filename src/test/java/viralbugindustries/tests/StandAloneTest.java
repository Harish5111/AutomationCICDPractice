package viralbugindustries.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import viralbugindustries.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String mail = "viralact@gmail.com";
		String psw = "Password@123";
		String prodname = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");		
		LandingPage landingpage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys(mail);
		driver.findElement(By.id("userPassword")).sendKeys(psw);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List <WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement zara = Products.stream().filter(y->
		y.findElement(By.cssSelector("b")).getText().equals(prodname)).findFirst().orElse(null);		
		zara.findElement(By.cssSelector(".card-body button:last-of-type")).click();			
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(z-> z.getText().equalsIgnoreCase(prodname)); 
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String page = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(page.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();		

	}

}
