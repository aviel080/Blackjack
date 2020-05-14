package bj;

import java.util.*;
public class GameManager {
	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		GameManager game1 = new GameManager();
		game1.startTurn(100);
		System.out.println("player: " + game1.player.showHand() + game1.player.handValue());
		System.out.println("dealer: " + game1.dealer.showHand() + game1.dealer.handValue());
		while (true)
		{
			System.out.println("player: " + game1.player.showHand() + game1.player.handValue());
			System.out.println("dealer: " + game1.dealer.showHand() + game1.dealer.handValue());
			int kelet = s.nextInt();
			if (kelet == 1)
			{
				game1.playerHit();
			}
			if (kelet == 2)
			{
				game1.playerHold();
				break;
			}
			if (kelet == 3)
			{
				game1.playerDouble();
				break;
			}
			if (kelet == 4)
			{
				game1.playerSurrender();
				break;
			}
			if (game1.player.handValue() > 21)
				break;
		}
		System.out.println("player: " + game1.player.showHand() + game1.player.handValue());
		System.out.println("dealer: " + game1.dealer.showHand() + game1.dealer.handValue());
		System.out.println(game1.endTurn());
	}
	private Deck gameDeck = new Deck();
	private Dealer dealer = new Dealer();
	private Player player = new Player();
	private int bet;
	public void startTurn(int betAmount)
	{
		dealer.pushCard(gameDeck.pull());
		dealer.pushCard(gameDeck.pull());
		player.pushCard(gameDeck.pull());
		player.pushCard(gameDeck.pull());
		if (player.handValue() == 21)
		{
			System.out.println("BLACKJACK");
		}
		bet = betAmount;
	}
	public int playerHit()
	{
		player.pushCard(gameDeck.pull());
		return player.handValue();
	}
	public void playerHold()
	{
		pushUntil17();
	}
	public void playerDouble()
	{
		player.pushCard(gameDeck.pull());
		bet = bet * 2;
		pushUntil17();
	}
	public void playerSurrender()
	{
		bet /= 2;
	}
	private void pushUntil17()
	{
		while (dealer.handValue() < 17)
		{
			dealer.pushCard(gameDeck.pull());
		}
	}
	public void Split()
	{
		player.secondHandPushCard(player.getCard());
		player.pushCard(gameDeck.pull());
		player.secondHandPushCard(gameDeck.pull());
	}
	public String endTurn()
	{
		int playerHand1 = player.handValue();
		int playerHand2 = player.secondHandValue();
		int dealerHand = dealer.handValue();
		String result = "";
		result += "Hand1 : " + checkHandResult(playerHand1,dealerHand);
		if (playerHand2 != 0)
		{
			result += "Hand2 : " + checkHandResult(playerHand2,dealerHand);
		}
		return result;
	}
	private String checkHandResult(int playerHand,int dealerHand)
	{
		if (playerHand > 21)
			return "LOSE";
		if (dealerHand > 21)
			return "Win";
		if (playerHand > dealerHand)
			return "Win";
		if (playerHand < dealerHand)
			return "LOSE"; 
		return "Tie";
	}
}
