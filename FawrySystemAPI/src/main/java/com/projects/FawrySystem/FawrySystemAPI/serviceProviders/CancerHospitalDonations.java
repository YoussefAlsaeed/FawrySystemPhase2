package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.DonationsCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;

public class CancerHospitalDonations extends Donation {
	public CancerHospitalDonations(Form form, DonationsCommand c) {
		super(form, c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Cancer Hospital Donations";
	}

}