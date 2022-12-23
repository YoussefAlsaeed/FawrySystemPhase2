
package com.projects.FawrySystem.FawrySystemAPI.mainPackage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.projects.FawrySystem.FawrySystemAPI.serviceProviders.*;
import com.projects.FawrySystem.FawrySystemAPI.transaction.*;

import java.util.Scanner;

public class UserController{
	
    File file= new File("users.txt");
    ArrayList <IService> servicesList= new ArrayList<IService>();
    HashMap<String,Double> discountList = new HashMap<String,Double>();

    
    public UserController (ArrayList <IService> servicesList) {
    	
        this.servicesList=servicesList;
        discountList.put("Overall Discount",10.0);
        discountList.put("Mobile Recharge Discount", 10.0);
    }
    public void setUserInfo(User user,String username,String password,String email)
    {
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setEmail(email);
    	
    }
    
	public void addToWallet(double amount,User user,AdminController a)
	{
		if(amount>user.getCreditCard())
		{
			System.out.println("Not enough money in your creditcard");
		}
		else {
			user.setWallet(user.getWallet()+amount);
			user.setCreditCard(user.getCreditCard()-amount);;
			AddToWalletTransaction t=new AddToWalletTransaction(amount);
			System.out.println(t);
			user.addTransaction(t);
			a.addToTransactions(t,user);
		}
			
		
		
	}
    
    public void searchforService(String service)
    {
    	ArrayList<String> results = new ArrayList<String>();
    	
    	service = service.trim().toLowerCase();
    	
    	int count = 0;
    	 	
    	for(int i = 0 ; i< servicesList.size() ; i++)
    	{
    		if(servicesList.get(i).getClass().getSimpleName().toLowerCase().contains(service))
    		{
    			System.out.println((count+1) + " . "+ servicesList.get(i).getClass().getSimpleName()); //THIS IS PRINTING NOT INCREMENTING
    			count++;
    		}
    	}
    	
    	if(count == 0)
    	{
    		System.out.println("Nothing matches your query	:'( ");
    	}
    	
    }

public void signUp(User user) throws IOException 
{
	
	FileWriter fr = null;
	BufferedWriter br = null;
	PrintWriter pr = null;
	Scanner read=new Scanner(file);
	String tempUsername="";
	try {
		boolean check=true;
		// to append to file, you need to initialize FileWriter using below constructor
		fr = new FileWriter(file, true);
		br = new BufferedWriter(fr);
		
		pr = new PrintWriter(br);
		while(read.hasNext())
		{
			 tempUsername=read.nextLine();
             String [] values=tempUsername.split("-");
             if(values[0].equals(user.getUsername()))
             {
            	 System.out.println("USER ALREADY EXISTS! ");
 				 pr.close();
				 br.close();
				 fr.close();
				 check = false;
				 
             }
             
		}
		if(check)
		{
			pr.println(user.getUsername()+"-"+user.getPassword()+"-"+user.getEmail());		
			System.out.println("Welcome, "+user.getUsername()+" You are now part of our system ;-) ");
		}
		
	} catch (IOException e) {
		e.printStackTrace();
		
	} finally {
		try {
			pr.close();
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}
    public boolean login(User user)
    { 
         boolean found=false;
         String tempUsername="";
        
         try {

             Scanner read=new Scanner(file);//object to read the file
            

             while (read.hasNext())
             {
                 tempUsername=read.nextLine();
                  String [] values=tempUsername.split("-");
                 if(values[0].equals(user.getUsername())&&values[1].equals(user.getPassword()))
                 {
                     found=true;
                 }

           }
             read.close();
         }
         catch(Exception e) {
           
             e.printStackTrace();
         }
        return found;
    }
    
    
   public boolean  viewUserTransactionHistory(User user)
   {
	   
	   return user.printTransactions();
   }
   
   
	public void viewBalance(User user) {
		System.out.println("CreditCard = "+user.getCreditCard());
		System.out.println("Wallet Balance = "+user.getWallet());
		
	}
	
	public void addtoDiscountList(String service, double discount)
	{
		discountList.put(service,discount);
	}
	
	public void removeDiscountList(String service , double discount)
	{
		discountList.put(service,discount);
	}
	public void viewDiscounts()
	{
		for(Entry<String, Double> map: discountList.entrySet()){  
			System.out.println(map.getKey()+ " : "+map.getValue() + "%");  
			}
	}

}

