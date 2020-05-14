package bj;

public class Player extends Dealer{
	private Hand secondHand = new Hand();
	private int moneyAmount;
	public int getMoneyAmount() {
		return moneyAmount;
	}
	public void setMoneyAmount(int moneyAmount) {
		this.moneyAmount = moneyAmount;
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
}
