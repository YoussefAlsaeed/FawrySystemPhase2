package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

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
	public String toString()
	{
		return "This is a Text Field, please enter ("+textFieldName+")"+"\n---------------------------------------\n";
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return textFieldName;
	}
	@Override
	public ArrayList<Object> getField() {
		ArrayList<Object> result=new ArrayList<Object>();
		result.add(textFieldName);
		return result;
	}

}
