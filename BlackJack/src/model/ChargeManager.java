package model;

public class ChargeManager {
	private static UserRepository userRepository = UserRepository.BuildUserRepository();
	public void Withdraw(User user,String amount)throws Exception {
		invalidInput(amount);
		int validAmount = Integer.parseInt(amount);
		if (validAmount == 0)
			throw new Exception("0 Cant Be Input");
		if (validAmount > user.getBalance())
			throw new Exception("Amount Less Than Money");
		Withdraw(user,validAmount);
	}
	public void Withdraw(User user,int amount) {
		user.addBalance(-amount);
		userRepository.Update(user);
	}
	public void Deposit(User user, String amount)throws Exception {
		invalidInput(amount);
		int validAmount = Integer.parseInt(amount);
		Deposit(user,validAmount);
	}
	public void Deposit(User user, int amount)
	{
		user.addBalance(amount);
		userRepository.Update(user);
	}
	private void invalidInput(String amount) throws Exception
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
