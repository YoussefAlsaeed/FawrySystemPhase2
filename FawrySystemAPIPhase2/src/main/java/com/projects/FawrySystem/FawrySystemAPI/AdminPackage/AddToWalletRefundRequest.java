package com.projects.FawrySystem.FawrySystemAPI.AdminPackage;



import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public class AddToWalletRefundRequest implements IRefundRequest {
	
	@Override
	public String refund(ITransaction acceptedTransaction,User user) {
	
		user.setCreditCard(user.getCreditCard()+acceptedTransaction.getAmount());
		user.setWallet(user.getWallet()-acceptedTransaction.getAmount());
		System.out.println(acceptedTransaction.getAmount()+" was returned to the user ("+user.getUsername()+") credit card");
		return acceptedTransaction.getAmount()+" was returned to the user ("+user.getUsername()+") credit card";
	
	}


		
	}


