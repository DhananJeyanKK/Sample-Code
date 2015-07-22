package com.bnpp.source;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class ContactDataUpdate extends DataWeb {


	 LoginModule Obj = new LoginModule();
	 String Screenshotpath = "/Users/mobilitytcs/Desktop/Automation/Selenium/Results/Screenshots/";
	 Properties prop = new Properties();
	 
	    /****************************************************************************************

										Before Class

	     ****************************************************************************************/

	 @BeforeClass
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
    public void tear() throws Exception {
    driver.quit();
   }

	   /****************************************************************************************

    					Test Scenarios - 1 Contact update - email addition

	    *****************************************************************************************/	

		@Test(priority=0,enabled=true)
	    public void CDUemail() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			
		    Obj.loginPositiveflow();
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   
			// locate the menu to hover over using its xpath
            
		    WebElement menu = driver.findElement(By.className("meta_nav_user")).findElement(By.id("username"));
		 	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a")));
	       	menu.click();    
	    	Thread.sleep(1500);

	     	// Initiate mouse action using Actions class
	   	
	     	Actions builder = new Actions(driver);

	     	// move the mouse to the earlier identified menu option
	  		
	     	builder.moveToElement(menu).build().perform();

	     	// wait for max of 5 seconds before proceeding. This allows sub menu
				// appears properly before trying to click on it

	       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a";
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	       // identify menu option from the resulting menu display and click

	     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	     	menuOption.click();
	     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	    Thread.sleep(400);
	   	    
	   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact_data_list")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.xpath(".//*[@id='addEmail']/span[1]")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cdu_mobphon")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    int random = new Random().nextInt();
		    String randomno = Integer.toString(Math.abs(random));
		    
		    driver.findElement(By.name("cdu_mobphon")).sendKeys("testrest"+randomno+"@gmail.com");
		    driver.findElement(By.xpath("//*[@id='cm_form']/div[2]/div/div[1]/label/span")).click();
		    Thread.sleep(500);
		   
            driver.findElement(By.xpath(".//*[@id='contactdata_container']/div/div[3]/div/form/div/div[4]/div/button[1]")).click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert_messages__inner p")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    Thread.sleep(400);
		    String alertmsg = driver.findElement(By.cssSelector(".alert_messages__inner p")).getText();
		    System.out.println(alertmsg);
		}
		
		@Test(priority=1,enabled=true)
	    public void CDUphone() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
//			Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   
			// locate the menu to hover over using its xpath
            
		    WebElement menu = driver.findElement(By.className("meta_nav_user")).findElement(By.id("username"));
	     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a")));
	     	menu.click();                                                    
	     	Thread.sleep(1500);

	     	// Initiate mouse action using Actions class
	   	
	     	Actions builder = new Actions(driver);

	     	// move the mouse to the earlier identified menu option
	  		
	     	builder.moveToElement(menu).build().perform();

	     	// wait for max of 5 seconds before proceeding. This allows sub menu
				// appears properly before trying to click on it

	       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a";
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	       // identify menu option from the resulting menu display and click

	     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	     	menuOption.click();
	     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	    Thread.sleep(400);
	   	    
	   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact_data_list")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.xpath(".//*[@id='addMobile']/span[2]")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cdu_mobphon")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    int random = new Random().nextInt();
		    String sample = Integer.toString(Math.abs(random));
		    String randomno = sample.substring(0,6);
		    
		    driver.findElement(By.name("cdu_mobphon")).clear();
		    Thread.sleep(300);
		    driver.findElement(By.name("cdu_mobphon")).sendKeys("0465"+randomno);
		                                 
		    driver.findElement(By.xpath("//*[@id='cm_form']/div[2]/div/div[1]/label/span")).click();
		    Thread.sleep(300);
	
            driver.findElement(By.xpath("//*[@id='next_button']/span")).click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details_inner")));
		    Thread.sleep(200);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    Thread.sleep(300);
		    		    
		    driver.findElement(By.id("signature")).click();
		    Thread.sleep(12000);
		    
		    driver.findElement(By.id("confirm_button")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert_messages__inner p")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    Thread.sleep(400);
		    
		    String alertmsg = driver.findElement(By.cssSelector(".alert_messages__inner p")).getText();
		    System.out.println(alertmsg);
		
		}
		
		
		@Test(priority=2,enabled=true)
	    public void CDUmailvalidation() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
//			Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   
			// locate the menu to hover over using its xpath
            
		    WebElement menu = driver.findElement(By.className("meta_nav_user")).findElement(By.id("username"));
	     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a")));
	     	menu.click();           
	     	Thread.sleep(1500);

	     	// Initiate mouse action using Actions class
	   	
	     	Actions builder = new Actions(driver);

	     	// move the mouse to the earlier identified menu option
	  		
	     	builder.moveToElement(menu).build().perform();

	     	// wait for max of 5 seconds before proceeding. This allows sub menu
				// appears properly before trying to click on it

	       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a";
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	       // identify menu option from the resulting menu display and click

	     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	     	menuOption.click();
	     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	    Thread.sleep(400);
	   	    
	   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact_data_list")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    
		    String firstmail = driver.findElements(By.cssSelector(".details_container h3")).get(0).getText().trim();
		    System.out.println(firstmail);
		    driver.findElement(By.xpath(".//*[@id='addEmail']/span[1]")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cdu_mobphon")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				    
		    driver.findElement(By.name("cdu_mobphon")).clear();
		    Thread.sleep(300);
		    driver.findElement(By.name("cdu_mobphon")).sendKeys(firstmail);
		   	       		    
		    driver.findElement(By.xpath("//*[@id='cm_form']/div[2]/div/div[1]/label/span")).click();
		    Thread.sleep(500);
		   
            driver.findElement(By.xpath(".//*[@id='contactdata_container']/div/div[3]/div/form/div/div[4]/div/button[1]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert_messages__inner p")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    Thread.sleep(400);
		   
		    String alertmsg = driver.findElement(By.cssSelector(".alert_messages__inner p")).getText();
		    System.out.println(alertmsg);
		
		}
		@Test(priority=3,enabled=true)
	    public void CDUphonevalidation() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
//			Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   
			// locate the menu to hover over using its xpath
            
		    WebElement menu = driver.findElement(By.className("meta_nav_user")).findElement(By.id("username"));
	     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a")));
	     	menu.click();           
	     	Thread.sleep(1500);

	     	// Initiate mouse action using Actions class
	   	
	     	Actions builder = new Actions(driver);

	     	// move the mouse to the earlier identified menu option
	  		
	     	builder.moveToElement(menu).build().perform();

	     	// wait for max of 5 seconds before proceeding. This allows sub menu
				// appears properly before trying to click on it

	       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a";
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	       // identify menu option from the resulting menu display and click

	     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	     	menuOption.click();
	     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	    Thread.sleep(400);
	   	    
	   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact_data_list")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    WebElement phone = driver.findElement(By.xpath(".//*[@id='contactdata_container']/div/div/div[3]/div/div[6]"));
		    String firstphone = phone.findElements(By.cssSelector(".details_container h3")).get(0).getText().trim();
		    System.out.println(firstphone);
		    
		    driver.findElement(By.xpath(".//*[@id='addMobile']/span[2]")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cdu_mobphon")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.name("cdu_mobphon")).clear();
		    Thread.sleep(300);
		    driver.findElement(By.name("cdu_mobphon")).sendKeys(firstphone);
		                                 
		    driver.findElement(By.xpath("//*[@id='cm_form']/div[2]/div/div[1]/label/span")).click();
		    Thread.sleep(300);
	
            driver.findElement(By.xpath("//*[@id='next_button']/span")).click();
            
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details_inner")));
//		    Thread.sleep(200);
//		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//		    Thread.sleep(300);
//		    		    
//		    driver.findElement(By.id("signature")).click();
//		    Thread.sleep(12000);
//		    
//		    driver.findElement(By.id("confirm_button")).click();
            
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert_messages__inner p")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    Thread.sleep(400);
		    
		    String alertmsg = driver.findElement(By.cssSelector(".alert_messages__inner p")).getText();
		    System.out.println(alertmsg);
		
		}
		
		
		@Test(priority=4,enabled=true)
	    public void invalidemailvalidation() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
//			Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   
			// locate the menu to hover over using its xpath
            
		    WebElement menu = driver.findElement(By.className("meta_nav_user")).findElement(By.id("username"));
	     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a")));
	     	menu.click();           
	     	Thread.sleep(1500);

	     	// Initiate mouse action using Actions class
	   	
	     	Actions builder = new Actions(driver);

	     	// move the mouse to the earlier identified menu option
	  		
	     	builder.moveToElement(menu).build().perform();

	     	// wait for max of 5 seconds before proceeding. This allows sub menu
				// appears properly before trying to click on it

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a";
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	       // identify menu option from the resulting menu display and click

	     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	     	menuOption.click();
	     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	    Thread.sleep(400);
	   	    
	   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact_data_list")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.xpath(".//*[@id='addEmail']/span[1]")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cdu_mobphon")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.name("cdu_mobphon")).clear();
		    Thread.sleep(300);
		    driver.findElement(By.name("cdu_mobphon")).sendKeys("gdshdsfhjd.gmail.com");
		                                 
		    driver.findElement(By.xpath(".//*[@id='contactdata_container']/div/div[3]/div/form/div/div[4]/div/button[1]")).click();
		    Thread.sleep(1000);
		
		    String error1 = driver.findElements(By.cssSelector(".field_error_message")).get(1).getText();
		    boolean result1 = false; 
		    result1 = error1.contains("valid");		
		    Assert.assertEquals(result1,true);
		    
		    String error2 = driver.findElements(By.cssSelector(".field_error_message")).get(2).getText();
		    boolean result2 = false; 
		    result2 = error2.contains("commercial");		
		    Assert.assertEquals(result2,true);
		    
     }
		
		@Test(priority=5,enabled=true)
	    public void invalidphonevalidation() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
//			Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   
			// locate the menu to hover over using its xpath
            
		    WebElement menu = driver.findElement(By.className("meta_nav_user")).findElement(By.id("username"));
	     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a")));
	     	menu.click();           
	     	Thread.sleep(1500);

	     	// Initiate mouse action using Actions class
	   	
	     	Actions builder = new Actions(driver);

	     	// move the mouse to the earlier identified menu option
	  		
	     	builder.moveToElement(menu).build().perform();

	     	// wait for max of 5 seconds before proceeding. This allows sub menu
				// appears properly before trying to click on it

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[4]/div/div/div/ul/li[1]/a";
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	       // identify menu option from the resulting menu display and click

	     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	     	menuOption.click();
	     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	    Thread.sleep(400);
	   	    
	   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact_data_list")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.xpath(".//*[@id='addMobile']/span[2]")).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cdu_mobphon")));
		    Thread.sleep(400);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    driver.findElement(By.name("cdu_mobphon")).clear();
		    Thread.sleep(300);
		    driver.findElement(By.name("cdu_mobphon")).sendKeys("gdshdsfhjd.gmail.com");
		                                 
		    driver.findElement(By.xpath("//*[@id='next_button']/span")).click();
		    Thread.sleep(1000);
		    
		    String error1 = driver.findElements(By.cssSelector(".field_error_message")).get(1).getText();
		    boolean result1 = false; 
		    result1 = error1.contains("valid");		
		    Assert.assertEquals(result1,true);
		    
		    String error2 = driver.findElements(By.cssSelector(".field_error_message")).get(2).getText();
		    boolean result2 = false; 
		    result2 = error2.contains("commercial");		
		    Assert.assertEquals(result2,true);
		    
     }
}
