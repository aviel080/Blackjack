package model;

import java.io.Serializable;

public class User implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private Statistic statistics;
	private int balance;
	
	private User()	{		
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
		return userName.hashCode();
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
	public String getStatistics()
	{
		String result = "";
		result += "Hands Played: " + statistics.gethandsPlayed() + "\n";
		result += statistics.getHandsBlackjack();
		result += statistics.getHandsWin();
		result += statistics.getHandsLose();
		result += statistics.getHandsTie();
		result += statistics.getHandsSurrender();
		return result;
	}
	public void updateStatistics(String handStatus)
	{
		statistics.updateStatistic(handStatus);
	}
	public void clearStatistics()
	{
		statistics = new Statistic();
	}
	public static class UserBuilder {
		private String userName;
		private String password;
		private Statistic statistics = new Statistic();
		private int balance = 0;
				
		public UserBuilder withUserName(String userName) {
			this.userName = userName;
			return this;
		}
		public UserBuilder withPassword(String password) {
			this.password = password;
			return this;
		}
		public UserBuilder withStatistics(Statistic statistics) {
			this.statistics = statistics;
			return this;
		}
		public UserBuilder withBalance(int balance) {
			this.balance = balance;
			return this;
		}
		public User build()
		{
			User user = new User();
			user.userName = this.userName;
			user.password = this.password;
			user.statistics = this.statistics;
			user.balance = this.balance;
			return user;
		}
	}
}
