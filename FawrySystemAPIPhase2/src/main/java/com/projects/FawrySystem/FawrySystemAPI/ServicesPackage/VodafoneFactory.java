package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;


public class VodafoneFactory extends ProviderFactory  {
	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	@Override
	public IService createServiceProvider(String type) {
		
		
		 if(type.toLowerCase().contains("mobile"))
		 {
			 form.setName("Vodafone Mobile Recharge");
			 //mobileRechargeCommand.setForm(form);
			 return new VodafoneMobileRecharge(form,mobileRechargeCommand);
		 }
	            
	      else if(type.toLowerCase().contains("internet"))
	      {
	    	  form.setName("Vodafone Internet");
	    	  //internetCommand.setForm(form);
	    	  return new VodafoneInternetPayment(form,internetCommand);
	      }
		 
	        	  
		return null;
	}


}
