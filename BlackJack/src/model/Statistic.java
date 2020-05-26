package model;

import java.io.Serializable;

public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int handsPlayed=0;
	private int handsBlackjack = 0;
	private int handsWin =0;
	private int handsLose=0;
	private int handsTie=0;	
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
	public int gethandsWin() {
		return handsWin;
	}
	public int gethandsLose() {
		return handsLose;
	}
	public int gethandsTie() {
		return handsTie;
	}
	public int gethandsPlayed() {
		return handsPlayed;
	}
	public int getHandsBlackjack() {
		return handsBlackjack;
	}
}
