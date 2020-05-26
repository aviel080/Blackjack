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
	public void playerHit(int hand)
	{
		if(hand == 1)
			player.pushCard(gameDeck.pull());
		else
			player.secondHandPushCard(gameDeck.pull());
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
		player.secondHandPushCard(player.removeCard());
		player.pushCard(gameDeck.pull());
		player.secondHandPushCard(gameDeck.pull());
	}
	public String endTurn()
	{
		String result = "";
		String Hand1 = handStatus(1);
		result += "Hand 1: " + Hand1 + ",Gain: " + handResult(Hand1) + "\n";
		if (isSplit())
		{
			String Hand2 = handStatus(2);
			result += "Hand 2: " + Hand2 + ",Gain: " + handResult(Hand2);
		}
		return result;
	}
	public String handStatus(int hand)
	{
		if (hand == 1)
			return checkHandResult(1);
		else
			return checkHandResult(2);
	}
	public String checkHandResult(int hand)
	{
		int playerHand = hand == 1 ? player.handValue() : player.secondHandValue();
		int dealerHand = dealer.handValue();
		if (playerHand == 21 && player.handSize() == 2)
		{
			if (dealerHand == 21 && dealer.handSize() == 2)
				return "Tie ";
			else
				return "BLACK JACK ";
		}
		if (dealerHand == 21 && dealer.handSize() == 2)
			return "LOSE ";
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
		String result = checkHandResult(1);
		sum += handResult(result);
		if (isSplit())
		{
			result = checkHandResult(2);
			sum += handResult(result);
		}
		return sum;
	}
	private int handResult(String result)
	{
		if (result.equals("BLACK JACK "))
			return (int)(betAmount * 2.5);
		if (result.equals("Win "))
			return betAmount * 2;
	    if (result.equals("LOSE "))
	    	return 0;
	    return betAmount;
	}
	@Override
	public String toString() {
	String result = "player Hand1: " + player.showHand() + player.handValue() + "\n";
	if (isSplit())
		result += "player Hand2: " + player.showSecondHand() + player.secondHandValue() + "\n";
	result += "dealer: " + dealer.showHand() + dealer.handValue();
	return result;
	}
	public int playerHandValue()
	{
		return player.handValue();
	}
	public int playerSecondHandValue()
	{
		return player.secondHandValue();
	}
	public boolean isSplit()
	{
		return player.secondHandValue() != 0;
	}	
}
