package com.blackjack.controller;

import com.blackjack.model.ChargeManager;
import com.blackjack.model.GameManager;
import com.blackjack.model.Statistic;
import com.blackjack.model.StatisticsRepository;
import com.blackjack.model.User;

public class GameController {
	private static GameController gameController = null;
	private ChargeManager chargeManager;
	private User user;
	private GameManager game;
	private Statistic userStatistics;
	private StatisticsRepository statisticsRepository;
	private GameController() {
		statisticsRepository = StatisticsRepository.BuildStatisticsRepository();
		chargeManager = new ChargeManager();
	}
	public static GameController BuildController(User user)
	{	
		if (gameController == null)
			gameController = new GameController();
		gameController.user = user;
		gameController.userStatistics = gameController.statisticsRepository.getUserStatistic(user);
		return gameController;
	}
	public GameManager playController(String betAmount) throws Exception
	{
		chargeManager.Withdraw(user, betAmount);		
		int bet = Integer.parseInt(betAmount);
		game = new GameManager(bet, user.getMode());
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
		userStatistics.updateStatistic(game.checkHandResult(1));
		if(game.isSplit())
			userStatistics.updateStatistic(game.checkHandResult(2));
		statisticsRepository.Update(userStatistics);
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
