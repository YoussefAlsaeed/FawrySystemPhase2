package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;


public class OrangeFactory extends ProviderFactory  {

	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	public IService createServiceProvider(String type) {
		
		
		 if(type.toLowerCase().contains("mobile"))
		 {
			 form.setName("Orange Mobile Recharge");
			// mobileRechargeCommand.setForm(form);
			 return new OrangeMobileRecharge(form,mobileRechargeCommand);
		 }
	            
	      else if(type.toLowerCase().contains("internet"))
	      {
	    	  form.setName("Orange Internet");
	    	 // internetCommand.setForm(form);
	    	  return new OrangeInternetPayment(form,internetCommand);
	      }
		 
	        	  
		return null;
	}


}
