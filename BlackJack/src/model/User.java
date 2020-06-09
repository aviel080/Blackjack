package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.ToString;

@Builder
public class User implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private static FileManager fileManager = FileManager.buildFileManager();
	private String userName;
	private String password;
	private int balance;
	private int userId;
	
	public User(String userName, String password, int balance,int userId) 
	{
		this.userName = userName;
		this.password = password;
		this.balance = balance;
		this.userId = getLastId();
	}
	private int getLastId()
	{
		try {
		@SuppressWarnings("unchecked")
		List<User> users = (ArrayList<User>)fileManager.read("allUsers.dat");
		if (users == null)
			return 0;
		return users.size();
		}catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
			return -1;
		}
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
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	@Override
	public String toString() {
		return "Hello " + userName +",\n" + "Money Amount: " + balance;		 
	}
}
