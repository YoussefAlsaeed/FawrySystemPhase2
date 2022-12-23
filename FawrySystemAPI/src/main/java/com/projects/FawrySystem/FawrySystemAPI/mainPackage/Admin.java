package com.projects.FawrySystem.FawrySystemAPI.mainPackage;

import java.util.ArrayList;
import java.util.HashMap;

import com.projects.FawrySystem.FawrySystemAPI.transaction.ITransaction;

public class Admin {
    private HashMap<String,User> refundRequests=new HashMap<String,User>();
    private ArrayList <ITransaction>transactions=new ArrayList<ITransaction>();
    private ArrayList <User>users=new ArrayList<User>();
    
    
    public void setTransactionList(ArrayList<ITransaction> transactionList) {
        this.transactions = transactionList;
    }
    public ArrayList<ITransaction> getTransactionList() {
        return transactions;
    }
    
    public void setUserList(ArrayList <User>users) {
        this.users = users;
    }
    public  ArrayList <User> getUserList() {
        return users;
    }
    
    
    
//    HashMap<String,User> refundRequests
    public void setRefundRequests(HashMap<String,User>refundRequests) {
        this.refundRequests=refundRequests;
    }

    public HashMap<String,User> getRefundRequests() {
        
        return refundRequests;
    }
    public void addTransaction(ITransaction t)
    {
        transactions.add(t);
    }
    public void addUser(User user)
    {
        users.add(user);
    }
    public void addToRefundRequests(String transactionID,User user)
    {    
        refundRequests.put(transactionID,user);
    }
    public boolean printTransactions()
    {
        if(transactions.size()==0)
        {
            System.out.println("No Transaction Yet");
            return false;
            
        }
        else
        {
            for(int i = 0;i<transactions.size();i++)
            {
//            
                 System.out.println(transactions.get(i));
            }
            
        }
        return true;
    }

}