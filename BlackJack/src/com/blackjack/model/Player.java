package com.blackjack.model;

public class Player extends Dealer{
	private Hand secondHand = new Hand();
	private int currentPlayedHand = 1;
	
	public int getCurrentPlayedHand() {
		return currentPlayedHand;
	}
	public void setCurrentPlayedHand(int currentPlayedHand) {
		this.currentPlayedHand = currentPlayedHand;
	}
	public void secondHandPushCard(Card card)
	{
		secondHand.addCard(card);
	}
	public void secondClearHand()
	{
		secondHand = new Hand();
	}
	public int secondHandValue()
	{
		return secondHand.getHandValue();
	}
	public int secondHandSize()
	{
		return secondHand.handSize();
	}
	public String showSecondHand()
	{
		return secondHand.returnHand();
	}
	
}
