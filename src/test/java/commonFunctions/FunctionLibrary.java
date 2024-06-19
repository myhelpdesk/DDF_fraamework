package commonFunctions;



import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;
//I am accessing(extends) AppUtil class for accessing locators value for login
public class FunctionLibrary extends AppUtil {
	public static boolean adminLogin(String user,String pass) throws Throwable
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("Valid username and password::"+Expected+"------"+Actual,true);
			driver.findElement(By.xpath(conpro.getProperty("ObjLogout"))).click();
			return true;
		}
		else
		{
			Thread.sleep(10000);
			String Error_message = driver.findElement(By.xpath(conpro.getProperty("ObjErrormessage"))).getText();
			//Thread.sleep(10000);
			//on the eroor popup page click ok button
			driver.findElement(By.xpath(conpro.getProperty("ObjOk"))).click();
			Reporter.log(Error_message+"------"+Expected+"-------"+Actual,true);
			return false;
		}
	}


}
