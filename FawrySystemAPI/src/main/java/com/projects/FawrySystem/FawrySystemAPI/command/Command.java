package com.projects.FawrySystem.FawrySystemAPI.command;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.composite.*;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.*;
import com.projects.FawrySystem.FawrySystemAPI.transaction.*;

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
