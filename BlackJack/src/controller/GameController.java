package controller;

import model.*;

public class GameController {
	private static GameController controller = null;
	private ChargeManager chargeManager = new ChargeManager();
	private User user;
	private GameManager game;
	public static GameController BuildController(User user)
	{	
		if (controller == null)
			controller = new GameController();
		controller.user = user;
		return controller;
	}
	public GameManager playController(String betAmount) throws Exception
	{
		chargeManager.Withdraw(user, betAmount);		
		int bet = Integer.parseInt(betAmount);
		game = new GameManager(bet);
		game.startTurn();
		return game;
	}
	public boolean hitController()
	{
		return game.playerHit();
	}
	public void endController()
	{
		chargeManager.Deposit(user, game.calcMoney());
		Statistic.getUserStatistic(user.getuserId()).updateStatistic(game.checkHandResult(1));
		if(game.isSplit())
			Statistic.getUserStatistic(user.getuserId()).updateStatistic(game.checkHandResult(2));
	}
	public boolean holdController()
	{
		return game.playerHold(); 
	}
	public void splitController() throws Exception
	{
		if(user.getBalance() < game.getBetAmount(1))
			throw new Exception("Not Enough Money");
		game.playerSplit();
		chargeManager.Withdraw(user,game.getBetAmount(1));
		game.setBetAmount(game.getBetAmount(1) , 2);
	}
	public void doubleController() throws Exception
	{
		int hand = game.getPlayerHand();
		if(user.getBalance() < game.getBetAmount(hand))
			throw new Exception("Not Enough Money");
		game.playerDouble();
		chargeManager.Withdraw(user, game.getBetAmount(hand));
		game.setBetAmount(game.getBetAmount(hand) * 2, hand);
	}
	public boolean surrenderController()
	{
		return game.playerSurrender();
	}
}
