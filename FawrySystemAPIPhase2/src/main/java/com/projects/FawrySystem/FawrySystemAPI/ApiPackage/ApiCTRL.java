package com.projects.FawrySystem.FawrySystemAPI.ApiPackage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import com.projects.FawrySystem.FawrySystemAPI.ServicesPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.UserPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.FawrySystemApiApplication;
import com.projects.FawrySystem.FawrySystemAPI.AdminPackage.*;
import com.projects.FawrySystem.FawrySystemAPI.transactions.*;


@RestController
public class ApiCTRL {
	 User currentUser ;
	 ArrayList <IService> services = new ArrayList<>();
	 ArrayList <User> users=FawrySystemApiApplication.users;
     UserController userController;  
     AdminController adminController=AdminController.getInstance();
     ServicesCTRL serviceCTRL;
     boolean signedIn=false;
     
	public ApiCTRL() {
		super();
		new File("users.txt");
		
        userController =UserController.getInstance();
        serviceCTRL=ServicesCTRL.getInstance();
	        
	}
	
	@GetMapping(value="user/searchforService/{servicename}")
	public ArrayList<String> search(@PathVariable ("servicename") String item)
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
	 
	@GetMapping(value="/user/check/login")
    public String loginAPI(@RequestBody User user)
    { 	
		boolean found=false;
		String result="";
		if(userController.login(user))
        { 
			for(int i=0;i<users.size();i++)            //Checks if the user already exists in the system
            {
                if(users.get(i).getUsername().equals(user.getUsername()))
                {
                	currentUser=users.get(i);
                	found=true;
                	result+=userController.getUserInfo(users.get(i));
                			                   
                }
            }
            if(!found)//if user has not logged in before
            {
            	currentUser=user;
                users.add(user); 
                result+=userController.getUserInfo(user);
            }
            signedIn=true;
            
            return "Login Successful\n"+result;
        }
		else return "User Not Found,Please signup first";
    }
	
	@PutMapping(value="/user/updateWallet/{amount}")
	public String addToWallet(@PathVariable ("amount") double amount )
	{
		if(signedIn){
			
		 if(userController.addToWallet(amount, currentUser, adminController)) // Adding money from credit card to current user's wallet and saving the transaction.
		 {
			 String result="Amount added to wallet = "+amount+"\n";
			 return result+"Your wallet balance now  = "+currentUser.getWallet()+"\n Creditcard balance =  "+currentUser.getCreditCard();
		 }
		 else return"Transaction failed ,Not enough balance in your creditcard\n Creditcard balance = " +currentUser.getCreditCard();
		 }
		else
			return "An Error Occured, Please Login First";
					
	}
	
	@PutMapping(value="/discount/updatePercentage/{choice}/{discount}")
	public  String addDiscount (@PathVariable ("choice") String choice,@PathVariable("discount") double discount)
   {
	
		return adminController.addDiscount(choice, discount, userController);
		
   }
	@PutMapping(value="/discount/removeDiscount/{choice}")
	public  String removeDiscount (@PathVariable ("choice") String choice)
   {
		return adminController.removeDiscount(choice, userController);				
   }	
	
	@GetMapping(value = "/user/getTransactions")
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
	
	@PostMapping(value="admin/addToRefundRequests/{TransactionID}")
    public String refundRequest(@PathVariable ("TransactionID") String TransactionID )
    {
        if(!signedIn)
        {
            return "An Error Occured Please Login First";
        }
        if(userController.inTransactionHistory(currentUser,TransactionID))
        {
        	
	        return adminController.addToRefundRequests(currentUser, TransactionID);

        }
        else 
        	return " No Transaction with this  ID " ;
    }

	 @PostMapping(value="/user/check/signup")
	 public String signup(@RequestBody User user)
	 {
		 try {
			if(userController.signUp(user))
			 {
				 users.add(user);
				 return "Welcome, "+user.getUsername()+" You are now part of our system ";
			 }
			 else return "Username already exists, please try something else";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 @GetMapping(value="/user/get/Wallet/Creditcard")
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
	 @GetMapping(value="/discount/getAll")
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
	 
	 @GetMapping(value="/admin/transactions/getUserTransactions/{username}")
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
	 @GetMapping(value="/user/logout")
	 public String logOut()
	 {
	    signedIn=false;
	    currentUser=null;
	    return "You are logged out ! ";
		 
	 }
	 
	 @GetMapping(value = "/admin/transactions/getAll")
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
	 
	 @GetMapping(value="/serviceProvider/getForm/{service}/{serviceProvider}")
	 public String getForm(@PathVariable ("service") String service,@PathVariable ("serviceProvider") String serviceProvider)
	 {
		 String form;
		 IService serviceProviderObj= serviceCTRL.createProvider(service,serviceProvider); //creates service provider using abstract factory
		 if(serviceProviderObj == null)
		 {
			 form = "An error occureed ,please check the service ,service provider you entered";
		 }
		 else
		 {
			 form = serviceCTRL.getForm(serviceProviderObj);//return the form as string
		 }	
		 return form;
	 }
	 
	 @PostMapping (value="/paymentTransaction/create/{service}/{serviceProvider}")
	 public String pay(@RequestBody ArrayList<String> values,@PathVariable ("service") String service,@PathVariable ("serviceProvider") String serviceProvider)
	 {	
		 if(!signedIn)
	        {
	            return "An Error Occured Please Login First";
	        }
		 
		 IService serviceProviderObj= serviceCTRL.createProvider(service,serviceProvider); //creates service provider using abstract factory
		 if(serviceProviderObj==null)
		 {
			 return "An error occureed ,please check the service ,service provider you entered";
		 }
		 String result=serviceCTRL.validate(serviceProviderObj,values);
		 if(result==null)
		 {
			 serviceCTRL.setFormValues(serviceProviderObj,values);
			 ITransaction transaction=serviceProviderObj.pay(currentUser);
			 if (transaction==null)
			 {
				 return "An Error Occured please check your balance and The payment method";
			 }
			 adminController.addToTransactions(transaction, currentUser);
			 result="Amount before applying discounts = "+values.get(1)+"\n Amount after applying discounts = "+transaction.getAmount()+"\n";
			 
			 result+=transaction.toString();
		 }
		 
		 return result;
	 }
	 
	 
	 @PutMapping(value="/form/addPaymentMethod/{providerFactoryName}/{paymentChoice}")
		public String addPaymentMethodd(@PathVariable ("providerFactoryName") String providerFactoryName ,@PathVariable("paymentChoice")String paymentChoice )
		{ 
			ProviderFactory providerFactory=serviceCTRL.chooseProviderFactory(providerFactoryName);	
			if(adminController.addPaymentMethodToProvider(providerFactory, paymentChoice))
				return "A new payment method : <"+paymentChoice+"> is added!";
			else
		       return "The choice of this payment method already exists or is not supported by the system yet :( ";
			
		}
	 @PutMapping(value = "user/creditcard/reviewRefundRequest/{chooseTransaction}/{acceptance}")
	 public String reviewRefundRequest(@PathVariable ("chooseTransaction") String chooseTransaction,@PathVariable("acceptance") String acceptance)
	 {
		if (chooseTransaction.charAt(0)=='2')
     	{
			 return "NO transacion";
     	}
     	String requestType=chooseTransaction.substring(0,1);
     	if(acceptance.equals("1") || acceptance.equals( "accept"))
    	{   
    		if(requestType.equals("0"))
    		  adminController.setRefundRequest(new AddToWalletRefundRequest());
    		
    		
    		else if(requestType.equals("1"))
      		  adminController.setRefundRequest(new PaymentRefundRequest());
    		
    		 return
    				 adminController.acceptTransaction(chooseTransaction);
    	}
    	return adminController.rejecttransaction(chooseTransaction);
	 }
	 
	 
	 @GetMapping(value = "/admin/getRefundRequest")
	 public String viewRefundRequest()
	 {
		 String result=adminController.viewRefundRequests();
		 if(result==null)
		 {
			 return "NO Transaction Yet";
		 }
		 return result;

     }
	 
	 @PutMapping(value="/form/textfield/{providerName}/{textfield}")
	 public String addTextFieldRequest(@PathVariable ("providerName") String providerName, @PathVariable ("textfield") String textfield)
	 {
		 return serviceCTRL.addTextField(providerName,textfield);
	 }
	 
	 @PutMapping(value="/form/dropdown/{providerName}/{dropdownfield}")
	 public String addDropDownFieldRequest(@RequestBody ArrayList<Object> values,@PathVariable ("providerName") String providerName ,@PathVariable ("dropdownfield") String dropdownfield)
	 {				 	
		return serviceCTRL.addDropDownField(providerName,dropdownfield,values);
	 }	 
	 
}