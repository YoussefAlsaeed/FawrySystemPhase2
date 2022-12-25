package com.projects.FawrySystem.FawrySystemAPI.refundRequestStrategy;

import com.projects.FawrySystem.FawrySystemAPI.mainPackage.User;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;

public class PaymentRefundRequest implements IRefundRequest {
	@Override
	public String refund(ITransaction acceptedTransaction,User user) {
	
		user.setCreditCard(user.getCreditCard()+acceptedTransaction.getAmount());
		System.out.println(acceptedTransaction.getAmount()+" was returned to the user ("+user.getUsername()+") credit card");
		return acceptedTransaction.getAmount()+" was returned to the user ("+user.getUsername()+") credit card";
}
}