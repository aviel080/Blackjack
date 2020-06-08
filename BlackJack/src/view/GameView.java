package view;

import java.util.Scanner;
import controller.*;
import model.*;

public class GameView {
	private static Scanner s = new Scanner(System.in);
	private Controller controller = Controller.BuildController();
	private User user;
	private GameManager game;
	public static void main(String[] args) 
	{
		GameView gameView = new GameView();
		gameView.mainScreen();
	}
	public void mainScreen()
	{
		System.out.println("Welcome To BlackJack!");
		System.out.println("1 - Login");
		System.out.println("2 - Signup");
		System.out.println("Q - Quit");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			loginScreen();
			break;
		case "2":
			signUpScreen();
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
	public void loginScreen()
	{
		System.out.println("~Login~");
		System.out.println("Enter Username: ");
		String username = s.nextLine();
		System.out.println("Enter Password: ");
		String password = s.nextLine();
		try {
			user = controller.loginController(username,password);
			System.out.println("Login Succesfully");
			inGameMenuScreen();
		}catch(Exception e) {
			System.out.println(e.toString());
			mainScreen();
		}				
	}
	public void signUpScreen()
	{
		System.out.println("~SignUp~");
		System.out.println("Enter Username: ");
		String username = s.nextLine();
		System.out.println("Enter Password: ");
		String password = s.nextLine();
		try {
			controller.signupController(username,password);
			System.out.println("User Added Succsefully");
		}catch(Exception e) {
			System.out.println(e.toString());
		}	
		mainScreen();
	}
	public void inGameMenuScreen()
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
			betScreen();
			break;
		case "2":
			depositScreen();
			break;
		case "3":
			withdrawScreen();
			break;
		case "4":
			statisticsScreen();
			break;
		case "5":
			mainScreen();
			break;
	    default:
			System.out.println("Invalid Input Please Try Again!");
			inGameMenuScreen();
		}
	}
	public void betScreen()
	{
		System.out.println(user);
		System.out.println("Enter Bet Amount: ");
		String betAmount = s.nextLine();
		try {
			game = controller.playController(betAmount);
			System.out.println("Succsefull Bet Amount : " + betAmount);
			playScreen();
		}catch(Exception e) {
			System.out.println(e.toString());
			inGameMenuScreen();
		}
	}
	public void playScreen()
	{	
		System.out.println(user);
		System.out.println(game);
		System.out.println("Hand : " + game.getPlayerHand());
		System.out.println("1 - Hit");
		System.out.println("2 - Hold");
		System.out.println("3 - Double");
		System.out.println("4 - Split");
		System.out.println("5 - Surrender");
		String select = s.nextLine();
		switch (select)
		{
		case "1":
			if(controller.hitController())
				playScreen();
			else
				endScreen();
			break;
		case "2":
			if(controller.holdController())
				playScreen();
			else
				endScreen();
			break;
		case "3":
			if (controller.doubleController())
				playScreen();
			else
				endScreen();
			break;
		case "4":
			if(controller.splitController())
				System.out.println("Split Succesfully");
			playScreen();
			break;
		case "5":
			if(controller.surrenderController())
				playScreen();
			else
				endScreen();
			break;
	    default:
	    	System.out.println("Invalid Input Please Try Again!");
	    	playScreen();
		}
	}
	public void endScreen()
	{
		System.out.println(game);
		System.out.println(game.endTurn());
		controller.endController();
		inGameMenuScreen();
	}
	public void depositScreen()
	{
		System.out.println("~Deposit~");
		System.out.println(user);
		System.out.println("Enter Amount To Deposit: ");
		String amount = s.nextLine();
		if (controller.depositController(amount))
			System.out.println("Succsefully Added " + amount);
		inGameMenuScreen();
	}
	public void withdrawScreen()
	{
		System.out.println("~Withdraw~");
		System.out.println(user);
		System.out.println("Enter Amount To Withdraw: ");
		String amount = s.nextLine();
		if (controller.withdrawController(amount))
			System.out.println("Succsefully Taken " + amount);
		inGameMenuScreen();
	}
	public void statisticsScreen()
	{
		System.out.println("~Statistics~");
		System.out.println(user);
		System.out.print(user.getStatistics());
		System.out.println("To Clear Statistics - clear");
		System.out.println("Back - Any");
		if(controller.statisticsController(s.nextLine()))
			statisticsScreen();
		inGameMenuScreen();
	}
}
