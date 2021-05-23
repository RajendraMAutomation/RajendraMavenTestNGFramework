package com.quality.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.quality.basepage.TestBase;
import com.quality.commonfunctions.CommonFunctions;
import com.quality.pages.LoginPage;
import junit.framework.Assert;

public class LoginPageTest extends TestBase{
	
	public LoginPageTest() {
		super();
	}
	
	//public static Properties ob = CommonFunctions.ObjectMap("Xpath", "\\src\\main\\java\\com\\quality\\objectrepository\\");
	
	LoginPage loginPage = new LoginPage();
	
	@BeforeTest
	public void setupExtentReport(){
		setupExtend();
	}
	@AfterTest
	public void closeExtentReport() {
		finishReport();
	}
	@BeforeMethod
	public void setup() {
		initializttion();
		CommonFunctions.driver = driver;
	}

	@Test
	public void testLogin() throws IOException {
		extentTest = reports.createTest("Login test");
		loginPage.login();
		System.out.println("Login successful");
		//CommonFunctions.takeSnapShot();
		//CommonFunctions.captureDevScreen();
		//CommonFunctions.flushUserMsg(false, "TCPass", "TCFailed");
	}
	
	@Test
	public void usum() throws IOException {
		extentTest = reports.createTest("Sum test");
		int a=10;
		int b=20;
		Assert.assertEquals(31, a+b);
		System.out.println(a+b);
		//CommonFunctions.flushUserMsg(true, "TCPass", "TCFailed");
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus()) {
			  //extentTest.fail(result.getName());
			  //extentTest.fail("this test method is failed due to ...");
				extentTest.log(Status.FAIL, "Failed to run the Method:  '"+result.getName()+"'  as...");
				extentTest.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(CommonFunctions.takeSnapShot()).build());
		  }
		if(ITestResult.SUCCESS==result.getStatus()) {
			extentTest.log(Status.PASS, "successfullty run the Method:  "+result.getName());
			}
		driver.quit();
		System.out.println("All Browser Closed");
	}
}
