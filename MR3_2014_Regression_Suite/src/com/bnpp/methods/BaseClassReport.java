package com.bnpp.methods;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.bnpp.data.DataWeb;


public class BaseClassReport extends DataWeb{


	public static WebDriver getDriver(){

	  if(driver==null){

		  ChromeOptions options = new ChromeOptions();
		  System.setProperty("webdriver.chrome.driver",chromepath);
		  DesiredCapabilities dc = DesiredCapabilities.chrome();
		  dc.setCapability("chrome.switches", Arrays.asList("--disable-local-storage"));
		  dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true); 
		  dc.setCapability(ChromeOptions.CAPABILITY, options);
		  driver = new ChromeDriver(dc);
	        }
	        
	     return driver;
	    }

      /**

	     * This function will take screenshot

	     * @param webdriver

	     * @param fileWithPath

	     * @throws Exception

	     */

	    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

	        //Call getScreenshotAs method to create image file

	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	            //Move image file to new destination

	                File DestFile=new File(fileWithPath);

	                //Copy file at destination

	                FileUtils.copyFile(SrcFile, DestFile);
 	    }
 }


