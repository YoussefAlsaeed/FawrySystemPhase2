package com.projects.FawrySystem.FawrySystemAPI.UserPackage;


public class CreditCardPaymentMethod implements IPaymentMethod {

	@Override
	public boolean pay(User user,double amount) {
		if(user.getCreditCard()<amount)
		{
			System.out.println("Not enough money in your credit card!");
			return false;
		}
		user.setCreditCard(user.getCreditCard()-amount);
		return true;

	}
	public String toString()
	{
		return "Payment method = Credit card";
		
	}

}
