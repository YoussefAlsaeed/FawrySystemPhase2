package com.projects.FawrySystem.FawrySystemAPI.UserPackage;


public class CashOnDeliveryMethod implements IPaymentMethod{

	@Override
	public boolean pay(User user, double amount) {
		System.out.println(user.getUsername()+",The amount you are going to pay = "+amount);
		return true;
		
	}
	public String toString()
	{
		return "Payment method = Cash On Delivery";
		
	}
	
}
