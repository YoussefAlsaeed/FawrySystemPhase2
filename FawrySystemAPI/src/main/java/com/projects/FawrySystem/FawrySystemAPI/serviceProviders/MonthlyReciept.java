package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.LandlineCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;

public class MonthlyReciept extends LandLine {

	public MonthlyReciept(Form form, LandlineCommand c) {
		super(form, c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Monthly Reciept Landline";
	}

}
