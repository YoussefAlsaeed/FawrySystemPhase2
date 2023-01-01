package com.projects.FawrySystem.FawrySystemAPI.transactions;


public class AddToWalletTransaction implements ITransaction 
{

	private String TransactionId;
	private static int counter=0;
	private double amount;
	public  AddToWalletTransaction(double amount) 
	{
		//every add to wallet transaction starts with '0'
		counter++;
		TransactionId="0"+Integer.toString(counter);
		this.amount=amount;
	}
		
	

	@Override
	public String toString()
	{
		return "<Add to wallet transaction>,<Transaction ID is "+ TransactionId +">," +"< Amount added to wallet is "+ amount +">";	
	}


	@Override
	public String getID() {
		return TransactionId;
	}



	@Override
	public double getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	
}
