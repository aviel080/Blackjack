package view;

import java.io.IOException;
import java.util.Scanner;
import controller.*;
import model.*;

public class GameView {
	private static Scanner s = new Scanner(System.in);
	public static void mainScreen()
	{
		System.out.println("Welcome To BlackJack!");
		System.out.println("1 - Login");
		System.out.println("2 - Signup");
		System.out.println("Other - Exit");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			Controller.loginController();
			break;
		case "2":
			Controller.signupController();
			break;
	    default:
	    	s.close();
	    	System.exit(0); 
		}
	}
	public static void loginScreen(StringBuilder username,StringBuilder password)
	{
		System.out.println("~Login~");
		System.out.println("Enter Username: ");
		username.append(s.nextLine());
		System.out.println("Enter Password: ");
		password.append(s.nextLine());
	}
	public static void signUpScreen(StringBuilder username,StringBuilder password)
	{
		System.out.println("~SignUp~");
		System.out.println("Enter Username: ");
		username.append(s.nextLine());
		System.out.println("Enter Password: ");
		password.append(s.nextLine());
	}
	public static void secondScreen(User user)
	{
		System.out.println("Hello " + user +",");
		System.out.println("Money Amount: " + user.getBalance());
		System.out.println("1 - Play");
		System.out.println("2 - Deposit Money");
		System.out.println("3 - Withdraw Money");
		System.out.println("4 - Statistics");
		System.out.println("Other - Exit");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			Controller.playController();
			break;
		case "2":
			Controller.depositController();
			break;
		case "3":
			Controller.withdrawController();
			break;
		case "4":
			Controller.statisticsController();
			break;
	    default:
	    	s.close();
	    	System.exit(0); 
		}
	}
	public static String betScreen(User user)
	{
		System.out.println("Hello " + user +",");
		System.out.println("Money Amount: " + user.getBalance());
		System.out.println("Enter Bet Amount: ");
		return s.nextLine();
	}
	public static void playScreen(GameManager game, User user)
	{	
		System.out.println(game);
		System.out.println("1 - Hit");
		System.out.println("2 - Hold");
		System.out.println("3 - Double");
		System.out.println("4 - Split");
		System.out.println("5 - Surrender");
		System.out.println("Other - Exit");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			Controller.hitController(1);
			break;
		case "2":
			Controller.holdController(1);
			break;
		case "3":
			Controller.doubleController();
			break;
		case "4":
			Controller.splitController();
			break;
		case "5":
			Controller.surrenderController();
			break;
	    default:
	    	s.close();
	    	System.exit(0); 
		}
	}
	public static void splitScreen(GameManager game, User user,int hand)
	{
		System.out.println(game);
		System.out.println("1 - Hit Hand " + hand);
		System.out.println("2 - Hold Hand "+ hand);
		System.out.println("Other - Exit");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			Controller.hitController(hand);
			break;
		case "2":
			Controller.holdController(hand);
			break;
	    default:
	    	s.close();
	    	System.exit(0); 
		}
	}
	public static void endScreen(String result, GameManager game)
	{
		System.out.println(game);
		System.out.println(result);
	}
	public static String depositScreen(User user)
	{
		System.out.println("~Deposit~");
		System.out.println("Hello " + user +",");
		System.out.println("Money Amount: " + user.getBalance());
		System.out.println("Enter Amount To Deposit: ");
		return s.nextLine();
	}
	public static String withdrawScreen(User user)
	{
		System.out.println("~Withdraw~");
		System.out.println("Hello " + user +",");
		System.out.println("Money Amount: " + user.getBalance());
		System.out.println("Enter Amount To Withdraw: ");
		return s.nextLine();
	}
	public static String statisticsScreen(User user)
	{
		System.out.println("~Statistics~");
		System.out.println("Hello " + user +",");
		System.out.println("Money Amount: " + user.getBalance());
		System.out.print(user.getStatistics());
		System.out.println("To Clear Statistics - clear");
		System.out.println("Back - Any");
		return s.nextLine();
	}
}
