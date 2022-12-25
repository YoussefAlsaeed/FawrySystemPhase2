package com.projects.FawrySystem.FawrySystemAPI.APIControllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.FawrySystem.FawrySystemAPI.FawrySystemApiApplication;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.CancerHospitalFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.EtisalatFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.MonthlyReceiptFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.NGOFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.OrangeFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.QuarterReceiptFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.SchoolProviderFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.VodafoneFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.WeFactory;
import com.projects.FawrySystem.FawrySystemAPI.composite.Form;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.AdminController;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.User;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.UserController;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.IService;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.IServiceProviders;
import com.projects.FawrySystem.FawrySystemAPI.transaction.AddToWalletTransaction;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;
@RestController
public class ApiCTRL {
	 User currentUser ;
	 ArrayList <IService> services = new ArrayList<>();
	 ArrayList <User> users=FawrySystemApiApplication.users;
     UserController userController;  
     AdminController adminController=AdminController.getInstance();
     WeFactory we=new WeFactory();
     VodafoneFactory vodafone=new VodafoneFactory();
     OrangeFactory orange=new OrangeFactory();
     EtisalatFactory etisalat=new EtisalatFactory();
     CancerHospitalFactory cancerhospital=new  CancerHospitalFactory(); 
     NGOFactory ngo=new NGOFactory(); 
     SchoolProviderFactory school=new SchoolProviderFactory();
     MonthlyReceiptFactory mr=new MonthlyReceiptFactory();
     QuarterReceiptFactory qr=new QuarterReceiptFactory();
     ServicesCTRL serviceCTRL;
     boolean signedIn;
     
	public ApiCTRL() {
		super();
		new File("users.txt");
		
        userController =UserController.getInstance();
        serviceCTRL=ServicesCTRL.getInstance();

	        
	}
	 @GetMapping(value="/searchforService/{service}")
	public ArrayList<String> search(@PathVariable ("service") String item)
	{
		
		 ArrayList<String> results;
		 if(signedIn)
		 {
		   results=serviceCTRL.searchforService(item);
			if(results.size()>0)
			{
				
				return results;
			}
			else results.add("Nothing matches your query :'( "); 
			return results;
		  }
			else
			 {
				 results=new ArrayList<String>();
				 results.add("An Error Occured Please, Login First");
				 return results;
			 }
	}
	@GetMapping(value="/login")
    public String loginAPI(@RequestBody User user)
    { 
		
		boolean found=false;
		if(userController.login(user))
        { 
			for(int i=0;i<users.size();i++)            //Checks if the user already exists in the system
            {
                if(users.get(i).getUsername().equals(user.getUsername()))
                {
                	currentUser=users.get(i);
                	found=true;
                    
                }
            }
            if(!found)
            {
            	currentUser=user;
                users.add(user); 
            }
            signedIn=true;
            return "Login Successful";
        }
		else return "User Not Found,Please signup first";
    }
	
	@PutMapping(value="/addToWallet/{amount}")
	public String addToWallet(@PathVariable ("amount") double amount )
	{
		if(signedIn){
			
		 if(userController.addToWallet(amount, currentUser, adminController)) // Adding money from credit card to current user's wallet and saving the transaction.
		 {
			 return "Amount added to wallet = "+currentUser.getWallet()+"\n Creditcard balance =  "+currentUser.getCreditCard();
		 }
		 else return"Transaction failed ,Not enough balance in your creditcard\n Creditcard balance = " +currentUser.getCreditCard();
		 }
		else
			return "An Error Occured, Please Login First";
			
			
	}
	@PutMapping(value="/addDiscount/{choice}/{discount}")
	public  String addDiscount (@PathVariable ("choice") String choice,@PathVariable("discount") double discount)
   {
	
		if(signedIn)
		{
			return adminController.addDiscount(choice, discount, userController);
			
		}
		else
			return "An Error Occured, Please Login First";
		
		
   }
	@PutMapping(value="/removeDiscount/{choice}")
	public  String removeDiscount (@PathVariable ("choice") String choice)
   {
	
		if(signedIn)
		{
			return adminController.removeDiscount(choice, userController);
			
		}
		else
			return "An Error Occured, Please Login First";
		
		
   }	
	
	@GetMapping(value = "/viewTransactions")
	public ArrayList<String> viewTransactions()
	{
		ArrayList<String> responses = new ArrayList<String>();
		if(!signedIn)
		{
			responses.add("An Error Occured, Please Login First");
			return responses;
			
		}
		else
		{
			ArrayList<ITransaction> transactions = userController.getUserTransactions(currentUser);

			if(transactions.size()==0)
			{
				responses.add("No Transactions Yet !");
				System.out.println("here");
			}
			else
			{
				for(int i = 0;i<transactions.size();i++)
				{
					responses.add(transactions.get(i).toString());
				}
			}
		}
		return responses;
	}
	@GetMapping(value="/refundRequest/{TransactionID}")
    public String refundRequest(@PathVariable ("TransactionID") String TransactionID )
    {
        if(!signedIn)
        {
            return "An Error Occured Please Login First";
        }
        if((boolean)userController.viewUserTransactionHistory(currentUser))
        {
	        adminController.addToRefundRequests(currentUser, TransactionID);
	        return "Your request will be accepted/rejected by the admin";
        }
        return"No Transaction with this  ID" ;

    }
	 @PostMapping(value="/signup")
	 public String signup(@RequestBody User user)
	 {
		 try {
			if(userController.signUp(user))
			 {
				 users.add(user);
				 return "Welcome, "+user.getUsername()+" You are now part of our system\n You are now logged in as "+user.getUsername();
			 }
			 else return "Username already exists, please try something else";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 @GetMapping(value="/viewMyBalance")
	 public String viewMyBalance() 
	 {
		// currentUser=user;
		if(currentUser==null)
			{
			   return "An Error Occured Please Login First";
			}
		 else
			 return userController.viewBalance(currentUser);
		 
	 }
	 @GetMapping(value="/viewDiscounts")
	 public HashMap<String,String> viewDiscounts() 
	 {
		 HashMap<String,String> discounts =new HashMap<String,String>();
		 
		if(!signedIn)
			{
			  discounts.put("An Error Occured","Please Login First");
			  return discounts;
			   
			}
		 else
			 return userController.viewDiscounts();
		 
	 }
	 
	 @GetMapping(value="/listUserTransactions/{username}")
	 public ArrayList<String> listUserTransactions(@PathVariable ("username") String username)
	 {	 
		 ArrayList<String> message = new ArrayList<String>();
		 if(!signedIn)
		 {
			 message.add("An Error Occured Please Login First");
			 return message;
		 }
		 else
		{
			 message=adminController.listuserTransactions(username);
			 if(message.size()>0)
				 return message;
			else
			{
				message.add("no transactions yet");
				return message;
			}
				
		 }
		 
	 }
	 @GetMapping(value="/logout")
	 public String logOut(@RequestBody User user)
	 {
	    signedIn=false;
	    return "You are logged out ! ";
		 
	 }
	 @GetMapping(value = "/listAllTransaction")
		public ArrayList<String> listAllTransactions()
		{
			ArrayList<String> newresponses = new ArrayList<String>();

				ArrayList<ITransaction> transactions = adminController.getaLLTransactions();

				if(!signedIn)
				{
					newresponses.add("An Error Occured Please Login First");
					return newresponses;
				}
				else if(transactions.size()==0)
				{
					newresponses.add("No Transactions Yet !");
					System.out.println("here");
				}
				else
				{
					for(int i = 0;i<transactions.size();i++)
					{
						newresponses.add(transactions.get(i).toString());
					}
				}

			return newresponses;
		}
	 
	 @GetMapping(value="/getForm/{service}/{serviceProvider}")
	 public String getForm(@PathVariable ("service") String service,@PathVariable ("serviceProvider") String serviceProvider)
	 {
		 IService serviceProviderObj= serviceCTRL.createProvider(service,serviceProvider); //creates service provider using abstract factory
		 String form= serviceCTRL.getForm(serviceProviderObj);//return the form as string
		 return form;
	 }
	 
	 @PostMapping (value="/pay/{service}/{serviceProvider}")
	 public String pay(@RequestBody ArrayList<String> values,@PathVariable ("service") String service,@PathVariable ("serviceProvider") String serviceProvider)
	 {
		 if(!signedIn)
	        {
	            return "An Error Occured Please Login First";
	        }
		 IService serviceProviderObj= serviceCTRL.createProvider(service,serviceProvider); //creates service provider using abstract factory
		 Form form=serviceProviderObj.getForm();
		 form.setValues(values);
		 ITransaction transaction=serviceProviderObj.pay(currentUser);
		 adminController.addToTransactions(transaction, currentUser);
		 String result=transaction.toString();
		 return result;
	 }
	 
	 




	 
	 
}
