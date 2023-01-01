package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;



public class CancerHospitalFactory extends ProviderFactory  {
	/*
	 * Creating new service providers of type (we)
	 * Each service provider takes form and command in its parameter
	 * Command type is set according to the service that the user wants
	 */
	@Override
	public IService createServiceProvider(String type) {
	
		if(type.toLowerCase().contains("donation"))
		 {
			 form.setName("Cancer Hospital Donation provider");
			 //donationCommand.setForm(form);
			 return new CancerHospitalDonations(form,donationCommand);
		 }

		return null;
	}


}
