package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public abstract class InternetPayment implements IService,IServiceProviders{

	IPaymentMethod paymentMethod;
	Form form;
	InternetCommand c;
	double cost;
	public InternetPayment(Form form,InternetCommand c)
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
	@Override
	public String toString()
	{
		return "Internet Payment Service";
	}
	public Form getForm()
	{
		return form;
	}
	
}
