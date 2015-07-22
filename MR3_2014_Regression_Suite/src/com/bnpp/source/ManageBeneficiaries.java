package com.bnpp.source;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import reporting.Bnpp.JyperionListener;

import com.bnpp.data.DataWeb;

@Listeners(JyperionListener.class)
public class ManageBeneficiaries extends DataWeb{
	
	 LoginModule Obj = new LoginModule();
	 String BeneficiaryName = "ZBNPPUser";
	 String IBAN="BE03001800076284",IBAN1="BE61091017090217",IBAN2="BE74310014076507";
	
	 String Screenshotpath = System.getProperty("user.dir")+"Screenshots/Standing Orders/";
     boolean ans=false; 
     
   
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
		dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,true); 
		dc.setCapability(ChromeOptions.CAPABILITY,options);
	    driver = new ChromeDriver(dc);
        driver.manage().deleteAllCookies();
        driver.get(BaseUrl1);
        
	// For android Mobile Browser
    /* DesiredCapabilities caps = DesiredCapabilities.android();
       caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
       driver = new AndroidDriver(caps);
       driver.get(BaseUrl);*/
 }
	
	 
	/****************************************************************************************
	 
    									After Class

	 ****************************************************************************************/

	@AfterClass(alwaysRun=true)
	public void tear() throws Exception {
		driver.quit();
	}

	/****************************************************************************************

					Test Scenarios - 1  Create the New Black Listed Beneficiary 

	 ****************************************************************************************/	

   
	@Test(priority=0,enabled=true)
	public void createBlacklisted() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,30);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	
	    Obj.loginPositiveflow();
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   
		// locate the menu to hover over using its xpath
	                                                   
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	Thread.sleep(1500);

     	// Initiate mouse action using Actions class
   	
     	Actions builder = new Actions(driver);

     	// move the mouse to the earlier identified menu option
  		
     	builder.moveToElement(menu).build().perform();

     	// wait for max of 5 seconds before proceeding. This allows sub menu
			// appears properly before trying to click on it
     
       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

       // identify menu option from the resulting menu display and click

     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
     	menuOption.click();
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("beneficiary_list_screen")));
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    
	      
	    driver.findElement(By.cssSelector(".add_btn")).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Thread.sleep(300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form_inner")));	
        Thread.sleep(300);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

        WebElement beneficiary = driver.findElement(By.id("beneficiary"));
        String Text2 = beneficiary.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        Thread.sleep(400);
	
        beneficiary.findElement(By.id("ben_iban")).sendKeys(IBAN);
        beneficiary.findElement(By.id("benfName")).sendKeys(BeneficiaryName);
    	beneficiary.findElement(By.id("benfAlias")).clear();
	 	beneficiary.findElement(By.id("benfAlias")).sendKeys("AliasName");
	 	beneficiary.findElement(By.id("address")).sendKeys("Test");
	 	beneficiary.findElement(By.id("postalCode")).sendKeys("123456");
	 	beneficiary.findElement(By.id("cityname")).sendKeys("Testing");
		beneficiary.findElement(By.id("personalComment")).sendKeys("Automation Testing");

		
//      JavascriptExecutor jse = (JavascriptExecutor)driver;
//      jse.executeScript("window.scrollBy(0,800)","");
//      Thread.sleep(500);
//      beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
//	   	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//	   	Thread.sleep(1500);
//	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//	   	System.out.println(beneficiary.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim());
//	   	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//	   	jse.executeScript("window.scrollBy(0,800)");
//	   	Thread.sleep(500);
	   	
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,800)","");
        Thread.sleep(500);
        
        beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	    Thread.sleep(300);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details_inner")));	
	    Thread.sleep(300);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    System.out.println(beneficiary.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim());
	   	
	   	beneficiary.findElement(By.id("signature")).click();
	   	Thread.sleep(10000);
	   	
	   	beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	 	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   	Thread.sleep(300);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("acknowledge_page")));	
	    Thread.sleep(300);
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

	   	System.out.println(beneficiary.findElement(By.cssSelector(".resultfrom_info")).getText().trim());
	   	
	   	beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("beneficiary_list_screen")));
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
  
	    
	    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
	    for(int i=0;i<beneSize;i++){
		   String beneficiariesNames = beneficiary.findElements(By.cssSelector(".listiban_group")).get(i).getText().trim();
	       System.out.println(beneficiariesNames);
		   if(beneficiariesNames.equalsIgnoreCase("AliasName"))
		        {
		            ans=true;
		        }
		    }
	      Assert.assertEquals(ans,true);
	      System.out.println("Beneficiary Added Successfully");
	   }

	/****************************************************************************************

						Test Scenarios - 2  Create the New white Listed Beneficiary 

	 ****************************************************************************************/	

   
	@Test(priority=1,enabled=true)
	public void createWhitelisted() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,30);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	
//		Obj.loginPositiveflow();
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
     	Thread.sleep(1500);

     	// Initiate mouse action using Actions class
   	
     	Actions builder = new Actions(driver);

     	// move the mouse to the earlier identified menu option
  		
     	builder.moveToElement(menu).build().perform();

     	// wait for max of 5 seconds before proceeding. This allows sub menu
			// appears properly before trying to click on it

       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

       // identify menu option from the resulting menu display and click

     	WebElement menuOption = driver.findElement(By.xpath(Submenu));
     	menuOption.click();
      	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("beneficiary_list_screen")));
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    
	      
	    driver.findElement(By.cssSelector(".add_btn")).click();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        Thread.sleep(300);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form_inner")));	
        Thread.sleep(300);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

        WebElement beneficiary = driver.findElement(By.id("beneficiary"));
        String Text2 = beneficiary.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        Thread.sleep(500);
	
        beneficiary.findElement(By.id("ben_iban")).sendKeys(IBAN2);
        beneficiary.findElement(By.id("benfName")).sendKeys(BeneficiaryName+"Test");
    	beneficiary.findElement(By.id("benfAlias")).clear();
	 	beneficiary.findElement(By.id("benfAlias")).sendKeys("AliasTestName");
	 	beneficiary.findElement(By.id("address")).sendKeys("Test");
	 	beneficiary.findElement(By.id("postalCode")).sendKeys("123456");
	 	beneficiary.findElement(By.id("cityname")).sendKeys("Testing");
		beneficiary.findElement(By.id("personalComment")).sendKeys("Automation Testing");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,800)","");
        Thread.sleep(500);
        
        beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	    Thread.sleep(300);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details_inner")));	
	    Thread.sleep(300);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    System.out.println(beneficiary.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim());
	  
	  	beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	   	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	   	Thread.sleep(300);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("acknowledge_page")));	
	    Thread.sleep(300);
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

	   	System.out.println(beneficiary.findElement(By.cssSelector(".resultfrom_info")).getText().trim());
	   	
	   	beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("beneficiary_list_screen")));
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    
	    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
	    for(int i=0;i<beneSize;i++){
		   String beneficiariesNames = beneficiary.findElements(By.cssSelector(".listiban_group")).get(i).getText().trim();
	       System.out.println(beneficiariesNames);
		   if(beneficiariesNames.equalsIgnoreCase("AliasTestName"))
		        {
		            ans=true;
		        }
		    }
	      Assert.assertEquals(ans,true);
	      System.out.println("Beneficiary Added Successfully");
	   }
	

	/****************************************************************************************

							 Test Scenarios - 3  Modify the Beneficiary 

	 ****************************************************************************************/	

    @Test(priority=2,enabled=true)
	public void modify() throws Exception{
	
		WebDriverWait wait = new WebDriverWait(driver,30);
		Properties prop = new Properties();
		  FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);

	//	Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	// locate the menu to hover over using its xpath
		//*[@id="1430321253687"]/div/div/div/div[3]/nav/div/ul/li[2]/a
		WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
		menu.click();           
		Thread.sleep(1500);

	// Initiate mouse action using Actions class
	
		Actions builder = new Actions(driver);

   //  Move the mouse to the earlier identified menu option
		
		builder.moveToElement(menu).build().perform();

     // wait for max of 5 seconds before proceeding. This allows sub menu
		// appears properly before trying to click on it

		String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	// identify menu option from the resulting menu display and click

	 	WebElement menuOption = driver.findElement(By.xpath(Submenu));
     	menuOption.click();
      	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("beneficiary_list_screen")));
	    Thread.sleep(400);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    

	    WebElement beneficiary = driver.findElement(By.id("beneficiary"));
	    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
	    String AliasName,beneficiaryIBAN=null;
	    if(beneSize>0)
	    {  
	    	AliasName = beneficiary.findElements(By.cssSelector(".listiban_group")).get(0).getText().trim();
	        System.out.println(AliasName);
	        beneficiaryIBAN = beneficiary.findElements(By.cssSelector(".list_content_col2 span")).get(0).getText().trim();
	        System.out.println(beneficiaryIBAN);
	        
	        beneficiary.findElements(By.cssSelector(".listiban_group")).get(0).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
  	        Thread.sleep(1000);
  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
          
  	        driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[2]/div/div/div[2]/div/div[1]/button[1]")).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(300);
  	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form_inner")));	
  	        Thread.sleep(300);
  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
  	      
  	  
  	        beneficiary.findElement(By.id("benfName")).click();
  	        beneficiary.findElement(By.id("benfName")).sendKeys("BNPP");
  	        beneficiary.findElement(By.id("benfAlias")).clear();
  	        beneficiary.findElement(By.id("benfAlias")).sendKeys("AllName");
  	      
  	        beneficiary.findElement(By.cssSelector(".btn_arrow_right, .arrow-left")).click();
  	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("details_inner")));	
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	        
  	        // beneficiary.findElement(By.className("btn_default btn_primary f_right btn_sign")).click();
    	    driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[3]/div[2]/div/div[1]/button[1]")).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("acknowledge_inner")));	
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
  	 
  	        WebElement Test = beneficiary.findElement(By.cssSelector(".resultfrom_info"));
  	        String actualIBAN = Test.findElements(By.cssSelector(".highlight_text")).get(0).getText();
  	        String actualName = Test.findElements(By.cssSelector(".highlight_text")).get(1).getText();
  	        String actualAlias = Test.findElements(By.cssSelector(".highlight_text")).get(2).getText();
  	        System.out.println("Modified Properly");
     	    Assert.assertEquals(actualIBAN.trim(),beneficiaryIBAN);
		    Assert.assertNotEquals(actualName.trim(),AliasName);    
 		    Assert.assertNotEquals(actualAlias.trim(),AliasName);	
 		    driver.findElement(By.xpath("//*[@id='beneficiary']/div/div/div[2]/div[2]/div/div/div/button[1]")).click();
 		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	        Thread.sleep(1000);
 	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 	
 	        for(int i=0;i<beneSize;i++){
 			   String beneficiariesNames = beneficiary.findElements(By.cssSelector(".listiban_group")).get(i).getText().trim();
 		       System.out.println(beneficiariesNames);
 			   if(beneficiariesNames.equalsIgnoreCase("AllName"))
 			        {
 			            ans=true;
 			        }
 			    }
 		      Assert.assertEquals(ans,true);
 		      System.out.println("Beneficiary changed Successfully");
 		    }
 		 
         }

	 /****************************************************************************************

									Test Scenarios - 4  Delete the Beneficiary

	 ****************************************************************************************/	

    @Test(priority=3,enabled=true)
	public void delete() throws Exception{
    	WebDriverWait wait = new WebDriverWait(driver,30);
		Properties prop = new Properties();
		  FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);

		//Obj.loginPositiveflow();
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
		Thread.sleep(1500);

	// Initiate mouse action using Actions class
	
		Actions builder = new Actions(driver);

   //  Move the mouse to the earlier identified menu option
		
		builder.moveToElement(menu).build().perform();

     // wait for max of 5 seconds before proceeding. This allows sub menu
		// appears properly before trying to click on it

		String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	// identify menu option from the resulting menu display and click

		WebElement menuOption = driver.findElement(By.xpath(Submenu));
		menuOption.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    WebElement beneficiary = driver.findElement(By.id("beneficiary"));
	    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
	    String AliasName,beneficiaryIBAN,Result=null;ans=true;
	    if(beneSize>0)
	    {  
	    	AliasName = beneficiary.findElements(By.cssSelector(".listiban_group")).get(0).getText().trim();
	        System.out.println(AliasName);
	        beneficiaryIBAN = beneficiary.findElements(By.cssSelector(".list_content_col2 span")).get(0).getText().trim();
	        System.out.println(beneficiaryIBAN);
	        beneficiary.findElements(By.cssSelector(".listiban_group")).get(0).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
  	        Thread.sleep(1000);
  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
            driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[2]/div/div/div[2]/div/div[1]/button[2]")).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
  	        Thread.sleep(1000);
  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
  	        driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[3]/div[2]/div/div/button[1]")).click();
  	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	        Thread.sleep(3000);
 	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
 	        if(beneficiary.findElement(By.cssSelector(".alert_messages__inner p")).isDisplayed()){
		    	  
	          	Result=beneficiary.findElement(By.cssSelector(".alert_messages__inner p")).getText();
	         }
 	        for(int i=0;i<beneSize-1;i++){
 	        	String beneficiariesIBAN = beneficiary.findElements(By.cssSelector(".list_content_col2 span")).get(i).getText().trim();
 		        System.out.println(beneficiariesIBAN);
			    if(beneficiariesIBAN.equalsIgnoreCase(beneficiaryIBAN))
			        {
			            ans=false;
			        }
			    }
		      Assert.assertEquals(ans,true);
		      System.out.println(Result);
	      }
      }


	/****************************************************************************************

									Test Scenarios - 5  Search Filter

	 ****************************************************************************************/	

   
    @Test(priority=4,enabled=true)
	public void searchfilter() throws Exception{
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
		Properties prop = new Properties();
		  FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
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
		Thread.sleep(1500);

	// Initiate mouse action using Actions class
	
		Actions builder = new Actions(driver);

   //  Move the mouse to the earlier identified menu option
		
		builder.moveToElement(menu).build().perform();

     // wait for max of 5 seconds before proceeding. This allows sub menu
		// appears properly before trying to click on it

		String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	// identify menu option from the resulting menu display and click

		WebElement menuOption = driver.findElement(By.xpath(Submenu));
		menuOption.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1000);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    WebElement beneficiary = driver.findElement(By.id("beneficiary"));
	    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
	    if(beneSize>0)
	    {  
	    	driver.findElement(By.cssSelector(".default_input_field > input")).click();
	    	driver.findElement(By.cssSelector(".default_input_field > input")).clear();
	    	driver.findElement(By.cssSelector(".default_input_field > input")).sendKeys("BNPP");
	    	Thread.sleep(1500);
	    	WebElement search = driver.findElement(By.cssSelector(".search_counter"));
	    	String tempResult = search.findElements(By.cssSelector("*")).get(0).getText();
            int SearchResult = Integer.parseInt(tempResult.trim()); 
            System.out.println(SearchResult);
            if(SearchResult>0)
            {
            int searchcount = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
            System.out.println(searchcount);
            Assert.assertEquals(searchcount,SearchResult);
            System.out.println("Search Functionality is working fine");
            driver.findElement(By.cssSelector(".search_wipe")).click();
        	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    	    Thread.sleep(2000);
    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
            }   
            
        	driver.findElement(By.cssSelector(".show_filters")).click();
        	driver.findElement(By.cssSelector(".show_filters")).click();
        	Thread.sleep(1000);
        	driver.findElement(By.cssSelector(".show_filters")).click();
        	Thread.sleep(1500);
            for(int i=0;i<2;i++){
            
            	driver.findElements(By.cssSelector(".filter_values p")).get(i).click();
            	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        	    Thread.sleep(1000);
        	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
        	    WebElement Count = driver.findElements(By.cssSelector(".filter_option")).get(i);
            	int nos = Count.findElements(By.cssSelector(".filter_option_value")).size();
            	System.out.println("The value is "+nos);
               	for(int j=0;j<nos;j++){
               		if(j>0){
               			driver.findElements(By.cssSelector(".filter_values p")).get(i).click();
                    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                	    Thread.sleep(1000);
                	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
               		 }
            		Count.findElements(By.cssSelector(".filter_option_value")).get(j).click();
             		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            	    Thread.sleep(1000);
            	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
            		driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[2]/div/div[1]/form/div[2]/div[2]/div/div/button[1]")).click();
            		WebElement search1 = driver.findElement(By.cssSelector(".search_counter"));
        	    	String tempResult1 = search1.findElements(By.cssSelector("*")).get(0).getText();
                    int SearchResult1 = Integer.parseInt(tempResult1.trim()); 
                    System.out.println("The count of search is: "+SearchResult1);
                    if(SearchResult1>0)
                    {
                    int searchcount1 = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
                    System.out.println(searchcount1);
                    Assert.assertEquals(searchcount1,SearchResult1);
                    System.out.println("Filters are working fine");
                    Thread.sleep(1500);
                    driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[2]/div/div[1]/form/div[2]/div[2]/div/div/button[2]")).click();
                	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            	    Thread.sleep(1500);
            	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
                   }   
            	}
            }
         }
     }
	
		/****************************************************************************************

					  Test Scenarios - 6  Beneficiary Management to Transfers 

		 ****************************************************************************************/	


//    @Test(priority=5,enabled=true)
	public void whitelistedbmToSCT() throws Exception{

    	WebDriverWait wait = new WebDriverWait(driver,30);
    	Properties prop = new Properties();
    	  FileInputStream input = new FileInputStream(filepath
      			+ "Object Repository/TransactionData.properties");
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
    	Thread.sleep(1500);

    	// Initiate mouse action using Actions class

    	Actions builder = new Actions(driver);

    	// move the mouse to the earlier identified menu option

    	builder.moveToElement(menu).build().perform();

    	// wait for max of 5 seconds before proceeding. This allows sub menu
    	// appears properly before trying to click on it

    	String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

    	// identify menu option from the resulting menu display and click
    	
    	WebElement menuOption = driver.findElement(By.xpath(Submenu));
    	menuOption.click();
    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    	Thread.sleep(400);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("beneficiary_list_screen")));
    	Thread.sleep(400);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));


    	driver.findElement(By.cssSelector(".add_btn")).click();
    	driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    	Thread.sleep(300);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form_inner")));	
    	Thread.sleep(300);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

    	WebElement beneficiary = driver.findElement(By.id("beneficiary"));
    	String Text2 = beneficiary.findElement(By.cssSelector(".module_title")).getText();
    	System.out.println(Text2);
    	Thread.sleep(500);

    	beneficiary.findElement(By.id("ben_iban")).sendKeys(IBAN2);
    	beneficiary.findElement(By.id("benfName")).sendKeys(BeneficiaryName+"Test");
    	beneficiary.findElement(By.id("benfAlias")).clear();
    	beneficiary.findElement(By.id("benfAlias")).sendKeys("AliasTestName");
    	beneficiary.findElement(By.id("address")).sendKeys("Test");
    	beneficiary.findElement(By.id("postalCode")).sendKeys("123456");
    	beneficiary.findElement(By.id("cityname")).sendKeys("Testing");
    	beneficiary.findElement(By.id("personalComment")).sendKeys("Automation Testing");

    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,800)","");
    	Thread.sleep(500);

    	beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
    	driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    	Thread.sleep(300);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details_inner")));	
    	Thread.sleep(300);
	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		System.out.println(beneficiary.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim());

		beneficiary.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details")));	
		Thread.sleep(300);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

		System.out.println(beneficiary.findElement(By.cssSelector(".resultfrom_info")).getText().trim());

		beneficiary.findElements(By.cssSelector(".btn_secondary")).get(0).click();
		Thread.sleep(400);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sepa")));
		Thread.sleep(400);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		
		  WebElement sepa = driver.findElement(By.id("sepa"));
	        String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
		    System.out.println(Text1);
   
	        sepa.findElements(By.cssSelector(".form_details")).get(0).click();
	        Thread.sleep(1500);
	        driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
	        sepa.findElement(By.id("enterAmount")).sendKeys("3");
	        Thread.sleep(500);
	        
		    String ActualIBANold = driver.findElements(By.cssSelector(".iban-label")).get(1).getText().trim();
		    System.out.println(ActualIBANold);
		    String ActualIBAN = ActualIBANold.replaceAll("\\s","");
	        Thread.sleep(500);
	        Assert.assertEquals(IBAN2,ActualIBAN);
	   	       
		    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("details_inner")));
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    
	        sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("acknowledge_page")));
	        Thread.sleep(300);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	        
	        String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
		    Thread.sleep(500);
		    
		    System.out.println(result);
	    }
		

		/****************************************************************************************

								Test Scenarios - 7  Beneficiary to Transfers 

		 ****************************************************************************************/	

//       @Test(priority=6,enabled=true)
		public void bmTosct() throws Exception{
	    	
	    	WebDriverWait wait = new WebDriverWait(driver,40);
			Properties prop = new Properties();
			  FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/TransactionData.properties");
			prop.load(input);

//    		Obj.loginPositiveflow();
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
			Thread.sleep(1500);

		// Initiate mouse action using Actions class
		
			Actions builder = new Actions(driver);

	   //  Move the mouse to the earlier identified menu option
			
			builder.moveToElement(menu).build().perform();

	     // wait for max of 5 seconds before proceeding. This allows sub menu
			// appears properly before trying to click on it

			String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

		// identify menu option from the resulting menu display and click

			WebElement menuOption = driver.findElement(By.xpath(Submenu));
			menuOption.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1000);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		    WebElement beneficiary = driver.findElement(By.id("beneficiary"));
		    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
		    String AliasName,beneficiaryIBAN=null;
		 	if(beneSize>0)
		    {  
		    	AliasName = beneficiary.findElements(By.cssSelector(".listiban_group")).get(0).getText().trim();
		        System.out.println(AliasName);
		        beneficiaryIBAN = beneficiary.findElements(By.cssSelector(".list_content_col2 span")).get(0).getText().trim();
		        System.out.println(beneficiaryIBAN);
		        beneficiary.findElements(By.cssSelector(".listiban_group")).get(0).click();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  	        Thread.sleep(1000);
	  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	            driver.findElement(By.xpath(".//*[@id='beneficiary']/div/div/div[2]/div/div/div[2]/div/div[2]/button")).click();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  	        Thread.sleep(1000);
	  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	        
	  	        WebElement sepa = driver.findElement(By.id("sepa"));
		        String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
			    System.out.println(Text1);
  	   
		        sepa.findElements(By.cssSelector(".form_details")).get(0).click();
		        Thread.sleep(1500);
		        driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  	        Thread.sleep(1000);
	  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	        
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
		        sepa.findElement(By.id("enterAmount")).sendKeys("3");
		        Thread.sleep(1000);
		        
		        String ActualIBAN = driver.findElements(By.cssSelector(".iban-label")).get(1).getText().trim();
			    System.out.println(ActualIBAN);
//			    String ActualIBAN = ActualIBANold.replaceAll("\\s","");
//		        Thread.sleep(500);
		        Assert.assertEquals(beneficiaryIBAN,ActualIBAN);
		   	       
        
	  	        sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
			    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        Thread.sleep(300);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("details_inner")));
		        Thread.sleep(300);
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		        sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		        Thread.sleep(300);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("acknowledge_page")));
		        Thread.sleep(300);
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	      
		        String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
			    Thread.sleep(500);
			    System.out.println(result);

//	            driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
//	            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//	  	        Thread.sleep(300);
//	  	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account_transactions")));
//	  	        Thread.sleep(300);
//	  	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//	  	        
//	  	        if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
//	  	        	String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
//	  	        	Assert.assertEquals(PageHeader,"Transactions");
//	  	        	System.out.println(result);
//	  	        }
	  	        
		    }
		 	
       }
      
        /****************************************************************************************

    						   Test Scenarios - 8 Checking the favorite icon

    	 ****************************************************************************************/	

       	
           @Test(priority=7,enabled=true)
    		public void bmFavourite() throws Exception{
    	    	
    	    	WebDriverWait wait = new WebDriverWait(driver,40);
    			Properties prop = new Properties();
    			  FileInputStream input = new FileInputStream(filepath
    		    			+ "Object Repository/TransactionData.properties");
    			prop.load(input);
    			boolean isChecked = false;

//       		Obj.loginPositiveflow();
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
    			Thread.sleep(1500);

    		// Initiate mouse action using Actions class
    		
    			Actions builder = new Actions(driver);

    	   //  Move the mouse to the earlier identified menu option
    			
    			builder.moveToElement(menu).build().perform();

    	     // wait for max of 5 seconds before proceeding. This allows sub menu
    			// appears properly before trying to click on it

    			String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[3]/a";
    			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

    		// identify menu option from the resulting menu display and click

    			WebElement menuOption = driver.findElement(By.xpath(Submenu));
    			menuOption.click();
    			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    		    Thread.sleep(800);
    		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
    		    Thread.sleep(500);
    		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
    		    WebElement beneficiary = driver.findElement(By.id("beneficiary"));
    		    int beneSize = beneficiary.findElements(By.cssSelector(".listiban_group")).size();
    		    System.out.println(beneSize);
    		   	if(beneSize>0)
    		    {  
    		   		WebElement fav = beneficiary.findElements(By.cssSelector(".fav_icon")).get(0);
    			  	fav.click();
    		   		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        		    Thread.sleep(800);
        		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
        		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listiban_group")));
        		    
        			isChecked = beneficiary.findElements(By.cssSelector(".fav_icon")).get(0).isSelected();
    		 		System.out.println(isChecked);

    		 	 		if(isChecked==true){
    		 			beneficiary.findElements(By.cssSelector(".fav_icon")).get(0).click();
    		 			isChecked = beneficiary.findElements(By.cssSelector(".fav_icon")).get(0).isSelected();
    		 			System.out.println(isChecked);
    		 		  }
    		    }
         }
}
 