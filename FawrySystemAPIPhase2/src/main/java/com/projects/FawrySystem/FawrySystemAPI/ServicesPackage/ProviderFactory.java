package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;

import com.projects.FawrySystem.FawrySystemAPI.commandPackage.*;



public abstract class ProviderFactory {
	protected Form form;
	protected MobileRechargeCommand mobileRechargeCommand= new MobileRechargeCommand();
	protected InternetCommand internetCommand=new InternetCommand();
	protected LandlineCommand landlineCommand=new LandlineCommand();
	protected DonationsCommand donationCommand=new DonationsCommand();
	protected ArrayList<Object>fields=new ArrayList<Object>();
	//create a drop down field for payment method that only as (credit card) as a default payment method
	//admin can add more payment methods to this drop down field from admin menu
	protected DropDownField d=new DropDownField("Payment Method",1,fields);
	//creating textfields for amount and mobile number as a default for every form to be created 

	protected TextField t1=new TextField("Amount");
	protected TextField t2=new TextField("Mobile Number");
	
	protected String formName;
	protected ArrayList<UIElements> elements = new ArrayList<UIElements>();
	public ProviderFactory()
    {
		/*
		 * create default form
		 *
		 *admin can add more text fields and drop down fields for specific provider from admin menu
		*/
		form=new Form();
        createForm();
    }

	public Form createForm() {
		//create default form
		fields.add("CreditCard");
		elements.add(d);
		elements.add(t1);
		elements.add(t2);
		for(int i=0;i<elements.size();i++)
		{
			form.addElement(elements.get(i));
		}
		return form;
		
	}

	public void addDropDownFlield(String name,int noOfFields,ArrayList<Object>array)
	{
		form.addElement(new DropDownField(name,noOfFields,array));
	}
	
	public void addTextField(String name)
	{
		form.addElement(new TextField(name));
	}
	
	public int getNoOfElements()
	{
		return elements.size();
	}
	abstract public IService createServiceProvider(String type) ;
	
	/*
	 * method to add more payment methods to a form 
	 *  wallet or cash on delivery methods can be added from here
	 */
	public boolean addPaymentMethod(Object newField)
	{	
		
		return this.d.addField(newField);
		
	}
}
