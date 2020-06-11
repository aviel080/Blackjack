package model;

import java.io.Serializable;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private static UserRepository userRepository = UserRepository.BuildUserRepository();
	private String userName;
	private String password;
	private int balance;
	private int userId;
	
	public User(String userName, String password, int balance,int userId) 
	{
		this.userName = userName;
		this.password = password;
		this.balance = balance;
		this.userId = userRepository.getSize();
	}
	public int getuserId() {
		return userId;
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
	@Override
	public int hashCode() {
		return Integer.hashCode(userId);
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	/*
	@Override
	public String toString() {
		return "Hello " + userName +",\n" + "Money Amount: " + balance;		 
	}
	*/
}
