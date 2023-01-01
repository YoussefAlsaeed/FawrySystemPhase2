package com.projects.FawrySystem.FawrySystemAPI.discountsDecorator;

import com.projects.FawrySystem.FawrySystemAPI.ServicesPackage.*;



public abstract class DiscountDecorator implements IServiceProviders {

	
	IServiceProviders service;
	double cost;
	 
	DiscountDecorator(IServiceProviders service) {
		this.service=service;
		pay();
		//getCost();
		// TODO Auto-generated constructor stub
	}
	
	public DiscountDecorator()
	{
		
	}

	//protected static double discountPercentage;
	public abstract boolean pay();
	
	
	@Override
	public void setCost(double n) {
		this.cost=n;
		
	}
	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return cost;
	}


}
