package com.projects.FawrySystem.FawrySystemAPI.composite;

public class TextField implements UIElements {

	String textFieldName;
	public TextField(String textFieldName)
	{
		this.textFieldName=textFieldName;
	}
	@Override
	public void view() {
		
		System.out.println("This is a Text Field, please enter ("+textFieldName+")");
		System.out.println("---------------------------------------");

	}

}
