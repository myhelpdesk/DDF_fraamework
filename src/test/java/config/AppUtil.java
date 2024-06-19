package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	
	//driver is a instant object create for webdriver class
		//I am declaring static variable means reusing some other class 
		public static WebDriver driver;
		//conpro is a instant object create for properties class
		public static Properties conpro;
		@BeforeTest
		public static void setUp()throws Throwable
		{
			// access the Properties class methods for that create object of Properties class 
			conpro = new Properties();
			//load property file
			//a.m1(), here m1 is load
			conpro.load(new FileInputStream("./PropertyFiles\\Environment.properties"));
			//how to access key value from Property class file?
			//A)with the help of Property class object
			//conpro is a Property class object,getProperty is a Property class methode
			//equalsIgnoreCase means case sensitive, means  chrome is  capital or small could be any thing
			//if it Browser key match to chrome then launch chrome browser
			if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
			{
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				}
			//if it Browser key match to Firefox then launch Firefox browser
			else if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox"))
			{
				driver = new FirefoxDriver();
			}
			//Above both browser not matching else will execute
			else
			{
				//diff b/w syso and Reporter.log?
				//if use syso("Browser Value is Not matching") it will print message console output only
				//If use Reporter.log("Browser Value is Not matching",true) It will print console output and print html reports
				Reporter.log("Browser Value is Not matching",true);
			}
			
		}
		@AfterTest
		public static void tearDown()
		{
			driver.quit();
		}


}
