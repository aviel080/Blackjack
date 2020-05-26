package model;

public class GameManager {
	private static Deck gameDeck = new Deck();
	private Dealer dealer = new Dealer();
	private Player player = new Player();
	private int betAmount;
	public GameManager(int betAmount)
	{
		this.betAmount = betAmount;
	}
	public void setBetAmount(int betAmount)
	{
		this.betAmount = betAmount;
	}
	public int getBetAmount()
	{
		return betAmount;
	}
	public void startTurn()
	{
		dealer.pushCard(gameDeck.pull());
		player.pushCard(gameDeck.pull());
		player.pushCard(gameDeck.pull());
	}
	public void playerHit()
	{
		player.pushCard(gameDeck.pull());
	}
	public void playerHold()
	{
		pushUntil17();
	}
	public void playerDouble() throws Exception
	{
		if(player.handSize() != 2)
			throw new Exception ("Player Hand Size Is Not 2");
		player.pushCard(gameDeck.pull());
		pushUntil17();
	}
	private void pushUntil17()
	{
		while (dealer.handValue() < 17)
		{
			dealer.pushCard(gameDeck.pull());
		}
	}
	public void playerSplit() throws Exception
	{
		if(player.handSize() != 2)
			throw new Exception ("Player Hand Size Is Not 2");
		if(player.getCard(0).getNumber() != player.getCard(1).getNumber())
			throw new Exception ("Player Cards Not Same");
		player.secondHandPushCard(player.getCard());
		player.pushCard(gameDeck.pull());
		player.secondHandPushCard(gameDeck.pull());
	}
	public String endTurn()
	{
		String result = "";
		String Hand1 = checkHandResult(player.handValue(),dealer.handValue());
		result += "Hand 1: " + Hand1 + handResult(Hand1);
		if (player.secondHandValue() != 0)
		{
			String Hand2 = checkHandResult(player.secondHandValue(),dealer.handValue());
			result += "Hand 2: " + Hand2 + handResult(Hand2);
		}
		return result;
	}
	public String checkHandResult(int playerHand ,int dealerHand)
	{
		if (playerHand > 21)
			return "LOSE ";
		if (dealerHand > 21)
			return "Win ";
		if (playerHand > dealerHand)
			return "Win ";
		if (playerHand < dealerHand)
			return "LOSE "; 
		return "Tie ";
	}
	public int calcMoney()
	{
		int sum = 0;
		String result = checkHandResult(player.handValue(),dealer.handValue());
		sum += handResult(result);
		if (player.secondHandValue() != 0)
		{
			result = checkHandResult(player.secondHandValue(),dealer.handValue());
			sum += handResult(result);
		}
		return sum;
	}
	private int handResult(String result)
	{
		if (result.equals("Win "))
			return betAmount * 2;
	    if (result.equals("LOSE "))
	    	return 0;
	    return betAmount;
	}
	@Override
	public String toString() {
	return "player: " + player.showHand() + player.handValue() + "\n" + "dealer: " + dealer.showHand() + dealer.handValue();
	}
	public int playerHandValue()
	{
		return player.handValue();
	}
}
