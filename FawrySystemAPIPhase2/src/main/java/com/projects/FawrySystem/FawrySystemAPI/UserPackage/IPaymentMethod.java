package com.projects.FawrySystem.FawrySystemAPI.UserPackage;


public interface IPaymentMethod {	

	boolean pay(User user, double amount);
	String toString();
}
