package bj;

public class Dealer {
	private Hand hand = new Hand();
	public void pushCard(Card card)
	{
		hand.addCard(card);
	}
	public void clearHand()
	{
		hand = new Hand();
	}
	public int handValue()
	{
		return hand.getHandValue();
	}
	public Card getCard()
	{
		return hand.removeCard();
	}
	public String showHand()
	{
		return hand.returnHand();
	}
}
