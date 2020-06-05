package view;

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
		System.out.println("Q - Quit");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			Controller.loginController();
			break;
		case "2":
			Controller.signupController();
			break;
		case "q":
		case "Q":
			System.out.println("Bye!");
			System.exit(0);
	    default:
			System.out.println("Invalid Input Please Try Again!");
	    	mainScreen(); 
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
	public static void inGameMenuScreen(User user)
	{
		System.out.println(user);
		System.out.println("1 - Play");
		System.out.println("2 - Deposit Money");
		System.out.println("3 - Withdraw Money");
		System.out.println("4 - Statistics");
		System.out.println("5 - LogOut");
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
		case "5":
			mainScreen();
			break;
	    default:
			System.out.println("Invalid Input Please Try Again!");
			inGameMenuScreen(user);
		}
	}
	public static String betScreen(User user)
	{
		System.out.println(user);
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
	    	System.out.println("Invalid Input Please Try Again!");
	    	playScreen(game, user);
		}
	}
	public static void splitScreen(GameManager game, User user,int hand)
	{
		System.out.println(game);
		System.out.println("1 - Hit Hand " + hand);
		System.out.println("2 - Hold Hand "+ hand);
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
	    	System.out.println("Invalid Input Please Try Again!");
	    	splitScreen(game, user, hand);
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
		System.out.println(user);
		System.out.println("Enter Amount To Deposit: ");
		return s.nextLine();
	}
	public static String withdrawScreen(User user)
	{
		System.out.println("~Withdraw~");
		System.out.println(user);
		System.out.println("Enter Amount To Withdraw: ");
		return s.nextLine();
	}
	public static String statisticsScreen(User user)
	{
		System.out.println("~Statistics~");
		System.out.println(user);
		System.out.print(user.getStatistics());
		System.out.println("To Clear Statistics - clear");
		System.out.println("Back - Any");
		return s.nextLine();//fs
	}
}
