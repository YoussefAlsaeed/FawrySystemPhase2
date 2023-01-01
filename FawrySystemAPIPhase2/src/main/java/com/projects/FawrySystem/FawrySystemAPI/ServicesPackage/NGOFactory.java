package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;



public class NGOFactory extends ProviderFactory  {
	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	@Override
	public IService createServiceProvider(String type) {
	
		 if(type.toLowerCase().contains("donation"))
		 {
			 form.setName("NGO donation ");
			 //donationCommand.setForm(form);
			 return new NgosDonations(form,donationCommand);
		 }

		 
	        	  
		return null;
	}


}
