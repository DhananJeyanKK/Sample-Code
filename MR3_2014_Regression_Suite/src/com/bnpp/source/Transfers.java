package com.bnpp.source;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import org.testng.annotations.Test;

import com.bnpp.data.DataWeb;

public class Transfers extends DataWeb{
	
	     LoginModule Obj = new LoginModule();
//	     String Screenshotpath1 = System.getProperty("user.dir")+"/src/Screen Shots/Transfers/";
	     
         /****************************************************************************************
    
                                           Before Class

          *****************************************************************************************/
	
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
 	    
		//  For android Mobile Browser
	    /* DesiredCapabilities caps = DesiredCapabilities.android();
	       caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	       driver = new AndroidDriver(caps);
	       driver.get(BaseUrl);*/
	       driver.get(BaseUrl);
	    }
	     

	      /****************************************************************************************
    
                                            After Class

	       *****************************************************************************************/
	
	        @AfterClass(alwaysRun=true)
	        public static void tear() throws Exception {
			driver.quit();
	        }
		 

		    /****************************************************************************************

	                                  Test Scenarios - 1  No Communication with New

	        *****************************************************************************************/	

	    @Test(priority=0,enabled=true)
		public void createNoCommunicationNew() throws Exception {
		 
		    WebDriverWait wait = new WebDriverWait(driver,40);	 
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

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	      	menuOption.click();
	       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	Thread.sleep(1500);
			    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			      		    
			WebElement sepa = driver.findElement(By.id("sepa"));
	        String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
		    System.out.println(Text1);
		
		    sepa.findElements(By.cssSelector(".form_details")).get(0).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1500);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
		    
		   // For New CR    
		    WebElement AlertCR = driver.findElement(By.xpath("//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();
		
		    
		    // For Old
		    
//		    driver.findElement(By.className("mobile-list-view")).findElements(By.cssSelector(".listiban_group")).get(0).click();
		 
		    
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1200);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
		
		    sepa.findElement(By.id("enterAmount")).sendKeys("9");
			Thread.sleep(500);
			  
			   WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
			   toAc.click();
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	       Thread.sleep(1000);
	 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		   
		    
		 // For New CR    
			
	    
		    driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[2]/div/div/span")).click();
		    Thread.sleep(300);
		    
		    driver.findElement(By.id("aliasName")).click();
		    driver.findElement(By.id("aliasName")).sendKeys("test");
		    
		    driver.findElement(By.id("unknownIban")).click();
//		    driver.findElement(By.id("unknownIban")).sendKeys("BE49001800007071");
		    driver.findElement(By.id("unknownIban")).sendKeys("BE28001753102420");                 
		    
		    driver.findElement(By.xpath("//*[@id='sf-gen-7']/div/div/div[2]/div[2]/div/div/div[3]/div[6]/div/div[1]/button/span")).click();
		    Thread.sleep(2500);
		    boolean errormsg=false;
		
		try{
		
			WebElement error = driver.findElement(By.className("containerRight"));
            error.findElement(By.cssSelector(".alert_messages__inner")).isDisplayed();
//			driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[2]/div/div/div[2]")).isDisplayed();
            if(errormsg==true){
		      System.out.println("This Beneficiary is already added");
		      }
		    }
		    catch(Exception e){
	    	
		    	driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[2]/div/div/div[3]/div[4]/label/span")).click();;
		    	Thread.sleep(500);
		        WebElement buttons = driver.findElement(By.cssSelector(".eb_tab_content__inner"));
		        buttons.findElement(By.cssSelector(".btn_default:first-child")).click();
		
//     	    	driver.findElement(By.xpath("//*[@id='sf-gen-7']/div/div/div[2]/div[2]/div/div/div[3]/div[6]/div/div[1]/button")).click();
				Thread.sleep(1500);
		     }
		
		    sepa.findElement(By.id("enterDate")).sendKeys("08092015");
	        Thread.sleep(1000);

		    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1000);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
		    boolean m2sign =false;
		    try{
		    	
		    	 m2sign = sepa.findElement(By.className("input_signature")).isDisplayed();
				 sepa.findElement(By.className("input_signature")).click();
				 Thread.sleep(10000);         
		    }
		   catch(NoSuchElementException e){
				    System.out.println("No M2 Signature");
			 }
				
		    driver.findElement(By.xpath(".//*[@id='sepaScreen']/div/div/div[2]/div/div[1]/button[1]/span")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
		   	  
		    String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
		    Thread.sleep(500);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    System.out.println(result);
		    
//	        driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
//		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//	        Thread.sleep(1000);
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//	        Thread.sleep(1000);
//		    if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
//		        	String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
//		        	Assert.assertEquals(PageHeader,"Transactions");
//		        	System.out.println(result);
//		       }
		  
	    }
			
			
    	    /****************************************************************************************
    
                                      Test Scenarios - 2  No Communication

            *****************************************************************************************/	

	    @Test(priority=1,enabled=true)
		public void createNoCommunication() throws Exception {
		 
		    WebDriverWait wait = new WebDriverWait(driver,40);	 
//		    Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
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

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	      	menuOption.click();
	       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	Thread.sleep(2000);
   		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
  		      		    
   		   WebElement sepa = driver.findElement(By.id("sepa"));
	       String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
		   System.out.println(Text1);
		
		   sepa.findElements(By.cssSelector(".form_details")).get(0).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		   
	   // For New CR    
 	       														
		    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();
 
	 // For Old
 	   
		// driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
		    
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(500);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
		
		   sepa.findElement(By.id("enterAmount")).sendKeys("13");
		   Thread.sleep(500);
		  
		   WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
		   toAc.click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	   
 	       
 	      // For New CR    
			
 	       WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
 	       AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();

          // For Old

	// 	  toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
 	       
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(500);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));
		 
	       sepa.findElement(By.id("enterDate")).sendKeys("08092015");
	       Thread.sleep(500);

		   sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 	   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
		 
 	       sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
 	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(500);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 	   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
 	   	   
 	       String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
		   Thread.sleep(500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   System.out.println(result);
		   
//         driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
// 	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//	       Thread.sleep(1000);
//	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//	       Thread.sleep(1000);
// 	        if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
// 	        	String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
// 	        	Assert.assertEquals(PageHeader,"Transactions");
// 	        	System.out.println(result);
// 	       }
  	  }
	

	    /****************************************************************************************
	    
                       Test Scenarios - 3  Belgium Structured Communication 

        *****************************************************************************************/	

		@Test(priority=2,enabled=true)
		public void createBelgiumCommunication() throws Exception {

			WebDriverWait wait = new WebDriverWait(driver,40);	 
//	   	    Obj.loginPositiveflow();
			driver.findElement(By.cssSelector(".site_logotype")).click();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(500);
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);


			//locate the menu to hover over using its xpath

			WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
			menu.click();           
			Thread.sleep(2000);

			//Initiate mouse action using Actions class

			Actions builder = new Actions(driver);

			//move the mouse to the earlier identified menu option

			builder.moveToElement(menu).build().perform();

			//wait for max of 5 seconds before proceeding. This allows sub menu
				//appears properly before trying to click on it

			String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

			//identify menu option from the resulting menu display and click

			WebElement menuOption = driver.findElement(By.xpath(Submenu));
			menuOption.click();
			driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			WebElement sepa = driver.findElement(By.id("sepa"));
			String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
			System.out.println(Text1);

			sepa.findElements(By.cssSelector(".form_details")).get(0).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

			  // For New CR    
				
		    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();
 
		    // For Old
 	   
		    // driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
		    
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));

			sepa.findElement(By.id("enterAmount")).sendKeys("9");
			Thread.sleep(600);

			WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
			toAc.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		      // For New CR    
			
	 	       WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
	 	     
	 	     //
	 	       String ActualIBAN1 = AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).getText().trim();
	 	       
	 	       AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 
	          // For Old

	 	  // 	toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 	       
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));

			sepa.findElement(By.id("enterDate")).sendKeys("08082015");
			Thread.sleep(600);
            
//			String ActualIBAN1 = driver.findElements(By.cssSelector(".iban-label")).get(1).getText().trim();
		    String ActualIBAN=ActualIBAN1.replaceAll("\\p{Z}","");
		    ActualIBAN = ActualIBAN.substring(4,ActualIBAN.length());
		    String keypad[] = ActualIBAN.split("(?!^)");
	        Thread.sleep(500);
	        
			WebElement com = sepa.findElements(By.className("inner-component")).get(2);
			com.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
										
			driver.findElement(By.xpath("//*[@id='sf-gen-1']/div/div/div[2]/div[1]/div/div/div[1]/div[2]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			WebElement bsm = driver.findElement(By.cssSelector(".select_message__structured"));
			bsm.findElement(By.cssSelector(".default_input_field > input")).click();
			
			for(int k=0;k<ActualIBAN.length();k++){
				String a = "Keys.NUMPAD"+keypad[k];
				bsm.findElement(By.cssSelector(".default_input_field > input")).sendKeys(a);
			}
			Thread.sleep(3000);
			
			   sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	       Thread.sleep(1000);
	 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
			 
	 	       sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	 	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		       Thread.sleep(1000);
		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
	 	   	   
			String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			System.out.println(result);
			
//			driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
//			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//			Thread.sleep(1000);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//			Thread.sleep(1000);
//			if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
//				String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
//				Assert.assertEquals(PageHeader,"Transactions");
//				System.out.println(result);
//			}
			
		}	
				
		
	    /****************************************************************************************
	    
                               Test Scenarios - 4  Free Structured Communication 

	    *****************************************************************************************/	

		@Test(priority=3,enabled=true)
		public void createFreeCommunication() throws Exception {

			WebDriverWait wait = new WebDriverWait(driver,40);	 
//	        Obj.loginPositiveflow();
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

			String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

			// identify menu option from the resulting menu display and click

			WebElement menuOption = driver.findElement(By.xpath(Submenu));
			menuOption.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

			WebElement sepa = driver.findElement(By.id("sepa"));
			String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
			System.out.println(Text1);

			sepa.findElements(By.cssSelector(".form_details")).get(0).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			
			  // For New CR    
			
		    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();
 
		    // For Old
 	   
		    // driver.findElements(By.cssSelector(".listiban_group")).get(0).click();

			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));

			sepa.findElement(By.id("enterAmount")).sendKeys("11");
			Thread.sleep(400);

			WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
			toAc.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

			// For New CR    
			
	 	    WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
	 	    AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 
	        // For Old

	 	    // toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 	       
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));

			sepa.findElement(By.id("enterDate")).sendKeys("08092015");
			Thread.sleep(600);

			WebElement com = sepa.findElements(By.className("inner-component")).get(2);
			com.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   
			driver.findElement(By.xpath(".//*[@id='sf-gen-1']/div/div/div[2]/div[1]/div/div/div[1]/div[1]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			driver.findElement(By.cssSelector(".default_text_area textarea")).clear();
			driver.findElement(By.cssSelector(".default_text_area textarea")).sendKeys("Automation Testing");
			Thread.sleep(500);

			sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	    Thread.sleep(1000);
	 	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
			 
	 	    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	 	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(500);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));

			String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			System.out.println(result);
			
//			driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
//			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//			Thread.sleep(1000);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//			Thread.sleep(1000);
//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//	
//			if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
//				String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
//				Assert.assertEquals(PageHeader,"Transactions");
//				System.out.println(result);
//			}
			
}
		
	
	    /****************************************************************************************
	    
                               Test Scenarios - 5  European Structured Communication 

	     *****************************************************************************************/	

		@Test(priority=4,enabled=true)
		public void createEuropeanCommunication() throws Exception {

			WebDriverWait wait = new WebDriverWait(driver,40);	 
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

			String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

		// identify menu option from the resulting menu display and click

			WebElement menuOption = driver.findElement(By.xpath(Submenu));
			menuOption.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

			WebElement sepa = driver.findElement(By.id("sepa"));
			String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
			System.out.println(Text1);

			sepa.findElements(By.cssSelector(".form_details")).get(0).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

			// For New CR    
			
		    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();
 
		    // For Old
 	   
		    // driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
			
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
	
			sepa.findElement(By.id("enterAmount")).sendKeys("9");
			Thread.sleep(600);
	
			WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
			toAc.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	
			// For New CR    
			
	 	    WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
	 	    AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 
	        // For Old

	 	    // toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 	    
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));
	
			sepa.findElement(By.id("enterDate")).sendKeys("08092015");
			Thread.sleep(600);
	
			WebElement com = sepa.findElements(By.className("inner-component")).get(2);
			com.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	
			driver.findElement(By.xpath(".//*[@id='sf-gen-1']/div/div/div[2]/div[1]/div/div/div[1]/div[3]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			
			driver.findElement(By.cssSelector(".default_text_area textarea")).clear();
			driver.findElement(By.cssSelector(".default_text_area textarea")).sendKeys("RF741");
			Thread.sleep(500);
	
			sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	    Thread.sleep(800);
	 	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
			 
	 	    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	 	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(800);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
	
			String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	
			driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(800);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(800);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
		if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
			String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
			Assert.assertEquals(PageHeader,"Transactions");
			System.out.println(result);
	}
}
		
			/****************************************************************************************

               				      Test Scenarios - 6    Modify the value

		 	*****************************************************************************************/	

        @Test(priority=5,enabled=true)
		public void modify() throws Exception {
		 
		    WebDriverWait wait = new WebDriverWait(driver,40);	 
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

	       String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
	       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	       WebElement menuOption = driver.findElement(By.xpath(Submenu));
	       menuOption.click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	 	   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
   		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
   	
   		   WebElement sepa = driver.findElement(By.id("sepa"));
	       String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
		   System.out.println(Text1);
		
		   sepa.findElements(By.cssSelector(".form_details")).get(0).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(500);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		   
 	       // For New CR    
			
		    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();

		    // For Old
	   
		    // driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
		    
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(500);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
		
		   sepa.findElement(By.id("enterAmount")).sendKeys("9");
		   Thread.sleep(600);
		  
		   WebElement toAc = driver.findElements(By.cssSelector(".form_fields")).get(2);
		   toAc.findElement(By.className("inner-component")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	   
 			// For New CR    
			
	 	    WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
	 	    AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 
	        // For Old

	 	    // toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 	    
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(500);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));
		
	     	   
		   sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	   Thread.sleep(1000);
	 	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
    	   Thread.sleep(1500);
 	       
 	       String oldFromIBAN = sepa.findElements(By.cssSelector(".listitem_details p")).get(1).getText().trim();
 	       String amt = sepa.findElements(By.cssSelector(".listitem_details p")).get(2).getText().trim();
 	       String oldToIBAN = sepa.findElements(By.cssSelector(".listitem_details p")).get(4).getText().trim();
 	       System.out.println("The to IBAN VAlue is :"+oldToIBAN);
 	  	       
 	       driver.findElement(By.xpath(".//*[@id='sepaScreen']/div/div/div[2]/div/div[2]")).click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	       
	       sepa.findElements(By.cssSelector(".form_details")).get(0).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		   
 	      // For New CR    
			
		    WebElement AlertCR3 = driver.findElement(By.xpath(".//*[@id='sf-gen-14']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR3.findElements(By.cssSelector(".listiban_group")).get(2).click();

		    // For Old
	   
		    // driver.findElements(By.cssSelector(".listiban_group")).get(2).click();
		    
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));
		
		   sepa.findElement(By.id("enterAmount")).clear();
		   sepa.findElement(By.id("enterAmount")).sendKeys("14,00");
		   Thread.sleep(500);
		   
	       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));
		   sepa.findElement(By.id("enterDate")).clear();
	       sepa.findElement(By.id("enterDate")).sendKeys("13062015");
		  
		   driver.findElements(By.cssSelector(".selectize-control")).get(1).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(1000);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	        
 	  	// For New CR    
 	       														
	 	    WebElement AlertCR4 = driver.findElement(By.xpath(".//*[@id='sf-gen-17']/div/div/div[2]/div[1]/div[4]")); 
	 	    AlertCR4.findElements(By.cssSelector(".listiban_group")).get(2).click();
	 
	        // For Old

//	       WebElement toAc1 = driver.findElements(By.cssSelector(".selectize-dropdown-content")).get(1);
// 	       toAc1.findElements(By.cssSelector(".listiban_group")).get(2).click();
	 	    
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 	       Thread.sleep(500);
 	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
           
		   sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	   Thread.sleep(500);
	 	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
	  	   Thread.sleep(1000);
 	  	   
	       String oldFromIBAN1 = sepa.findElements(By.cssSelector(".listitem_details p")).get(1).getText().trim();
	       System.out.println(oldFromIBAN1);
	       String amt1 = sepa.findElements(By.cssSelector(".listitem_details p")).get(2).getText().trim();
 	       String oldToIBAN1 = sepa.findElements(By.cssSelector(".listitem_details p")).get(4).getText().trim();
 	       String date1 = sepa.findElements(By.cssSelector(".listitem_details p")).get(5).getText().trim();
 	       Thread.sleep(500);
 	       
 	       Assert.assertNotEquals(oldFromIBAN1,oldFromIBAN);
 	       Assert.assertNotEquals(amt1,amt);
 	       Assert.assertNotEquals(oldToIBAN1,oldToIBAN);
 	     
 	       System.out.println("Modified Properly");
 	       
	 	   sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	 	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1000);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
	       
	       String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
		   Thread.sleep(500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   
           driver.findElement(By.xpath("//*[@id='sepaScreen']/div/div/div[3]/div/div/div[1]/button[1]")).click();
	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	       Thread.sleep(1000);
	       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	       Thread.sleep(1000);
	       if(driver.findElements(By.className("eb_tab_inner")).get(0).isDisplayed()){
	        	String PageHeader = driver.findElements(By.className("eb_tab_inner")).get(0).getText();
	        	Assert.assertEquals(PageHeader,"Transactions");
	        	System.out.println(result);
	       }
	  }
		
		/****************************************************************************************

          				      Test Scenarios - 7    SCT to STO with Specific date

	 	*****************************************************************************************/	
  
		@Test(priority=6,enabled=true)
		public void transfersToSTO() throws Exception {

			WebDriverWait wait = new WebDriverWait(driver,40);	 
//	        Obj.loginPositiveflow();
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

			String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

			// identify menu option from the resulting menu display and click

			WebElement menuOption = driver.findElement(By.xpath(Submenu));
			menuOption.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

			WebElement sepa = driver.findElement(By.id("sepa"));
			String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
			System.out.println(Text1);

			sepa.findElements(By.cssSelector(".form_details")).get(0).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		   // For New CR    
			
		    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
		    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();
 
		    // For Old
 	   
		    // driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
		    
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));

			sepa.findElement(By.id("enterAmount")).sendKeys("14");
			Thread.sleep(600);

			WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
			toAc.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

			  // For New CR    
			
	 	       WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
	 	       AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();

	          // For Old

     // 	toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(800);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));

			sepa.findElement(By.id("enterDate")).sendKeys("09062015");
			Thread.sleep(600);

			WebElement com = sepa.findElements(By.className("inner-component")).get(2);
			com.click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));


			driver.findElement(By.xpath(".//*[@id='sf-gen-1']/div/div/div[2]/div[1]/div/div/div[1]/div[1]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			driver.findElement(By.cssSelector(".default_text_area textarea")).clear();
			driver.findElement(By.cssSelector(".default_text_area textarea")).sendKeys("Automation Testing");
			Thread.sleep(500);

			sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 	    Thread.sleep(500);
	 	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
			 
	 	    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	 	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(500);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));

			String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
            System.out.println("Acknowledge info: "+result);
			driver.findElement(By.xpath(".//*[@id='sepaScreen']/div/div/div[3]/div/div/div[2]")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(800);
		  	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		  	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("enterdetails")));
			
			boolean Fromtest,Totest=false;
			Fromtest = driver.findElements(By.cssSelector(".iban-label")).get(0).isDisplayed();
			Totest= driver.findElements(By.cssSelector(".iban-label")).get(1).isDisplayed();
			
	        Assert.assertEquals(true,Fromtest);
	        Assert.assertEquals(true,Totest);
	        
	        WebElement sepaSTO = driver.findElement(By.id("standorder"));
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
	        driver.findElement(By.cssSelector(".date_picker input")).sendKeys("03062015");
	        
	        driver.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    Thread.sleep(1000);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
			Thread.sleep(500);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		  	 
		   try{
			     boolean id= false;
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
    }
		
		
		
		/****************************************************************************************

	      					Test Scenarios - 8   SCT to STO with Last Specific date

		 *****************************************************************************************/	

		@Test(priority=7,enabled=true)
		public void transfersToSTO1() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,40);	 
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
		Thread.sleep(2000);
		
		// Initiate mouse action using Actions class
		
		Actions builder = new Actions(driver);
		
		// move the mouse to the earlier identified menu option
		
		builder.moveToElement(menu).build().perform();
		
		// wait for max of 5 seconds before proceeding. This allows sub menu
		// appears properly before trying to click on it
		
		String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[1]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 
		
		// identify menu option from the resulting menu display and click
		
		WebElement menuOption = driver.findElement(By.xpath(Submenu));
		menuOption.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		
		WebElement sepa = driver.findElement(By.id("sepa"));
		String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
		System.out.println(Text1);

		sepa.findElements(By.cssSelector(".form_details")).get(0).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

	   // For New CR    
		
	    WebElement AlertCR = driver.findElement(By.xpath(".//*[@id='sf-gen-4']/div/div/div[2]/div[1]/div[3]")); 
	    AlertCR.findElements(By.cssSelector(".listiban_group")).get(0).click();

	    // For Old
	   
	    // driver.findElements(By.cssSelector(".listiban_group")).get(0).click();
	    
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterAmount")));

		sepa.findElement(By.id("enterAmount")).sendKeys("21");
		Thread.sleep(600);

		WebElement toAc = sepa.findElements(By.className("inner-component")).get(1);
		toAc.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	

		  // For New CR    
		
 	       WebElement AlertCR1 = driver.findElement(By.xpath(".//*[@id='sf-gen-7']/div/div/div[2]/div[1]/div[4]")); 
 	       AlertCR1.findElements(By.cssSelector(".listiban_group")).get(0).click();

          // For Old

 // 	toAc.findElements(By.cssSelector(".listiban_group")).get(0).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(800);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterDate")));

		sepa.findElement(By.id("enterDate")).sendKeys("08092015");
		Thread.sleep(600);

		WebElement com = sepa.findElements(By.className("inner-component")).get(2);
		com.click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));


		driver.findElement(By.xpath(".//*[@id='sf-gen-1']/div/div/div[2]/div[1]/div/div/div[1]/div[1]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
		driver.findElement(By.cssSelector(".default_text_area textarea")).clear();
		driver.findElement(By.cssSelector(".default_text_area textarea")).sendKeys("Automation Testing");
		Thread.sleep(500);
		
		sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
		
		sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
		
		String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
		Thread.sleep(500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		System.out.println("Acknowledge info: "+result);
		
		driver.findElement(By.xpath(".//*[@id='sepaScreen']/div/div/div[3]/div/div/div[2]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(800);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("enterdetails")));
		
		boolean Fromtest,Totest=false;
		Fromtest = driver.findElements(By.cssSelector(".iban-label")).get(0).isDisplayed();
		Totest= driver.findElements(By.cssSelector(".iban-label")).get(1).isDisplayed();
		
		Assert.assertEquals(true,Fromtest);
		Assert.assertEquals(true,Totest);
		
		WebElement sepaSTO = driver.findElement(By.id("standorder"));
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
		
		driver.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));  
		Thread.sleep(500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			 
		try{
			 boolean id =false; 
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
		}

}
