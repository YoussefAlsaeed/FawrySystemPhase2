package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.DonationsCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;
import com.projects.FawrySystem.FawrySystemAPI.discountsDecorator.DonationsDiscount;

public class SchoolsDonations extends Donation{

	public SchoolsDonations(Form form, DonationsCommand c) {
		super(form, c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Schools Donation";
	}



}
