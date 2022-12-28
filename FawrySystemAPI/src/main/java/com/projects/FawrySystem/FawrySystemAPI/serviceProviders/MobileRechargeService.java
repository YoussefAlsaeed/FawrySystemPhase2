package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.PaymentMethodStrategy.*;
import com.projects.FawrySystem.FawrySystemAPI.command.*;
import com.projects.FawrySystem.FawrySystemAPI.composite.*;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;
import com.projects.FawrySystem.FawrySystemAPI.transaction.PaymentTransaction;

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
