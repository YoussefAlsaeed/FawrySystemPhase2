package com.projects.FawrySystem.FawrySystemAPI.abstractFactory;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.command.*;
import com.projects.FawrySystem.FawrySystemAPI.composite.*;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.*;

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
