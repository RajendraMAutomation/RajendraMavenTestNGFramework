package com.quality.selfpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LaunchGoogle {
	 //private static WebDriver driver;
	/*public static void main(String[] args) throws InterruptedException   {
		System.setProperty("webdriver.chrome.driver", "..\\RajendraMavenTestNGFramework\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
		Thread.sleep(500);
		driver.quit();

	}*/
	@Test
	public void run() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "..\\RajendraMavenTestNGFramework\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("laptop");
		driver.findElement(By.xpath("//input[@type=\"submit\" and @class=\"nav-input\"]")).click();
		Thread.sleep(500);
		driver.quit();
	}

}
