package com.bnpp.source;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
public class AccountsCardsFAQ extends DataWeb {
	

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

	                                Test Scenarios - 1 FAQ Questions

	       *****************************************************************************************/	
		   
	       @Test(priority=0,enabled=true)
		   public void faq() throws Exception{
			   
				WebDriverWait wait = new WebDriverWait(driver, 30);
				Properties prop = new Properties();
				FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/TransactionData.properties");
				prop.load(input);
				
			       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			       Thread.sleep(3000);
				   driver.manage().window().maximize();
				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
							.className("progressing")));
				   
//			   try {
//						WebDriverWait wait1 = new WebDriverWait(driver,5);
//						wait1.until(ExpectedConditions.visibilityOfElementLocated(By
//								.className(prop.getProperty("overlayPreLogonPopup"))));
//						WebElement we = driver.findElement(By.className(prop
//								.getProperty("overlayPreLogonPopup")));
//						we.findElement(By.cssSelector(prop.getProperty("Closebutton")))
//								.click();
//
//					}catch(Exception E1){
//
//						System.out.println("No Pop-up is Displayed");
//					}
//			   
				    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					Thread.sleep(600);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By
							.className("progressing")));
				   
		           driver.findElements(By.cssSelector(".link_list a")).get(1).click();
                   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				   Thread.sleep(800);
				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
							.className("progressing")));
				   
	        	   String header = driver.findElement(By.cssSelector(".module_title")).getText();
				   System.out.println("The Screen header is: "+header);
				  
				   WebElement active = driver.findElement(By.cssSelector(".header_l1.dropdown-active"));
				   Assert.assertEquals(active.getText().trim(),"TOP FAQS");
				   System.out.println(active.getText().trim()+" is displayed properly");
				   

				   int FAQCount = driver.findElements(By.className("module_listitem")).size();
				   for(int i=1;i<FAQCount;i++){
					   
					   WebElement estimate = driver.findElements(By.className("module_listitem")).get(i);
					   WebElement heading = estimate.findElement(By.cssSelector(".accordion_l1__inner"));
					   System.out.println(heading.getText());
					   heading.click();
					   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					   Thread.sleep(400);
					   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
								.className("progressing")));
					   
					   int questionsCount = estimate.findElements(By.cssSelector(".faq_question")).size();
					   System.out.println(questionsCount);
					   if(questionsCount>1)
					   {
					   for(int j=0;j<questionsCount;j++){
					 
					   WebElement questions = estimate.findElements(By.cssSelector(".faq_question")).get(j);
					   System.out.println(questions.getText());
					   questions.click();
					   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					   Thread.sleep(400);
					   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
								.className("progressing")));
					   	}
					 }
					 heading.click();
					 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					   Thread.sleep(400);
					   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
								.className("progressing")));
				}
				   driver.findElement(By.cssSelector(".lnk_primary")).click(); 
				   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				   Thread.sleep(400);
				   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
							.className("progressing")));
				  
		   }
		   
		   /****************************************************************************************

	       								Test Scenarios - 2  FAQ Search

		    ****************************************************************************************/	


	   @Test(priority=1,enabled=true)
	   public void faqSearch() throws Exception{

			   WebDriverWait wait = new WebDriverWait(driver, 30);
			   Properties prop = new Properties();
				  FileInputStream input = new FileInputStream(filepath
			    			+ "Object Repository/TransactionData.properties");
			   prop.load(input);

			   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			   driver.manage().window().maximize();
			   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
		       .className("progressing")));
	
//	  try {
//		   WebDriverWait wait1 = new WebDriverWait(driver,5);
//		  wait1.until(ExpectedConditions.visibilityOfElementLocated(By
//			.className(prop.getProperty("overlayPreLogonPopup"))));
//		  WebElement we = driver.findElement(By.className(prop
//			.getProperty("overlayPreLogonPopup")));
//		  we.findElement(By.cssSelector(prop.getProperty("Closebutton")))
//		 	.click();
	//
//		   }catch(Exception E1){
	//
//		System.out.println("No Pop-up is Displayed");
//		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(600);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
		.className("progressing")));

		driver.findElements(By.cssSelector(".link_list a")).get(1).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(800);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
		.className("progressing")));

		String header = driver.findElement(By.cssSelector(".module_title")).getText();
		System.out.println("The Screen header is: "+header);

		WebElement active = driver.findElement(By.cssSelector(".header_l1.dropdown-active"));
		Assert.assertEquals(active.getText().trim(),"TOP FAQS");
		System.out.println(active.getText().trim()+"  is displayed properly successfully");

		driver.findElement(By.xpath(".//*[@id='1392308256421']/div/div/div[2]/div[1]/div/div/div/div/input")).click();
		driver.findElement(By.xpath(".//*[@id='1392308256421']/div/div/div[2]/div[1]/div/div/div/div/input")).sendKeys("card");
		driver.findElement(By.cssSelector(".search_icon span")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(800);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By
		.className("progressing")));
		
		String Countold = driver.findElement(By.xpath(".//*[@id='1392308256421']/div/div/div[2]/div[1]/div/p/span[2]/span[1]")).getText().trim();
		int Count = Integer.parseInt(Countold.trim());
		System.out.println(Count);
		
		WebElement search = driver.findElement(By.className("module_content_inner"));
		int FAQSearchCount = search.findElements(By.cssSelector(".faq_question")).size();
		System.out.println(FAQSearchCount);
		
		Assert.assertEquals(Count,FAQSearchCount);
		
		   driver.findElement(By.cssSelector(".lnk_primary")).click(); 
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   Thread.sleep(400);
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By
					.className("progressing")));
	 
	   }

	   
	      /****************************************************************************************

	   							Test Scenarios - 3  FAQ Widget inner check

	      *****************************************************************************************/	
	  
		@Test(priority=2,enabled=true)
		public void faqInnerCheck() throws Exception{
			
			WebDriverWait wait = new WebDriverWait(driver,30);
			Properties prop = new Properties();
			  FileInputStream input = new FileInputStream(filepath
		    			+ "Object Repository/TransactionData.properties");
			prop.load(input);
			
			 Obj.loginPositiveflow();
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
	     
			 driver.findElements(By.cssSelector(".meta_icon")).get(1).click();
			 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector(".widget_header__inner")));
				
			   if(driver.findElement(By.cssSelector(".widget_header__inner")).isDisplayed()){
			   String Faq = driver.findElement(By.cssSelector(".widget_header__inner h2")).getText().trim();
			   System.out.println(Faq);
			   Assert.assertEquals("Frequently asked questions",Faq);
			   
			 }
		}
		 
	      /****************************************************************************************

	                              Test Scenarios - 4  Accounts Verification

	      *****************************************************************************************/	
		
		@Test(priority=3,enabled=true)	
		public void Accounts() throws Exception{

			WebDriverWait wait = new WebDriverWait(driver,50);
	        FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		
	           prop.load(input);
			
//			   Obj.loginPositiveflow();
			   driver.findElement(By.cssSelector(".site_logotype")).click();
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			   Thread.sleep(1500);
			   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			   Thread.sleep(1000);
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			   
		
			if(driver.findElement(By.cssSelector(".accordion")).isDisplayed()){
				
				int type = driver.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".accordion_main")).size();
				System.out.println(type);
				for(int i=0;i<type;i++){
			
			    WebElement main = driver.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".accordion_main")).get(i);
			   
			    if(i==1){
			    driver.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".header_l1 h4")).get(0).click();
			    Thread.sleep(300);
			    driver.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".header_l1 h4")).get(1).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    Thread.sleep(300);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		        JavascriptExecutor jse = (JavascriptExecutor)driver;
		        jse.executeScript("window.scrollBy(0,800)","");
		        Thread.sleep(500);
			    }
			    
				int count = main.findElements(By.cssSelector(".list_item")).size();
				System.out.println("Total no.of.accounts displayed: "+count);
				
				String Acc = main.findElement(By.cssSelector(".listiban_group")).findElements(By.cssSelector("*")).get(0).getText();
				String Amt = main.findElement(By.cssSelector(".list_content_col2 span")).getText();
		
				main.findElements(By.cssSelector(".list_item")).get(0).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountstransactions")));
							
				String SelectedAc = driver.findElement(By.cssSelector(".selected_details__account")).getText();
				System.out.println(SelectedAc);
				String AmountHome=driver.findElement(By.cssSelector(".module_selected_value span")).getText();
				System.out.println(AmountHome);
		       
				Assert.assertEquals(Acc,SelectedAc);
				Assert.assertEquals(Amt,AmountHome);
				
				driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				}
			}
		}
		
		
		 /****************************************************************************************

	                              Test Scenarios - 5  Cards Verification

	      *****************************************************************************************/	
		
		@Test(priority=4,enabled=true)	
		public void Cards() throws Exception{

			WebDriverWait wait = new WebDriverWait(driver,50);
	        FileInputStream input = new FileInputStream(filepath
	    			+ "Object Repository/TransactionData.properties");
		
	        prop.load(input);
		
//			   Obj.loginPositiveflow();
			   driver.findElement(By.cssSelector(".site_logotype")).click();
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			   Thread.sleep(1500);
			   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			   Thread.sleep(1000);
			   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			   
			  driver.findElements(By.cssSelector(".content_menu_items")).get(1).click();
			  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			  Thread.sleep(300);
			  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
			  
			  int size = driver.findElements(By.cssSelector(".accordion")).size();
			  System.out.println(size);

			if(driver.findElements(By.cssSelector(".accordion")).get(1).isDisplayed()){
					
				WebElement cardList = driver.findElement(By.id("cardList"));  
				int type = cardList.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".accordion_main")).size();
				System.out.println(type);
		
				for(int i=0;i<type;i++){
			    
			    WebElement main = cardList.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".accordion_main")).get(i);
			   
			    if(i==1){
			    	
			    cardList.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".header_l1 h4")).get(0).click();
			    Thread.sleep(300);
			    cardList.findElement(By.cssSelector(".accordion")).findElements(By.cssSelector(".header_l1 h4")).get(1).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    Thread.sleep(300);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
		        JavascriptExecutor jse = (JavascriptExecutor)driver;
		        jse.executeScript("window.scrollBy(0,800)","");
		        Thread.sleep(500);
			    
			    }
			    
				int count = main.findElements(By.cssSelector(".list_item")).size();
				System.out.println("Total no.of.accounts displayed: "+count);
				
				String Acc = main.findElement(By.cssSelector(".listiban_group")).findElements(By.cssSelector("*")).get(0).getText();
		
				main.findElements(By.cssSelector(".list_item")).get(0).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cardsTransactions")));
							
				String SelectedAc = driver.findElement(By.cssSelector(".selected_details__account")).getText();
				System.out.println(SelectedAc);
					       
				Assert.assertEquals(Acc,SelectedAc);
				
				
				driver.findElements(By.cssSelector(".eb_tab_inner")).get(1).click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));

				String ACdetails = driver.findElements(By.cssSelector(".listitem_details p")).get(0).getText().trim();
				String CardType = driver.findElements(By.cssSelector(".listitem_details p")).get(1).getText().trim();
				String cardholder = driver.findElements(By.cssSelector(".listitem_details p")).get(2).getText().trim();
				String IBAN = driver.findElements(By.cssSelector(".listitem_details p")).get(6).getText().trim();
				System.out.println(IBAN);
				System.out.println(cardholder);
				System.out.println(CardType);
				Assert.assertEquals(ACdetails,SelectedAc);
								
				driver.findElement(By.cssSelector(".site_logotype")).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("progressing")));
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				}
			}
		}
	}

