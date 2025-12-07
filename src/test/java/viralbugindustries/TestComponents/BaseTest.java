package viralbugindustries.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import viralbugindustries.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException
	{		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+"\\src\\main\\java\\viralbugindustries\\resources\\GlobalData.properties");
		prop.load(fis);
//		String broswerName = prop.getProperty("browser");	
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); // if you wanna set global parameter for browser change
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();				
			if(browserName.contains("headless"))
			{
		        options.addArguments("--headless=new");
		        options.addArguments("--disable-gpu");
		        options.addArguments("--no-sandbox");
		        options.addArguments("--disable-dev-shm-usage");
		        options.addArguments("--window-size=1440,900");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //only use this when u r running headless argument or for custom full screen
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\haris\\drivers to practice selenium\\gecko driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		
		driver.manage().window().maximize();	
		return driver;
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}

}
