package model;

import java.io.Serializable;

public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int handsPlayed = 0;
	private int handsBlackjack = 0;
	private int handsWin = 0;
	private int handsLose = 0;
	private int handsTie = 0;	
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
	}
	public int gethandsPlayed() {
		return handsPlayed;
	}
	public String getHandsBlackjack() {
		return "Hands BlackJack: "+ handsBlackjack + " (" + calcPrecent(handsBlackjack) + "%)\n";
	}
	public String gethandsWin() {
		return "Hands Win: "+ handsWin + " (" + calcPrecent(handsWin) + "%)\n";
	}
	public String gethandsLose() {
		return "Hands Lose: "+ handsLose + " (" + calcPrecent(handsLose) + "%)\n";
	}
	public String gethandsTie() {
		return "Hands Tie: "+ handsTie + " (" + calcPrecent(handsTie) + "%)\n";
	}
	private int calcPrecent (int num)
	{
		if (handsPlayed == 0)
			return 0;
		else
			return (int)(((double) num /handsPlayed)* 100);
	}
}
