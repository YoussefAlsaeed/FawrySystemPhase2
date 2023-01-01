package com.projects.FawrySystem.FawrySystemAPI.commandPackage;
import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.ServicesPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;
import com.projects.FawrySystem.FawrySystemAPI.discountsDecorator.*;

public class MobileRechargeCommand extends Command {
	IPaymentMethod payment;
	IServiceProviders service;
	
	@Override
	public ITransaction execute() {
		double c=Double.parseDouble(values.get(1));
		service.setCost(c);	//set the service cost to the amount that the user entered
		String sName=service.toString();
		System.out.println("-----------------------");
		applyDiscounts();//get cost after applying discounts (decorator)
		System.out.println("-----------------------");
		double amount=service.getCost();
		
		if(values.get(0).toLowerCase().contains("credit")) //if user chose credit card as payment method
		{
			this.payment=new CreditCardPaymentMethod();
			if(!payment.pay(user,amount)) return null;//if amount entered is more than user's balance
			System.out.println("Your credit card balance=" +user.getCreditCard());
		}
		else if(values.get(0).toLowerCase().contains("wallet"))//if user chose wallet as payment method
		{
			payment=new WalletPaymentMethod();
			if(!payment.pay(user, amount)) return null;//if amount entered is more than user's balance
			System.out.println("Your wallet balance="+user.getWallet());
		}
		else if(values.get(0).toLowerCase().contains("cash"))//if user chose cash on delivery as payment method
		{
			payment=new CashOnDeliveryMethod();
		}
		else {
			System.out.println("Payment Method not found");//if payment method is not of the above
			return null;
		}
		//System.out.println(payment);
		PaymentTransaction t=new PaymentTransaction(sName, amount, payment); //create new payment transaction
		System.out.println(t);
		user.addTransaction(t);//add this transaction to user transaction list
		return t;
		
		
	}
	
	//applying decorator pattern here
	public void applyDiscounts()
	{
		service=new MobileRechargeDiscount(service);
		
		if(user.getTransactionList().size()==0)
		{
			service=new OverallDiscount(service);
		}
		
		
	}
	//setters and getters
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public IServiceProviders getService() {
		return service;
	}
	public void setService(IServiceProviders service) {
		this.service = service;
	}


}
