package com.projects.FawrySystem.FawrySystemAPI.abstractFactory;

import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.EtisalatInternetPayment;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.EtisalatMobileRecharge;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.IService;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.QuarterReceipt;

public class QuarterReceiptFactory extends ProviderFactory{

	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	

	@Override
	public IService createServiceProvider(String type) {
		
		 if(type.toLowerCase().contains("landline"))
		 {
			 form.setName("Quarter Receipt Landline");
			 //landlineCommand.setForm(form);
			 return new QuarterReceipt(form,landlineCommand);
		 }
	            
	      
		 
	        	  
		return null;
	}

}
