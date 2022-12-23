package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.PaymentMethodStrategy.*;
import com.projects.FawrySystem.FawrySystemAPI.command.*;
import com.projects.FawrySystem.FawrySystemAPI.composite.*;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;

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
		form.view();
		ArrayList<String> values=new ArrayList<String>();
		//get values entered by user from form
		values=form.getValues();
		System.out.println(values);
		c.setValues(values);
		c.setService(this);
		c.setUser(user);
		//execute
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
		return "Donation Service";
	}
	
}
