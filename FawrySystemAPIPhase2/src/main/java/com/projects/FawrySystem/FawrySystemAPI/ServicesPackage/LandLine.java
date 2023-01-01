package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public abstract class LandLine implements IService,IServiceProviders{

	IPaymentMethod paymentMethod;
	Form form;
	LandlineCommand c;
	double cost;
	public LandLine(Form form,LandlineCommand c)
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		
		this.cost = cost;
	}
	public Form getForm()
	{
		return form;
	}
	@Override
	public String toString()
	{
		return "Landline Service";
	}
	
}
