package com.blackjack.model;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int handsPlayed = 0;
	private int handsBlackjack = 0;
	private int handsWin = 0;
	private int handsLose = 0;
	private int handsTie = 0;	
	private int handsSurrender = 0;
	private int depositHistory =0;
	private String userName;
	
	public Statistic(String userName)
	{
		this.userName = userName;
	}
	public void updateDepositHistory(int amount)
	{
		depositHistory += amount;
	}
	@Override
	public boolean equals(Object obj) {
		return this.userName.equals(((Statistic)obj).userName);
	}
	@Override
	public int hashCode() {
		return userName.hashCode();
	}
	public void updateStatistic(String handstatus)
	{
		handsPlayed++;
		if (handstatus.equals("BLACK JACK "))
			handsBlackjack++;
		if (handstatus.equals("Win "))
			handsWin++;
		if (handstatus.equals("LOSE "))
			handsLose++;
		if (handstatus.equals("Tie "))
			handsTie++;
		if (handstatus.equals("Surrender "))
			handsSurrender++;
	}
	private int calcPrecent (int num)
	{
		if (handsPlayed == 0)
			return 0;
		else
			return (int)(((double) num /handsPlayed)* 100);
	}
	@Override
	public String toString() {
		String [] message = {"BlackJack","Win","Lose","Tie","Surrender"};
		int [] numbers = {handsBlackjack,handsWin,handsLose,handsTie,handsSurrender}; 
		String result = "";
		result += userName + "\n";
		result += "Hands Played: " + handsPlayed + "\n";
		for (int i=0;i<5;i++) {
			result += "Hands " + message[i] + ": " + numbers[i] +  " (" + calcPrecent(numbers[i]) + "%)\n";
		}
		result += "Deposit History: " + depositHistory +"$\n";
		return result;
	}
	public void clearStatistics()
	{
		handsPlayed = 0;
		handsBlackjack = 0;
		handsWin = 0;
		handsLose = 0;
		handsTie = 0;	
	    handsSurrender = 0;
	}
}
