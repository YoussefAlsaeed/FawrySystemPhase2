package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;



public class EtisalatFactory extends ProviderFactory  {
	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	@Override
	public IService createServiceProvider(String type) {
	
		 if(type.toLowerCase().contains("mobile"))
		 {
			 form.setName("Etisalat Mobile Recharge");
			 //mobileRechargeCommand.setForm(form);
			 return new EtisalatMobileRecharge(form,mobileRechargeCommand);
		 }
	            
	      else if(type.toLowerCase().contains("internet"))
	      {
	    	  form.setName("Etisalat Internet");
	    	  //internetCommand.setForm(form);
	    	  return new EtisalatInternetPayment(form,internetCommand);
	      }
	        	  
		return null;
	}
	
}
