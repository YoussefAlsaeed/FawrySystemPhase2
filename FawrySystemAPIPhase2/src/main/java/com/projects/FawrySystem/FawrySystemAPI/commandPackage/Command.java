package com.projects.FawrySystem.FawrySystemAPI.commandPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.ServicesPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;



public abstract class Command {

//	Form form;
	User user;
	IServiceProviders service;
	ArrayList<String> values;
	//public Command(Form)
	//public void execute() {}
	public abstract ITransaction execute();
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
}
