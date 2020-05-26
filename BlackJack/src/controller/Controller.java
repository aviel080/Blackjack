package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import model.*;
import view.*;

public class Controller {
	private static User user;
	private static GameManager game;
	private static int hand = 1;
	public static void main(String[] args) 
	{
		GameView.mainScreen();
	}
	@SuppressWarnings("unused")
	public static void Check()
	{
		 Set<User> users = new HashSet<User>();
		 try {
		 users = FileManager.read();
		 }catch (Exception a)
		 { }
		 
		 for (User a : users)
		 {
			 System.out.println(a + " " + a.getBalance());
			 System.out.println(a.getStatistics());
		 }
	}
	public static void playController()
	{
		String betAmount = GameView.betScreen(user);
		try {
		ChargeManager.Withdraw(user, betAmount);
		System.out.println("Succsefull Bet Amount : " + betAmount);
		} catch(Exception e){	
			System.out.println(e.toString());
			GameView.betScreen(user);
			return;
		}
		int bet = Integer.parseInt(betAmount);
		game = new GameManager(bet);
		hand = 1;
		game.startTurn();
		GameView.playScreen(game, user);
	}
	public static void hitController(int hand)
	{
		if (game.isSplit() == false)
		{
			game.playerHit(hand);
			if (game.playerHandValue() > 21)
			{
				endController();
			}
			else
				GameView.playScreen(game, user);
		}
		else
		{
			game.playerHit(hand);
			if (game.playerHandValue() > 21)
			{
				hand = 2;
			}
			else if(hand == 2)
			{
				if(game.playerSecondHandValue() > 21)
				{
					endController();
					return;
				}
			}
			GameView.splitScreen(game, user,hand);
		}
	}
	public static void holdController(int hand)
	{
		if (game.isSplit() == false)
		{
			game.playerHold();
			endController();
		}
		else
		{
			if (hand == 1)
			{
				hand = 2;
				GameView.splitScreen(game, user,hand);
			}
			else if(hand == 2)
			{
				game.playerHold();
				endController();
			}
		}
	}
	public static void doubleController()
	{
		try {
			ChargeManager.Withdraw(user,String.valueOf(game.getBetAmount()));
			game.playerDouble();
		} catch(Exception e){	
			System.out.println(e.toString());
			GameView.playScreen(game, user);
			return;
		}
		game.setBetAmount(game.getBetAmount() * 2);
		System.out.println("Succsefull New Bet Amount : " + game.getBetAmount());
		endController();
	}
	public static void splitController()
	{
		try{
			ChargeManager.Withdraw(user,String.valueOf(game.getBetAmount()));
			game.playerSplit();
		}catch (Exception e){
			System.out.println(e.toString());
			GameView.playScreen(game, user);
			return;
		}
		System.out.println("Succsefull Split Bet Amount Per Hand: " + game.getBetAmount());
		hand = 1;
		GameView.splitScreen(game, user,hand);	
	}
	public static void surrenderController()
	{
		GameView.endScreen("Surrender", game);
		try {
			ChargeManager.Deposit(user, String.valueOf(game.getBetAmount() / 2));
			} catch(Exception e){	
				System.out.println(e.toString());
			}
		GameView.secondScreen(user);
	}
	private static void endController()
	{
		GameView.endScreen(game.endTurn(), game);
		try {
			ChargeManager.Deposit(user, String.valueOf(game.calcMoney()));
			} catch(Exception e){	
				System.out.println(e.toString());
			}
		user.updateStatistics(game.handStatus(1));
		if(game.isSplit())
			user.updateStatistics(game.handStatus(2));
		FileManager.Update(user);
		GameView.secondScreen(user);
	}
	public static void loginController()
	{
		StringBuilder username = new StringBuilder();
		StringBuilder password = new StringBuilder();
		GameView.loginScreen(username,password);
		LoginManager sum = new LoginManager();
		try {
			user = sum.userLogin(username.toString(),password.toString());
			System.out.println("Login Succsefully");
			GameView.secondScreen(user);
		}catch(Exception e){
			System.out.println(e.toString());
			GameView.mainScreen();
		}
	}
	public static void signupController()
	{
		StringBuilder username = new StringBuilder();
		StringBuilder password = new StringBuilder();
		GameView.signUpScreen(username,password);
		SignUpManager sum = new SignUpManager();
		try {
			sum.signNewUser(username.toString(),password.toString());
			System.out.println("User Added Succsefully");
		}catch(Exception e){	
			System.out.println(e.toString());
		}
		GameView.mainScreen();
	}
	public static void depositController()
	{
		String amount = GameView.depositScreen(user);
		try {
		ChargeManager.Deposit(user, amount);
		System.out.println("Succsefully Added " + amount);
		} catch(Exception e){	
			System.out.println(e.toString());
		}
		GameView.secondScreen(user);
	}
	public static void withdrawController()
	{
		String amount = GameView.withdrawScreen(user);
		try {
		ChargeManager.Withdraw(user, amount);
		System.out.println("Succsefully Taken " + amount);
		} catch(Exception e){	
			System.out.println(e.toString());
		}
		GameView.secondScreen(user);
	}
	public static void statisticsController()
	{
		if(GameView.statisticsScreen(user).equals("clear"))
		{
			user.clearStatistics();
			FileManager.Update(user);
			statisticsController();
			return;
		}
		GameView.secondScreen(user);
	}
	
}
