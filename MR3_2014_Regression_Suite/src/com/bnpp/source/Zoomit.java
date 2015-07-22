package com.bnpp.source;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import org.testng.annotations.Test;

import com.bnpp.data.DataWeb;

public class Zoomit  extends DataWeb{
	

	LoginModule Obj = new LoginModule();
	String Screenshotpath = System.getProperty("user.dir")+"Screenshots/Zoomit/";
	
    
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
	/*//  For android Mobile Browser
      DesiredCapabilities caps = DesiredCapabilities.android();
      caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
      driver = new AndroidDriver(caps);
      driver.get(BaseUrl);*/
}
	/****************************************************************************************
	 
									After Method

	 ****************************************************************************************/

	//@AfterTest(alwaysRun=true)
	 public void screenShot() throws Exception {
		    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile,new File(Screenshotpath+"Zoomit_"+yourDate+".jpg"));
     }
	
	/****************************************************************************************
	 
   									After Class

	 ****************************************************************************************/

	@AfterClass(alwaysRun=true)
	public void tear() throws Exception {
		driver.quit();
	}

	/****************************************************************************************

							Test Scenarios - 1  Zoomit Invoices - Pay Now

	 ****************************************************************************************/	

	@Test(priority=0,enabled=true)
	public void zoomitInvoices1() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	    boolean check=false;
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    Obj.loginPositiveflow();
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
 
	    // locate the menu to hover over using its xpath
	
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();

     			if(zoomittype.equalsIgnoreCase("INVOICES"))
                {          
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String InvoiceCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
				   int InvoiceCount1=Integer.parseInt(InvoiceCount.trim());
	      		   System.out.println("The Invoice list count1 is "+InvoiceCount1);
			 
	      		    WebElement Invoicemain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int InvoiceCount2 =Invoicemain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Invoice list count2 is "+InvoiceCount2);
	      		    Assert.assertEquals(InvoiceCount2,InvoiceCount1);
	      		    
	      		   for(int j=0;j<InvoiceCount2;j++){
	      			
	       	        jse.executeScript("window.scrollBy(0,260)","");
	      	        Thread.sleep(500);
	      		    Invoicemain.findElements(By.cssSelector(".listiban_group")).get(j).click();
	      		    Thread.sleep(1300);
	      		  
	      		    String IBANDetails = Invoicemain.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
	      		    Assert.assertEquals(IBANDetails,IBANHome);
	      		    
					WebElement PayOption = Invoicemain.findElements(By.cssSelector(".list_item")).get(j).findElement(By.cssSelector(".navigation_btns"));
				    int optionCheck = PayOption.findElements(By.cssSelector("*")).size();
					System.out.println("The option for "+j+" element is "+optionCheck);
					
					if(optionCheck>3){
				
						PayOption.findElement(By.cssSelector(".btn_default:first-child")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						Thread.sleep(500);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sepa")));
				  	    Thread.sleep(500);
				  	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				  	    
						WebElement sepa = driver.findElement(By.id("sepa"));
				        String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
					    System.out.println(Text1);
			    
				   
					    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 	    Thread.sleep(1000);
				 	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
				 	 	 
					    try{
				     		 sepa.findElement(By.className("sign_input")).click();
							 Thread.sleep(10000);         
						    }
						 catch(NoSuchElementException e){
						     System.out.println("No foreign beneficiaries");
							 }
						
				 	   	sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
				 	   	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 	   	Thread.sleep(1000);
				 	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
			 	   	   
				 	   	String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
				 	   	Thread.sleep(500);
				 	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 	   	System.out.println(result);
					   
					    driver.findElements(By.cssSelector(".btn_secondary")).get(0).click();
					    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				   	    Thread.sleep(800);
				   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
				   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					
				   	    driver.findElement(By.cssSelector(".refresh_txt")).click();
						driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
						Thread.sleep(800);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
						Thread.sleep(300);
						driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
				   	    String InvoiceCountafter = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
				   	    int InvoiceCount3=Integer.parseInt(InvoiceCountafter.trim());
				   	    System.out.println("The Invoice list count is "+InvoiceCount3);
				   	    Assert.assertEquals(InvoiceCount1-1,InvoiceCount3);
				   	    check=true;
				   	    break;
					  }
	     		    }
	    		    driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/span[2]")).click();
	      		    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  	       	    Thread.sleep(800);
	  	       	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	       	    Thread.sleep(300);
	  	       	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
                 } 
	  		  }if(check==true) break;
		   }
		}

	
	/****************************************************************************************

						 Test Scenarios - 2  Zoomit Invoices - Already Paid

	 ****************************************************************************************/	

	@Test(priority=1,enabled=true)
	public void zoomitInvoices2() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	   
	    JavascriptExecutor jse = (JavascriptExecutor)driver;

//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
 
	    // locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();

				
				if(zoomittype.equalsIgnoreCase("INVOICES"))
                {          
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String InvoiceCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
				   int InvoiceCount1=Integer.parseInt(InvoiceCount.trim());
	      		   System.out.println("The Invoice list count1 is "+InvoiceCount1);
			 
	      		    WebElement Invoicemain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int InvoiceCount2 =Invoicemain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Invoice list count2 is "+InvoiceCount2);
	      		    Assert.assertEquals(InvoiceCount2, InvoiceCount1);
	      		    
	      		   for(int j=0;j<InvoiceCount2;j++){
	      			
	       	        jse.executeScript("window.scrollBy(0,260)","");
	      	        Thread.sleep(500);
	      		    Invoicemain.findElements(By.cssSelector(".listiban_group")).get(j).click();
	      		    Thread.sleep(1300);
	      		  
	      		    String IBANDetails = Invoicemain.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
	      		    Assert.assertEquals(IBANDetails,IBANHome);
	      		    
					WebElement PayOption = Invoicemain.findElements(By.cssSelector(".list_item")).get(j).findElement(By.cssSelector(".navigation_btns"));
				    int optionCheck = PayOption.findElements(By.cssSelector("*")).size();
					System.out.println("The option for "+j+" element is "+optionCheck);
					
					if(optionCheck>3){
				
						PayOption.findElements(By.cssSelector(".btn_primary")).get(1).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						Thread.sleep(500);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.className("details_inner")));
				  	    Thread.sleep(500);
				  	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			   
					    driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[3]/div/div[2]/div/div/div/button[1]")).click();
					    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				   	    Thread.sleep(400);
				   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
				     	Thread.sleep(400);	
				   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	
				   	    driver.findElement(By.cssSelector(".refresh_txt")).click();
						driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
						Thread.sleep(800);
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
						Thread.sleep(300);
						driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
				   	    String InvoiceCountafter = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
				   	    int InvoiceCount3=Integer.parseInt(InvoiceCountafter.trim());
				   	    System.out.println("The Invoice list count is "+InvoiceCount3);
				   	    Assert.assertEquals(InvoiceCount1-1,InvoiceCount3);
				   	    break;
					  }
	     		    }
	      		   
	      		    driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/span[2]")).click();
	      		    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  	       	    Thread.sleep(800);
	  	       	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	       	    Thread.sleep(300);
	  	       	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
                 }
	  		  }
		   }
		}



	/****************************************************************************************

							Test Scenarios - 3  Zoomit Invoices View

	 ****************************************************************************************/	

	@Test(priority=2,enabled=true)
	public void zoomitInvoices3() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	
//	    Obj.loginPositiveflow();
	    boolean IsabelCheck1=false;
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(400);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
   	    Thread.sleep(1500);
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
			
				if(zoomittype.equalsIgnoreCase("INVOICES"))
                {             
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String InvoiceCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
				   int InvoiceCount1=Integer.parseInt(InvoiceCount.trim());
	      		   System.out.println("The Invoice list count1 is "+InvoiceCount1);
			 
	      		    WebElement Invoicemain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int InvoiceCount2 =Invoicemain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Invoice list count2 is "+InvoiceCount2);
	      		    Assert.assertEquals(InvoiceCount2, InvoiceCount1);	
	      		    
					Invoicemain.findElements(By.cssSelector(".lnk_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(800);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);
					try{
						
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:lnkZoomitLogo")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					IsabelCheck1=true;
					
					}catch(Exception isabel){
						
						System.out.println("Isabel site is not loaded");
					}	
					
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(800);
									
					System.out.println(driver.findElement(By.cssSelector(".alert_messages__inner")).getText().trim());
					
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	      		    
					//  Checking the Isabel site is loaded or not
					
					Assert.assertEquals(IsabelCheck1,true);
                 }
			  }
		  }
	 }

	
	/****************************************************************************************

							Test Scenarios - 4  Zoomit Direct Debit View

	 ****************************************************************************************/	

	@Test(priority=3,enabled=true)
	public void zoomitDirectdebit() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	
	     boolean IsabelCheck2=false;
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
			
				
				if(zoomittype.equalsIgnoreCase("DIRECT DEBITS"))
                {             
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String InvoiceCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[3]/div[1]/div/span[2]")).getText();
				   int InvoiceCount1=Integer.parseInt(InvoiceCount.trim());
	      		   System.out.println("The Invoice list count1 is "+InvoiceCount1);
			 
	      		    WebElement Invoicemain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int InvoiceCount2 =Invoicemain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Invoice list count2 is "+InvoiceCount2);
	      		    Assert.assertEquals(InvoiceCount2, InvoiceCount1);	
	      		    
					Invoicemain.findElements(By.cssSelector(".lnk_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(1000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);
					try{
						
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:lnkZoomitLogo")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					IsabelCheck2=true;
				
					}catch(Exception isabel){
						System.out.println("Isabel site is not loaded");
					}	
				
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(800);
					
					System.out.println(driver.findElement(By.cssSelector(".alert_messages__inner")).getText().trim());
					
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	      		    
					//  Checking the Isabel site is loaded or not
					
					Assert.assertEquals(IsabelCheck2,true);
                 }
			  }
		  }
	 }


	/****************************************************************************************

								 Test Scenarios - 5  Senders

	 ****************************************************************************************/	

	@Test(priority=4,enabled=true)
	public void zoomitSenders() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);

	    boolean IsabelCheck3=false;
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
				
				
				if(zoomittype.equalsIgnoreCase("SENDERS"))
                {												
				   System.out.println("The header is "+zoomittype);
				   String SendersCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[4]/div[1]/div/span[2]")).getText();
				   int SendersCount1=Integer.parseInt(SendersCount.trim());
	      		   System.out.println("The Senders list count is "+SendersCount1);
			 
	      		    WebElement Sendersmain = listitem.findElements(By.cssSelector(".accordion_main")).get(3);
	      		    int SendersCount2 = Sendersmain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Senders list count is "+SendersCount2);
	      		    Assert.assertEquals(SendersCount2, SendersCount1);
	      		    
	      		    Sendersmain.findElements(By.cssSelector(".listiban_group")).get(0).click();
	      		    Thread.sleep(1000);
	      		                                                   
	      		    String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
	      		    String IBANDetails = Sendersmain.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
	      		  
	      		    Assert.assertEquals(IBANDetails,IBANHome);
	      		    
	      		    Sendersmain.findElements(By.cssSelector(".btn_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(1000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
					
				 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);
						   
					try{
						
					   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:zoomitLogo")));
					   Thread.sleep(500);
					   driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					   IsabelCheck3=true;	     
		    	   
			           }catch(Exception e){
	
			        	  System.out.println("Isabel site is not loaded properly"); 
			           }
			       
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(1000);
					  
					System.out.println(driver.findElement(By.cssSelector(".alert_messages__inner")).getText().trim());
								
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					
					Assert.assertEquals(IsabelCheck3, true);
			   }
			}
		}
	}
	

		/****************************************************************************************

							Test Scenarios - 6  PaySlips with Accept(Pay Now)

		 ****************************************************************************************/	

	@Test(priority=5,enabled=true)
	public void zoomitPaySlips1() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);

	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    boolean check=false;
	    
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){

			if(listitem.findElements(By.cssSelector(".accordion_l1__inner")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".accordion_l1__inner")).get(i).findElement(By.cssSelector(".header_l1 h4")).getText().trim();
				
				if(zoomittype.equalsIgnoreCase("PAYSLIPS"))
                {												
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String PayslipsCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/span[2]")).getText();
				   int PayslipsCount1=Integer.parseInt(PayslipsCount.trim());
	      		   System.out.println("The Payslips list count1 is "+PayslipsCount1);
			 
	      		   WebElement Payslipsmain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		   int PayslipsCount2 = Payslipsmain.findElements(By.cssSelector(".listiban_group")).size();
	      		   System.out.println("The Payslips list count2 is "+PayslipsCount2);
	      		   Assert.assertEquals(PayslipsCount2, PayslipsCount1);
	      		    
	      		   for(int j=0;j<PayslipsCount2;j++){

	      	       jse.executeScript("window.scrollBy(0,270)","");
	      	       Thread.sleep(500);
	      	       Payslipsmain.findElements(By.cssSelector(".listiban_group")).get(j).click();
	      		   Thread.sleep(1300);
	      		  
	      		   String IBANDetails = Payslipsmain.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
	      		   Assert.assertEquals(IBANDetails,IBANHome);
	      		   
				   WebElement PayOption = Payslipsmain.findElements(By.cssSelector(".list_item")).get(j).findElement(By.cssSelector(".navigation_btns"));
				   int optionCheck = PayOption.findElements(By.cssSelector("*")).size();
				   System.out.println("The option for "+j+" element is "+optionCheck);
					
				   if(optionCheck>3){
						
						PayOption.findElement(By.cssSelector(".btn_default:first-child")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						Thread.sleep(500);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sepa")));
				  	    Thread.sleep(500);
				  	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				  	    
						WebElement sepa = driver.findElement(By.id("sepa"));
				        String Text1 = sepa.findElement(By.cssSelector(".module_title")).getText();
					    System.out.println(Text1);
			    
					    sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 	    Thread.sleep(1000);
				 	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("trnsfr_confirm")));
				 	 	 
					    try{
				     		 sepa.findElement(By.className("sign_input")).click();
							 Thread.sleep(10000);         
						    }
						 catch(NoSuchElementException e){
						     System.out.println("No foreign beneficiaries");
							 }
						
				 	   	sepa.findElement(By.cssSelector(".right_navigation_btn .btn_default")).click();
				 	   	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 	   	Thread.sleep(1000);
				 	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 	   	wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));
			 	   	   
				 	   	String result = driver.findElement(By.cssSelector(".acknowledge_info h2")).getText().trim();
				 	   	Thread.sleep(500);
				 	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 	   	System.out.println(result);
					   
					    driver.findElements(By.cssSelector(".btn_secondary")).get(0).click();
					    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
				   	    Thread.sleep(800);
				   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				   	    Thread.sleep(200);
				   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					
				   	    driver.findElement(By.cssSelector(".refresh_txt")).click();
				   	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
				   	    Thread.sleep(800);
				   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				   	    Thread.sleep(200);
				   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
																	
				   	    String PayslipsCountafter = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/span[2]")).getText();
				   	    int PayslipsCount3=Integer.parseInt(PayslipsCountafter.trim());
				   	    System.out.println("The PaySlips list count is "+PayslipsCount3);
				   	    Assert.assertEquals(PayslipsCount1-1,PayslipsCount3);
	    		   	    check=true;
				   	    break;
				   	    }
	      		    }
 	       	        driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/span[2]")).click();
	      		    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  	       	    Thread.sleep(800);
	  	       	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	       	    Thread.sleep(300);
	  	       	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
                 } 
			}  if(check==true) break;
		}
	}
	
	
		/****************************************************************************************

							Test Scenarios - 7  PaySlips with already paid

		 ****************************************************************************************/	

	@Test(priority=6,enabled=true)
	public void zoomitPaySlips2() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);

	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    boolean check=false;
	    
//	    Obj.loginPositiveflow();
		
	    driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(500);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){

			if(listitem.findElements(By.cssSelector(".accordion_l1__inner")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".accordion_l1__inner")).get(i).findElement(By.cssSelector(".header_l1 h4")).getText().trim();
				
				if(zoomittype.equalsIgnoreCase("PAYSLIPS"))
                {												
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String PayslipsCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/span[2]")).getText();
				   int PayslipsCount1=Integer.parseInt(PayslipsCount.trim());
	      		   System.out.println("The Payslips list count1 is "+PayslipsCount1);
			 
	      		    WebElement Payslipsmain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int PayslipsCount2 = Payslipsmain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Payslips list count2 is "+PayslipsCount2);
	      		    Assert.assertEquals(PayslipsCount2, PayslipsCount1);
	      		    
	      		   for(int j=0;j<PayslipsCount2;j++){

	      	        jse.executeScript("window.scrollBy(0,270)","");
	      	        Thread.sleep(500);
	      	        Payslipsmain.findElements(By.cssSelector(".listiban_group")).get(j).click();
	      		    Thread.sleep(1300);
	      		  
	      		    String IBANDetails = Payslipsmain.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
	      		    Assert.assertEquals(IBANDetails,IBANHome);
	      		    
					WebElement PayOption = Payslipsmain.findElements(By.cssSelector(".list_item")).get(j).findElement(By.cssSelector(".navigation_btns"));
				    int optionCheck = PayOption.findElements(By.cssSelector("*")).size();
					System.out.println("The option for "+j+" element is "+optionCheck);
					
					if(optionCheck>3){
						
						PayOption.findElements(By.cssSelector(".btn_primary")).get(1).click();
						driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
						Thread.sleep(500);
						wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".details_inner")));
				  	    Thread.sleep(500);
				  	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				  	    
				  	    
				  	    zoomit.findElement(By.cssSelector(".details")).findElement(By.cssSelector(".btn_default:first-child")).click();
				 	   	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				 	   	Thread.sleep(800);
				 	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
				     	Thread.sleep(400);
				 	   	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				 				 					
				   	    driver.findElement(By.cssSelector(".refresh_txt")).click();
				   	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
				   	    Thread.sleep(800);
				   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				   	    Thread.sleep(200);
				   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
																	
				   	    String PayslipsCountafter = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/span[2]")).getText();
				   	    int PayslipsCount3=Integer.parseInt(PayslipsCountafter.trim());
				   	    System.out.println("The PaySlips list count is "+PayslipsCount3);
				   	    Assert.assertEquals(PayslipsCount1-1,PayslipsCount3);
	    		   	    check=true;
				   	    break;
				   	    }
			     	 }	
	      		    driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[1]/div/span[2]")).click();
	      		    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	  	       	    Thread.sleep(800);
	  	       	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	  	       	    Thread.sleep(300);
	  	       	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	      	    }if(check==true) break;
			 }
		 }
	 }
	

	/****************************************************************************************

									Test Scenarios - 8  Zoomit Payslips View

	 ****************************************************************************************/	

	@Test(priority=7,enabled=true)
	public void zoomitPaySlips3() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	
//	    Obj.loginPositiveflow();
	    boolean IsabelCheck4=false;
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(400);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
   	    Thread.sleep(400);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".eb_tab_inner")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
			
				
				if(zoomittype.equalsIgnoreCase("PAYSLIPS"))
                {             
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String PaySlipCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[2]/div[2]/div[1]/div/span[2]")).getText();
				   int PaySlipCount1=Integer.parseInt(PaySlipCount.trim());
	      		   System.out.println("The PaySlip list count1 is "+PaySlipCount1);
			 
	      		    WebElement PaySlipmain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int PaySlipCount2 =PaySlipmain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The PaySlip list count2 is "+PaySlipCount2);
	      		    Assert.assertEquals(PaySlipCount2, PaySlipCount1);	
	      		    
	      		    PaySlipmain.findElements(By.cssSelector(".lnk_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(1000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);

					try{
						
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:lnkZoomitLogo")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					IsabelCheck4=true;
				
					}catch(Exception isabel){
						System.out.println("Isabel site is not loaded");
					}	
				
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(800);
					
					System.out.println(driver.findElement(By.cssSelector(".alert_messages__inner")).getText().trim());
					
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	      		    
					//  Checking the Isabel site is loaded or not
					
					Assert.assertEquals(IsabelCheck4,true);
                 }
			  }
		  }
	 }


	/****************************************************************************************

						  Test Scenarios - 9  Zoomit History of all views

	 ****************************************************************************************/	

	@Test(priority=8,enabled=true)
	public void zoomitHistory() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	
	    boolean HisIsabelCheck1=false,HisIsabelCheck2=false,HisIsabelCheck3=false,HisIsabelCheck4=false;
	    
//	    Obj.loginPositiveflow();
	    driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(400);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	    Thread.sleep(400);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[2]/div/div[2]/div")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        								
        driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[2]/div/div[2]/div")).click();
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		Thread.sleep(800);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		
		WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(1);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		for(int i=0;i<Count;i++){
		
			if(listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).isDisplayed())
			{
				
				String zoomittype = listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).getText().trim();
				listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(500);
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
			    Thread.sleep(500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
										
				if(zoomittype.equalsIgnoreCase("INVOICES"))
                {             
				   System.out.println("The header is "+zoomittype);
				   driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
				   Thread.sleep(500);
				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String InvoiceCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[2]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
				   int InvoiceCount1=Integer.parseInt(InvoiceCount.trim());
	      		   System.out.println("The Invoice list count1 is "+InvoiceCount1);
			 
	      		    WebElement Invoicemain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int InvoiceCount2 =Invoicemain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Invoice list count2 is "+InvoiceCount2);
	      		    Assert.assertEquals(InvoiceCount2, InvoiceCount1);	
	      		    
					Invoicemain.findElements(By.cssSelector(".lnk_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(1000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);
					try{
						
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:lnkZoomitLogo")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					HisIsabelCheck1=true;
					
					}catch(Exception isabel){
						
						System.out.println("Isabel site is not loaded");
					}	
					
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(800);
									
			
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    
				
					listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(500);
				    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				    Thread.sleep(500);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	              }
				
				
				if(zoomittype.equalsIgnoreCase("DIRECT DEBITS"))
                {             
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String DirectDebitCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[2]/div/div[2]/div[3]/div[1]/div/span[2]")).getText();
				   int DirectDebitCount1=Integer.parseInt(DirectDebitCount.trim());
	      		   System.out.println("The DirectDebit list count1 is "+DirectDebitCount1);
	      	
	      		   WebElement DirectDebitmain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		   int DirectDebitCount2 = DirectDebitmain.findElements(By.cssSelector(".listiban_group")).size();
	      		   System.out.println("The DirectDebit list count2 is "+DirectDebitCount2);
	      		   Assert.assertEquals(DirectDebitCount2, DirectDebitCount1);	
	      		   
	      		   DirectDebitmain.findElements(By.cssSelector(".lnk_primary")).get(0).click();
				   driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
				   Thread.sleep(1000);
				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			 
				   ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
				   driver.switchTo().window(tabs2.get(1));
				   driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
				   Thread.sleep(500);
					try{
						
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:lnkZoomitLogo")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					HisIsabelCheck2=true;
				
					}catch(Exception isabel){
						System.out.println("Isabel site is not loaded");
					}	
				
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(800);
					
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	      		    
				
					listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(500);
				    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				    Thread.sleep(500);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	      	   }
				
				if(zoomittype.equalsIgnoreCase("PAYSLIPS"))
                {             
				   System.out.println("The header is "+zoomittype);
				   String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
				   String PaySlipCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[2]/div/div[2]/div[2]/div[1]/div/span[2]")).getText();
				   int PaySlipCount1=Integer.parseInt(PaySlipCount.trim());
	      		   System.out.println("The PaySlip list count1 is "+PaySlipCount1);
			 
	      		    WebElement PaySlipmain = listitem.findElements(By.cssSelector(".accordion_main")).get(i);
	      		    int PaySlipCount2 = PaySlipmain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The PaySlip list count2 is "+PaySlipCount2);
	      		    Assert.assertEquals(PaySlipCount2, PaySlipCount1);	
	      		    
	      		    Thread.sleep(1500);
	      		    PaySlipmain.findElements(By.cssSelector(".lnk_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(1000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
			 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);

					try{
						
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:lnkZoomitLogo")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					HisIsabelCheck4=true;
				
					}catch(Exception isabel){
						System.out.println("Isabel site is not loaded");
					}	
				
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(800);
					
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					
					listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(500);
				    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				    Thread.sleep(500);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				  }
				
				if(zoomittype.equalsIgnoreCase("SENDERS"))
                {												
				   System.out.println("The header is "+zoomittype);   
				   String SendersCount = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[2]/div/div[2]/div[4]/div[1]/div/span[2]")).getText();
				   int SendersCount1=Integer.parseInt(SendersCount.trim());
	      		   System.out.println("The Senders list count is "+SendersCount1);
			 
	      		    WebElement Sendersmain = listitem.findElements(By.cssSelector(".accordion_main")).get(3);
	      		    int SendersCount2 = Sendersmain.findElements(By.cssSelector(".listiban_group")).size();
	      		    System.out.println("The Senders list count is "+SendersCount2);
	      		    Assert.assertEquals(SendersCount2,SendersCount1);
	      		    
	       		    Sendersmain.findElements(By.cssSelector(".listiban_group")).get(0).click();
	      		    Thread.sleep(1000);
	      		                                                   
	      		    String IBANHome= driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[1]/div/div[2]/div/div/div[1]/div[1]/h2")).getText().trim();
	      		    String IBANDetails = Sendersmain.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
	      		  
	      		    Assert.assertEquals(IBANDetails,IBANHome);
	      		    
	      		    Sendersmain.findElements(By.cssSelector(".btn_primary")).get(0).click();
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(1000);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
				 
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					driver.switchTo().window(tabs2.get(1));
					driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					Thread.sleep(500);
						   
					try{
						
					   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerForm:zoomitLogo")));
					   Thread.sleep(500);
					   driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
					   HisIsabelCheck3=true;	     
			    	   driver.findElement(By.id("sendersForm:detailsPanel:termsConditions")).click();
			    	   Thread.sleep(400);
			    	   driver.findElement(By.cssSelector(".ui-button")).click();
			    	   Thread.sleep(400);
			    	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendersForm:senderOptinStatus")));
			    	   
			           }catch(Exception e){
			        	   
			        	   if(HisIsabelCheck3==true){
			        	   if(driver.findElement(By.id("sendersForm:senderOptinStatus")).isDisplayed())
			        	     System.out.println("Already Accepted"); } 
			        	   else System.out.println("Isabel site is not loaded properly"); 
			           }
			       
					driver.close();
					driver.switchTo().window(tabs2.get(0));
					Thread.sleep(1000);
					
					driver.findElement(By.cssSelector(".refresh_txt")).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(1200);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(500);
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					
					listitem.findElements(By.cssSelector(".header_l1 h4")).get(i).click();
					driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					Thread.sleep(500);
				    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));
				    Thread.sleep(500);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
                }
			  }
			}
		
		Assert.assertEquals(HisIsabelCheck1,true);	 //  Checking the Isabel site is loaded or not While clicking "INVOICES"
		
		Assert.assertEquals(HisIsabelCheck2,true);	 //  Checking the Isabel site is loaded or not While clicking "DIRECT DEBITS"
		
		Assert.assertEquals(HisIsabelCheck3,true);	 //  Checking the Isabel site is loaded or not While clicking "SENDERS"
		
		Assert.assertEquals(HisIsabelCheck4,true);	 //  Checking the Isabel site is loaded or not While clicking "PAYSLIPS"

	}
	

	/****************************************************************************************

									Test Scenarios - 10 Zoomit New Search

	 ****************************************************************************************/	

	@Test(priority=9,enabled=true)
	public void zoomitSearch() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	 
	    int SearchResult2=0; 
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(800);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(0);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		zoomit.findElements(By.cssSelector(".default_input_field > input")).get(0).click();
		zoomit.findElements(By.cssSelector(".default_input_field > input")).get(0).sendKeys("GAS");
		zoomit.findElements(By.cssSelector(".search_icon")).get(0).click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
   	    
   	    
   	    String SearchResult = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[1]/div/div[1]/div/div/p/span[1]")).getText();
	    int SearchResult1 = Integer.parseInt(SearchResult);
	    System.out.println(SearchResult1);
	    
	    if(SearchResult1>0){
	    	
	    	  SearchResult2 = listitem.findElements(By.cssSelector(".listiban_group")).size();
	    	  System.out.println(SearchResult2);
	    	  Assert.assertEquals(SearchResult2,SearchResult1);
	    	}
	    }
	
	
	/****************************************************************************************

									Test Scenarios - 11  Zoomit History Search

	 ****************************************************************************************/	

	@Test(priority=10,enabled=true)
	public void zoomitSearch1() throws Exception{
	
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    Properties prop = new Properties();
	    FileInputStream input = new FileInputStream(filepath
    			+ "Object Repository/TransactionData.properties");
	    prop.load(input);
	 
	    int SearchResult2=0; 
//	    Obj.loginPositiveflow();
		driver.findElement(By.cssSelector(".site_logotype")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Thread.sleep(1500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	   
		// locate the menu to hover over using its xpath
	                                                
	    WebElement menu = driver.findElement(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[3]/a"));
     	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='1430321253687']/div/div/div/div[3]/nav/div/ul/li[2]/a")));
     	menu.click();           
     	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(400);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("zoomit_inner")));	
     	Thread.sleep(400);	
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
	    Thread.sleep(400);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[2]/div/div[2]/div")));	
	
	    WebElement zoomit = driver.findElement(By.id("zoomit"));
	    String Text2 = zoomit.findElement(By.cssSelector(".module_title")).getText();
        System.out.println(Text2);
        
        //  Selecting the New Tab
        
        driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[2]/div[2]/div/div[2]/div")).click();
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		Thread.sleep(800);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		
        WebElement listitem = zoomit.findElements(By.className("module_listitem")).get(1);
        
        //  Selecting the Sub Tabs in the NEW    
    
    	int Count= listitem.findElements(By.cssSelector(".header_l1 h4")).size();
		System.out.println(Count);
		
		zoomit.findElements(By.cssSelector(".default_input_field > input")).get(1).click();
		zoomit.findElements(By.cssSelector(".default_input_field > input")).get(1).sendKeys("GAS");
		zoomit.findElements(By.cssSelector(".search_icon")).get(1).click();
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   	    Thread.sleep(800);
   	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));	
   	    
   	    													
   	    String SearchResult = driver.findElement(By.xpath(".//*[@id='zoomit']/div[2]/div[1]/div[3]/div[2]/div/div[1]/div/div/p/span[1]")).getText().trim();
	    int SearchResult1 = Integer.parseInt(SearchResult);
	    System.out.println(SearchResult1);
	    
	    if(SearchResult1>0){
	    	
	    	  SearchResult2 = listitem.findElements(By.cssSelector(".listiban_group")).size();
	    	  System.out.println(SearchResult2);
	    	  Assert.assertEquals(SearchResult2,SearchResult1);
	    	}
	    }
	 }