package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.InternetCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.*;

public class EtisalatInternetPayment extends InternetPayment {
	
	public EtisalatInternetPayment(Form form, InternetCommand c) {
		super(form,c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Etisalat Internet Payment";
	}

}
