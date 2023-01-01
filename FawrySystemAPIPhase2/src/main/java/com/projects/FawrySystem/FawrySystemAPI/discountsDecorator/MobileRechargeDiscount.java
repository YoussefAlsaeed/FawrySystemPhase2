package com.projects.FawrySystem.FawrySystemAPI.discountsDecorator;

import com.projects.FawrySystem.FawrySystemAPI.ServicesPackage.*;



public class MobileRechargeDiscount extends DiscountDecorator{
	static double discountPercentage=0.1;
	public MobileRechargeDiscount(IServiceProviders service) {
		
		super(service);
	}
	//public MobileRechargeDiscount() {}
	public static void setDiscountPercentage(double n) {
		discountPercentage=n;
	
	}
	public static double getDis()
	{
		return discountPercentage;
	}
	@Override
	public boolean pay()
	{
		//calculate amount after applying discount
		double c=service.getCost()*discountPercentage;
		double cost=service.getCost()-c;
		setCost(cost);
		System.out.println("Price after applying mobile recharge service discount("+discountPercentage*100+"%)="+ cost);
		return false;
	}
}


