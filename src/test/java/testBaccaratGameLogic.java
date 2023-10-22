import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class testBaccaratGameLogic {
	
	static BaccaratGameLogic gameLogic;
	
	@BeforeAll
	static void setup() {
		
		gameLogic = new BaccaratGameLogic();
	}
	
	// handTotal()
	@Test
	void testHandTotal1() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Spade", 1, "Ace"));
		hand.add(new Card("Heart", 2, "2"));
		assertEquals(3, gameLogic.handTotal(hand), "handTotal: incorrect for sum < 10");
		hand.add(new Card("Club", 0, "Jack"));
		assertEquals(3, gameLogic.handTotal(hand), "handTotal: incorrect for sum < 10");
	}
	
	@Test
	void testHandTotal2() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Heart", 9, "9"));
		hand.add(new Card("Diamond", 9, "9"));
		assertEquals(8, gameLogic.handTotal(hand), "handTotal: incorrect for sum >= 10");
		hand.add(new Card("Spade", 2, "2"));
		assertEquals(0, gameLogic.handTotal(hand), "handTotal: incorrect for sum >= 10");
	}	
	
	// evaluatePlayerDraw()
	@Test
	void testEvaluatePlayerDraw1() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Heart", 9, "9"));
		hand.add(new Card("Diamond", 9, "9"));
		assertTrue(!(gameLogic.evaluatePlayerDraw(hand)), "evaluatePlayerDraw(): wrong evaluate for score > 5");
		
	}
	
	@Test
	void testEvaluatePlayerDraw2() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Heart", 9, "9"));
		hand.add(new Card("Diamond", 1, "Ace"));
		assertTrue(gameLogic.evaluatePlayerDraw(hand), "evaluatePlayerDraw(): wrong evaluate for score <= 5");
	}
	
	// evaluateBankerDraw()
	@Test
	void testEvaluateBankerDraw1() {
		ArrayList<Card> hand = new ArrayList<>();
		hand.add(new Card("Diamond", 0, "10"));
		hand.add(new Card("Diamond", 5, "5"));
		Card playerThirdCard1 = null;
		assertTrue(gameLogic.evaluateBankerDraw(hand, playerThirdCard1), "evaluateBankerDraw(): wrong evaluate for case player's third card = null");
		Card playerThirdCard2 = new Card("Diamond", 4, "4");
		assertTrue(gameLogic.evaluateBankerDraw(hand, playerThirdCard2), "evaluateBankerDraw(): wrong evaluate for case player's third card = 4");
		Card playerThirdCard3 = new Card("Diamond", 5, "5");
		assertTrue(gameLogic.evaluateBankerDraw(hand, playerThirdCard3), "evaluateBankerDraw(): wrong evaluate for case player's third card = 5");
	}
	
	@Test
	void testEvaluateBankerDraw2() {
		// case 1: player does not draw a third card
		for (int i = 0; i < 10; i++) {
			Card playerThirdCard = null;
			for (int j = 0; j <= 5; j++) {
				ArrayList<Card> bankerHand = new ArrayList<>();
				bankerHand.add(new Card("", 0, ""));
				bankerHand.add(new Card("", j, ""));
				assertTrue(gameLogic.evaluateBankerDraw(bankerHand, playerThirdCard), "evaluateBankerDraw(): fail for case third card = null");
			}
			for (int j = 6; j < 10; j++) {
				ArrayList<Card> bankerHand = new ArrayList<>();
				bankerHand.add(new Card("", 0, ""));
				bankerHand.add(new Card("", j, ""));
				assertTrue(!gameLogic.evaluateBankerDraw(bankerHand, playerThirdCard), "evaluateBankerDraw(): fail for case third card = null");
			}
		}
		
		// case 2: player draws a third card
		int boundaryArray[] = {3, 3, 4, 4, 5, 5, 6, 6, 2, 3};
		for (int i = 0; i < 10; i++) {
			Card playerThirdCard = new Card("", i, "");
			int bound = boundaryArray[i];
			for (int j = 0; j <= bound; j++) {
				ArrayList<Card> bankerHand = new ArrayList<>();
				bankerHand.add(new Card("", 0, ""));
				bankerHand.add(new Card("", j, ""));
				assertTrue(gameLogic.evaluateBankerDraw(bankerHand, playerThirdCard), "evaluateBankerDraw(): fail for case third card value = " + i);
			}
			for (int j = bound + 1; j < 10; j++) {
				ArrayList<Card> bankerHand = new ArrayList<>();
				bankerHand.add(new Card("", 0, ""));
				bankerHand.add(new Card("", j, ""));
				assertTrue(!gameLogic.evaluateBankerDraw(bankerHand, playerThirdCard), "evaluateBankerDraw(): fail for case third card value = " + i);
			}
		}
	}
	
	// whoWon()
	@Test
	void testWhoWon1() {
		ArrayList<Card> player = new ArrayList<>();
		ArrayList<Card> banker = new ArrayList<>();
		player.add(new Card("Diamond", 0, "10"));
		player.add(new Card("Diamond", 5, "5"));
		banker.add(new Card("Heart", 9, "9"));
		banker.add(new Card("Diamond", 1, "Ace"));
		assertEquals("Player", gameLogic.whoWon(player, banker), "whoWon(): fail");
	}
	
	@Test
	void testWhoWon2() {
		ArrayList<Card> player = new ArrayList<>();
		ArrayList<Card> banker = new ArrayList<>();
		player.add(new Card("Diamond", 0, "10"));
		player.add(new Card("Diamond", 5, "5"));
		banker.add(new Card("Heart", 4, "4"));
		banker.add(new Card("Diamond", 5, "5"));
		assertEquals("Banker", gameLogic.whoWon(player, banker), "whoWon(): fail");
	}
	
	@Test
	void testWhoWon3() {
		ArrayList<Card> player = new ArrayList<>();
		ArrayList<Card> banker = new ArrayList<>();
		player.add(new Card("Diamond", 0, "Jack"));
		player.add(new Card("Diamond", 5, "5"));
		banker.add(new Card("Heart", 8, "8"));
		banker.add(new Card("Diamond", 7, "7"));
		assertEquals("Draw", gameLogic.whoWon(player, banker), "whoWon(): fail");
	}
	
}
