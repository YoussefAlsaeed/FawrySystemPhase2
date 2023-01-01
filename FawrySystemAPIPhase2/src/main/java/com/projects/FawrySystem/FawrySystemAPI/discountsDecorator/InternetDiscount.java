package com.projects.FawrySystem.FawrySystemAPI.discountsDecorator;

import com.projects.FawrySystem.FawrySystemAPI.ServicesPackage.*;



public class InternetDiscount extends DiscountDecorator{
	static double discountPercentage;
	public InternetDiscount(IServiceProviders service) {
		super(service);
	}
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
		double c=service.getCost()*discountPercentage;//calculate amount after applying discount
		//System.out.println("C"+c);
		setCost(service.getCost()-c);
		System.out.println("Price after applying Internet payment service discount("+discountPercentage*100+"%)="+ service.getCost());
		return false;
	}

}