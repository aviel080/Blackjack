package model;

public class ChargeManager {
	public static void Withdraw(User user,String amount)throws Exception {
		for (char a : amount.toCharArray())
		{
			if (Character.isDigit(a) == false)
				throw new Exception("Invalid Amount");
		}
		int validAmount = Integer.parseInt(amount);
		if (validAmount > user.getBalance())
			throw new Exception("Amount Less Than Money");
		user.addBalance(-validAmount);
		FileManager.Update(user);
	}
	public static void Deposit(User user, String amount)throws Exception {
		for (char a : amount.toCharArray())
		{
			if (Character.isDigit(a) == false)
				throw new Exception("Invalid Amount");
		}
		int validAmount = Integer.parseInt(amount);
		user.addBalance(validAmount);
		FileManager.Update(user);
	}
}
