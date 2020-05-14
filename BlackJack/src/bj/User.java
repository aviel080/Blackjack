package bj;

public class User {
	private String userName;
	private String password;
	private Statistic statistics = new Statistic();
	private int balance=0;
	public User(String userName,String password)
	{
		this.userName = userName;
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void addBalance(int amount) {
		balance += amount;
	}
	
}
