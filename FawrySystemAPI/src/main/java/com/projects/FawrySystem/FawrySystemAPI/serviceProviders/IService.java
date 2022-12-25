package com.projects.FawrySystem.FawrySystemAPI.serviceProviders;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.composite.Form;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;
import com.projects.FawrySystem.FawrySystemAPI.transaction.PaymentTransaction;

public interface IService {
	//public boolean fillForm(User user);
	public ITransaction pay(User user);
	public Form getForm();
	
	
}
