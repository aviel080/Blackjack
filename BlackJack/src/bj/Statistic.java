package bj;

public class Statistic {
	private int gamesWin;
	private int gamesLose;
	private int gamesTie;
	private int moneyBalance;
	
	public void updateStatistic(char gameStatus)
	{
		if (gameStatus == 'W')
			gamesWin++;
		if (gameStatus == 'L')
			gamesLose++;
		if (gameStatus == 'T')
			gamesTie++;
	}

	public int getGamesWin() {
		return gamesWin;
	}
	public int getgamesLose() {
		return gamesLose;
	}
	public int getgamesTie() {
		return gamesTie;
	}

	public int getMoneyBalance() {
		return moneyBalance;
	}

	public void updateMoneyBalance(int amount) {
		moneyBalance += amount;
	}

}
