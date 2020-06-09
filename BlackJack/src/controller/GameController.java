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
	public boolean splitController()
	{
		try{
			if(user.getBalance() < game.getBetAmount(1))
				throw new Exception("Not Enough Money");
			game.playerSplit();
		}catch (Exception e){
			System.out.println(e.toString());
			return false;
		}
		chargeManager.Withdraw(user,game.getBetAmount(1));
		game.setBetAmount(game.getBetAmount(1) , 2);
		return true;
	}
	public boolean doubleController()
	{
		boolean status;
		int hand = game.getPlayerHand();
		try {
			if(user.getBalance() < game.getBetAmount(hand))
				throw new Exception("Not Enough Money");
			status = game.playerDouble();
		} catch(Exception e){	
			System.out.println(e.toString());
			return true;
		}
		chargeManager.Withdraw(user, game.getBetAmount(hand));
		game.setBetAmount(game.getBetAmount(hand) * 2, hand);
		return status;
	}
	public boolean surrenderController()
	{
		return game.playerSurrender();
	}
}
