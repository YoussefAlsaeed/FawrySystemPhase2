package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;


import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;



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
