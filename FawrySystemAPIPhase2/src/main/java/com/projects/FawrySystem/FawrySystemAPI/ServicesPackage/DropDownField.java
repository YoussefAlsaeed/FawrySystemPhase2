package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

public class DropDownField implements UIElements {

	String dropDownField;
	int noOfFields;
	ArrayList<Object> fields;
	/*
	 * constructor takes name of the drop down field, number of fields 
	 * and array of objects that has the options for the drop down field
	 */
	public DropDownField(String dropDownField,int noOfFields, ArrayList<Object> array)
	{
		this.noOfFields=noOfFields;
		this.fields=array;
		this.dropDownField=dropDownField;
	}
	//print drop down field
	@Override
	public void view() {
		
		System.out.println("This is ("+dropDownField+") drop down field\n <please choose from these options>");
		for(int i=0;i<fields.size();i++)
		{
			System.out.println(">"+fields.get(i));
		}
		System.out.println("---------------------------------------");

	}
	public void incrementNoOfFields(int noOfFields)
	{
		this.noOfFields+=noOfFields;
	}
	//add new field to options of the drop down field
	public boolean addField(Object field)
	{
		for(int i=0;i<fields.size();i++)
		{
			if(field.toString()==fields.get(i).toString())
			{
				return false;
			}
		}
		this.incrementNoOfFields(1);
		fields.add(field);
		return true;
			
	}
	public String toString()
	{
		String dropDown= "This is ("+dropDownField+") drop down field\n <please choose from these options>\n";
		for(int i=0;i<fields.size();i++)
		{
			dropDown+=">"+fields.get(i);
			dropDown+="\n";
		}
		dropDown+="\n---------------------------------------\n";
		return dropDown;
	}
	@Override
	public ArrayList<Object> getField() {
		return fields;
	}
	public void setArray(ArrayList<Object> array) {
		this.fields = array;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return dropDownField;
	}
	
	

}
