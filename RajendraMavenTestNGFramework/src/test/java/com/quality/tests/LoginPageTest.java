package com.quality.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
	@BeforeMethod
	public void setup() {
		initializttion();
		CommonFunctions.driver = driver;
	}

	@Test
	public void testLogin() throws IOException {
		loginPage.login();
		System.out.println("Login successful");
		CommonFunctions.takeSnapShot();
		CommonFunctions.captureDevScreen();
	}
	
	@Test
	public void usum() {
		int a=10;
		int b=20;
		Assert.assertEquals(30, a+b);
		System.out.println(a+b);
	}
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
		System.out.println("All Browser Closed");
	}
}
