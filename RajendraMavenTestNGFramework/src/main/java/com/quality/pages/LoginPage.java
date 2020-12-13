package com.quality.pages;

import java.util.Properties;

import com.quality.basepage.TestBase;
import com.quality.commonfunctions.CommonFunctions;

public class LoginPage extends TestBase{

	public static Properties ob = CommonFunctions.ObjectMap("Xpath", "\\src\\main\\java\\com\\quality\\objectrepository\\");
	
	public void login() {
		//CommonFunctions.writeTextINWebElement(ob.getProperty("amazonInputSearchBox"), "laptop");
		CommonFunctions.writeTextINWebElement(ob.getProperty("amazonInputSearchBox"), CommonFunctions.getDataFromJsonFile("search"));
		//CommonFunctions.clickOnWebElement(ob.getProperty("searchButton"));
		CommonFunctions.clickOnXpathUsingJE(ob.getProperty("searchButton"));
		CommonFunctions.verifyWebElementIsAvailable(ob.getProperty("searchButton"));
	}
	
	
	
}
