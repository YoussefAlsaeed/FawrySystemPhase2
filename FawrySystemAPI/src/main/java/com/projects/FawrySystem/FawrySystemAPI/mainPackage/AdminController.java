
package com.projects.FawrySystem.FawrySystemAPI.mainPackage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.projects.FawrySystem.FawrySystemAPI.abstractFactory.*;
import com.projects.FawrySystem.FawrySystemAPI.composite.*;
import com.projects.FawrySystem.FawrySystemAPI.discountsDecorator.*;
import com.projects.FawrySystem.FawrySystemAPI.refundRequestStrategy.*;
import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.*;
import com.projects.FawrySystem.FawrySystemAPI.transaction.*;

public class AdminController {
	
	Admin admin=new Admin();

    IRefundRequest refundRequestStrategy;
    
    public AdminController()
    {
    	
    }
    
    public void addPaymentMethodToProvider(ProviderFactory provider ,String s)
    {
    	if(s.equals("1"))
			provider.addPaymentMethod("wallet");
		else if(s.equals("2"))
			provider.addPaymentMethod("cash on delivery");
		else System.out.println("Choice invalid!");
    }
    
    
	public boolean viewRefundRequests()
	{
		boolean found=false;
		ArrayList<ITransaction> transactions=admin.getTransactionList();
		for( Entry<String, User> entry :admin.getRefundRequests().entrySet() )
		{
			
		    System.out.print("UserName = "+entry.getValue().getUsername()+ " : " );
		    for(int i=0;i<transactions.size();i++)
		    {
		    	if(entry.getKey().equals(transactions.get(i).getID()))
		    	{
		    		System.out.print(transactions.get(i)+"\n");
		    		System.out.println("--------------------------------");
		    		found=true;
		    	}
		    }
		}
		return found;
		
	}
	
	
	public boolean listallTransactions()
	{
		return admin.printTransactions();
	}
	

	public ArrayList<String> listuserTransactions(String user)
	{
		ArrayList<String> response = new ArrayList<String>();
	
		for (int i = 0; i <admin.getUserList().size(); i++)
		{
			if(admin.getUserList().get(i).getUsername().equals(user))
			{
				String msg = admin.getUserList().get(i).toString();
				response.add(msg);
				System.out.println(admin.getUserList().get(i).toString());
			}
		}
		
		return response;
	}
	
	public void addDiscount(String c,double discount, UserController userController)
	{
		if(c.equals("1"))
    	{
    		//MobileRechargeDiscount d=new MobileRechargeDiscount(null);
    		MobileRechargeDiscount.setDiscountPercentage(discount/100);
    		System.out.println("Discount now is:"+MobileRechargeDiscount.getDis()); 
    		userController.addtoDiscountList("Mobile Recharge Discount",MobileRechargeDiscount.getDis()*100);
    		

    	}
    	else if(c.equals("2"))
    	{
    		//MobileRechargeDiscount d=new MobileRechargeDiscount(null);
    		InternetDiscount.setDiscountPercentage(discount/100);
    		System.out.println("Discount now is:"+InternetDiscount.getDis()); 
    		userController.addtoDiscountList("Internet Discount",InternetDiscount.getDis()*100);

    	}
    	else if(c.equals("3"))
    	{
    		//MobileRechargeDiscount d=new MobileRechargeDiscount(null);
    		LandLineDiscount.setDiscountPercentage(discount/100);
    		System.out.println("Discount now is:"+LandLineDiscount.getDis()); 
    		userController.addtoDiscountList("LandLine Discount",LandLineDiscount.getDis()*100);

    	}
    	else if(c.equals("4"))
    	{
    		//MobileRechargeDiscount d=new MobileRechargeDiscount(null);
    		DonationsDiscount.setDiscountPercentage(discount/100);
    		System.out.println("Discount now is:"+DonationsDiscount.getDis()); 
    		userController.addtoDiscountList("Donations Discount",DonationsDiscount.getDis()*100);

    	}
    	else if(c.equals("5"))
    	{
    		//MobileRechargeDiscount d=new MobileRechargeDiscount(null);
    		OverallDiscount.setDiscountPercentage(discount/100);
    		System.out.println("Discount now is:"+OverallDiscount.getDis()); 
    		userController.addtoDiscountList("Overall Discount",OverallDiscount.getDis()*100);

    	}
    	else System.out.println("Invalid choice");
	}
	
	public void removeDiscount(String c, UserController userController)
	{
		if(c.equals("1"))
    	{
    		MobileRechargeDiscount.setDiscountPercentage(0.0);
    		System.out.println(MobileRechargeDiscount.getDis()); 
    		userController.removeDiscountList("Mobile Recharge Discount",0.0);

    	}
    	else if(c.equals("2"))
    	{
    		InternetDiscount.setDiscountPercentage(0.0);
    		System.out.println(InternetDiscount.getDis()); 
    		userController.removeDiscountList("Internet Discount",0.0);

    	}
    	else if(c.equals("3"))
    	{
    		LandLineDiscount.setDiscountPercentage(0.0);
    		System.out.println(LandLineDiscount.getDis()); 
    		userController.removeDiscountList("LandLine Discount",0.0);

    	}
    	else if(c.equals("4"))
    	{
    		DonationsDiscount.setDiscountPercentage(0.0);
    		System.out.println(DonationsDiscount.getDis()); 
    		userController.removeDiscountList("Donations Discount",0.0);

    	}
    	else if(c.equals("5"))
    	{
    		OverallDiscount.setDiscountPercentage(0.0);
    		System.out.println(OverallDiscount.getDis()); 
    		userController.removeDiscountList("Overall Discount",0.0);

    	}
    	else System.out.println("Invalid choice");
    	
	}
	

	public void addToRefundRequests(User user, String transactionID) {
		for(int i=0;i<admin.getTransactionList().size();i++)
		{
			if(admin.getTransactionList().get(i).getID().equals(transactionID))
			{
				admin.addToRefundRequests(transactionID, user);
			}
		}
		
	}

	public void addToTransactions(ITransaction t, User user) {
		admin.addTransaction(t);
		if(!(admin.getUserList().contains(user)))
		   admin.addUser(user);
	}
	
	public void acceptTransaction(String transactionID,User user)
	{    
		user =admin.getRefundRequests().get(transactionID);
		ITransaction acceptedTransaction=null;
		for(int i=0;i<admin.getTransactionList().size();i++)
		{
			if(admin.getTransactionList().get(i).getID().equals(transactionID))
			{
				acceptedTransaction = admin.getTransactionList().get(i);
			
			}
		}
		refundRequestStrategy.refund(acceptedTransaction,user);
		refundedTransaction(acceptedTransaction, user);
	}
	
	public void refundedTransaction(ITransaction t, User user)
	{
		ITransaction transaction = new RefundTransaction(t.getAmount());
		user.addTransaction(transaction);
		addToTransactions(transaction,user);
		
	}
	
	public void setRefundRequest(IRefundRequest request) {
		this.refundRequestStrategy= request;
	}
	
	public IRefundRequest  RefundRequest() {
		return refundRequestStrategy;
	}


	public boolean checkTransactions(User user) {
		ArrayList<ITransaction>t=user.getTransactionList();
		if(t.size()>0){
			return true;
		}
		else 
		return false;
		
	}
	
	



	
}