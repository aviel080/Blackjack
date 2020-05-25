package controller;

import java.util.HashSet;
import java.util.Set;

import model.*;
import view.*;

public class Controller {
	private static User user;
	private static GameManager game;
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
		game.startTurn();
		GameView.playScreen(game, user);
	}
	public static void hitController()
	{
		game.playerHit();
		if (game.playerHandValue() > 21)
		{
			endController();
		}
		else
			GameView.playScreen(game, user);
	}
	public static void holdController()
	{
		game.playerHold();
		endController();
	}
	public static void doubleController()
	{
		game.playerDouble();
		endController();
	}
	public static void splitController()
	{
		try{
			game.playerSplit();
		}catch (Exception e){
			System.out.println(e.toString());
			GameView.playScreen(game, user);
			return;
		}
		
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
	public void statisticsController()
	{
		
	}
	
}
