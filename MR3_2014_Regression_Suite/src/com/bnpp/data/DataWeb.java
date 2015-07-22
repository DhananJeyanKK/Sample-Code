package com.bnpp.data;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataWeb {
	
           /****************************************************************************************
    
                                              Declaration

          *****************************************************************************************/

        public static WebDriver driver;
        public static WebElement we;
	    public String filepath = System.getProperty("user.dir")+"/src/";
		public static String chromepath="/Users/mobilitytcs/Desktop/Automation/Selenium/chromedriver";
		public String BaseUrl="https://easybanking.qabnpparibasfortis.be";
		public String BaseUrl1="https://p1.easybanking.qabnpparibasfortis.be";
		public String BaseUrl2="https://easybanking.testaccess.qabnpparibasfortis.be";
		public String PendingCount;
		public String PlannedCount;
		public String RejectedCount;
		public String month;
		public String transCount;
	    public int num=0,num1=0,transCount1=0,transCount2=0,PendingCount1=0,PendingCount2=0,PlannedCount1,PlannedCount2,RejectedCount1,RejectedCount2,Results1;  
        public boolean languagecheck = false;
 	
 	    // To get the current month 
   	    
	    static DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_&_HH_mm_ss");
   	    static Date date = new Date();
   	    public static String yourDate = dateFormat.format(date);
   	    
 	    // To get the current month 
   	    
 	     DateFormat dateFormat1 = new SimpleDateFormat("MMMMMMMMM");
 	     Date date1 = new Date();
 	     public String yourDate1 = dateFormat1.format(date1);

}
