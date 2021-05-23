package com.quality.commonfunctions;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.quality.basepage.TestBase;

public class CommonFunctions extends TestBase{

	public static WebDriver driver;
	public static JSONParser jsonParser;
	public static Object obj;
	public static JSONObject jsonObject;
	
	public static Properties ObjectMap(String sName, String path) {
		//path = "\\src\\test\\resources\\";
		//path = "\\src\\main\\java\\com\\quality\\objectrepository\\";
		Properties properties = new Properties();
		try {
			String workingDir = System.getProperty("user.dir");
			String or_fileName = sName + ".properties";
			FileInputStream master = new FileInputStream(workingDir +path+or_fileName);
			properties.load(master);
			master.close();
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static String getDataFromJsonFile(String key) {
		String value = null;
		try {
			jsonParser = new JSONParser();
			obj = jsonParser.parse(new FileReader("..\\RajendraMavenTestNGFramework\\src\\main\\java\\com\\quality\\testdata\\TestData.json"));
			jsonObject = (JSONObject) obj;
			value = (String) jsonObject.get(key);
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return value;
		} catch (ParseException e) {
			e.printStackTrace();
			return value;
		}
	}
	
	
	public static boolean clickOnWebElement(String xPathOfWebElememt) {
		boolean msg = false;
		try {
			WebElement webElement = driver.findElement(By.xpath(xPathOfWebElememt));
			msg = webElement.isEnabled();
			if(msg) {
				webElement.click();
				System.out.println("Clicked on: "+xPathOfWebElememt);
				return msg;
			} else {
				System.out.println("WebElement "+xPathOfWebElememt+" is not clicked as Disabled");
				return msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to clicked on :: "+xPathOfWebElememt);
			return false;
		}
	}
	
	public static void writeTextINWebElement(String xPathOfWebElememt, String valueToInsert) {
		try {
			WebElement webElement = driver.findElement(By.xpath(xPathOfWebElememt));
			webElement.clear();
			webElement.sendKeys(valueToInsert);
			Thread.sleep(500);
			//CommonFunctions.takeSnapShot();
			System.out.println(valueToInsert+" is inserted at "+xPathOfWebElememt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
// To take screenShot of browser window:
	
	public static String takeSnapShot() throws IOException {
		
			TakesScreenshot scrShot = ((TakesScreenshot)driver);// Conver Webdriver object to TakesScreenshot
			File srcFile = scrShot.getScreenshotAs(OutputType.FILE); //Call getScreenshotAs method to create image File
			String timestamp = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss").format(new Date()); //get current Date and Time from system in given format..//if HH then consider 24 hours and if hh then consider 12 hours
			File a = new File("..\\RajendraMavenTestNGFramework\\ExtentReports");
			if(!(a.exists())) {
				a.mkdir();
			}
			String fileName = "Automation_"+timestamp+".png";
			String newFilePath  = a.getAbsolutePath()+"\\"+fileName;
			File destFile = new File(newFilePath); //Define destination of File with specified name
			FileUtils.copyFile(srcFile, destFile); //Copy file at destination
			return fileName;
	}
	
	// Take ScreenShot of Whole Screen:
	
	public static void captureDevScreen() {
		
		String fileName = "";
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");		//if HH then consider 24 hours and if hh then consider 12 hours
		String formatDateTime = now.format(formatter);
		try {
			Robot robot = new Robot();
			String format = "png";
			fileName = "Automation_Full_"+formatDateTime+"."+format;
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File("..\\RajendraMavenTestNGFramework\\ExtentReports\\"+fileName));
			
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return fileName;
		
	}
	
	public static void clickUsingJE(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
		System.out.println("Successfully clicked using JavaScript");
	}
	
	public static void clickOnXpathUsingJE(String xPathOfWebElement) {
		WebElement webElement = driver.findElement(By.xpath(xPathOfWebElement));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
		System.out.println("Successfully clicked using JavaScript");
	}
	
	public static boolean verifyWebElementIsAvailable(String xPathOfWebElement) {
	
	//	xPathOfWebElement = xPathOfWebElement.replace("xpath=", "");
		List<WebElement> webElement =	driver.findElements(By.xpath(xPathOfWebElement));
		int size = webElement.size();
		if(!(size==0)) {
			System.out.println("Element is found as per given xpath>>>  "+xPathOfWebElement);
			return true;
		}else {
		return false;
		}
	}
	
	// verify Dropdown Values i.e. expected value Vs. Actual values
	//this function first get all actual value and then compare with expected.
	public static boolean verifyValuesInDropdown(String[] expectedValuesInDropdown, String xPathForDropdown, String commonXpathToSelectValuesFromDropdown) throws IOException {
		boolean flag = false;
		clickOnWebElement(xPathForDropdown);
		takeSnapShot();
		List<WebElement> dropdownActualValues =	driver.findElements(By.xpath(commonXpathToSelectValuesFromDropdown));
		System.out.println("Actual values in dropdown are:>>");
		for(WebElement webElement : dropdownActualValues) {
			System.out.println(webElement.getText());
		}
		for(int i=0;i<expectedValuesInDropdown.length;i++) {
			for(WebElement web : dropdownActualValues) {
				System.out.println("expected value is "+expectedValuesInDropdown[i]+" and Actual value is "+web.getText());
				flag = expectedValuesInDropdown[i].equalsIgnoreCase(web.getText());
				if(flag) {
					System.out.println("Passed");
					break;
				}
			}
		}
		return flag;
	}
	
	
	
	
	public static boolean fn_selectFromDropdown(String nameOfDrompdown, String xPathforDropdown,
			String commonXpathToSelectValuesFromDropdown, String valueToSelectFromDropdown) throws IOException {
		boolean msg = false;
		try {
			msg = clickOnWebElement(xPathforDropdown);
			if (msg) {
				Thread.sleep(1000);
				WebElement webElement = driver.findElement(
						By.xpath(commonXpathToSelectValuesFromDropdown + "='" + valueToSelectFromDropdown + "']"));
				Thread.sleep(500);
				if (webElement.isDisplayed()) {
					webElement.click();
					Thread.sleep(500);
					takeSnapShot();
					System.out
							.println(valueToSelectFromDropdown + " is selected from " + nameOfDrompdown + " dropdown");
					return true;
				} else {
					System.out.println(valueToSelectFromDropdown + " is NOT selected from " + nameOfDrompdown
							+ " dropdown as " + valueToSelectFromDropdown + " is not available");
					return true;
				}
			}else 
				{
				System.out.println(valueToSelectFromDropdown + " is NOT selected from " + nameOfDrompdown + " dropdown as Dropdown is Disabled");
				return true;
				}
		} catch (InterruptedException e) {
			System.out.println(valueToSelectFromDropdown+" not able to select from "+nameOfDrompdown+ " dropdown");
			e.printStackTrace();
			return false;
		}
}
	
	public static boolean selectMultipleValuesFromDropdown(String xPathToClickDropdown, String commonXPathToSelectValueFromDropdown, String[] listOfAllValuesToSelect) {
		boolean flag = false;
		try {
			for(String str : listOfAllValuesToSelect) {
				clickOnWebElement(xPathToClickDropdown);
				String actualXpath = commonXPathToSelectValueFromDropdown+"='"+str+"']";
				flag = clickOnWebElement(actualXpath);
				if(!flag) {
					break;
				}
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		}
	}
	
	//-------------------------Specific to project  
	public static ArrayList<String> selectAllValuesFromDropdown(String xPathOfDropdown) {
		ArrayList<String> temp = new ArrayList<>();
		
		String dropdownXpath = null;
		String optionsXpath = null;
		WebElement dropdownElement = null;
		
		try {
			dropdownXpath = xPathOfDropdown+"//coral-icon";
			optionsXpath = xPathOfDropdown+"//coral-selectlist-item";
			List<WebElement> dropdownElements = driver.findElements(By.xpath(optionsXpath));
			
			dropdownElement = driver.findElement(By.xpath(dropdownXpath));
			for(WebElement webElement : dropdownElements) {
				clickUsingJE(dropdownElement);
				clickUsingJE(webElement);
				temp.add(webElement.getAttribute("value"));
			}
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean isAlertPresent() {
		//this function can be call to check if an Alert box is present
		try {
			driver.switchTo().alert();	//normal method to switched to alert box
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}   
	public static void flushUserMsg(boolean isTestPassed,String PassMsg,String failMsg ) throws IOException {
		  if(isTestPassed) {
			  extentTest.log(Status.PASS, PassMsg, MediaEntityBuilder.createScreenCaptureFromPath(takeSnapShot()).build() ); 
		  }
		  else
			  extentTest.log(Status.FAIL, failMsg, MediaEntityBuilder.createScreenCaptureFromPath(takeSnapShot()).build() );
		}
	
}
