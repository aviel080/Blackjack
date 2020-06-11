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
	public void depositController(String amount) throws Exception
	{
		chargeManager.Deposit(user, amount);
	}
	public void withdrawController(String amount) throws Exception
	{
		chargeManager.Withdraw(user, amount);
	}
}
