package com.blackjack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.blackjack.controller.GameController;
import com.blackjack.model.*;


class GameManagerTests 
{
	private GameManager game = null;
	private static iRepository [] repositories;
	private User user = null;
	private String [] message = {"BlackJack","Win","Tie","Surrender","Lose"};
	private SignUpManager signUpManager = new SignUpManager();
	private LoginManager loginManager = new LoginManager();
	private ChargeManager chargeManager = new ChargeManager();
	private GameController gameController = null;
	@BeforeAll
	public static void startUp ()
	{
		repositories = new iRepository[2];
		repositories[0] = UserRepository.BuildUserRepository();
		repositories[1] = StatisticsRepository.BuildStatisticsRepository();
	}
	@AfterEach
	public void clean()
	{
		repositories[0].clean();
		repositories[1].clean();
	}
	@Test
	void testWinMoreThanTheDealer() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(9,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(8,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(19, player.handValue());
		assertEquals(18, dealer.handValue());
		assertEquals("Win ", game.checkHandResult(1));
		assertEquals(200, game.calcMoney());
	}
	@Test
	void testLoseLessThanTheDealer() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(8,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(9,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(18, player.handValue());
		assertEquals(19, dealer.handValue());
		assertEquals("LOSE ", game.checkHandResult(1));
		assertEquals(0, game.calcMoney());
	}
	@Test
	void testWinDealerBurns() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(9,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(8,"HEARTS"));
		dealer.pushCard(new Card(5,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(19, player.handValue());
		assertEquals(23, dealer.handValue());
		assertEquals("Win ", game.checkHandResult(1));
		assertEquals(200, game.calcMoney());
	}
	@Test
	void testLosePlayerBurns() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(8,"HEARTS"));
		player.pushCard(new Card(8,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(9,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(26, player.handValue());
		assertEquals(19, dealer.handValue());
		assertEquals("LOSE ", game.checkHandResult(1));
		assertEquals(0, game.calcMoney());
	}
	@Test
	void testBlackJackTie() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(1,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(1,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(21, player.handValue());
		assertEquals(21, dealer.handValue());
		assertEquals("Tie ", game.checkHandResult(1));
		assertEquals(100, game.calcMoney());
	}
	@Test
	void testBlackJackWins() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(1,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(21, player.handValue());
		assertEquals(20, dealer.handValue());
		assertEquals("BLACK JACK ", game.checkHandResult(1));
		assertEquals(250, game.calcMoney());
	}
	@Test
	void testBlackJackLose() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(1,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(20, player.handValue());
		assertEquals(21, dealer.handValue());
		assertEquals("LOSE ", game.checkHandResult(1));
		assertEquals(0, game.calcMoney());
	}
	@Test
	void testTie() 
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		player.pushCard(new Card(9,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(9,"HEARTS"));
		game = new GameManager(player,dealer);
		assertEquals(19, player.handValue());
		assertEquals(19, dealer.handValue());
		assertEquals("Tie ", game.checkHandResult(1));
		assertEquals(100, game.calcMoney());
	}
	@Test
	void userNameSpace()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("ab c", "password");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("Spaces Not Allowed", e.getMessage());
		}
	}
	@Test
	void userNameLessThan3()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("12", "password");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("UserName Length Less Than 3 Letters", e.getMessage());
		}
	}
	@Test
	void userNameMoreThan10()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("12345678910", "password");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("UserName Length More Than 10 Letters", e.getMessage());
		}
	}
	@Test
	void passwordLessThan3()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("user", "12");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("Password Length Less Than 3 Letters", e.getMessage());
		}
	}
	@Test
	void passwordMoreThan10()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("user", "12345678910");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("Password Length More Than 10 Letters", e.getMessage());
		}
	}
	@Test
	void userNameAvailable()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("user", "123");
		signUpManager.signNewUser("user", "123");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("Username Already Exists", e.getMessage());
		}
	}
	@Test
	void userNameAvailableUpperCase()
	{
		SignUpManager signUpManager = new SignUpManager();
		try {
		signUpManager.signNewUser("user", "123");
		signUpManager.signNewUser("User", "123");
		fail("FAIL");
		}catch (Exception e) {
			assertEquals("Username Already Exists", e.getMessage());
		}
	}
	@Test
	void userExists()
	{
		LoginManager loginManager = new LoginManager();
		try {
			loginManager.userLogin("123", "password");
			fail("FAIL");
		}
		catch (Exception e) {
			assertEquals("User Doesn't Exists", e.getMessage());
		}
	}
	@Test
	void passwordMatch()
	{
		SignUpManager signUpManager = new SignUpManager();
		LoginManager loginManager = new LoginManager();
		try {
			signUpManager.signNewUser("123", "123");
			loginManager.userLogin("123", "password");
			fail("FAIL");
		}
		catch (Exception e) {
			assertEquals("Invalid Password", e.getMessage());
		}
	}
	@Test
	void chargeManagerTests()
	{
		User user = null;
		SignUpManager signUpManager = new SignUpManager();
		LoginManager loginManager = new LoginManager();
		ChargeManager chargeManager = new ChargeManager();
		try {
			signUpManager.signNewUser("123", "123");
			user = loginManager.userLogin("123", "123");		
			chargeManager.Deposit(user, "100");
			assertEquals(100,user.getBalance());
			chargeManager.Withdraw(user, "50");
			assertEquals(50, user.getBalance());
		}	catch (Exception e) {
			fail("UnWanted Exception");
		}
		try {
			chargeManager.Withdraw(user, "52");
		}catch (Exception e) {
			assertEquals("Amount Less Than Money",e.getMessage());
		}
		try {
			chargeManager.Withdraw(user, "0");
		}catch (Exception e) {
			assertEquals("0 Cant Be Input",e.getMessage());
		}
		try {
			chargeManager.Deposit(user, "-100");
		}catch (Exception e) {
			assertEquals("Invalid Amount",e.getMessage());
		}
		try {
			chargeManager.Withdraw(user, "-100");
		}catch (Exception e) {
			assertEquals("Invalid Amount",e.getMessage());
		}
		try {
			chargeManager.Deposit(user, "");
		}catch (Exception e) {
			assertEquals("No Input",e.getMessage());
		}
		try {
			chargeManager.Withdraw(user, "");
		}catch (Exception e) {
			assertEquals("No Input",e.getMessage());
		}
	}
	@Test
	void statisticsTest() throws Exception
	{
		int money = 0;
		int betAmount = 100;
		int [] counters = new int [5];
		int handsPlayed = 0;
		try {
		signUpManager.signNewUser("eyal", "123");
		user = loginManager.userLogin("eyal", "123");
		chargeManager.Deposit(user, "1000000");
		gameController = GameController.BuildController(user);
		}catch (Exception e) {
			fail("UnWanted Exception");
		}
		for (int i=0;i<100;i++)
		{
			game = gameController.playController(String.valueOf(betAmount));
			money = user.getBalance();
			gameController.holdController();
			gameController.endController();
			handsPlayed++;
			StatisticsRepository statisticsRepository = StatisticsRepository.BuildStatisticsRepository();
			assertEquals(handsPlayed, statisticsRepository.getUserStatistic(user).getHandsPlayed());
			String result = game.checkHandResult(1);
			switch (result)
			{
				case "BLACK JACK ":
					counters[0]++;
					assertEquals(money + (int)(betAmount * 2.5), user.getBalance());
					assertEquals(counters[0], statisticsRepository.getUserStatistic(user).getHandsBlackjack());
					break;
				case "Win ":
					counters[1]++;
					assertEquals(money + betAmount * 2, user.getBalance());
					assertEquals(counters[1], statisticsRepository.getUserStatistic(user).getHandsWin());
					break;
				case "Tie ":
					counters[2]++;
					assertEquals(money + betAmount, user.getBalance());
					assertEquals(counters[2], statisticsRepository.getUserStatistic(user).getHandsTie());
					break;
				case "Surrender ":
					counters[3]++;
					assertEquals(money + betAmount / 2, user.getBalance());
					assertEquals(counters[3], statisticsRepository.getUserStatistic(user).getHandsSurrender());
					break;
				case "LOSE ":
					counters[4]++;
					assertEquals(money + 0, user.getBalance());
					assertEquals(counters[4], statisticsRepository.getUserStatistic(user).getHandsLose());
					break;
				default:
					fail("Default");
			}
		}
		for (int i=0;i<5;i++) {
			System.out.println(message[i] + ": " + counters[i]);
		}
		System.out.println(user.getBalance());
		System.out.println(StatisticsRepository.BuildStatisticsRepository().getUserStatistic(user));
	}
	@Test
	void splitTest()
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(9,"HEARTS"));
		game = new GameManager(player,dealer);
		try {
		game.playerSplit();
		}catch (Exception e) {
			assertEquals("Player Hand Size Is Not 2", e.getMessage());
		}
		player.pushCard(new Card(5,"HEARTS"));
		try {
		game.playerSplit();
		}catch (Exception e) {
			assertEquals("Player Cards Not Same", e.getMessage());
		}
		player.removeCard();
		player.pushCard(new Card(10,"HEARTS"));
		try {
		game.playerSplit();
		game.playerSplit();
		}catch (Exception e) {
			assertEquals("Already Splited", e.getMessage());
		}
		assertNotEquals(0, player.secondHandValue());
		assertEquals(true, game.isSplit());
		game.playerHold();
		assertEquals(2, game.getPlayerHand());
		game.playerHold();
		assertEquals(19, dealer.handValue());
	}
	@Test
	void hitTest()
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		player.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(10,"HEARTS"));
		dealer.pushCard(new Card(9,"HEARTS"));
		game = new GameManager(player,dealer);
		game.playerHit();
		assertEquals(2, player.handSize());
		assertEquals(true, player.handValue() > 10);
	}
	@Test
	void surrenderTest()
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		game = new GameManager(player,dealer);
		game.startTurn();
		assertEquals(false,game.playerSurrender());
 		assertEquals("Surrender ", game.checkHandResult(1));
	}
	@Test
	void doubleTest()
	{
		Player player = new Player();
		Dealer dealer = new Dealer();
		game = new GameManager(player,dealer);
		player.pushCard(new Card(10,"HEARTS"));//1
		try {
			game.playerDouble();
		}catch (Exception e) {
			assertEquals("Player Hand 1 Size Is Not 2", e.getMessage());
		}
		player.pushCard(new Card(2,"HEARTS"));//2
		player.pushCard(new Card(2,"HEARTS"));//3
		try {
			game.playerDouble();
		}catch (Exception e) {
			assertEquals("Player Hand 1 Size Is Not 2", e.getMessage());
		}
		player.removeCard();//2
		try {
			game.playerDouble();
		}catch (Exception e) {
			fail("UnWanted Exception");
		}
		assertEquals(3, player.handSize());
		assertEquals(true, dealer.handValue() >= 17);
	}
}