package com.projects.FawrySystem.FawrySystemAPI.composite;

import java.util.ArrayList;
import java.util.Scanner;

public class Form implements UIElements {
	String formName;
	ArrayList<UIElements> elements = new ArrayList<UIElements>();
	
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
	
	//add UIEelements to form
	public void addElement(UIElements element)
	{
		elements.add(element);
	}
	
	//method to allow user to enter values for the form
	public ArrayList<String> getValues()
	{
		Scanner sc=new Scanner(System.in);
		ArrayList<String> values=new ArrayList<String>();
		System.out.println("\nPlease Enter your answers by order");
		int counter=1;
		for(int i=0;i<elements.size();i++)
		{
			System.out.print(counter+": ");
			values.add(sc.nextLine());
			counter++;
			
		}
		
		return values;
		
	}
	public void setName(String name)
	{
		this.formName=name;
	}
	
}
