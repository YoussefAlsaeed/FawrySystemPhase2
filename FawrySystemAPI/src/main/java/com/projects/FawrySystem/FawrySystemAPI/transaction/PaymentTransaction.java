package com.projects.FawrySystem.FawrySystemAPI.transaction;

import com.projects.FawrySystem.FawrySystemAPI.PaymentMethodStrategy.*;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.*;

public class PaymentTransaction implements ITransaction
{
	//private IServiceProviders service;
	private String serviceName;
	private String TransactionId;
	private static int counter=0;
	private double amount;
	private IPaymentMethod paymentMethod;

	public PaymentTransaction(String sName, double amount, IPaymentMethod paymentMethod) {
		counter++;
		//every payment transaction starts with '1'
		TransactionId="1"+Integer.toString(counter);
		this.serviceName = sName;
		this.amount=amount;
		this.paymentMethod=paymentMethod;
	}

	public String toString()
	{
		return "\n<Payment transaction>\n<Transaction ID is "+ TransactionId +(">\n")+ "<Amount paid by user "+ amount +">\n" +"<"+ serviceName+">\n"+"<"+paymentMethod+">\n" ;
		
	}


	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return TransactionId;
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	
}
