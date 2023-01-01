package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public abstract class MobileRechargeService implements IService,IServiceProviders{

	IPaymentMethod paymentMethod;
	private double cost;
	private Form form;
	private MobileRechargeCommand c;
	public MobileRechargeService(Form form,MobileRechargeCommand c)
	{
		this.form=form;
		this.c=c;
	}
	public ITransaction pay(User user)
	{
		c.setValues(form.getValues());
		c.setService(this);
		c.setUser(user);
		return c.execute();
	}
	public Form getForm()
	{
		return form;
	}
	
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString()
	{
		return "Mobile Recharge Service";
	}
	
	
}
