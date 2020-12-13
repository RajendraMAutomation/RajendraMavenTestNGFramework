package com.quality.basepage;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.quality.commonfunctions.CommonFunctions;
import com.quality.util.TestUtil;

public class TestBase {
	public static Properties readConfig;
	public static WebDriver driver;
	public TestBase() {
		
		readConfig = CommonFunctions.ObjectMap("config","\\src\\main\\java\\com\\quality\\config\\") ;
		
	}
	
	public static void initializttion() {
		String browserName = readConfig.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "..\\RajendraMavenTestNGFramework\\Driver\\chromedriver.exe");
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(readConfig.getProperty("url"));
			
			
		}
	}
}
