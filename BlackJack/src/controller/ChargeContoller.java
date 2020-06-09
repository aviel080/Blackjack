package controller;

import model.ChargeManager;
import model.User;

public class ChargeContoller {
	private static ChargeContoller chargeContoller = null;
	private ChargeManager chargeManager = new ChargeManager();
	private User user;
	private ChargeContoller() {
	}
	public static ChargeContoller BuildChargeContoller(User user)
	{	
		if (chargeContoller == null)
			chargeContoller = new ChargeContoller();
		chargeContoller.user = user;
		return chargeContoller;
	}
	public boolean depositController(String amount)
	{
		try {
		chargeManager.Deposit(user, amount);
		}
		catch(Exception e){	
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	public boolean withdrawController(String amount)
	{
		try {
		chargeManager.Withdraw(user, amount);
		}
		catch(Exception e){	
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
}
