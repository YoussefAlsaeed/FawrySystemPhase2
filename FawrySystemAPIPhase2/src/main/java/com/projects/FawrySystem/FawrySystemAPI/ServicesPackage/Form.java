package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;
//import java.util.Scanner;

public class Form implements UIElements {
	String formName;
	ArrayList<UIElements> elements = new ArrayList<UIElements>();
	ArrayList<String> values;
	
	public Form(String formName)
	{
		
		this.formName=formName;
	}
	public Form() {
		
	}
	
	//print form
	@Override
	public void view() {
		System.out.println("* * * * * * * * * * * * * * * * * * ");
		System.out.println("\t\tThis is "+formName+" form");
		int counter=1;
		for(int i=0;i<elements.size();i++)
		{
			System.out.print("\n"+counter+"- ");
			elements.get(i).view();
			counter++;
		}

	}
	public String toString()
	{
		String form= "\t\tThis is "+formName+" form";
		int counter=1;
		for(int i=0;i<elements.size();i++)
		{
			form+="\n"+counter+"- ";
			form+=elements.get(i).toString();
			counter++;
		}
		return form;
	}
	
	
	public ArrayList<UIElements> getElements() {
		return elements;
	}
	
	public ArrayList<Object> getField() {
        ArrayList<Object> values=new ArrayList<Object>();
        for(int i=0;i<elements.size();i++)
        {
        
            values.add(elements.get(i).getField());
        
        }
        return values;
    }
	public void setElements(ArrayList<UIElements> elements) {
		this.elements = elements;
	}
	//add UIEelements to form
	public void addElement(UIElements element)
	{
		elements.add(element);
	}
	
	//method to allow user to enter values for the form
//	public ArrayList<String> getValuesFromConsle()
//	{
//		Scanner sc=new Scanner(System.in);
//		ArrayList<String> input=new ArrayList<String>();
//		System.out.println("\nPlease Enter your answers by order");
//		int counter=1;
//		for(int i=0;i<elements.size();i++)
//		{
//			System.out.print(counter+": ");
//			input.add(sc.nextLine());
//			counter++;
//			
//		}
//		
//		return input;
//		
//	}
	public  ArrayList<String> getValues()
	{
		return values;
	}
	public void setValues(ArrayList<String> values)
	{
		this.values=values;
	}
	
	
	public void setName(String name)
	{
		this.formName=name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return formName;
	}
	
}
