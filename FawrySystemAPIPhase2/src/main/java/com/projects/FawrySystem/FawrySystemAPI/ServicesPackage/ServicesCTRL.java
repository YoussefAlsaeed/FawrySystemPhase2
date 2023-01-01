package com.projects.FawrySystem.FawrySystemAPI.ServicesPackage;

import java.util.ArrayList;


public class ServicesCTRL {
	WeFactory weFactory=new WeFactory();
    VodafoneFactory vodafoneFactory=new VodafoneFactory();
    OrangeFactory orangeFactory=new OrangeFactory();
    EtisalatFactory etisalatFactory=new EtisalatFactory();
    CancerHospitalFactory cancerhospitalFactory=new  CancerHospitalFactory(); 
    NGOFactory ngoFactory=new NGOFactory(); 
    SchoolProviderFactory schoolFactory=new SchoolProviderFactory();
    MonthlyReceiptFactory monthlyReceiptFactory=new MonthlyReceiptFactory();
    QuarterReceiptFactory quarterReceiptFactory=new QuarterReceiptFactory();
    private static ServicesCTRL instance;
    ArrayList <IService> servicesList= new ArrayList<IService>();
    public static ServicesCTRL getInstance() {
    	if(instance == null)
    	{
			instance = new ServicesCTRL();
    	}
    	return instance;
	}
	public ServicesCTRL() {
		
		servicesList.add(weFactory.createServiceProvider("mobile"));
		servicesList.add(weFactory.createServiceProvider("internet"));
		servicesList.add(vodafoneFactory.createServiceProvider("mobile"));
		servicesList.add(vodafoneFactory.createServiceProvider("internet"));
		servicesList.add(orangeFactory.createServiceProvider("mobile"));
		servicesList.add(orangeFactory.createServiceProvider("internet"));
		servicesList.add(etisalatFactory.createServiceProvider("mobile"));
		servicesList.add(etisalatFactory.createServiceProvider("internet"));
		servicesList.add(cancerhospitalFactory.createServiceProvider("donation"));
		servicesList.add(ngoFactory.createServiceProvider("donation"));
		servicesList.add(schoolFactory.createServiceProvider("donation"));
		servicesList.add(monthlyReceiptFactory.createServiceProvider("landline"));
		servicesList.add(quarterReceiptFactory.createServiceProvider("landline"));
	}

	public IService createProvider(String userChoice,String providerChoice ) {
		
		 IService service;
		 if(userChoice.contains("mobile")||userChoice.contains("internet")) // Choosing the service and service provider to pay for
         {
         	if(userChoice.toLowerCase().contains("mobile"))
         		userChoice="mobile";
         	else if(userChoice.toLowerCase().contains("internet"))
         		userChoice="internet";
         	 if(providerChoice.toLowerCase().contains("we"))
         		 service=weFactory.createServiceProvider(userChoice);
          	else if(providerChoice.toLowerCase().contains("orange"))
          		service=orangeFactory.createServiceProvider(userChoice);
          	else if(providerChoice.toLowerCase().contains("vodafone"))
          		service=vodafoneFactory.createServiceProvider(userChoice);
          	else if(providerChoice.toLowerCase().contains("etisalat"))
          		service=etisalatFactory.createServiceProvider(userChoice);
          	else
           	{   
          		//System.out.println("no provider with this type");
          		return null;
             }	 
        }
         
         else if(userChoice.toLowerCase().contains("donation"))
         {
         	 if(providerChoice.toLowerCase().contains("school"))
         		 service=schoolFactory.createServiceProvider(userChoice);
          	else if(providerChoice.toLowerCase().contains("ngo"))
          		service=ngoFactory.createServiceProvider(userChoice);
          	else if(providerChoice.toLowerCase().contains("cancer"))
          		service=cancerhospitalFactory.createServiceProvider(userChoice);
          	else
           	{   //System.out.println("no provider with this type");
        		    return null;
             }
         }
         else if(userChoice.toLowerCase().contains("landline"))
         {
         	 
         	 if(providerChoice.toLowerCase().contains("month"))
         		 service= monthlyReceiptFactory.createServiceProvider(userChoice);
          	else if(providerChoice.toLowerCase().contains("quarter"))
          		service=quarterReceiptFactory.createServiceProvider(userChoice);
          	else
           	{   //System.out.println("no provider with this type");
           			return null;
             }
         }
         else 
     	{  
     		System.out.println("no service with this type");
     				return null;
         }
		 return service;
		 
	}
	
	public ArrayList<String> searchforService(String service)
    {
        
        service = service.trim().toLowerCase();
        ArrayList<String> resultList = new ArrayList<String>();
        
        int count = 0;
             
        for(int i = 0 ; i< servicesList.size() ; i++)
        {
            if(servicesList.get(i).getClass().getSimpleName().toLowerCase().contains(service))
            {
    			
            	 System.out.println((count+1) + " . "+ servicesList.get(i).getClass().getSimpleName()); 
    			 resultList.add((count+1) + " . "+ servicesList.get(i).getClass().getSimpleName());
                 count++;
                
               
            }
        }
        
        if(count == 0)
        {
        	System.out.println("Nothing matches your query :'( ");
            resultList.add("Nothing matches your query :'( ");
        }
        
        return resultList;
        
    }


	public String getForm(IService serviceProviderObj) {
		
		return serviceProviderObj.getForm().toString();
	}
	
	
	public ProviderFactory chooseProviderFactory(String providerFactoryName)
    {
        ProviderFactory providerFactory = null ;

        if(providerFactoryName.toLowerCase().contains("vodafone"))
        {
            providerFactory= vodafoneFactory;                        
        }
        else if(providerFactoryName.toLowerCase().contains("orange"))
        {
            providerFactory =orangeFactory;
        }
        else if(providerFactoryName.toLowerCase().contains("we"))
        {
            providerFactory=weFactory;
        }
        else if(providerFactoryName.toLowerCase().contains("etisalat"))
        {
            providerFactory= etisalatFactory;
        }    
        else if(providerFactoryName.toLowerCase().contains("schools"))
        {
            providerFactory = schoolFactory;
        }    
        else if(providerFactoryName.toLowerCase().contains("ngo"))
        {
            providerFactory =ngoFactory;
        }
        else if(providerFactoryName.toLowerCase().contains("cancer"))
        {
            providerFactory = cancerhospitalFactory;
        }
        else if(providerFactoryName.toLowerCase().contains("monthly"))
        {
            providerFactory =monthlyReceiptFactory;
        }
        else if(providerFactoryName.toLowerCase().contains("quarter"))
        {
            providerFactory =quarterReceiptFactory;    
        }
        else
        	{ System.out.println("no provider with this type :( ");
        	  return null;
        	}
        
        return providerFactory;
    }
	
	
	public String addTextField(String providerName , String textfield)
	{
		chooseProviderFactory(providerName).addTextField(textfield);
		return "Textfield < "+textfield+" > has been added to " + providerName +"'s form";
	}
	
	public String addDropDownField(String providerName , String dropdownfield,ArrayList<Object> values)
	{	
		chooseProviderFactory(providerName).addDropDownFlield(dropdownfield, values.size(), values);
		return "Dropdown field < "+dropdownfield+" > has been added to " + providerName +"'s form";
	}
	
	//getters for the factories
	public WeFactory getWeFactory() {
		return weFactory;
	}
	public VodafoneFactory getVodafoneFactory() {
		return vodafoneFactory;
	}
	public OrangeFactory getOrangeFactory() {
		return orangeFactory;
	}
	public EtisalatFactory getEtisalatFactory() {
		return etisalatFactory;
	}
	public CancerHospitalFactory getCancerhospitalFactory() {
		return cancerhospitalFactory;
	}
	public NGOFactory getNgoFactory() {
		return ngoFactory;
	}
	public SchoolProviderFactory getSchoolFactory() {
		return schoolFactory;
	}
	public MonthlyReceiptFactory getMonthlyReceiptFactory() {
		return monthlyReceiptFactory;
	}
	public QuarterReceiptFactory getQuarterReceiptFactory() {
		return quarterReceiptFactory;
	}
	public ArrayList<IService> getServicesList() {
		return servicesList;
	}
	public String validate(IService serviceProviderObj, ArrayList<String> values) {
		String paymentMethod = values.get(0);
		ArrayList<Object> validPaymentMethods =serviceProviderObj.getForm().getElements().get(0).getField();
		System.out.println(validPaymentMethods);
		for(int i=0;i<validPaymentMethods.size();i++)
		{
			//checking if user input in the payment method field is correct
			if(validPaymentMethods.get(i).toString().toLowerCase().contains(paymentMethod.toLowerCase()))
			{
				return null;
			}
		}
		//if payment method not found
		return "Payment method not found";
		
		
	}
	public void setFormValues(IService serviceProviderObj, ArrayList<String> values) {
		 Form form=serviceProviderObj.getForm();
		 form.setValues(values);
		
	}
}
