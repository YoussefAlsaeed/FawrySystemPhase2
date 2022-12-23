package com.projects.FawrySystem.FawrySystemAPI.APIControllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.CancerHospitalFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.EtisalatFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.MonthlyReceiptFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.NGOFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.OrangeFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.QuarterReceiptFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.SchoolProviderFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.VodafoneFactory;
import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.WeFactory;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.AdminController;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.User;
import com.projects.FawrySystem.FawrySystemAPI.mainPackage.UserController;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.IService;
import com.projects.FawrySystem.FawrySystemAPI.transaction.AddToWalletTransaction;
import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;
@RestController
public class ApiCTRL {
	 User currentUser ;
	 ArrayList <IService> services = new ArrayList<>();
     ArrayList <User> users=new ArrayList<>();
     UserController userController;  
     AdminController adminController=new AdminController();
     WeFactory we=new WeFactory();
     VodafoneFactory vodafone=new VodafoneFactory();
     OrangeFactory orange=new OrangeFactory();
     EtisalatFactory etisalat=new EtisalatFactory();
     CancerHospitalFactory cancerhospital=new  CancerHospitalFactory(); 
     NGOFactory ngo=new NGOFactory(); 
     SchoolProviderFactory school=new SchoolProviderFactory();
     MonthlyReceiptFactory mr=new MonthlyReceiptFactory();
     QuarterReceiptFactory qr=new QuarterReceiptFactory();
     
	public ApiCTRL() {
		super();
		new File("users.txt");
		services.add(we.createServiceProvider("mobile"));
        services.add(we.createServiceProvider("internet"));
        services.add(vodafone.createServiceProvider("mobile"));
        services.add(vodafone.createServiceProvider("internet"));
        services.add(orange.createServiceProvider("mobile"));
        services.add(orange.createServiceProvider("internet"));
        services.add(etisalat.createServiceProvider("mobile"));
        services.add(etisalat.createServiceProvider("internet"));
        services.add(cancerhospital.createServiceProvider("donation"));
        services.add(ngo.createServiceProvider("donation"));
        services.add(school.createServiceProvider("donation"));
        services.add(mr.createServiceProvider("landline"));
        services.add(qr.createServiceProvider("landline"));
        userController = new UserController(services);
	        

	}
	 @GetMapping(value="/searchforService/{service}")
	public ArrayList<String> search(@PathVariable ("service") String item)
	{
		
		ArrayList<String> results=userController.searchforService(item);
		 if(currentUser==null)
			{
			   results.add("An Error Occured Please, Login First");
			   return results;
			}
		if(results.size()>0)
		{
			return results;
		}
		else results.add("Nothing matches your query :'( "); 
		return results;
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
           
            return "Login Successful";
        }
		else return "User Not Found,Please signup first";
    }
	@PutMapping(value="/addToWallet/{amount}")
	public String addToWallet(@PathVariable ("amount") double amount )
	{
		if(currentUser==null)
		{
			return "An Error Occured, Please Login First";
		}
		 if(userController.addToWallet(amount, currentUser, adminController)) // Adding money from credit card to current user's wallet and saving the transaction.
		 {
			 return "Amount added to wallet = "+currentUser.getWallet()+"\n Creditcard balance =  "+currentUser.getCreditCard();
		 }
		 else return"Transaction faild ,Not enough balance in your creditcard\n Creditcard balance = " +currentUser.getCreditCard();
			
	}
	
	@GetMapping(value = "/viewTransations")
	public ArrayList<String> viewTransactions()
	{
		ArrayList<String> responses = new ArrayList<String>();

		if(currentUser==null)
		{
			responses.add("An Error Occured, Please Login First");
			return responses;
		}
		else
		{
			ArrayList<ITransaction> transactions =  (ArrayList<ITransaction>) userController.viewUserTransactionHistory(currentUser).get(1);

			if(transactions.size()==0)
			{
				responses.add("No Transactions Yet !");
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
        if(currentUser==null)
        {
            return "An Error Occured Please Login First";
        }
        if((boolean)userController.viewUserTransactionHistory(currentUser).get(0))
        {
        adminController.addToRefundRequests(currentUser, TransactionID);
        return "Your request will be accepted/rejected by the admin";
        }
        return"No Transaction with this  ID" ;

    }
}
