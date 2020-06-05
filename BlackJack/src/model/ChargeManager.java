package model;

public class ChargeManager {
	public static void Withdraw(User user,String amount)throws Exception {
		invalidInput(amount);
		int validAmount = Integer.parseInt(amount);
		if (validAmount == 0)
			throw new Exception("0 Cant Be Input");
		if (validAmount > user.getBalance())
			throw new Exception("Amount Less Than Money");
		user.addBalance(-validAmount);
		FileManager.Update(user);
	}
	public static void Deposit(User user, String amount)throws Exception {
		invalidInput(amount);
		int validAmount = Integer.parseInt(amount);
		user.addBalance(validAmount);
		FileManager.Update(user);
	}
	private static void invalidInput(String amount) throws Exception
	{
		if (amount.equals(""))
			throw new Exception ("No Input");
		for (char a : amount.toCharArray())
		{
			if (Character.isDigit(a) == false)
				throw new Exception ("Invalid Amount");
		}
	}
}
