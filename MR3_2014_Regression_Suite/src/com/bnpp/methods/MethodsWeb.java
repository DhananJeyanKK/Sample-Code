package com.bnpp.methods;

public class MethodsWeb {

	
	public String check(String confirm) throws Exception{

		
		 if (confirm.contains("testaccess")){
			 String confirmNew = confirm.substring(0,53);
			 String rest = confirm.substring(55,confirm.length());
			 confirm=confirmNew+"en"+rest;
	         System.out.println(confirm);
		 }
		
		 else if(confirm.contains("p1")){
		 String confirmNew = confirm.substring(0,45);
		 String rest = confirm.substring(47,confirm.length());
		 confirm=confirmNew+"en"+rest;
         System.out.println(confirm);
		 }
		
		 else{
			 String confirmNew = confirm.substring(0,42);
    		 String rest = confirm.substring(44,confirm.length());
    		 confirm=confirmNew+"en"+rest;
             System.out.println(confirm);
		 }
 		 return confirm;
	}
}
