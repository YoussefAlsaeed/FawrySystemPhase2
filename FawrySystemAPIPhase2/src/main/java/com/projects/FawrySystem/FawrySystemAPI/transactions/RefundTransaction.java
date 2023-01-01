package com.projects.FawrySystem.FawrySystemAPI.transactions;


public class RefundTransaction implements ITransaction{

	private String transactionId;
	private static int counter=0;
	private double amount;
	public  RefundTransaction(double amount) 
	{
		counter++;
		//every refund refund transaction starts with '2' 
		transactionId="2"+Integer.toString(counter);
		this.amount=amount;
	}
	
	public String getID()
	{return transactionId;}
		
	
	
	
	@Override
	public String toString()
	{

		return "<Refund transaction> <Transaction ID is "+ transactionId +(">,")+ "<Amount refunded to user: "+ amount +">" ;
		
	}

	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
}
