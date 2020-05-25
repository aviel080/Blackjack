package model;

public class Player extends Dealer{
	private Hand secondHand = new Hand();
	
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
}
