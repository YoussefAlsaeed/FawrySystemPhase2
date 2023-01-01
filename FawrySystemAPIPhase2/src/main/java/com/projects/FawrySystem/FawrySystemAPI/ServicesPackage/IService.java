package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public interface IService {
	//public boolean fillForm(User user);
	public ITransaction pay(User user);
	public Form getForm();
	
	
}
