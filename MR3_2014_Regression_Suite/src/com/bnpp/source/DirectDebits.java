package com.bnpp.source;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
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
import com.bnpp.methods.MethodsWeb;

@Listeners(JyperionListener.class)
public class DirectDebits extends DataWeb {
	

	 LoginModule Obj = new LoginModule();
	 String Screenshotpath = "/Users/mobilitytcs/Desktop/Automation/Selenium/Results/Screenshots/";
	 
	
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

// For android Mobile Browser
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

                              Test Scenarios - 1  Upcoming Mandate Verification

      *****************************************************************************************/	

 	  @Test(priority=0,enabled=true)
	   public void sepaDDupcoming() throws Exception{
		
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 Properties prop = new Properties();
		 FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
			prop.load(input);
			
		    JavascriptExecutor jse = (JavascriptExecutor)driver;	
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

	        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[4]/a";
	      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

	   	// identify menu option from the resulting menu display and click

	      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	      	menuOption.click();
	       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	Thread.sleep(2000);
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
	   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 	
	   	    WebElement SepaDD = driver.findElement(By.id("sepadd"));
   	        String Text1 = SepaDD.findElement(By.cssSelector(".module_title")).getText();
		    System.out.println(Text1);
		    
		    WebElement module = SepaDD.findElements(By.className("module_content")).get(0);
		    int types = module.findElements(By.cssSelector(".header_l1 h4")).size();
		    System.out.println(types);
		   
		    for(int i=0;i<types;i++){
		   	    	
		 	if(module.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed()){
		 		   
		 		    String header = module.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
				    System.out.println(header);
		 		
		 		if(header.equalsIgnoreCase("UPCOMING")){
		    	    Thread.sleep(2000);
		 			String headerCount = module.findElements(By.cssSelector(".accordion_l1__inner")).get(i).getText();
		 			Scanner text = new Scanner(headerCount).useDelimiter("[^0-9]+");
		 			int sepaDDCount = text.nextInt();
		 			System.out.println("Total no.of.transactions :"+sepaDDCount);
		 			int loopclickCount=sepaDDCount;
			
		 			WebElement component = module.findElements(By.cssSelector(".accordion_main")).get(i);
		 			
		 			if(sepaDDCount>50){
		 				
		 				    jse.executeScript("window.scrollBy(0,800)","");
				            Thread.sleep(500);
				            
		 				  do{
								WebElement loadmore = component.findElement(By.cssSelector(".load_more"));
								loadmore.findElement(By.cssSelector(".btn_primary")).click();
								driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
								Thread.sleep(1200);
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
								loopclickCount=loopclickCount-50;
						     }while(loopclickCount>50);
 	 				}
		 			
		 			int sepaCount = component.findElements(By.cssSelector(".listiban_group")).size();
	 				System.out.println(sepaCount);		
	 			 			
	 				Assert.assertEquals(sepaCount,sepaDDCount);
			
	 				// Clicking the first item
					
	 				component.findElements(By.cssSelector(".listiban_group")).get(0).click();
	 				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 				Thread.sleep(900);
	 				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    
	 				WebElement IBANelement = driver.findElement(By.cssSelector(".extra_contentdetails.details"));
	 				String IBAN = IBANelement.findElements(By.cssSelector(".listitem_details span")).get(0).getText().trim();
	 				String Beneficiary = IBANelement.findElements(By.cssSelector(".listitem_details span")).get(3).getText().trim();
	 				String Identifier = IBANelement.findElements(By.cssSelector(".listitem_details span")).get(4).getText().trim();
	 				System.out.println(IBAN);
	 				System.out.println(Beneficiary);
	 				System.out.println(Identifier);
		 
	 				IBANelement.findElements(By.cssSelector(".listitem_details span")).get(12).click();
	 				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 				Thread.sleep(900);
	 				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

	 				WebElement mandate1 = SepaDD.findElement(By.cssSelector(".details"));
	 				String IBAN1 = mandate1.findElements(By.cssSelector(".listitem_details span")).get(0).getText().trim();
		   
	 				WebElement mandate = SepaDD.findElements(By.cssSelector(".detail_group")).get(1);
	 				String Beneficiary1 = mandate.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim();
	 				String Identifier1 = mandate.findElements(By.cssSelector(".listitem_details")).get(1).getText().trim();
		    
	 				Assert.assertEquals(IBAN,IBAN1);
	 				Assert.assertEquals(Beneficiary,Beneficiary1);
	 				Assert.assertEquals(Identifier,Identifier1);
	 				
	 				driver.findElement(By.cssSelector(".back_link")).click();
	 				Thread.sleep(200);
	 				driver.findElement(By.id("search")).click();
	 			    Thread.sleep(300);
	 		        driver.findElements(By.className("module_listitem")).get(0).findElements(By.cssSelector(".listiban_group")).get(0).click();
	 				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	 				Thread.sleep(300);
	 				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//	 				jse.executeScript("window.scrollBy(1500,0)");
	 				jse.executeScript("scroll(0,-1500);");
		            Thread.sleep(200);
	 				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".site_logotype")));
	 			    break;
	      		}else{System.out.println("No Upcoming Transactions");}
		 	}
	    }
    }
		 
	      /****************************************************************************************

	                              Test Scenarios - 2  Upcoming Rejection Scenario

	      *****************************************************************************************/	

	 
		  @Test(priority=1,enabled=true)
		   public void sepaDDRejected() throws Exception{
			
			 WebDriverWait wait = new WebDriverWait(driver,30);
			 Properties prop = new Properties();
			  FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/TransactionData.properties");
				prop.load(input);
		
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				
//				   Obj.loginPositiveflow();			    
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
		      	Thread.sleep(2000);

		  	// Initiate mouse action using Actions class
		    	
		      	Actions builder = new Actions(driver);

		  	// move the mouse to the earlier identified menu option
		   		
		      	builder.moveToElement(menu).build().perform();

		 	// wait for max of 5 seconds before proceeding. This allows sub menu
		 			// appears properly before trying to click on it

		        String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[4]/a";
		      	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Submenu))); 

		   	// identify menu option from the resulting menu display and click

		      	WebElement menuOption = driver.findElement(By.xpath(Submenu));
		      	menuOption.click();
		       	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    	Thread.sleep(2000);
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".module_title")));  
		   		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 	
		   	    WebElement SepaDD = driver.findElement(By.id("sepadd"));
	   	        String Text1 = SepaDD.findElement(By.cssSelector(".module_title")).getText();
			    System.out.println(Text1);
			    
			    WebElement module = SepaDD.findElements(By.className("module_content")).get(0);
			    int types = module.findElements(By.cssSelector(".header_l1 h4")).size();
			    System.out.println(types);
			   
			    for(int i=0;i<types;i++){
			   	    	
			 	if(module.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed()){
			 		   
			 		    String header = module.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
					    System.out.println(header);
			 		
			 		if(header.equalsIgnoreCase("UPCOMING")){
			 			Thread.sleep(2000);
			 			String headerCount = module.findElements(By.cssSelector(".accordion_l1__inner")).get(i).getText();
			 			Scanner text = new Scanner(headerCount).useDelimiter("[^0-9]+");
			 			int sepaDDCount = text.nextInt();
			 			System.out.println("Total no.of.transactions :"+sepaDDCount);
			 			int loopclickCount=sepaDDCount;
				
			 			WebElement component = module.findElements(By.cssSelector(".accordion_main")).get(i);
			 			
			 			if(sepaDDCount>50){
			 				
			 		        jse.executeScript("window.scrollBy(0,800)","");
				            Thread.sleep(500);
				            
			 				  do{
									WebElement loadmore = component.findElement(By.cssSelector(".load_more"));
									loadmore.findElement(By.cssSelector(".btn_primary")).click();
									driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
									Thread.sleep(1200);
									wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
									loopclickCount=loopclickCount-50;
							     }while(loopclickCount>50);
	 	 				}
			 			
			 			int sepaCount = component.findElements(By.cssSelector(".listiban_group")).size();
		 				System.out.println("List count is "+sepaCount);		
		 			 			
		 				Assert.assertEquals(sepaCount,sepaDDCount);
		 				
		 			// Clicking the first item
		 		         
				 		for(int j=0;j<sepaCount;j++){
					
			 			    WebElement check = component.findElements(By.cssSelector(".list_item")).get(j);
				 		    component.findElements(By.cssSelector(".list_content_details")).get(j).findElement(By.cssSelector(".listiban_group")).click();
				 			Thread.sleep(300);
				 			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 			Thread.sleep(400);
				 			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navigation_btns")));
				 			
				 			if(check.findElement(By.cssSelector(".navigation_btns")).isDisplayed()){
				 				
				 				 jse.executeScript("window.scrollBy(0,400)","");
					             Thread.sleep(500);
				 				
					             check.findElement(By.cssSelector(".btn_default:first-child")).click();
				 				  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
								  Thread.sleep(400);
								  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ucr_comp")));
								  Thread.sleep(400);
								  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
								  
								  // Entering the M2 Signature 
								  SepaDD.findElements(By.cssSelector(".default_input_field")).get(2).click();
								  Thread.sleep(10000);
								  							  
								  SepaDD.findElement(By.cssSelector(".btn_default:first-child")).click();
								  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
								  Thread.sleep(200);
								  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("direct_debit_inner")));
								  Thread.sleep(200);
								  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

								  String Result = SepaDD.findElement(By.cssSelector(".alert_messages__inner p")).getText().trim();
								  System.out.println(Result);
								  break;
				 			 }
				 		  }
			 		}else{System.out.println("No Upcoming Transactions");}
			 	}
		    }
	    }

	   
	/****************************************************************************************
	 
	                          Test Scenarios - 3   History Mandate Verification
	                          
	 *****************************************************************************************/

	//@Test(priority=2,enabled=true)
	public void sepaDDHistory() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Properties prop = new Properties();
		  FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);

//		   Obj.loginPositiveflow();
		   driver.findElement(By.cssSelector(".site_logotype")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   Thread.sleep(1000);
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   
		// locate the menu to hover over using its xpath

		WebElement menu = driver
				.findElement(By
						.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
		menu.click();
		Thread.sleep(2000);

		// Initiate mouse action using Actions class

		Actions builder = new Actions(driver);

		// move the mouse to the earlier identified menu option

		builder.moveToElement(menu).build().perform();

		// wait for max of 5 seconds before proceeding. This allows sub menu
		// appears properly before trying to click on it

		String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[4]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(Submenu)));

		// identify menu option from the resulting menu display and click

		WebElement menuOption = driver.findElement(By.xpath(Submenu));
		menuOption.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector(".module_title")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));

   	    WebElement SepaDD = driver.findElement(By.id("sepadd"));
	    String Text1 = SepaDD.findElement(By.cssSelector(".module_title")).getText();
	    System.out.println(Text1);
	    
	    WebElement module = SepaDD.findElement(By.cssSelector(".module_content"));
	    int types = module.findElements(By.cssSelector(".header_l1 h4")).size();
	    System.out.println(types);
	    boolean Upcomingcheck=false;
	    
	    for(int i=0;i<types;i++){
	   	    	
	 	if(module.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed()){
	 		   
	 		    String header = module.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
			    System.out.println(header);
               	
			    if(header.equalsIgnoreCase("UPCOMING")){
			    	    Upcomingcheck=true;
		                module.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
		                driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
						Thread.sleep(500);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			      }
			    
	 		if(header.equalsIgnoreCase("HISTORY")){
	 			if(Upcomingcheck==true){
	 			Thread.sleep(2000);
	 	        module.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
                driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));}
	 		
				String headerCount = module.findElements(By.cssSelector(".accordion_l1__inner")).get(i).getText();
	 			Scanner text = new Scanner(headerCount).useDelimiter("[^0-9]+");
	 			int sepaDDCount = text.nextInt();
	 			System.out.println("Total no.of.transactions :"+sepaDDCount);
	 			int loopclickCount=sepaDDCount;
		
	 			WebElement component = module.findElement(By.cssSelector(".accordion_main:last-child"));
	 			
	 			if(sepaDDCount>50){
	 				
	 			       JavascriptExecutor jse = (JavascriptExecutor)driver;
	 		           jse.executeScript("window.scrollBy(0,800)","");
	 		           Thread.sleep(500);
	 				
	 				   do{
							WebElement loadmore = component.findElement(By.className("list_details_more"));
							loadmore.findElement(By.cssSelector(".btn_primary")).click();
							driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
							Thread.sleep(1200);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
							loopclickCount=loopclickCount-50;
					     }while(loopclickCount>50);
	 				   
	 				}
	 			
//	 			Thread.sleep(1000);
//	 			int sepaCount = component.findElements(By.cssSelector(".listiban_group")).size();
// 				System.out.println(sepaCount);		
 			 			
 			    Thread.sleep(1000);
 			    int sepaCount = component.findElements(By.cssSelector(".list_content_details")).size();
				System.out.println(sepaCount);		
				
				Assert.assertEquals(sepaCount,sepaDDCount);
		
 				// Clicking the first item
 				
 				Thread.sleep(500);
 				wait.until(ExpectedConditions.invisibilityOfElementLocated(By
 						.className("progressing")));
 		       
 		 		for(int j=0;j<sepaCount;j++){
 		 			
 				component.findElements(By.cssSelector(".listiban_group")).get(j).click();
 				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 				Thread.sleep(900);
 				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    
 				WebElement IBANelement = driver.findElement(By.cssSelector(".extra_contentdetails.details"));
 				String IBAN = IBANelement.findElements(By.cssSelector(".listitem_details span")).get(0).getText().trim();
 				String Beneficiary = IBANelement.findElements(By.cssSelector(".listitem_details span")).get(3).getText().trim();
 				String Identifier = IBANelement.findElements(By.cssSelector(".listitem_details span")).get(4).getText().trim();
 				System.out.println(IBAN);
 				System.out.println(Beneficiary);
 				System.out.println(Identifier);
	 
 				if(IBANelement.findElements(By.cssSelector(".listitem_details span")).get(12).isEnabled()){
 					
 				IBANelement.findElements(By.cssSelector(".listitem_details span")).get(12).click();
 				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 				Thread.sleep(500);
 				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

 				WebElement mandate1 = SepaDD.findElement(By.cssSelector(".details"));
 				String IBAN1 = mandate1.findElements(By.cssSelector(".listitem_details span")).get(0).getText().trim();
	   
 				WebElement mandate = SepaDD.findElements(By.cssSelector(".detail_group")).get(1);
 				String Beneficiary1 = mandate.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim();
 				String Identifier1 = mandate.findElements(By.cssSelector(".listitem_details")).get(1).getText().trim();
	    
 				Assert.assertEquals(IBAN,IBAN1);
 				Assert.assertEquals(Beneficiary,Beneficiary1);
 				Assert.assertEquals(Identifier,Identifier1);
 				
 				driver.findElement(By.cssSelector(".back_link")).click();
 				Thread.sleep(400);
 				driver.findElement(By.id("search")).click();
 				Thread.sleep(400);
 				break;
 				}
 	   	     } 
 		  }
	   }
    }
 }
	

/****************************************************************************************
 
                          Test Scenarios - 4  History Request for refund
                          
 *****************************************************************************************/

@Test(priority=3,enabled=true)
public void sepaDDRequestforRefund() throws Exception {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	Properties prop = new Properties();
	  FileInputStream input = new FileInputStream(filepath
  			+ "Object Repository/TransactionData.properties");
	prop.load(input);
	   int value=0;
//       Obj.loginPositiveflow();
	   driver.findElement(By.cssSelector(".site_logotype")).click();
	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   Thread.sleep(1500);
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   Thread.sleep(1000);
	   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   
	// locate the menu to hover over using its xpath

	WebElement menu = driver
			.findElement(By
					.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By
			.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
	menu.click();
	Thread.sleep(2000);

	// Initiate mouse action using Actions class

	Actions builder = new Actions(driver);

	// move the mouse to the earlier identified menu option

	builder.moveToElement(menu).build().perform();

	// wait for max of 5 seconds before proceeding. This allows sub menu
	// appears properly before trying to click on it

	String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[4]/a";
	wait.until(ExpectedConditions.presenceOfElementLocated(By
			.xpath(Submenu)));

	// identify menu option from the resulting menu display and click

	WebElement menuOption = driver.findElement(By.xpath(Submenu));
	menuOption.click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Thread.sleep(2000);
	wait.until(ExpectedConditions.presenceOfElementLocated(By
			.cssSelector(".module_title")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By
			.className("progressing")));
	Thread.sleep(1500);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By
			.className("progressing")));

	    WebElement SepaDD = driver.findElement(By.id("sepadd"));
        String Text1 = SepaDD.findElement(By.cssSelector(".module_title")).getText();
    System.out.println(Text1);
    
    WebElement module = SepaDD.findElements(By.className("module_content")).get(0);
    int types = module.findElements(By.cssSelector(".header_l1 h4")).size();
    System.out.println(types);
    boolean Upcomingcheck=false,TransactionsRRCheck=false;
    
    for(int i=0;i<types;i++){
   	    	
 	if(module.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed()){
 		   
 		    String header = module.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
		    System.out.println(header);
		    
		    if(header.equalsIgnoreCase("UPCOMING")){
	    	    Upcomingcheck=true;
                module.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
                driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));}
		    
	    
 			if(header.equalsIgnoreCase("HISTORY")){
 				 
            if(Upcomingcheck==true){
            module.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));}
           
            Thread.sleep(2000);
 			String headerCount = module.findElements(By.cssSelector(".accordion_l1__inner")).get(i).getText();
 			Scanner text = new Scanner(headerCount).useDelimiter("[^0-9]+");
 			int sepaDDCount2 = text.nextInt();
 			System.out.println("Total no.of.transactions :"+sepaDDCount2);
 			int loopclickCount=sepaDDCount2;
	
 			WebElement component = module.findElements(By.cssSelector(".accordion_main")).get(i);
 			
 			if(sepaDDCount2>50){
 					
 					JavascriptExecutor jse = (JavascriptExecutor)driver;
		            jse.executeScript("window.scrollBy(0,800)","");
		            Thread.sleep(500);
		            
 				  do{
						WebElement loadmore = component.findElement(By.cssSelector(".load_more"));
						loadmore.findElement(By.cssSelector(".btn_primary")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						Thread.sleep(1200);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
						loopclickCount=loopclickCount-50;
				     }while(loopclickCount>50);
 				}
 			
 			    Thread.sleep(1000);
 			    int sepaCount3 = component.findElements(By.cssSelector(".list_content_details")).size();
				System.out.println(sepaCount3);		
			 	Assert.assertEquals(sepaCount3,sepaDDCount2);
	
				// Clicking the first item
	         
			 		for(int j=0;j<sepaCount3;j++){
				
		 			    WebElement check = component.findElements(By.cssSelector(".list_item")).get(j);
			 		    component.findElements(By.cssSelector(".list_content_details")).get(j).findElement(By.cssSelector(".listiban_group")).click();
			 			Thread.sleep(300);
			 			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			 			Thread.sleep(400);
			 			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navigation_btns")));
			 			
			 			if(check.findElement(By.cssSelector(".navigation_btns")).isDisplayed()){
			 				
			 				  check.findElement(By.cssSelector(".btn_default:first-child")).click();
			 				  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
							  Thread.sleep(400);
							  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ucr_comp")));
							  Thread.sleep(400);
							  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
							  
							  // Entering the M2 Signature 
							  SepaDD.findElements(By.cssSelector(".default_input_field")).get(2).click();
							  Thread.sleep(10000);
							  							  
							  SepaDD.findElement(By.cssSelector(".btn_default:first-child")).click();
							  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
							  Thread.sleep(200);
							  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("direct_debit_inner")));
							  Thread.sleep(200);
							  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

							  String Result = SepaDD.findElement(By.cssSelector(".alert_messages__inner p")).getText().trim();
							  System.out.println(Result);
							  break;
			 			}
			 		}
  	    	    }
 			}
    	}
	}

	/****************************************************************************************
	 
	                          Test Scenarios - 5   Search Functionality
	                          
	 *****************************************************************************************/

	@Test(priority=4, enabled = true)
	public void sepaDDsearch() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Properties prop = new Properties();
		  FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);

		int sepaDDCount ;
		
//		   Obj.loginPositiveflow();
		   driver.findElement(By.cssSelector(".site_logotype")).click();
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		   Thread.sleep(1500);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   Thread.sleep(1000);
		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		// locate the menu to hover over using its xpath

		WebElement menu = driver
				.findElement(By
						.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
		menu.click();
		Thread.sleep(2000);

		// Initiate mouse action using Actions class

		Actions builder = new Actions(driver);

		// move the mouse to the earlier identified menu option

		builder.moveToElement(menu).build().perform();

		// wait for max of 5 seconds before proceeding. This allows sub menu
		// appears properly before trying to click on it

		String Submenu = ".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/ul/li[4]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath(Submenu)));

		// identify menu option from the resulting menu display and click

		WebElement menuOption = driver.findElement(By.xpath(Submenu));
		menuOption.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector(".module_title")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
				.className("progressing")));

		WebElement SepaDD = driver.findElement(By.id("sepadd"));
		String Text1 = SepaDD.findElement(By.cssSelector(".module_title"))
				.getText();
		System.out.println(Text1);

		WebElement module = SepaDD
				.findElements(By.className("module_content")).get(0);
	    int types = module.findElements(By.cssSelector(".header_l1 h4")).size();
	    System.out.println(types);
		
	    	SepaDD.findElement(By.id("search")).click();
	   		SepaDD.findElement(By.id("search")).sendKeys("13");
	   		SepaDD.findElement(By.cssSelector(".search_icon span")).click();
	   		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	   		Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.className("progressing")));

	   		String sepaResult = driver.findElement(By.xpath(".//*[@id='sepadd']/div/div/div[4]/div/div[1]/div/div/div[2]/span")).getText().trim();
	   		System.out.println(sepaResult);
	   		boolean sepaCheck;
	   		sepaCheck=sepaResult.matches(".*\\d+.*");
	   	   
	   		if(sepaCheck==true){
	             	
	   			Scanner in = new Scanner(sepaResult).useDelimiter("[^0-9]+");
	   			int integerCount = in.nextInt();
	   			System.out.println(integerCount);
	   				
	   			for(int i=0;i<types;i++){
	   			   		
	   			   	if(module.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed()){
	   		  	
	   			   		String header=module
	   								.findElements(By.cssSelector(".accordion_l1__inner")).get(i)
	   								.getText();
	   			   		System.out.println(header);
	   			   		Scanner text = new Scanner(header).useDelimiter("[^0-9]+");
	   			   		sepaDDCount = text.nextInt();
	   			   		System.out.println("Total no.of.transactions :" + sepaDDCount);
	   			   		WebElement component = module.findElements(By.cssSelector(".accordion_main")).get(i);
	   			   	    int loopclickCount=sepaDDCount;    
	   			   		if(sepaDDCount>50){
	   			   		    	 do{
	   			   	        		WebElement loadmore = component.findElement(By.cssSelector(".load_more"));
	   			   	        		loadmore.findElement(By.cssSelector(".btn_primary")).click();
	   			   	        		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   			   	        		Thread.sleep(1200);
	   			   	        		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   			   	        		loopclickCount=loopclickCount-50;
	   			   	        		}while(loopclickCount>50);
	   			   			}
	   			   		 }
	   			   	  }
	   			
	   			int SearchCount = module.findElements(By.cssSelector(".listiban_group")).size();
				Assert.assertEquals(integerCount,SearchCount);   
	   			}else{	
   				 System.out.println(sepaResult);}
				}
	  }

