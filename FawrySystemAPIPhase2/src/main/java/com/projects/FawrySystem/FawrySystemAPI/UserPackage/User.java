package com.projects.FawrySystem.FawrySystemAPI.UserPackage;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.projects.FawrySystem.FawrySystemAPI.transactions.*;

@Service
public class User 
{
			
		private String username;
		private String password;
		private String email;
		private ArrayList <ITransaction> transactionList= new ArrayList<ITransaction>();
		private double CreditCard=10000; 
		private double wallet;
	
		public User()
		{
			
		}
		public User(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		public ArrayList<ITransaction> getTransactionList() {
			return transactionList;
		}

		public void setTransactionList(ArrayList<ITransaction> transactionList) {
			this.transactionList = transactionList;
		}

		public double getCreditCard() {
			return CreditCard;
		}

		public void setCreditCard(double creditCard) {
			CreditCard = creditCard;
		}

		public double getWallet() {
			return wallet;
		}

		public void setWallet(double wallet) {
			this.wallet = wallet;
		}

		
		public boolean printTransactions()
		{
			
			
			if(transactionList.size()==0)
			{
				System.out.println("No Transaction Yet");
				return false;
			}
			else
			{
				for(int i = 0;i<transactionList.size();i++)
				{
					System.out.println(transactionList.get(i));
				}
				
			}
			return true;
		}
		public boolean checkForTransaction(User user, String c)
		{
			boolean found=false;
			
			if(transactionList.size()==0)
			{
				found= false;
			}
			else
			{
				for(int i = 0;i<transactionList.size();i++)
				{
					if(getTransactionList().get(i).getID().equals(c))
					{
						found= true;
					}
				}
			}
			return found;
		}

		
		public void addTransaction(ITransaction t)
		{
			transactionList.add(t);
		}
		
		
		public String getUsername() {
			return username;
		}
		
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			String result= "username: " + username + " \n";
			if( transactionList.size()>0)
		             result+=transactionList.toString();
			return result;
		}

}
