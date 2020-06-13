package model;

import java.io.Serializable;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private int virtualMoney;
	private int realMoney;
	private boolean realPlay;
	
	public User(String userName, String password, int virtualMoney,int realMoney, boolean realPlay) 
	{
		this.userName = userName;
		this.password = password;
		this.virtualMoney = virtualMoney;
		this.realMoney = realMoney;
		this.realPlay = realPlay;
	}
	public void changeMode()
	{
		realPlay = realPlay ^ true;
	}
	public boolean getMode()
	{
		return realPlay;
	}
	public int getRealMoney() {
		return realMoney;
	}
	public int getVirtualMoney() {
		return virtualMoney;
	}
	public void addBalance(int amount) {
		if (realPlay)
			realMoney += amount;

		else
			virtualMoney += amount;
	}
	public int getBalance()
	{
		if (realPlay)
			return realMoney;
		return virtualMoney;
	}
	public String getModeString()
	{
		String result = "";
		if (realPlay)
			result += "Real ";
		else
			result += "Virtual ";
		result += "Money Mode!";
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		User user = (User)obj;
		return this.userName.equals(user.userName);
	}
	@Override
	public int hashCode() {
		return userName.hashCode();
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
