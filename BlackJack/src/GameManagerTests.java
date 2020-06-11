import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import model.*;


class GameManagerTests 
{
	private GameManager game;
	private Player player;
	private Dealer dealer;

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
}
