package com.bnpp.source;

import java.io.File;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import reporting.Bnpp.JyperionListener;

import com.bnpp.data.DataWeb;
import com.bnpp.methods.MethodsWeb;

@Listeners(JyperionListener.class)
public class LoginModule extends DataWeb {

	 MethodsWeb object = new MethodsWeb();
//	 LoginModule Obj = new LoginModule();
	

	 
	 /****************************************************************************************

    									Before Class

     ****************************************************************************************/

@BeforeClass(alwaysRun=true)
public void set() throws Exception {

System.out.println("welcome!!");
ChromeOptions options = new ChromeOptions();
System.setProperty("webdriver.chrome.driver",chromepath);
DesiredCapabilities dc = DesiredCapabilities.chrome();
dc.setCapability("chrome.switches", Arrays.asList("--disable-local-storage"));
dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
dc.setCapability(ChromeOptions.CAPABILITY, options);
driver = new ChromeDriver(dc);
driver.manage().deleteAllCookies();
driver.get(BaseUrl);

}


     /****************************************************************************************

                                         After Class

     *****************************************************************************************/

   @AfterClass(alwaysRun=true)
   public void finish() throws Exception {
   driver.quit();
   }

	 
   /****************************************************************************************
	               
	                                    Login Flow Method
	                                    
	 *****************************************************************************************/
 
	public void loginPositiveflow() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Properties prop = new Properties();

		//		############# loading the properties file from project folder
		
		FileInputStream input = new FileInputStream(filepath
				+ "Object Repository/LoginData.properties");
		FileInputStream input1 = new FileInputStream(filepath
				+ "Test Data/TestData.properties");
		prop.load(input);
		prop.load(input1);
		
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    Thread.sleep(500);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.className("progressing")));
		
//		driver.navigate().refresh();
//		
//		try {
//			WebDriverWait wait1 = new WebDriverWait(driver,7);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By
//					.className(prop.getProperty("overlayPreLogonPopup"))));
//			WebElement we = driver.findElement(By.className(prop
//					.getProperty("overlayPreLogonPopup")));
//			we.findElement(By.cssSelector(prop.getProperty("Closebutton")))
//					.click();
//
//		}catch(Exception E1){
//
//			System.out.println("No Pop-up is Displayed");
//		}

		// *****************        Login Screen Check      *******************
	
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));
	
		if (driver.findElement(By.className(prop.getProperty("inputcn")))
				.isDisplayed()) {
			we = driver.findElement(By.id(prop.getProperty("infomsg")));
			String HomeMsg = "Login Screen is displayed with message " + " '' "
					+ we.getText().trim() + " '' ";
			System.out.println(HomeMsg);
		}
		System.out.println("'Login page Tested'");
		driver.findElement(By.className(prop.getProperty("inputcn"))).sendKeys(prop.getProperty("CustomerID"));
		Thread.sleep(300);
		driver.findElement(By.className(prop.getProperty("inputcdn"))).click();
		Thread.sleep(300);
		driver.findElement(By.className(prop.getProperty("inputcdn"))).clear();
		Thread.sleep(500);
		driver.findElement(By.className(prop.getProperty("inputcdn"))).clear();
		driver.findElement(By.className(prop.getProperty("inputcdn")))
				.sendKeys(prop.getProperty("Cardno"));
		driver.findElement(By.className(prop.getProperty("loginbt"))).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));
        
		// *************** To select the Contracts **********************
	   try{
			int count = 0;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			boolean contractCheck = driver.findElement(By.cssSelector(".contract_choice")).isDisplayed();
			
			if(contractCheck==true){
			WebElement ContractMain = driver.findElement(By.cssSelector(".contract_choice"));
			count = ContractMain.findElements(By.cssSelector(".btn_primary")).size();
			System.out.println(prop.getProperty("SuccessMsg")
						+ " This Accounts having " +count +" contracts");
			System.out.println("Selecting " + ContractMain.findElements(By.cssSelector(".btn_primary")).get(0).getText());	
			ContractMain.findElements(By.cssSelector(".btn_primary")).get(0).click();
			System.out.println("");
			Thread.sleep(5000);
		  }
		   
		 }catch (Exception e1) {
			System.out.println(prop.getProperty("SuccessMsg")
					+ " This Accounts has no contracts ");
			System.out.println("");
		}
	 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.id("signature")));
		Thread.sleep(3000);

		driver.findElement(
				By.xpath(".//*[@id='login_overlayer']/form/div[2]/div/div[2]/div/div[1]/button"))
				.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));

		
	// ***************** Checking the change language confirmation ********

	try{
			if(driver
					.findElement(By.className(prop.getProperty("chgelngmsg")))
					.isDisplayed()) {
				String temp1 = "Successfully Entered the M1 Signature";
				System.out.println(temp1);

			 	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				driver.findElement(By.xpath(prop.getProperty("chgelngbtn")))
						.click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(1500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.className("progressing")));
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				languagecheck = true;
			}
		}catch (NoSuchElementException e2) {
		
				String temp1 = "Successfully Entered the M1 Signature";
				System.out.println(temp1);
				System.out.println("Browser and contract language are same");
		}

		// ***************** Security Message Screen *****************

		try {
			if (driver.findElement(By.id("security_msg")).isDisplayed()) {
				driver.findElement(
						By.xpath(".//*[@id='loginintermediate_overlayer']/div/div/div/button[1]"))
						.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
						.className("progressing")));
				
				if(languagecheck==true){				
			    String language  = driver.getCurrentUrl();
			    String change = object.check(language);
			    driver.get(change);
			    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    	Thread.sleep(500);
				}
			}
		} catch (NoSuchElementException e3) {

//			try {
//				if (driver.findElement(By.cssSelector(".btn_primary2"))
//						.isDisplayed()) {
//
//					String temp1 = "Invalid MI/Not Entered the M1 Signature";
//					System.out.println(temp1);
//
//				}
//			} catch (NoSuchElementException z1) {

				System.out.println("Security screen is already enabled");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.className("content_menu_items")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				
				   if(languagecheck==true){				
				    String language = driver.getCurrentUrl();
				    String change = object.check(language);
				    driver.get(change);
				    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    	Thread.sleep(500);
					}
				
			  }
		 }
	//}


	/****************************************************************************************

         					Test Scenario - 1   Delete the Profile

 	*****************************************************************************************/

	@Test(priority=0,enabled=true)
	public void deletetheProfile() throws Exception {
	
//	WebDriverWait wait = new WebDriverWait(driver, 30);
//	Properties prop = new Properties();
//	FileInputStream input = new FileInputStream(filepath
//			+ "LoginData.properties");
//	prop.load(input);
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	Properties prop = new Properties();
	FileInputStream input = new FileInputStream(filepath
			+ "Object Repository/LoginData.properties");
	FileInputStream input1 = new FileInputStream(filepath
			+ "Test Data/TestData.properties");
	prop.load(input);
	prop.load(input1);
	
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));
		try {
			WebDriverWait wait1 = new WebDriverWait(driver,5);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By
					.className(prop.getProperty("overlayPreLogonPopup"))));
			WebElement we = driver.findElement(By.className(prop
					.getProperty("overlayPreLogonPopup")));
			we.findElement(By.cssSelector(prop.getProperty("Closebutton")))
					.click();

		}catch(Exception E1){

			System.out.println("No Pop-up is Displayed");
		}
			driver.findElement(By.cssSelector(".save_profile__text")).click();
			driver.findElement(By.className("input_alias_name")).sendKeys("DeleteProfile");
			
			loginPositiveflow();
			Thread.sleep(1000);
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.className("progressing")));
			
			driver.findElement(By.cssSelector(".meta_nav_logout")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			driver.findElement(By.xpath(".//*[@id='1394604485011']/div/div/div[1]/div/div[2]/div[2]/div/div/a[1]")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			driver.findElement(By.cssSelector("a.btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			boolean result = false;
			result = driver.findElement(By.cssSelector(".saved_details")).isDisplayed();
			System.out.println(result);
			Assert.assertEquals(true,result);
			
			driver.findElement(By.cssSelector(".wipe_saved")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(800);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			driver.findElement(By.xpath(".//*[@id='1399301539146']/div/div/div[3]/div/div[2]/div[2]/div/div/a[1]")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			boolean check = false;
			check = driver.findElement(By.id("clientNum")).isDisplayed();
			System.out.println(check);
			Assert.assertEquals(true,check);
}

		/****************************************************************************************

								Test Scenario - 2 Store the Profile

	 	*****************************************************************************************/
	
	@Test(priority=1,enabled=true)
	public void StoretheProfile() throws Exception {

//	WebDriverWait wait = new WebDriverWait(driver, 30);
//	Properties prop = new Properties();
//	FileInputStream input = new FileInputStream(filepath
//			+ "LoginData.properties");
//	prop.load(input);
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(filepath
				+ "Object Repository/LoginData.properties");
		FileInputStream input1 = new FileInputStream(filepath
				+ "Test Data/TestData.properties");
		prop.load(input);
		prop.load(input1);
	
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   driver.manage().window().maximize();
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));
		try {
			WebDriverWait wait1 = new WebDriverWait(driver,5);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By
					.className(prop.getProperty("overlayPreLogonPopup"))));
			WebElement we = driver.findElement(By.className(prop
					.getProperty("overlayPreLogonPopup")));
			we.findElement(By.cssSelector(prop.getProperty("Closebutton")))
					.click();

		}catch(Exception E1){

			System.out.println("No Pop-up is Displayed");
		}
			driver.findElement(By.cssSelector(".save_profile__text")).click();
			driver.findElement(By.className("input_alias_name")).sendKeys("Profile");
			
			loginPositiveflow();
			Thread.sleep(1000);
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.className("progressing")));
			
			driver.findElement(By.cssSelector(".meta_nav_logout")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			driver.findElement(By.xpath(".//*[@id='1394604485011']/div/div/div[1]/div/div[2]/div[2]/div/div/a[1]")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			driver.findElement(By.cssSelector("a.btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			boolean result = false;
			result = driver.findElement(By.cssSelector(".saved_details")).isDisplayed();
			System.out.println(result);
			Assert.assertEquals(true,result);
		
	 }
}