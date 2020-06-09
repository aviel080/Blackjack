package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.*;
import model.*;

public class GameView {
	private static Scanner s = new Scanner(System.in);
	private GameController gameController;
	private FileManager fileManager = FileManager.buildFileManager();
	private User user;
	private GameManager game;
	public static void main(String[] args) 
	{
		GameView gameView = new GameView();
		//gameView.Check();
		gameView.mainScreen();
	}
	@SuppressWarnings("unchecked")
	public void Check()
	{  
		 List<User> users = new ArrayList<User>();
		 List<Statistic> statistics = new ArrayList<Statistic>();
		 try {
		 users = (ArrayList<User>)fileManager.read("allUsers.dat");
		 statistics = (ArrayList<Statistic>)fileManager.read("statistics.dat");
		 }catch (Exception a)
		 { }
		 for (int i=0;i < users.size();i++)
		 {
			 System.out.println(users.get(i));
			 System.out.println(statistics.get(i));
		 }
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
			user = RegisterContoller.BuildRegisterContoller().loginController(username, password);
			gameController = GameController.BuildController(user);
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
			RegisterContoller.BuildRegisterContoller().signupController(username, password);
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
			game = gameController.playController(betAmount);
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
			if(gameController.hitController())
				playScreen();
			else
				endScreen();
			break;
		case "2":
			if(gameController.holdController())
				playScreen();
			else
				endScreen();
			break;
		case "3":
			if (gameController.doubleController())
				playScreen();
			else
				endScreen();
			break;
		case "4":
			if(gameController.splitController())
				System.out.println("Split Succesfully");
			playScreen();
			break;
		case "5":
			if(gameController.surrenderController())
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
		gameController.endController();
		inGameMenuScreen();
	}
	public void depositScreen()
	{
		System.out.println("~Deposit~");
		System.out.println(user);
		System.out.println("Enter Amount To Deposit: ");
		String amount = s.nextLine();
		if (ChargeContoller.BuildChargeContoller(user).depositController(amount))
			System.out.println("Succsefully Added " + amount);
		inGameMenuScreen();
	}
	public void withdrawScreen()
	{
		System.out.println("~Withdraw~");
		System.out.println(user);
		System.out.println("Enter Amount To Withdraw: ");
		String amount = s.nextLine();
		if (ChargeContoller.BuildChargeContoller(user).withdrawController(amount))
			System.out.println("Succsefully Taken " + amount);
		inGameMenuScreen();
	}
	public void statisticsScreen()
	{
		System.out.println("~Statistics~");
		System.out.println(user);
		System.out.print(Statistic.getUserStatistic(user.getuserId()));
		System.out.println("To Clear Statistics - clear");
		System.out.println("Back - Any");
		if(StatisticController.BuildStatisticController(user).statisticsController(s.nextLine()))
			statisticsScreen();
		inGameMenuScreen();
	}
}
