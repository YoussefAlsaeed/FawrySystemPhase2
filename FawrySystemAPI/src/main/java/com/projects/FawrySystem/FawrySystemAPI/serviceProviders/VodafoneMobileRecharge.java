package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import com.projects.FawrySystem.FawrySystemAPI.command.Command;
import com.projects.FawrySystem.FawrySystemAPI.command.MobileRechargeCommand;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;

public class VodafoneMobileRecharge extends MobileRechargeService{
	

	public VodafoneMobileRecharge(Form form, MobileRechargeCommand c) {
		super(form, c);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Vodafone Mobile Recharge";
	}


}
