package view;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import controller.*;
import model.*;

public class GameView {
	private static Scanner s = new Scanner(System.in);
	private GameController gameController;
	private User user;
	private GameManager game;
	public static void main(String[] args) throws Exception
	{
		GameView gameView = new GameView();
		/*
		for (int i=0;i<5;i++)
		{
			Random rnd = new Random();
			int num = rnd.nextInt(100000000) + 1000;
			RegisterContoller.BuildRegisterContoller().signupController(String.valueOf(num), "password");
		}
		*/
		gameView.Check();
		gameView.mainScreen();
	}
	public void Check()
	{  
		 UserRepository userRepository = UserRepository.BuildUserRepository();
		 StatisticsRepository statisticsRepository = StatisticsRepository.BuildStatisticsRepository();
		 Set<User> users = null;
		 try {
		 users = userRepository.getUsers();
		 }catch (Exception a) { 
			 
		 }
		 System.out.println(statisticsRepository.getStatistics());
		 for (User a : users)
		 {
			 System.out.println(a);
			 System.out.println(statisticsRepository.getUserStatistic(a));
		 }
	}
	public void mainScreen()
	{
		System.out.println("Welcome To BlackJack!");
		System.out.println("1 - Login");
		System.out.println("2 - Signup");
		System.out.println("Q - Quit");
		String select= s.nextLine();
		switch (select){
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
		try {
		System.out.println("~Login~");
		System.out.println("Enter Username: ");
		String username = s.nextLine();
		System.out.println("Enter Password: ");
		String password = s.nextLine();
		user = RegisterContoller.BuildRegisterContoller().loginController(username, password);
		gameController = GameController.BuildController(user);
		System.out.println("Login Succesfully");
		inGameMenuScreen();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			mainScreen();
		}				
	}
	public void signUpScreen()
	{
		try {
		System.out.println("~SignUp~");
		System.out.println("Enter Username: ");
		String username = s.nextLine();
		System.out.println("Enter Password: ");
		String password = s.nextLine();
		RegisterContoller.BuildRegisterContoller().signupController(username, password);
		System.out.println("User Added Succsefully");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		mainScreen();
	}
	public void inGameMenuScreen()
	{
		System.out.println(user);
		System.out.println(user.getModeString());
		System.out.println("1 - Play");
		System.out.println("2 - Deposit Money");
		System.out.println("3 - Withdraw Money");
		System.out.println("4 - Statistics");
		System.out.println("5 - To Change Mode");
		System.out.println("Q - LogOut");
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
			user.changeMode();
			inGameMenuScreen();
			break;
		case "Q":
		case "q":
			mainScreen();
			break;
	    default:
			System.out.println("Invalid Input Please Try Again!");
			inGameMenuScreen();
		}
	}
	public void betScreen()
	{
		try {
		System.out.println(user);
		System.out.println("Enter Bet Amount: ");
		String betAmount = s.nextLine();
		game = gameController.playController(betAmount);
		System.out.println("Succsefull Bet Amount : " + betAmount);
		playScreen();
		}catch(Exception e) {
			System.out.println(e.getMessage());
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
			try {
				gameController.doubleController();
				endScreen();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				playScreen();
			}
			break;
		case "4":
			try {
				gameController.splitController();
				System.out.println("Split Succesfully");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
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
		System.out.println(user.getModeString());
		System.out.println("~Deposit~");
		System.out.println(user);
		System.out.println("Enter Amount To Deposit: ");
		String amount = s.nextLine();
		try {
			ChargeContoller.BuildChargeContoller(user).depositController(amount);
			System.out.println("Succsefully Added " + amount);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		inGameMenuScreen();
	}
	public void withdrawScreen()
	{
		System.out.println(user.getModeString());
		System.out.println("~Withdraw~");
		System.out.println(user);
		System.out.println("Enter Amount To Withdraw: ");
		String amount = s.nextLine();
		try {
			ChargeContoller.BuildChargeContoller(user).withdrawController(amount);
			System.out.println("Succsefully Taken " + amount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		inGameMenuScreen();
	}
	public void statisticsScreen()
	{
		System.out.println("~Statistics~");
		System.out.println(user);
		StatisticsRepository statisticsRepository = StatisticsRepository.BuildStatisticsRepository();
		System.out.println(statisticsRepository.getUserStatistic(user));
		System.out.println(statisticsRepository.getUserStatistic(user).Check());
		System.out.println("To Clear Statistics - clear");
		System.out.println("Back - Any");
		if(StatisticController.BuildStatisticController(user).statisticsController(s.nextLine()))
			statisticsScreen();
		inGameMenuScreen();
	}
}
