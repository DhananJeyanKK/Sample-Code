package com.bnpp.source;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import reporting.Bnpp.JyperionListener;

import com.bnpp.data.DataWeb;

@Listeners(JyperionListener.class)
public class Mybank extends DataWeb {

	LoginModule Obj = new LoginModule();
	String Screenshotpath = "/Users/mobilitytcs/Desktop/Automation/Selenium/Results/Screenshots/";
	Properties prop = new Properties();
	boolean id = false;

	/****************************************************************************************
	 
	  									Before Class
	  									
	 ****************************************************************************************/

	@BeforeClass
	public void set() throws Exception {

		System.out.println("welcome!!");
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", chromepath);
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setCapability("chrome.switches",
				Arrays.asList("--disable-local-storage"));
		dc.setCapability(
				CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		dc.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(dc);
		driver.manage().deleteAllCookies();
		driver.get(BaseUrl);

	}

	/****************************************************************************************
	 
	  								 After Class
	  								 
	 *****************************************************************************************/

	@AfterClass(alwaysRun = true)
	public void tear() throws Exception {
		driver.quit();
	}

	@Test
	public void MybankV1() throws Exception {
		 
		WebDriverWait wait = new WebDriverWait(driver,40);	
		Obj.loginPositiveflow();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		
		driver.findElement(
				By.xpath(".//*[@id='1392308252578']/div/div[1]/div[2]/ul/li[2]/a"))
				.click();
		driver.findElement(
				By.xpath(".//*[@id='1430213272830']/div/div[1]/div/div[2]/div/div[1]/div[2]/span"))
				.click();
		driver.findElement(
				By.xpath(".//*[@id='1430213272830']/div/div[1]/div/div[2]/div/div[1]/div[2]/span"))
				.click();
		driver.findElement(
				By.xpath(".//*[@id='1430213272830']/div/div[1]/div/div[2]/div/div[2]/div[2]/span"))
				.click();
		driver.findElement(
				By.xpath(".//*[@id='1430213272830']/div/div[1]/div/div[2]/div/div[2]/div[2]/span"))
				.click();
		driver.findElement(By.xpath(".//*[@id='1430213272830']/div/div[2]/a"))
				.click();
		Thread.sleep(15000);

		ArrayList<String> tabsnew = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(tabsnew.get(1));
		driver.close();
		driver.switchTo().window(tabsnew.get(0));

//		int a[] = { 1000, 3000, 6200, 7800, 9000, 9999 };
//
//		for (int i = 0; i < a.length; i++) {
//
//			String b = Integer.toString(a[i]);
//			System.out.println(b);
//
//			driver.findElement(By.id("branch_locator")).sendKeys(b);
//			driver.findElement(By.id("branch_loc_btn")).click();
//			Thread.sleep(12000);
//
//			ArrayList<String> tabs5 = new ArrayList<String>(
//					driver.getWindowHandles());
//			driver.switchTo().window(tabs5.get(1));
//			driver.close();
//			driver.switchTo().window(tabs5.get(0));
//			Thread.sleep(2000);
//			driver.findElement(By.id("branch_locator")).clear();
//
//		}
//
//		for(int i=0;i<4;i++) {
//
//			driver.findElements(By.cssSelector(".socialad_icon")).get(i)
//					.click();
//			Thread.sleep(10000);
//			ArrayList<String> tabs = new ArrayList<String>(
//					driver.getWindowHandles());
//			driver.switchTo().window(tabs.get(1));
//			driver.close();
//			driver.switchTo().window(tabs.get(0));
//			Thread.sleep(1000);
//		}
	}
	
	
	@Test
	public void CR47() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,40);	
		Obj.loginPositiveflow();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		
		
		
	}

}
