package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.InternetCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.User;

public class VodafoneInternetPayment extends InternetPayment{


	public VodafoneInternetPayment(Form form, InternetCommand c) {
		super(form, c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Vodafone Internet Payment";
	}


}
