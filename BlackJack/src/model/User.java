package model;

import java.io.Serializable;

public class User implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private Statistic statistics;
	private int balance=0;
	
	public User(String userName,String password)
	{
		this.userName = userName;
		this.password = password;
		this.statistics = new Statistic();
	}
	public int getBalance() {
		return balance;
	}
	public void addBalance(int amount) {
		balance += amount;
	}
	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		return this.userName.equals(user.userName);
	}
}
