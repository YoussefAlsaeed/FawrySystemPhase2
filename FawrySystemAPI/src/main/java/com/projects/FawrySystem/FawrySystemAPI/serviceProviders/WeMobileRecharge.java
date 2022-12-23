package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.MobileRechargeCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;

public class WeMobileRecharge extends MobileRechargeService {

	public WeMobileRecharge(Form form, MobileRechargeCommand c) {
		super(form, c);
	
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		System.out.println(super.toString());
		return "Service Provider: WE";
	}
	

}
