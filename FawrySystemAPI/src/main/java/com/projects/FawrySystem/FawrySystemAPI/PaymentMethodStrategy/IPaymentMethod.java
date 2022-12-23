package com.projects.FawrySystem.FawrySystemAPI.PaymentMethodStrategy;

import com.projects.FawrySystem.FawrySystemAPI.mainPackage.User;

public interface IPaymentMethod {	

	boolean pay(User user, double amount);
	String toString();
}
