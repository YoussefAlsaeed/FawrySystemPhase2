package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;


import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;



public class NgosDonations extends Donation{

	public NgosDonations(Form form, DonationsCommand c) {
		super(form, c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "NGOs Donation";
	}

}
