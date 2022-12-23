package com.projects.FawrySystem.FawrySystemAPI.abstractFactory;

import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.EtisalatInternetPayment;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.EtisalatMobileRecharge;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.IService;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.SchoolsDonations;

public class SchoolProviderFactory extends ProviderFactory{

	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	@Override
	public IService createServiceProvider(String type) {
	
		
		 if(type.toLowerCase().contains("donation"))
		 {
			 form.setName("School Donation ");
			// donationCommand.setForm(form);
			 return new SchoolsDonations(form,donationCommand);
		 }
	            
	    
	        	  
		return null;
	}

}
