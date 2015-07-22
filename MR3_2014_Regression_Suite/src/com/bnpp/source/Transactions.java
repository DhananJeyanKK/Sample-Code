package com.bnpp.source;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
import com.thoughtworks.selenium.webdriven.commands.Click;

@Listeners(JyperionListener.class)
public class Transactions extends DataWeb {
	
	 LoginModule Obj = new LoginModule();
	 Properties prop = new Properties();
	 boolean plannedvalue,planfinal = false;
		
     
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
       
	//  For android Mobile Browser
    /* DesiredCapabilities caps = DesiredCapabilities.android();
       caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
       driver = new AndroidDriver(caps);
       driver.get(BaseUrl);*/
        driver.get(BaseUrl);

 }
	 
	/****************************************************************************************
 
                                          After Class

     ****************************************************************************************/
	
	    @AfterClass(alwaysRun=true)
		public void tear() throws Exception {
			driver.quit();
		}

	    
		
		/****************************************************************************************

										Test Scenarios - 1   Pending Transaction 

	 	****************************************************************************************/	

		@Test(priority=0,enabled=true)	
		public void Pendingtransaction() throws Exception{

			WebDriverWait wait = new WebDriverWait(driver,50);
	        FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
			prop.load(input);
			boolean pendingvalue = false;
		
			Obj.loginPositiveflow();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

			if(driver.findElement(By.cssSelector(".laccounts")).isDisplayed()){

				WebElement main = driver.findElements(By.cssSelector(".laccounts")).get(0);
				int count = main.findElements(By.cssSelector(".list_item")).size();
				System.out.println("Total no.of.accounts displayed in home page"+count);
				main.findElements(By.cssSelector(".list_item")).get(0).click();
				
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
			
				WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
				int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
				System.out.println(menu);	

			for(int i=0;i<menu;i++){
		
				if(modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
				{
				String transactiontype = modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).getText();
				System.out.println("The header is "+transactiontype);
	   		    
	   		    if(transactiontype.equalsIgnoreCase("PENDING"))
	                {
	   		    	   pendingvalue = true;
	   	               PendingCount=driver.findElement(By.xpath(prop.getProperty("PendingCount"))).getText();
	           	       PendingCount1=Integer.parseInt(PendingCount.trim());
	          	       System.out.println(PendingCount1);
	          	       int loopclickCount = PendingCount1;
	          	       WebElement pendingmain=modulelist.findElements(By.cssSelector(".accordion_main")).get(0);
	          	       pendingmain.findElement(By.cssSelector(".header_l1 h4")).click();
	          	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	          	       Thread.sleep(1500);
	          	 	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	          	 	if(PendingCount1>50){
					     do{
							WebElement pending = pendingmain.findElement(By.cssSelector(".load_more"));
							pending.findElement(By.cssSelector(".btn_primary")).click();
							driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
							Thread.sleep(1500);
							wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
							loopclickCount=loopclickCount-50;
							}while(loopclickCount>50);
	          	 	}
	          	 		PendingCount2=pendingmain.findElements(By.cssSelector(".list_item")).size(); 
	          	 		System.out.println(PendingCount2);
	          	 		Assert.assertEquals(PendingCount1,PendingCount2);
	          	    } 
				}
	   	
			}
			if(pendingvalue==false){
	           System.out.println("No Pending transactions are available now");					
			}
		}
	}	    
	    
		
		/****************************************************************************************

							Test Scenarios - 2   Rejected Transaction 

		 ****************************************************************************************/	

	@Test(priority=1,enabled = true)
	public void Rejectedtransaction() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver,50);
	     FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);
		boolean Rejectedvalue = false;
	
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1200);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	

		if(driver.findElement(By.cssSelector(".laccounts")).isDisplayed()){

			WebElement main = driver.findElements(By.cssSelector(".laccounts")).get(0);
			int count = main.findElements(By.cssSelector(".list_item")).size();
			System.out.println("Total no.of.accounts displayed in home page"+count);
			main.findElements(By.cssSelector(".list_item")).get(0).click();
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
		
			WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
			int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
			System.out.println(menu);	

			for (int i = 0; i < menu; i++) {

				if (modulelist.findElements(By.cssSelector(".header_l1 h4"))
						.get(i).isDisplayed()) {
					String transactiontype = modulelist
							.findElements(By.cssSelector(".header_l1 h4"))
							.get(i).getText();
					System.out.println("The header is " + transactiontype);

					if (transactiontype.equalsIgnoreCase("REJECTED")) {
						Rejectedvalue = true;
						RejectedCount = driver.findElement(
								By.xpath(prop.getProperty("RejectedCount")))
								.getText();
						RejectedCount1 = Integer.parseInt(RejectedCount.trim());
						System.out.println(RejectedCount1);
						int loopclickCount = RejectedCount1;
						WebElement rejectedmain = modulelist.findElements(
								By.cssSelector(".accordion_main")).get(2);
						rejectedmain.findElement(
								By.cssSelector(".header_l1 h4")).click();
						driver.manage().timeouts()
								.implicitlyWait(10, TimeUnit.SECONDS);
						Thread.sleep(1500);
						wait.until(ExpectedConditions
								.invisibilityOfElementLocated(By
										.className("progressing")));
						if (RejectedCount1 > 50) {
							do {
								WebElement pending = rejectedmain
										.findElement(By
												.cssSelector(".load_more"));
								pending.findElement(
										By.cssSelector(".btn_primary")).click();
								driver.manage().timeouts()
										.implicitlyWait(10, TimeUnit.SECONDS);
								Thread.sleep(1500);
								wait.until(ExpectedConditions
										.invisibilityOfElementLocated(By
												.className("progressing")));
								loopclickCount = loopclickCount - 50;
							} while (loopclickCount > 50);
						}
						RejectedCount2 = rejectedmain.findElements(
								By.cssSelector(".list_item")).size();
						System.out.println(RejectedCount1);
						Assert.assertEquals(RejectedCount1, RejectedCount2);
					}

				}

			}
			if(Rejectedvalue == false) {
				System.out.println("No Rejected transactions are available now");
			}
		}
	}
		

	/****************************************************************************************

							Test Scenarios - 3    Planned Transaction

 	****************************************************************************************/	

	@Test(priority=2,enabled=true)
	public void plannedtransaction() throws Exception{

		WebDriverWait wait = new WebDriverWait(driver,50);
	     FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);
		
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	
		if(driver.findElement(By.cssSelector(".laccounts")).isDisplayed()){

			WebElement main = driver.findElements(By.cssSelector(".laccounts")).get(0);
			int count = main.findElements(By.cssSelector(".list_item")).size();
			System.out.println("Total no.of.accounts displayed in home page"+count);
			main.findElements(By.cssSelector(".list_item")).get(0).click();
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
		
			WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
			int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
			System.out.println(menu);	

			for(int i=0;i<menu;i++){
	
				if(modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
				{
					String transactiontype = modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).getText();
					System.out.println("The header is "+transactiontype);
					if(transactiontype.equalsIgnoreCase("PLANNED"))
					{
						plannedvalue = true;
						PlannedCount=driver.findElement(By.xpath(prop.getProperty("PlannedCount"))).getText();
						PlannedCount1=Integer.parseInt(PlannedCount.trim());
						System.out.println(PlannedCount1);
						int loopclickCount = PlannedCount1;
						WebElement plannedmain=modulelist.findElements(By.cssSelector(".accordion_main")).get(1);
						plannedmain.findElement(By.cssSelector(".header_l1 h4")).click();
						Thread.sleep(1500);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
						if(PlannedCount1>50){
				     do{
				    	 
						WebElement planned = plannedmain.findElement(By.cssSelector(".load_more"));
						planned.findElement(By.cssSelector(".btn_primary")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						Thread.sleep(1000);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
						Thread.sleep(500);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
						loopclickCount=loopclickCount-50;
						}while(loopclickCount>50);
					   }
						PlannedCount2=plannedmain.findElements(By.cssSelector(".list_item")).size(); 
						System.out.println(PlannedCount2);
						Assert.assertEquals(PlannedCount1,PlannedCount2);
					} 
				}
			}
			if(plannedvalue==false){
				System.out.println("No planned transactions are available now");					
			}
		}
	}
	
	    /****************************************************************************************
 
                 Test Scenarios - 4  Search Results in EXECUTED correctly displayed or not

		 ****************************************************************************************/	
	 
	@Test(priority=3,enabled=true)
	public void bookedSearch() throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver,50);
	     FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);

//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		if(driver.findElement(By.cssSelector(".laccounts")).isDisplayed()){

			WebElement main = driver.findElements(By.cssSelector(".laccounts")).get(0);
			int count = main.findElements(By.cssSelector(".list_item")).size();
			System.out.println("Total no.of.accounts displayed in home page"+count);
			main.findElements(By.cssSelector(".list_item")).get(0).click();
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
		
			WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
			int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
			System.out.println(menu);	

			for(int i=0;i<menu;i++){
	
				if(modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
				{
					String transactiontype = modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).getText();
					System.out.println("The header is "+transactiontype);

					if(transactiontype.equalsIgnoreCase("EXECUTED"))
	   				  { 
	   			         WebElement booked=modulelist.findElements(By.cssSelector(".accordion_main")).get(3);
          	   			 if(booked.findElement(By.className("notavailable")).isDisplayed())
	   					 {
	   					   System.out.println("The Accounts doesn't have transactions");
	   					 }
	   				    else{
//	   				         	booked.findElement(By.cssSelector(".header_l1 h4")).click();
//	   				         	Thread.sleep(1500);
//	   				         	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action_icons__global span")));
	   				      
	   				    		driver.findElement(By.cssSelector(".action_icons__global span")).click();
	   				         	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".default_input_control .default_input_field input")));
	   				         	driver.findElement(By.cssSelector(".default_input_control .default_input_field input")).sendKeys("cash");
	   				         	Thread.sleep(1500);
	   				         	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search_icon")));
	   				         	driver.findElement(By.cssSelector(".search_icon")).click();
	   				         	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("SearchResult"))));
	   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   				         	WebElement result = driver.findElement(By.xpath(prop.getProperty("SearchResult")));		
	   				         	List<WebElement> allFormChildElements = result.findElements(By.cssSelector("*"));
	   				         	if(allFormChildElements.get(1).isDisplayed()){
	   				         		
	   				         		String SearchCount=allFormChildElements.get(1).getText();
	   				         		int SearchCount1=Integer.parseInt(SearchCount.trim());
	   				         		System.out.println("Count1 is "+SearchCount1);
	   				         		int SearchCount2=booked.findElements(By.cssSelector(".l_icon .list_wrapper_inner")).size(); 
	   				         		System.out.println("Count2 is "+SearchCount2);
	   				         		Assert.assertEquals(SearchCount1,SearchCount2);
	   				         	
	   				         		driver.findElement(By.cssSelector(".search_wipe")).click();
	   				         	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   				         		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   				         		Thread.sleep(1500);
	   				         		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   				         	}
	   				         	else
	   				         	{
	   				         		String SearchResults=allFormChildElements.get(3).getText();
	   				         		System.out.println("The result is "+SearchResults);
	   				         	}
	   				    	}  
	   				  }
				   }
			    }
			}
		}

	
   /***************************************************************************************************
	   				  
	    Test Scenarios - 5   Search filter results in booked transactions correctly displayed or not

	 ***************************************************************************************************/	
	   				 	 
	   @Test(priority=4,enabled=true)
	   	public void bookedFilter() throws Exception{
	   				 		
	   	  WebDriverWait wait = new WebDriverWait(driver,50);
	      FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		 		prop.load(input);
		 		
//		 	    Obj.loginPositiveflow();
		 		driver.findElement(By.cssSelector(".site_logotype")).click();
		 	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 		Thread.sleep(1500);
		 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		 		Thread.sleep(500);
		 		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);	
		 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

				if(driver.findElement(By.cssSelector(".laccounts")).isDisplayed()){

					WebElement main = driver.findElements(By.cssSelector(".laccounts")).get(0);
					int count = main.findElements(By.cssSelector(".list_item")).size();
					System.out.println("Total no.of.accounts displayed in home page"+count);
					main.findElements(By.cssSelector(".list_item")).get(0).click();
					
					driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(1000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
				
					WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
					int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
					System.out.println(menu);	
					
	   				 for(int i=0;i<menu;i++){
	   				 	
	   				 	if(modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
	   				 	{
	   				 		String transactiontype = modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).getText();
	   				 		System.out.println("The header is "+transactiontype);

	   				 		if(transactiontype.equalsIgnoreCase("EXECUTED"))
	   				 	   	{ 
	   				 			WebElement booked=modulelist.findElements(By.cssSelector(".accordion_main")).get(3);
	   				           	if(booked.findElement(By.className("notavailable")).isDisplayed())
	   				 	   		{
	   				 	   			System.out.println("The Accounts doesn't have transactions");
	   				 	   		}
	   				 	   		else{
	   				 	   		driver.findElement(By.cssSelector(".action_icons__global span")).click();
		   				        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".default_input_control .default_input_field input")));
		   				        Thread.sleep(500);	    		    	
	   				 	   		driver.findElement(By.cssSelector(".show_filters p")).click();
	   				 	   		Thread.sleep(500);	
		   				   	  for(int j=0;j<4;j++){
	   			    		   
	   			    	   	   System.out.println("The J value is: "+j);
	   			       		   driver.findElements(By.cssSelector(".filter_values p")).get(j).click();
	   			       		   WebElement filter=driver.findElements(By.cssSelector(".filter_option")).get(j);
	   			       		   wait.until(ExpectedConditions.elementToBeClickable(filter));
	   			       		   filter.findElements(By.className("filter_option_value")).get(1).click();
	   			       		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   	   			 		   Thread.sleep(1000);
	   	   				       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   	   				 			
	   			       		   if(j==1){
	   			       			    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("amount_value")));
	   			       			    	booked.findElement(By.className("amount_value")).sendKeys("500");
	   			       			    	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   			       			    	Thread.sleep(1000);
	   			       			    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		   	   				 	    	WebElement button=driver.findElements(By.cssSelector(".navigation_btns")).get(2);
	   			       			    	button.findElement(By.cssSelector("button.btn_default.btn_primary.btn_confirm")).click();
	   			       		   		 	}
	   			       		   if(j==3){
	   			       			    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("IbanInput"))));
	   			       			    	driver.findElement(By.xpath(prop.getProperty("IbanInput"))).sendKeys("BE02001800071840");	
	   			       			    	Thread.sleep(1000);
	   			       			    	driver.findElement(By.xpath(prop.getProperty("IbanConfirm"))).click();
	   			       		        	}
	   			       		   
	   			       		   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	   			       		   Thread.sleep(1500);
	   			       		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   			       	                    
	   			       		   driver.findElement(By.xpath("//*[@id='account_transactions']/div[2]/div/div/div[4]/div[4]/div[2]/div[1]/div[2]/div[2]/div/div/button[1]")).click();
	   			       		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   			       		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("SearchResult"))));
	   			       		   WebElement result = driver.findElement(By.xpath(prop.getProperty("SearchResult")));	
	   			       		   List<WebElement> allFormChildElements = result.findElements(By.cssSelector("*"));
	   			       		  
	   			       		   if(allFormChildElements.get(1).isDisplayed()){
	   			       			   
	   			       		   	   String SearchCount1=allFormChildElements.get(1).getText();
	   			       			   int SearchCount3=Integer.parseInt(SearchCount1.trim());
	   			       			   System.out.println("Count is "+SearchCount3);
	   			       			   int SearchCount4 = booked.findElements(By.cssSelector(".l_icon .list_wrapper_inner")).size(); 
	   			       			   System.out.println(SearchCount4);
	   			       			   Assert.assertEquals(SearchCount3,SearchCount4);
	   			       			   
	   			       		      }
	   			       		   else{

	   				        	   String SearchResults=allFormChildElements.get(3).getText();
	   				    	       System.out.println("The result is "+SearchResults);
	   			  	              
	   				          	}
	   			       		   driver.findElement(By.xpath("//*[@id='account_transactions']/div[2]/div/div/div[4]/div[4]/div[2]/div[1]/div[2]/div[2]/div/div/button[2]")).click();
	   			       		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	   			       		   
	   			       		}//Closing the header for-loop 
	   			
	   				    }//Closing Valid transactions else loop
	   	          
	   			    }//Executed- CHECKING IF-LOOP
	   		   
	   	         }//header validation
	   	    
	   		}//header-for loop
	   			
	   	}//A/c's home page
	   				 
	}//Closing the test
	

		
	   /***************************************************************************************************
		   				  
		    					Test Scenarios - 6   Transaction Tabs (E-Statements)

		***************************************************************************************************/	
		   				 	 
     @Test(priority=5,enabled=true)
		public void transactionStatementsTab() throws Exception{
			
		WebDriverWait wait = new WebDriverWait(driver,50);
	     FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);
		
//	    Obj.loginPositiveflow();
 		driver.findElement(By.cssSelector(".site_logotype")).click();
 	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
 		Thread.sleep(1500);
 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 		Thread.sleep(500);
 		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);	
 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 		
 		
 		if(driver.findElement(By.cssSelector(".laccounts")).isDisplayed()){
			
			WebElement home = driver.findElements(By.cssSelector(".laccounts")).get(0);
			int count = home.findElements(By.cssSelector(".list_item")).size();
			System.out.println(count);
    		home.findElements(By.cssSelector(".list_item")).get(0).click();
    		
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
			
			WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
			int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
			System.out.println(menu);	
 			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				
      
	        driver.findElements(By.cssSelector(".eb_tab_inner")).get(1).click();
	        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	        Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		  
			WebElement statements = driver.findElement(By.className("estatements"));
	       
	       	 // Total no.of Years Displayed
			
	       	 int Yearcount = statements.findElements(By.cssSelector(".accordion_l1__inner")).size();
	    	 System.out.println("Total no.of.years displayed : "+Yearcount);
	     
	    	 // Checking all the years in E-statements	
	    	
	    	 for(int k=0;k<Yearcount;k++){
	    
	    		 if(k>0)
	    		 {
	    	     	statements.findElements(By.cssSelector(".accordion_l1__inner")).get(k).click();
	    	     	Thread.sleep(1500);
	    			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    			Thread.sleep(1000);
	    			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
  	       	      }
	    	 
	    		WebElement main= statements.findElements(By.cssSelector(".content_l1")).get(k);
	       	    num = main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).size();
			    System.out.println("Total No.of.Months is "+num);
	            
			    if(num>3){
			        	
			    	WebElement we=main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(0);
			        if(k==0)
			        {
		    		  Assert.assertEquals(yourDate1,we.getText());	 
		              System.out.println("Last three months transaction displayed successfully");
		    	      statements.findElement(By.cssSelector(".showall_months")).click();
		              Thread.sleep(1000);
		              wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		              Thread.sleep(1500);
		              num1=main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).size();
		              System.out.println(num1);
			          if(num1>num)
			          {
			          	System.out.println("All months displayed successfully");
			           	for(int i=0;i<num1;i++)			        	
			           	{ 
			              	month=main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(i).getText();
			              	WebElement eStat = main.findElements(By.cssSelector(".accordion_l2__inner")).get(i);
			              	transCount = eStat.findElements(By.cssSelector("*")).get(2).getText();
			              	transCount1=Integer.parseInt(transCount.trim());
			              	if(transCount1>0)
			              	{
			              	   System.out.println("This "+month+" month having "+transCount1+" transactions");  
			              	   if(i>0)
			              	   {
			               	 	 statements.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(i).click(); 
			               	   }
			               	     WebElement transCount= main.findElements(By.className("accordion_list")).get(i);
			               	 	 transCount2=transCount.findElements(By.cssSelector(".listiban_group")).size();
			               	 	 Assert.assertEquals(transCount1,transCount2);
			               	 	 System.out.println("No of transactions are corectly displayed");
			               	 	 transCount.findElements(By.cssSelector(".listiban_group")).get(0).click();
			               	 	 Thread.sleep(1500);
			                	 	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			                	 	 Thread.sleep(2000);
			                 }
			              }
			          }
			        }else{
			       		for(int i=0;i<num;i++)			        	
			       		{ 
			       			month=main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(i).getText();
			       			WebElement eStat = main.findElements(By.cssSelector(".accordion_l2__inner")).get(i);
			       			transCount = eStat.findElements(By.cssSelector("*")).get(2).getText();
			       			transCount1=Integer.parseInt(transCount.trim());
			       			if(transCount1>0)
			       			{
			       			  System.out.println("This "+month+" month having "+transCount1+" transactions");  
			       			  if(i>0 || k>0){
			       				  
			       				  main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(i).click(); 
			       			  }
			       			  WebElement transCount= main.findElements(By.className("accordion_list")).get(i);
			       			  transCount2=transCount.findElements(By.cssSelector(".listiban_group")).size();
			       			  System.out.println(transCount2);
			       			  Assert.assertEquals(transCount1,transCount2);
			       			  System.out.println("No of transactions are corectly displayed");
			       			  Thread.sleep(1500);
			       			  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			       			  Thread.sleep(1000);
			       			  transCount.findElements(By.cssSelector(".listiban_group")).get(0).click();
			       			  Thread.sleep(1000);
            	 			  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
            	 			  Thread.sleep(2000);
			       			}
			       		}
			       	}
			    }
			 else
			    {
				  int click=0;
			      for(int i=0;i<num;i++)			        	
				    { 
				      month=main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(i).getText();
				      System.out.println(month);
				      WebElement eStat = main.findElements(By.cssSelector(".accordion_l2__inner")).get(i);
					  transCount = eStat.findElements(By.cssSelector("*")).get(2).getText();
				      transCount1=Integer.parseInt(transCount.trim());
					  if(transCount1>0)
					  {
					   System.out.println("This "+month+" month having "+transCount1+" transactions");  
				        if(click>0){
						main.findElements(By.cssSelector(prop.getProperty("MonthsHeader"))).get(i).click(); }
				//	   WebElement transCount= statements.findElements(By.className("accordion_list")).get(i);
					   WebElement transCount= main.findElements(By.className("accordion_list")).get(i);
					   transCount2=transCount.findElements(By.cssSelector(".listiban_group")).size();
					   System.out.println(transCount2);
					   Assert.assertEquals(transCount1,transCount2);
					   System.out.println("No of transactions are corectly displayed");
					   Thread.sleep(1500);
					   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					   Thread.sleep(1000);
					   transCount.findElements(By.cssSelector(".listiban_group")).get(0).click();
       	 			   Thread.sleep(1000);
       	 			   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
       	 			   Thread.sleep(2000);
       	 			   click++;
		              }
			       }
	    	  
	            }//Else loop is closing
	   
	         }// Closing for-loop years
	   
	      }//A/c home if-loop   	
		   
		    Robot r = new Robot();
	        r.keyPress(KeyEvent.VK_ENTER);
	        r.keyRelease(KeyEvent.VK_ENTER);  
	        Thread.sleep(2000);
  	      
	    }//function 6
	    
	    
	    
		   /***************************************************************************************************
			  
								Test Scenarios - 7   Transaction Tabs (Details Tab)

		    ***************************************************************************************************/	
	 	 
	   @Test(priority=6,enabled=true)
	    public void transactiondetailsTab() throws Exception{

	    	WebDriverWait wait = new WebDriverWait(driver,50);
	        FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
	    	prop.load(input);

//	 	    Obj.loginPositiveflow();
	 		driver.findElement(By.cssSelector(".site_logotype")).click();
	 	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	 		Thread.sleep(1500);
	 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 		Thread.sleep(500);
	 		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);	
	 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	 		
	    	if(driver.findElement(By.cssSelector(".accordion_main")).isDisplayed()){

	    		WebElement home = driver.findElements(By.cssSelector(".accordion_main")).get(0);
	    		int count = home.findElements(By.cssSelector(".list_item")).size();
	    		System.out.println(count);
	    		home.findElements(By.cssSelector(".list_item")).get(0).click();

	    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    		Thread.sleep(1500);
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));


	    		driver.findElements(By.cssSelector(".eb_tab_inner")).get(2).click();
	    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    		Thread.sleep(500);
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    		Thread.sleep(1000);
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

				String SelectedAc = driver.findElement(By.cssSelector(".selected_details__account")).getText();
				System.out.println(SelectedAc);
				String AccountNameHome = driver.findElement(By.xpath(".//*[@id='accountsHeader']/div/div/div/div[2]/div/div[1]/div/div/div[1]/div[2]/p[3]")).getText();
				System.out.println(AccountNameHome);
				String AmountHome=driver.findElement(By.cssSelector(".module_selected_value span")).getText();
				System.out.println(AmountHome);
			    String TabName = driver.findElements(By.cssSelector(".eb_tab_inner")).get(1).getText();
		        System.out.println(TabName);
		        
	        	 WebElement details=driver.findElement(By.cssSelector(".details_inner"));
	        	 String AccountName= details.findElements(By.cssSelector(".listitem_details p")).get(0).getText();
	        	 Assert.assertEquals(AccountName,AccountNameHome);
	        	 String IBAN= details.findElements(By.cssSelector(".listitem_details p")).get(1).getText();
	        	 Assert.assertEquals(IBAN,SelectedAc);
	        	 String Balance= details.findElements(By.cssSelector(".listitem_details p")).get(4).getText();
	    		 Balance = Balance.substring(0,Balance.length()-4);
	        	 System.out.println(Balance);
	        	 Assert.assertEquals(Balance,AmountHome);
	
	    	}
	    }
	    
	    
		   /***************************************************************************************************
			  
								Test Scenarios - 8  Transaction To Send Money

		    ***************************************************************************************************/	
	 	 
	    @Test(priority=7,enabled=true)
	    public void transactionsToSCT() throws Exception{

	    	WebDriverWait wait = new WebDriverWait(driver,50);
	        FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
	    	prop.load(input);
	    
	    	plannedtransaction();
	    	Thread.sleep(1500);
			
	    	if(plannedvalue==true){
	    		
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("module_listitem")));
	    		WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
	    		WebElement plannedmain = modulelist.findElements(By.cssSelector(".accordion_main")).get(1);
	    		
	    		for(int s=0;s<PlannedCount2;s++){
	    			
	    		String TranType = plannedmain.findElements(By.cssSelector(".listiban_group")).get(s).getText();
	    		System.out.println(TranType);
	    			
	    		if(TranType.equalsIgnoreCase("SEPA TRANSFER")){
	    				
	    				planfinal=true;
	    				WebElement select = plannedmain.findElements(By.cssSelector(".list_item")).get(s);
	    				select.click();
	    				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	    		Thread.sleep(700);
	    	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    	    		
	    	    		select.findElement(By.cssSelector(".btn_default:first-child")).click();
	    	    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    				Thread.sleep(1500);
	    				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    				
	    				   WebElement sepa = driver.findElement(By.id("sepa"));
	    			       String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
	    				   System.out.println(Text1);
	    				
	    				   sepa.findElement(By.id("enterAmount")).clear();
	    				   sepa.findElement(By.id("enterAmount")).sendKeys("13");
	    				   Thread.sleep(500);
	    				   
	    				   sepa.findElement(By.id("enterDate")).clear();
	    				   sepa.findElement(By.id("enterDate")).sendKeys("08092015");
	    				   Thread.sleep(500);
	    				 
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
	    		 	   	   
	    		 	       sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
	    		 	       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    			       Thread.sleep(500);
	    			       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    		 	   	   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
	    		 	   	   
	    		 	       String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
	    				   Thread.sleep(500);
	    				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    				   System.out.println(result);
	    		           break;
	    		   	}
	    		}if (planfinal == false) {
					System.out.println("No 'Sepa Transfer' transactions are available now");
			}
    	}
    }
	    
  		   /***************************************************************************************************
			  
								Test Scenarios - 9  Transaction To Standing Order 

		    ***************************************************************************************************/	
	 	 
	   @Test(priority=8,enabled=true)
	    public void transactionsToSTO() throws Exception{

	    	WebDriverWait wait = new WebDriverWait(driver,50);
	        FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
	    	prop.load(input);
	        boolean errormsg = false;
	        
	    	plannedtransaction();
	    	
	    	Thread.sleep(1500);
	    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountsHeader")));
	       	WebElement header = driver.findElement(By.id("accountsHeader"));
	       	
	    	String SelectedAc = header.findElement(By.cssSelector(".accounts_title")).getText();
	       	System.out.println(SelectedAc);
	    	String AccountNameHome = driver.findElement(By.xpath(".//*[@id='accountsHeader']/div/div/div/div[2]/div/div[1]/div/div/div[1]/div[2]/p[3]")).getText();
	    	System.out.println(AccountNameHome);
	    	String AmountHome=header.findElements(By.cssSelector(".module_selected_value span")).get(0).getText();
	    	System.out.println(AmountHome);
	    	
	    	if(plannedvalue==true){
	    		
	    		WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
	    		WebElement plannedmain = modulelist.findElements(By.cssSelector(".accordion_main")).get(1);
	    		
    		
	    		for(int s=0;s<PlannedCount2;s++){
	    		String TranType = plannedmain.findElements(By.cssSelector(".listiban_group")).get(s).getText();
	    		System.out.println(TranType);
	    			
	    			if(TranType.equalsIgnoreCase("STANDING ORDER")){
	    				
	    				planfinal=true;
	    				WebElement select = plannedmain.findElements(By.cssSelector(".list_item")).get(s);
	    				select.click();
	    				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	    		Thread.sleep(700);
	    	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    	    		
	    	    		select.findElement(By.cssSelector(".btn_default:first-child")).click();
	    	    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    				Thread.sleep(1500);
	    				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("details_inner")));
	    				
	    				 WebElement details=driver.findElement(By.cssSelector(".details_inner"));
	    	        	 String AccountName= details.findElements(By.cssSelector(".listitem_details p")).get(0).getText();
	    	        	 Assert.assertEquals(AccountName,AccountNameHome);
	    	        	 String IBAN= details.findElements(By.cssSelector(".listitem_details p")).get(1).getText();
	    	        	 Assert.assertEquals(IBAN,SelectedAc);
	    	        	 String Balance= details.findElements(By.cssSelector(".listitem_details p")).get(5).getText();
	    	    		 System.out.println(Balance);
	    	    		 Balance = Balance.substring(0,Balance.length()-7);
	    	    		 int Balance1 = Integer.parseInt(Balance);
	    	        	 Balance1 = Balance1+3;
	    	             Balance = String.valueOf(Balance1);
	    	           
	    	             driver.findElements(By.cssSelector(".btn_primary")).get(0).click();
	    	        	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    			 Thread.sleep(1000);
		    			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form_inner")));
	    	        	 
		    			 driver.findElement(By.id("enterAmount")).clear();
		    			 driver.findElement(By.id("enterAmount")).sendKeys(Balance);
		    			 Thread.sleep(600);
		    			 
		    			 driver.findElements(By.cssSelector(".btn_primary")).get(0).click();
		    			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    	        	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		    			 Thread.sleep(800);
		    					    			 
		    			 
//		    			 try{
//		    				    errormsg = driver.findElement(By.cssSelector(".alert_messages__inner")).isDisplayed();
//		    				    if(errormsg==true){
//		    				      System.out.println("No Change have been made");
//		    				      
//		    				     driver.findElement(By.id("enterAmount")).clear();
//		    				     Thread.sleep(500);
//		 		    			 driver.findElement(By.id("enterAmount")).sendKeys(Balance);
//		 		    			 Thread.sleep(1000);
//		 		    			 driver.findElements(By.cssSelector(".btn_primary")).get(0).click();
//		 	    	        	 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
//		 		    					 		    			
//		 		   		     }
//		    			 }catch(NoSuchElementException e){
//		    				          
//		    				    	System.out.println("Standing order amount changed");
//		    				   }
		    			
		    			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("details_inner")));
		    			 
		    			         WebElement sepaSTO = driver.findElement(By.id("standorder"));
		    			         
		    			   try{
		    				     boolean id = false;
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
		    			 break;
	    		   		}
	    			}if (planfinal == false){
	    				
					System.out.println("No 'Standing order' transactions are available now");
	    			
	    			}
	    		}
	    	}
	    
	   /***************************************************************************************************
	  
						Test Scenarios - 10  Transaction To Direct Debits 

	    ***************************************************************************************************/	

@Test(priority=9,enabled=true)
public void transactionsToSepaDD() throws Exception{

	WebDriverWait wait = new WebDriverWait(driver,50);
    FileInputStream input = new FileInputStream(filepath
			+ "Object Repository/TransactionData.properties");
	prop.load(input);
    boolean errormsg = false;
 
	plannedtransaction();
	Thread.sleep(1500);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountsHeader")));
   	WebElement header = driver.findElement(By.id("accountsHeader"));
//	String SelectedAc = header.findElement(By.cssSelector(".selected_details__account")).findElement(By.cssSelector(".accounts_title")).getText();
	String SelectedAc = header.findElement(By.cssSelector(".accounts_title")).getText();
   	System.out.println(SelectedAc);
	String AccountNameHome = driver.findElement(By.xpath(".//*[@id='accountsHeader']/div/div/div/div[2]/div/div[1]/div/div/div[1]/div[2]/p[3]")).getText();
	System.out.println(AccountNameHome);
	String AmountHome=header.findElements(By.cssSelector(".module_selected_value span")).get(0).getText();
	System.out.println(AmountHome);
	
	if(plannedvalue==true){
		
		WebElement modulelist = driver.findElements(By.className("module_listitem")).get(1); 
		WebElement plannedmain = modulelist.findElements(By.cssSelector(".accordion_main")).get(1);
		
//		WebElement modulelist = driver.findElement(By.className("module_listitem")); 
//		WebElement plannedmain = modulelist.findElements(By.cssSelector(".accordion_main")).get(1);
	
		for(int s=0;s<PlannedCount2;s++){
		String TranType = plannedmain.findElements(By.cssSelector(".listiban_group")).get(s).getText();
		System.out.println(TranType);
			
			if(TranType.equalsIgnoreCase("SEPA DIRECT DEBIT")){
				
				planfinal=true;
				WebElement select = plannedmain.findElements(By.cssSelector(".list_item")).get(s);
				select.click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    		Thread.sleep(700);
	    		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    		
	    		select.findElement(By.cssSelector(".btn_default:first-child")).click();
	    		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(800);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("module_content")));
				
				
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
 				
 				WebElement SepaDD = driver.findElement(By.id("sepadd"));
 				WebElement mandate1 = SepaDD.findElement(By.cssSelector(".details"));
 				String IBAN1 = mandate1.findElements(By.cssSelector(".listitem_details span")).get(0).getText().trim();
	   
 				WebElement mandate = SepaDD.findElements(By.cssSelector(".detail_group")).get(1);
 				String Beneficiary1 = mandate.findElements(By.cssSelector(".listitem_details")).get(0).getText().trim();
 				String Identifier1 = mandate.findElements(By.cssSelector(".listitem_details")).get(1).getText().trim();
	    
 				Assert.assertEquals(IBAN,IBAN1);
 				Assert.assertEquals(Beneficiary,Beneficiary1);
 				Assert.assertEquals(Identifier,Identifier1);
       			break;    			 
			 }
			
		 }if (planfinal == false){
			 System.out.println("No 'Sepa DD' transactions are available now");	}
		}
	}

  
	   
	    /****************************************************************************************
 
                 Test Scenarios - 11  Print the transactions

		 ****************************************************************************************/	
	 
	    @Test(priority=10,enabled=true)
		public void printbooked() throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver,50);
	     FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		prop.load(input);

//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

		if(driver.findElement(By.cssSelector(".accordion_main")).isDisplayed()){

			WebElement main = driver.findElements(By.cssSelector(".accordion_main")).get(0);
			int count = main.findElements(By.cssSelector(".list_item")).size();
			System.out.println("Total no.of.accounts displayed in home page : "+count);
			main.findElements(By.cssSelector(".list_item")).get(0).click();
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			Thread.sleep(1500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			
			WebElement modulelist = driver.findElement(By.className("module_listitem")); 
			int menu= modulelist.findElements(By.cssSelector(".header_l1 h4")).size();
			System.out.println(menu);	

			for(int i=0;i<menu;i++){
	
				if(modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
				{
					String transactiontype = modulelist.findElements(By.cssSelector(".header_l1 h4")).get(i).getText();
					System.out.println("The header is "+transactiontype);

					if(transactiontype.equalsIgnoreCase("EXECUTED"))
	   				  { 
	   			         WebElement booked=modulelist.findElements(By.cssSelector(".accordion_main")).get(3);
          	   			 if(booked.findElement(By.className("notavailable")).isDisplayed())
	   					 {
	   					   System.out.println("The Accounts doesn't have transactions");
	   					 }
	   				    else{
	   				   
   				         	driver.findElement(By.xpath(".//*[@id='account_transactions']/div[2]/div/div/div[3]/div[4]/div[1]/div[2]/span[4]/span")).click();
   				         	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   				     	    Thread.sleep(800);
   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
   				         	Thread.sleep(500);
   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
   				         
   				         	Robot r = new Robot();
   					        r.keyPress(KeyEvent.VK_ESCAPE);
   					        r.keyRelease(KeyEvent.VK_ESCAPE);  
   					        Thread.sleep(1000);
   					
   				         	driver.findElement(By.xpath(".//*[@id='account_transactions']/div[2]/div/div/div[3]/div[4]/div[1]/div[2]/span[3]/span")).click();
   				         	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
   				     	    Thread.sleep(800);
   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
   				         	Thread.sleep(500);
   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		         
   				         	driver.findElement(By.xpath(".//*[@id='account_transactions']/div[2]/div/div/div[3]/div[4]/div[1]/div[2]/span[2]/span")).click();
   				         	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
   				     	    Thread.sleep(800);
   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
   				         	Thread.sleep(500);
   				         	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 					        
	   				      }
          	   		   }
					}
				}
			}
		}
	    
	    
  	} // Closing this class
