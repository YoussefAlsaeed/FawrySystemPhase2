package com.projects.FawrySystem.FawrySystemAPI.UserPackage;


public class WalletPaymentMethod implements IPaymentMethod {

	@Override
	public boolean pay(User user,double amount) {
		if(user.getWallet()<amount)
		{
			System.out.println("Not Enough balance in your wallet, please add money to your wallet");
			return false;
		}
		else user.setWallet(user.getWallet()-amount);
		return true;
		

	}
	public String toString()
	{
		return "Payment method = Wallet";
		
	}
}
