package com.bnpp.source;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import reporting.Bnpp.JyperionListener;

import com.bnpp.data.DataWeb;

@Listeners(JyperionListener.class)
public class StandingOrders extends DataWeb {
	
	 LoginModule Obj = new LoginModule();
	 String Screenshotpath = "/Users/mobilitytcs/Desktop/Automation/Selenium/Results/Screenshots/";
	 boolean id = false;
	 
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
driver.get(BaseUrl1);
//  For android Mobile Browser
/* DesiredCapabilities caps = DesiredCapabilities.android();
caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
driver = new AndroidDriver(caps);
driver.get(BaseUrl);*/


}


      /****************************************************************************************

                                          After Class

      *****************************************************************************************/

	@AfterClass(alwaysRun=true)
    public void tear() throws Exception {
    driver.quit();
    }

 	 
       /****************************************************************************************

                         Test Scenarios - 1  Create the Standing order with New

      *****************************************************************************************/	

	   
	  @Test(priority=0,enabled=true)
	   public void createStandingOrders1() throws Exception{
		
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 Properties prop = new Properties();
		 FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/StandingOrders.properties");
			prop.load(input);
			
		    Obj.loginPositiveflow();
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
		  // locate the menu to hover over using its xpath
		    
		    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
	      	menu.click();           
	      	Thread.sleep(2000);

	  	// Initiate mouse action using Actions class
	    	
	      	Actions builder = new Actions(driver);

	  	// move the mouse to the earlier identified menu option
	   		
	      	builder.moveToElement(menu).build().perform();

	 	// wait for max of 5 seconds before proceeding. This allows sub menu
	 			// appears properly before trying to click on it

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[2]/a";
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	      	menuOption.click();
	       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	Thread.sleep(2000);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
	   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
  	
	   	    WebElement sepaSTO = driver.findElement(By.id("standorder"));
    	    String Text1 = sepaSTO.findElement(By.cssSelector(".module_title")).getText();
		    System.out.println(Text1);
		   
		    sepaSTO.findElement(By.cssSelector(".add_btn")).click();
  		    Thread.sleep(1500);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("enterdetails")));  
		    Thread.sleep(500);
	   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
  		  
	   		sepaSTO.findElements(By.cssSelector(".eb_tab_inner span")).get(1).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(500);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.id("aliasName")).click();
		    driver.findElement(By.id("aliasName")).sendKeys("test");
		    
		    driver.findElement(By.id("unknownIban")).click();
		    driver.findElement(By.id("unknownIban")).sendKeys("BE49001800007071");
		    boolean errormsg=false;
		try{
			
		    WebElement error = sepaSTO.findElements(By.cssSelector(".eb_tab_content")).get(1);
		    errormsg = error.findElement(By.cssSelector(".alert_messages__inner")).isDisplayed();
		    if(errormsg==true){
		      System.out.println("This Beneficiary is already added");
		      }
		    }
		    catch(Exception e){
		          
		    	driver.findElement(By.cssSelector(".save_profile__text")).click();
		    	Thread.sleep(1000);
		    }
	       
	       driver.findElement(By.id("enterAmount")).sendKeys("5");
	   	   Thread.sleep(600);
	   		
	   	   WebElement FirstExecdate = sepaSTO.findElements(By.className("inner-component")).get(2);
	   	   FirstExecdate.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       
	       WebElement FirstExecdate1 = FirstExecdate.findElement(By.cssSelector(".selectize-dropdown-content"));
	       FirstExecdate1.findElements(By.cssSelector("*")).get(0).click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
 	   
           driver.findElement(By.cssSelector(".date_picker input")).click();
           driver.findElement(By.cssSelector(".date_picker input")).sendKeys("11082015");
           
	   	   WebElement Frequency = sepaSTO.findElements(By.className("inner-component")).get(4);
	       Frequency.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       
	       WebElement Frequency1 = Frequency.findElement(By.cssSelector(".selectize-dropdown-content"));
	       Frequency1.findElements(By.cssSelector("*")).get(1).click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
 	       
 	   	   WebElement Communication = sepaSTO.findElements(By.className("inner-component")).get(6);
 	       Communication.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       
	       JavascriptExecutor jse = (JavascriptExecutor)driver;
	       jse.executeScript("window.scrollBy(0,800)","");
	       Thread.sleep(500);
	        
	       WebElement Communication1 = Communication.findElement(By.cssSelector(".selectize-dropdown-content"));
	       Communication1.findElements(By.cssSelector("*")).get(2).click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
 	       driver.findElement(By.cssSelector(".default_text_area textarea")).clear();
 	       driver.findElement(By.cssSelector(".default_text_area textarea")).sendKeys("Automation Testing");
		   Thread.sleep(500);
		   
		   driver.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
		   Thread.sleep(500);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       
	       if(errormsg==false){
			    System.out.println("Please Enter M2 Signature");   	
		    	Thread.sleep(10000);
		     }
	                                     
	       driver.findElement(By.xpath(".//*[@id='standorder']/div/div[2]/div/div[3]/form/div[2]/div/div[1]/button[1]/span")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));  
		   Thread.sleep(500);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       String Result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText();
	       System.out.println(Result);
	  }
	 
	      /****************************************************************************************

       				Test Scenarios - 2  Create the Standing order with known beneficiary

	       *****************************************************************************************/	


	   @Test(priority=1,enabled=true)
	   public void createStandingOrders2() throws Exception{

		   WebDriverWait wait = new WebDriverWait(driver,30);
		   Properties prop = new Properties();
		   FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/StandingOrders.properties");
		   prop.load(input);

//		   Obj.loginPositiveflow();
		   driver.findElement(By.cssSelector(".site_logotype")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   Thread.sleep(1000);
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		   // locate the menu to hover over using its xpath

		   WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
		   menu.click();           
		   Thread.sleep(2000);

		   // Initiate mouse action using Actions class

		   Actions builder = new Actions(driver);

		   // move the mouse to the earlier identified menu option

		   builder.moveToElement(menu).build().perform();

		   // wait for max of 5 seconds before proceeding. This allows sub menu
		   // appears properly before trying to click on it

		   String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[2]/a";
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

		   // identify menu option from the resulting menu display and click
		   
		   WebElement menuOption = driver.findElement(By.xpath(Submenu));
		   menuOption.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(2000);
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

		   WebElement sepaSTO = driver.findElement(By.id("standorder"));
		   String Text1 = sepaSTO.findElement(By.cssSelector(".module_title")).getText();
		   System.out.println(Text1);

		   sepaSTO.findElement(By.cssSelector(".add_btn")).click();
		   Thread.sleep(1500);
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("enterdetails")));  
		   Thread.sleep(500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

		   WebElement toAc = sepaSTO.findElements(By.className("inner-component")).get(1);
		   toAc.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		   toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		   driver.findElement(By.id("enterAmount")).sendKeys("7");
		   Thread.sleep(600);

		   WebElement FirstExecdate = sepaSTO.findElements(By.className("inner-component")).get(2);
		   FirstExecdate.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

		   WebElement FirstExecdate1 = FirstExecdate.findElement(By.cssSelector(".selectize-dropdown-content"));
		   FirstExecdate1.findElements(By.cssSelector("*")).get(0).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		   driver.findElement(By.cssSelector(".date_picker input")).click();
		   driver.findElement(By.cssSelector(".date_picker input")).sendKeys("18092015");

		   WebElement Frequency = sepaSTO.findElements(By.className("inner-component")).get(4);
		   Frequency.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

		   WebElement Frequency1 = Frequency.findElement(By.cssSelector(".selectize-dropdown-content"));
		   Frequency1.findElements(By.cssSelector("*")).get(1).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		   driver.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
		   Thread.sleep(500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		  	 
		   try{
				 id = sepaSTO.findElement(By.className("sign_input")).isDisplayed();
				 sepaSTO.findElement(By.className("sign_input")).click();
				 Thread.sleep(10000);         
				 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(0).click();			 		    			
	   		  }
		   catch(NoSuchElementException e){
				          
				 System.out.println("No foreign benediciaries");
				 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(1).click();
			 }
			 
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(800);
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));  
		   Thread.sleep(500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   String Result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText();
		   System.out.println(Result);
}

	   	 	   
     /****************************************************************************************

     				Test Scenarios - 3  Create the Standing order - last date of month

      *****************************************************************************************/		 
	
	  @Test(priority=2,enabled=true)
	   public void createStandingOrders3() throws Exception{
		
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 Properties prop = new Properties();
		 FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/StandingOrders.properties");
			prop.load(input);
			
//			Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
		  // locate the menu to hover over using its xpath
		    
		    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
	      	menu.click();           
	      	Thread.sleep(2000);

	  	// Initiate mouse action using Actions class
	    	
	      	Actions builder = new Actions(driver);

	  	// move the mouse to the earlier identified menu option
	   		
	      	builder.moveToElement(menu).build().perform();

	 	// wait for max of 5 seconds before proceeding. This allows sub menu
	 			// appears properly before trying to click on it

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[2]/a";
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	      	menuOption.click();
	       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	Thread.sleep(2000);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
	   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	
	   	    WebElement sepaSTO = driver.findElement(By.id("standorder"));
	   	    String Text1 = sepaSTO.findElement(By.cssSelector(".module_title")).getText();
		    System.out.println(Text1);
		   
		    sepaSTO.findElement(By.cssSelector(".add_btn")).click();
		    Thread.sleep(1500);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("enterdetails")));  
		    Thread.sleep(500);
	   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		  
	   	    WebElement toAc = sepaSTO.findElements(By.className("inner-component")).get(1);
		    toAc.click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	   
	        toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	       
	        driver.findElement(By.id("enterAmount")).sendKeys("10");
	   	    Thread.sleep(600);
	   		
	   	    WebElement FirstExecdate = sepaSTO.findElements(By.className("inner-component")).get(2);
	   	    FirstExecdate.click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       
	        WebElement FirstExecdate1 = FirstExecdate.findElement(By.cssSelector(".selectize-dropdown-content"));
	        FirstExecdate1.findElements(By.cssSelector("*")).get(1).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	   	    WebElement LastSpecificdate = sepaSTO.findElements(By.className("inner-component")).get(3);
	   	    LastSpecificdate.click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	        
	        WebElement LastSpecificdate1 = LastSpecificdate.findElement(By.cssSelector(".selectize-dropdown-content"));
	        LastSpecificdate1.findElements(By.cssSelector("*")).get(1).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
          
	   	    WebElement Frequency = sepaSTO.findElements(By.className("inner-component")).get(4);
	        Frequency.click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	        
	        WebElement Frequency1 = Frequency.findElement(By.cssSelector(".selectize-dropdown-content"));
	        Frequency1.findElements(By.cssSelector("*")).get(1).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	       
	   	    WebElement Communication = sepaSTO.findElements(By.className("inner-component")).get(6);
	        Communication.click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       
	        WebElement Communication1 = Communication.findElement(By.cssSelector(".selectize-dropdown-content"));
	        Communication1.findElements(By.cssSelector("*")).get(2).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	        driver.findElement(By.cssSelector(".default_text_area textarea")).clear();
	        driver.findElement(By.cssSelector(".default_text_area textarea")).sendKeys("Automation Testing");
		    Thread.sleep(500);
		    
		    driver.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
		    Thread.sleep(500);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	 
		   try{
				 id = sepaSTO.findElement(By.className("sign_input")).isDisplayed();
				 sepaSTO.findElement(By.className("sign_input")).click();
				 Thread.sleep(10000);         
				 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(0).click();			 		    			
	   		  }
		   catch(NoSuchElementException e){
				          
				 System.out.println("No foreign benediciaries");
				 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(1).click();
			 }
	                        
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(800);
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));  
		   Thread.sleep(500);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       String Result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText();
	       System.out.println(Result);
	 }
	 
		 
	     /****************************************************************************************

	     				Test Scenarios - 4  Modify the Standing order

	      *****************************************************************************************/		   
	 
	  @Test(priority=3,enabled=true)
	   public void modifyStandingOrders() throws Exception{
		
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 Properties prop = new Properties();
		 FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/StandingOrders.properties");
			prop.load(input);
			

			//	Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
		  // locate the menu to hover over using its xpath
		    
		    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
	      	menu.click();           
	      	Thread.sleep(2000);

	  	// Initiate mouse action using Actions class
	    	
	      	Actions builder = new Actions(driver);

	  	// move the mouse to the earlier identified menu option
	   		
	      	builder.moveToElement(menu).build().perform();

	 	// wait for max of 5 seconds before proceeding. This allows sub menu
	 			// appears properly before trying to click on it

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[2]/a";
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	      	menuOption.click();
	       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	Thread.sleep(2000);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
	   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	
	   	    WebElement sepaSTO = driver.findElement(By.id("standorder"));
  	        String Text1 = sepaSTO.findElement(By.cssSelector(".module_title")).getText();
		    System.out.println(Text1);
		    
		    WebElement list = sepaSTO.findElement(By.className("module_tab_content"));
		    int STOnos = list.findElements(By.cssSelector(".list_item__header")).size();
	        
		    if(STOnos>0){
		    	
		    	 list.findElements(By.cssSelector(".list_item__header")).get(0).click();
		    	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			     Thread.sleep(1000);
			     wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
				 Thread.sleep(500);
			     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    	 
				 String Balance= sepaSTO.findElements(By.cssSelector(".listitem_details p")).get(5).getText();
	    		 System.out.println(Balance);
	    		 Balance = Balance.substring(0,Balance.length()-7);
	    		 int Balance1 = Integer.parseInt(Balance);
	        	 Balance1 = Balance1+3;
	             Balance = String.valueOf(Balance1);
			          	     
			     
			     driver.findElement(By.xpath(".//*[@id='standorder']/div/div/div/div/div[3]/div[3]/div/button[1]")).click();
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			     Thread.sleep(800);
			     wait.until(ExpectedConditions.presenceOfElementLocated(By.className("form_inner")));  
				 Thread.sleep(500);
			     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			     
			     driver.findElement(By.id("enterAmount")).clear();
			     driver.findElement(By.id("enterAmount")).sendKeys(Balance);
			   	 Thread.sleep(600);
			   	 
			   	 driver.findElement(By.xpath("//*[@id='standorder']/div/div/div/div/div/div[3]/form/div[1]/div[12]/div/button[1]/span")).click();
			   	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			     Thread.sleep(800);
			     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			     Thread.sleep(500);
			     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("details_inner")));
			     
				   try{
						 id = sepaSTO.findElement(By.className("sign_input")).isDisplayed();
						 sepaSTO.findElement(By.className("sign_input")).click();
						 Thread.sleep(10000);         
						 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(0).click();			 		    			
			   		  }
				   catch(NoSuchElementException e){
						          
						 System.out.println("No foreign benediciaries");
						 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(1).click();
					 }
			              
		                        
				 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    		 Thread.sleep(1000);
	    		 wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));  
	    		 Thread.sleep(500);
	    		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    		 String Result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText();
	    		 System.out.println(Result);
	    		 Thread.sleep(5000);
			     
	       }
	  }   		

		 
	     /****************************************************************************************

	     				Test Scenarios - 5  Delete the Standing order 

	      *****************************************************************************************/	
	   
	@Test(priority=4,enabled=true)
    public void deleteStandingOrders() throws Exception{
	
	 WebDriverWait wait = new WebDriverWait(driver,30);
	 Properties prop = new Properties();
	 FileInputStream input = new FileInputStream(filepath
 			+ "Object Repository/StandingOrders.properties");
		prop.load(input);
		
		Obj.loginPositiveflow();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".site_logotype")));
//		driver.findElement(By.cssSelector(".site_logotype")).click();
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	  // locate the menu to hover over using its xpath
	    
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
	    menu.click();           
	    Thread.sleep(2000);

	// Initiate mouse action using Actions class
 	
	    Actions builder = new Actions(driver);

	// move the mouse to the earlier identified menu option
		
	    builder.moveToElement(menu).build().perform();

	// wait for max of 5 seconds before proceeding. This allows sub menu
			// appears properly before trying to click on it

	    String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[2]/a";
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 
     
	// identify menu option from the resulting menu display and click

   		WebElement menuOption = driver.findElement(By.xpath(Submenu));
   		menuOption.click();
    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    	Thread.sleep(1500);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
	    Thread.sleep(500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

	    WebElement sepaSTO = driver.findElement(By.id("standorder"));
	    String Text1 = sepaSTO.findElement(By.cssSelector(".module_title")).getText();
	    System.out.println(Text1);
	    
	    WebElement list = sepaSTO.findElement(By.className("module_tab_content"));
	    int STOnos = list.findElements(By.cssSelector(".list_item__header")).size();
     
	    if(STOnos>0){
	    	
	    	 list.findElements(By.cssSelector(".list_item__header")).get(0).click();
	    	 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		     Thread.sleep(800);
		     wait.until(ExpectedConditions.presenceOfElementLocated(By.className("border_top")));  
			 Thread.sleep(500);
		     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    	 
		                               
		     driver.findElement(By.xpath(".//*[@id='standorder']/div/div/div/div/div[4]/div/div/button[2]")).click();
//		     driver.findElement(By.xpath(".//*[@id='standorder']/div/div/div/div/div[3]/div[3]/div/button[2]")).click();
		     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		     Thread.sleep(800);
		     wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
			 Thread.sleep(500);
		     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		     
		     try{
		    	 boolean M2= false;
		    	 M2 = sepaSTO.findElement(By.className("signature_challenge")).isDisplayed();
		    	 if(M2==true){
		    		 Thread.sleep(10000);
		    		 driver.findElement(By.xpath(".//*[@id='standorder']/div/div/div/div/div[4]/div/div[2]/div[2]/form/div[2]/div/div/div/button[1]/span")).click();
		    	 }
		     }catch(Exception e){
		    	   
		    	     System.out.println("No need of M2");
		    	     driver.findElement(By.xpath("//*[@id='standorder']/div/div/div/div/div[4]/div/div/div[3]/div/div/button[1]")).click();
		     }
		     
		     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		     Thread.sleep(800);
		     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".list_item__header")));  
			 Thread.sleep(500);
		     wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		     
            if(driver.findElement(By.cssSelector(".alert_messages__inner")).isDisplayed()){
            	
               System.out.println(driver.findElement(By.cssSelector(".alert_messages__inner")).getText());
            }		     
	    }
	}   		
	
	 
        /****************************************************************************************

                             Test Scenarios - 6  Print the Standing order

        *****************************************************************************************/	

	   
//	  @Test(priority=5,enabled=true)
	   public void printcreatedSTO() throws Exception{
		   WebDriverWait wait = new WebDriverWait(driver,30);
		   Properties prop = new Properties();
			 FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/StandingOrders.properties");
			prop.load(input);
		  for(int i=0;i<2;i++){
			
			if(i==0){
		           createStandingOrders3();
			    }
			  else{
				  modifyStandingOrders();
			    }
			
		   WebElement sepaSTO = driver.findElement(By.id("standorder"));
		   WebElement printpdf = driver.findElement(By.cssSelector(".action_icons"));
		   
		   printpdf.findElements(By.cssSelector("*")).get(1).click();
		   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		   Thread.sleep(1500);
		   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(scrFile, new File(Screenshotpath+"Standing_Orders_PDF_"+yourDate+".jpg"));
		      
		   printpdf.findElements(By.cssSelector("*")).get(0).click();
	       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	       Thread.sleep(2500);
	       driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		   FileUtils.copyFile(scrFile, new File(Screenshotpath+"Standing_Orders_Print_"+yourDate+".jpg"));
	
		   Robot r = new Robot();
		   r.keyPress(KeyEvent.VK_ESCAPE);
		   r.keyRelease(KeyEvent.VK_ESCAPE);  
		   Thread.sleep(1000);		 
		   
		   r.keyPress(KeyEvent.VK_ESCAPE);
		   r.keyRelease(KeyEvent.VK_ESCAPE);  
		   Thread.sleep(1000);
		   
		   sepaSTO.findElements(By.cssSelector(".btn_primary")).get(0).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(800);		   
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

		   WebElement list = sepaSTO.findElement(By.className("module_tab_content")); 
		   list.findElements(By.cssSelector(".list_item__header")).get(0).click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(800);
		   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("border_top")));  
		   Thread.sleep(500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				  	 
		   driver.findElement(By.cssSelector(".action_icons__global span")).click();
		   Thread.sleep(1500);
		   FileUtils.copyFile(scrFile, new File(Screenshotpath+"Standing_Orders_PDF1_"+yourDate+".jpg"));
      }
   }
}
