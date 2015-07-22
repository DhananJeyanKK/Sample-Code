package com.bnpp.source;


	import java.io.FileInputStream;
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
	public class PersonalAlias210 extends DataWeb {


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

	    					Test Scenarios - 1 CR210

		    *****************************************************************************************/	

			
			@Test(priority=0,enabled=true)
		    public void CR210() throws Exception{
				
				WebDriverWait wait = new WebDriverWait(driver,30);
				
				Obj.loginPositiveflow();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				
				WebElement test1 = driver.findElements(By.cssSelector(".extra")).get(0);
				test1.click();
				test1.findElements(By.cssSelector(".dropdown-menu li")).get(0).click();
				Thread.sleep(100);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(200);
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sepa")));		
			    
			    String Text1 = driver.findElement(By.cssSelector(".module_title")).getText();
			    System.out.println(Text1);
			    
			    driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(3000);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			    
				WebElement test2 = driver.findElements(By.cssSelector(".extra")).get(0);
				test2.click();
				test2.findElements(By.cssSelector(".dropdown-menu li")).get(1).click();
				Thread.sleep(100);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(200);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
				
				driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
					
				
				WebElement test3 = driver.findElements(By.cssSelector(".extra")).get(0);
				test3.click();
				test3.findElements(By.cssSelector(".dropdown-menu li")).get(2).click();
				Thread.sleep(100);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(200);	
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("estatements")));
				
				driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
							
				WebElement test4 = driver.findElements(By.cssSelector(".extra")).get(0);
				test4.click();
				test4.findElements(By.cssSelector(".dropdown-menu li")).get(3).click();
				Thread.sleep(2000);
				boolean confirm = false;
			    confirm = driver.findElement(By.cssSelector(".form-control")).isDisplayed();
				Assert.assertEquals(true,confirm);
				
				driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(800);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				WebElement test5 = driver.findElements(By.cssSelector(".extra")).get(0);
				test5.click();
				test5.findElements(By.cssSelector(".dropdown-menu li")).get(4).click();
				Thread.sleep(100);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(200);	
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".selected_details__account")));

				driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				WebElement test6 = driver.findElements(By.cssSelector(".extra")).get(0);
				test6.click();
				test6.findElements(By.cssSelector(".dropdown-menu li")).get(5).click();
				Thread.sleep(100);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(200);	
				
				driver.findElement(By.xpath(".//*[@id='1394811224952']/div/div/div/ul[2]/li[3]/a")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);					
			}
			
			   /****************************************************************************************

									Test Scenarios - 2  Personalization alias postive

			    *****************************************************************************************/	

			@Test(priority=1,enabled=true)
		    public void PersonalizationaliasConfirm() throws Exception{
			
					WebDriverWait wait = new WebDriverWait(driver,30);
				    String oldnameOrg =driver.findElements(By.cssSelector(".list_header_title")).get(0).getText();
					
				    WebElement test = driver.findElements(By.cssSelector(".extra")).get(0);
					test.click();
					test.findElements(By.cssSelector(".dropdown-menu li")).get(3).click();
					Thread.sleep(2000);
				
				    System.out.println(oldnameOrg);
				    String oldname = oldnameOrg.replaceAll("[^A-Z]","");
				    int random = new Random().nextInt();
				    String sample = Integer.toString(Math.abs(random));
				    String randomno = sample.substring(0,3);
				    oldname = oldname.concat(randomno);
				    System.out.println(oldname);
				    driver.findElement(By.cssSelector(".form-control")).clear();
				    driver.findElement(By.cssSelector(".form-control")).sendKeys(oldname);
				    driver.findElement(By.cssSelector(".editable-submit")).click();
				    Thread.sleep(1000);
				    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(200);	
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".site_logotype")));
					
					String newname = driver.findElements(By.cssSelector(".list_header_title")).get(0).getText();
					System.out.println(newname);
					if(oldnameOrg.contentEquals(newname)){
						System.out.println("Failed");}
					else {
						System.out.println("Pass");}
														
			}
			
			   /****************************************************************************************

								Test Scenarios - 3  Personalization alias Negative

			    *****************************************************************************************/		
			@Test(priority=2,enabled=true)
		    public void PersonaltionaliasCancel() throws Exception{
			
				    WebDriverWait wait = new WebDriverWait(driver,30);
				    String oldnameOrg =driver.findElements(By.cssSelector(".list_header_title")).get(0).getText();
					WebElement test = driver.findElements(By.cssSelector(".extra")).get(0);
					test.click();
					test.findElements(By.cssSelector(".dropdown-menu li")).get(3).click();
					Thread.sleep(2000);
				
				    System.out.println(oldnameOrg);
				    String oldname = oldnameOrg.replaceAll("[^A-Z]","");
				    int random = new Random().nextInt();
				    String sample = Integer.toString(Math.abs(random));
				    String randomno = sample.substring(0,3);
				    oldname = oldname.concat(randomno);
				    System.out.println(oldname);
				    driver.findElement(By.cssSelector(".form-control")).clear();
				    driver.findElement(By.cssSelector(".form-control")).sendKeys(oldname);
				    driver.findElement(By.cssSelector(".editable-cancel")).click();
				    Thread.sleep(1000);
				    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
					Thread.sleep(200);	
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".site_logotype")));
					
					String newname = driver.findElements(By.cssSelector(".list_header_title")).get(0).getText();
					System.out.println(newname);
					if(oldnameOrg.contentEquals(newname)){
						System.out.println("Pass");}
					else {
						System.out.println("Fail");}
														
			}
			
			
			   /****************************************************************************************

								        Test Scenarios - 4  Multilan CR - Specific date

			    *****************************************************************************************/	
			
			@Test(priority=3,enabled=true)
		    public void MultiLan1() throws Exception{
			

				   WebDriverWait wait = new WebDriverWait(driver,30);
				   Properties prop = new Properties();
				   FileInputStream input = new FileInputStream(filepath
				    			+ "Object Repository/StandingOrders.properties");
				   prop.load(input);

//				   Obj.loginPositiveflow();
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
				  	String datenew; 
				   
				   try{
						 id = sepaSTO.findElement(By.className("sign_input")).isDisplayed();
						 datenew = driver.findElement(By.xpath("//*[@id='standorder']/div/div[2]/div/div[3]/div/div[4]/div[1]/div/span")).getText().trim();
						 datenew = datenew.replaceAll("\\D+","");
						 sepaSTO.findElement(By.className("sign_input")).click();
						 Thread.sleep(10000);         
						 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(0).click();			 		    			
			   		  }
				   catch(NoSuchElementException e){
						          
						 System.out.println("No foreign beneficiaries");
						 datenew = driver.findElement(By.xpath("//*[@id='standorder']/div/div[2]/div/div[3]/div/div[4]/div[1]/div/span")).getText().trim();
						 datenew = datenew.replaceAll("\\D+","");
						 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(1).click();
					 }
					 
				   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				   Thread.sleep(800);
				   wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));  
				   Thread.sleep(500);
				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				   String Result = driver.findElement(By.cssSelector(".show_inline")).getText();
				   Result = Result.replaceAll("\\D+",""); 
				   System.out.println(Result);
				   if(Result.equalsIgnoreCase("18092015")){
					   System.out.println("Pass");
				   }else{
					   System.out.println("Fail");
				   }
			}

			/****************************************************************************************

	        						Test Scenarios - 5  Multilan CR - last Specific date

			*****************************************************************************************/	
			
			@Test(priority=4,enabled=true)
		    public void MultiLan2() throws Exception{	
			 WebDriverWait wait = new WebDriverWait(driver,30);
			 Properties prop = new Properties();
			 FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/StandingOrders.properties");
				prop.load(input);
				
//				Obj.loginPositiveflow();
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
		  	     
		        String datenew=null;
		        
			   try{
					 id = sepaSTO.findElement(By.className("sign_input")).isDisplayed();
					 driver.findElement(By.cssSelector(".view_more p")).click();
					 Thread.sleep(1000);
					 
					 datenew = driver.findElement(By.xpath("//*[@id='standorder']/div/div[2]/div/div[3]/div/div[4]/div[1]/div/span")).getText().trim();
					 datenew = datenew.replaceAll("\\D+","");
					 sepaSTO.findElement(By.className("sign_input")).click();
					 Thread.sleep(10000);         
					 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(0).click();			 		    			
		   		  }
			   catch(NoSuchElementException e){
					          
					 System.out.println("No foreign beneficiaries");
					 datenew = driver.findElement(By.xpath("//*[@id='standorder']/div/div[2]/div/div[3]/div/div[4]/div[1]/div/span")).getText().trim();
					 datenew = datenew.replaceAll("\\D+","");
					 sepaSTO.findElements(By.cssSelector(".btn_primary")).get(1).click();
				 }
		                        
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		       Thread.sleep(800);
		       wait.until(ExpectedConditions.presenceOfElementLocated(By.className("acknowledge_page")));  
			   Thread.sleep(500);
		       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			   String Result = driver.findElement(By.cssSelector(".show_inline")).getText();
			   Result = Result.replaceAll("\\D+",""); 
			    System.out.println(Result);
			   if(Result.equalsIgnoreCase(datenew)){
				   System.out.println("Pass");
			   }else{
				   System.out.println("Fail");
			   }
		}
		
	}
