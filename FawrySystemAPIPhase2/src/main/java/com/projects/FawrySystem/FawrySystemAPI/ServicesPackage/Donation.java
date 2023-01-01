package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public abstract class Donation implements IService,IServiceProviders{

	/*
	 * Every service provider has a form,command, payment method,cost(amount to be payed)
	 */
	IPaymentMethod paymentMethod;
	Form form;
	DonationsCommand c;
	double cost;
	public Donation(Form form,DonationsCommand c)
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
		return "Donation Service";
	}
	
}
