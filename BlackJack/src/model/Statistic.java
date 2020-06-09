package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Statistic implements Serializable {
	private static final long serialVersionUID = 1L;
	private static FileManager fileManager = FileManager.buildFileManager();
	private int handsPlayed = 0;
	private int handsBlackjack = 0;
	private int handsWin = 0;
	private int handsLose = 0;
	private int handsTie = 0;	
	private int handsSurrender = 0;
	private int userId;
	
	public Statistic(int userId)
	{
		this.userId = userId;
	}
	@Override
	public boolean equals(Object obj) {
		return this.userId == ((Statistic)obj).userId;
	}
	public static Statistic getUserStatistic(int userId)
	{
		try {
		@SuppressWarnings("unchecked")
		List<Statistic> statistic = (ArrayList<Statistic>)fileManager.read("statistics.dat");
		return statistic.get(userId);
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}

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
		fileManager.Update(this, "statistics.dat");
	}
	public int gethandsPlayed() {
		return handsPlayed;
	}
	public String getHandsBlackjack() {
		return "Hands BlackJack: "+ handsBlackjack + " (" + calcPrecent(handsBlackjack) + "%)\n";
	}
	public String getHandsWin() {
		return "Hands Win: "+ handsWin + " (" + calcPrecent(handsWin) + "%)\n";
	}
	public String getHandsLose() {
		return "Hands Lose: "+ handsLose + " (" + calcPrecent(handsLose) + "%)\n";
	}
	public String getHandsTie() {
		return "Hands Tie: "+ handsTie + " (" + calcPrecent(handsTie) + "%)\n";
	}
	public String getHandsSurrender() {
		return "Hands Surrender: "+ handsSurrender + " (" + calcPrecent(handsSurrender) + "%)\n";
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
		String result = "";
		result += "Hands Played: " + gethandsPlayed() + "\n";
		result += getHandsBlackjack();
		result += getHandsWin();
		result += getHandsLose();
		result += getHandsTie();
		result += getHandsSurrender();
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
		fileManager.Update(this, "statistics.dat");
	}
}
