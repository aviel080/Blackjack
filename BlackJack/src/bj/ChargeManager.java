package bj;

public class ChargeManager {
	public void Withdraw(User user,int amount)throws Exception {
		if(user.getBalance() < amount)
			throw new Exception("Balance too low");
		user.addBalance(-amount);
	}
	public void Deposit(User user, int amount) {
		user.addBalance(amount);
	}
}
