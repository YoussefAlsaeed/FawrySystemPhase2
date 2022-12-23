package com.projects.FawrySystem.FawrySystemAPI.composite;

import java.util.ArrayList;

public class DropDownField implements UIElements {

	String dropDownField;
	int noOfFields;
	ArrayList<Object> array;
	/*
	 * constructor takes name of the drop down field, number of fields 
	 * and array of objects that has the options for the drop down field
	 */
	public DropDownField(String dropDownField,int noOfFields, ArrayList<Object> array)
	{
		this.noOfFields=noOfFields;
		this.array=array;
		this.dropDownField=dropDownField;
	}
	//print drop down field
	@Override
	public void view() {
		
		System.out.println("This is ("+dropDownField+") drop down field\n <please choose from these options>");
		for(int i=0;i<array.size();i++)
		{
			System.out.println(">"+array.get(i));
		}
		System.out.println("---------------------------------------");

	}
	public void incrementNoOfFields(int noOfFields)
	{
		this.noOfFields+=noOfFields;
	}
	//add new field to options of the drop down field
	public void addField(Object field)
	{
		this.incrementNoOfFields(1);
		array.add(field);
		
		
	}

}
