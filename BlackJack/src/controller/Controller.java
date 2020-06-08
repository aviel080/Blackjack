package controller;

import model.*;

public class Controller {
	private static Controller controller = null;
	private LoginManager loginManager= new LoginManager();
	private SignUpManager signUpManager= new SignUpManager();
	private ChargeManager chargeManager = new ChargeManager();
	private User user;
	private GameManager game;
	public static Controller BuildController()
	{
		if (controller == null)
			controller = new Controller();
		return controller;
	}
	public User loginController(String username,String password)throws Exception
	{
		user = loginManager.userLogin(username, password);
		return user;
	}
	public void signupController(String username,String password)throws Exception
	{
		signUpManager.signNewUser(username,password);
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
		user.updateStatistics(game.checkHandResult(1));
		if(game.isSplit())
			user.updateStatistics(game.checkHandResult(2));
		FileManager.Update(user);
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
	public boolean depositController(String amount)
	{
		try {
		chargeManager.Deposit(user, amount);
		}
		catch(Exception e){	
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	public boolean withdrawController(String amount)
	{
		try {
		chargeManager.Withdraw(user, amount);
		}
		catch(Exception e){	
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	public boolean statisticsController(String choose)
	{
		if(choose.equals("clear"))
		{
			user.clearStatistics();
			FileManager.Update(user);
			return true;
		}
		return false;
	}
	/*
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
	*/
}
