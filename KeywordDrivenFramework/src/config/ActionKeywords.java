package config;
 
import static executionEngine.DriverScript.OR;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ActionKeywords {
	// static Properties p;
		public static WebDriver driver;
		
	public static void openBrowser(String object)
	{ 
		
		Log.debug("Open Browser");	
	//Log.info("Open Browser");
		System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
		driver=new FirefoxDriver();
		}
 
	public static void launchApp(String object)
	{
		Log.info("Launch App");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		}
 
	public static void input_Email(String object)
	{
		Log.info("EnterEmail Id");	
		 driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.UserName);
		}
 
	 
	public static void waitFor(String object) throws Exception
	{
		Log.info("Wait");	
		Thread.sleep(5000);
		}
 
	
	public static void CloseBrowser(String object)
	{
		Log.info("Close Browser");	
			driver.quit();
		}
 
	}