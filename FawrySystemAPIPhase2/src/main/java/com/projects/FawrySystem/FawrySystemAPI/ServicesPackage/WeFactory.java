package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;



public class WeFactory extends ProviderFactory  {
	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	@Override
	public IService createServiceProvider(String type) {
	
		
		 if(type.toLowerCase().contains("mobile"))
		 {
			 form.setName("WE Mobile Recharge");
			 return new WeMobileRecharge(form,mobileRechargeCommand);
		 }
	            
	      else if(type.toLowerCase().contains("internet"))
	      {
	    	  form.setName("WE Internet");
	    	  return new WeInternetPayment(form,internetCommand);
	      }
		 
	        	  
		return null;
	}


}
