import java.util.ArrayList;

public class BaccaratGameLogic {
	
	
	
	// assuming hand1 = playerHand & hand2 = bankerHand, evaluate two hands at the game, 
	// return a string of winner
	public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		int playerScore = handTotal(hand1);
		int bankerScore = handTotal(hand2);
		
		// compare the score between player & banker and decide the winner
		if (playerScore > bankerScore) {
			return "Player";
		}
		if (playerScore < bankerScore) {
			return "Banker";
		}
		
		return "Draw";
	}
	
	// return the total points in the inputed hand
	public int handTotal(ArrayList<Card> hand) {
		int total = 0;
		for (Card card : hand) { // sum values in hand
			total += card.value;
		}
		total = total % 10; // take the last digit
		return total;
	}
	
	// return whether banker should draw a third card
	public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		int score = handTotal(hand);
		// player does not draw third card -> bankers draw when 0 - 5
		if (playerCard == null) {
			if (0 <= score && score <= 5) {
				return true;
			}
			return false;
		}
		
		int playerCardValue = playerCard.value;
		
		// player draws 0, 1, 9 -> bankers draw when 0 - 3
		if (playerCardValue == 0 || playerCardValue == 1 || playerCardValue == 9) {
			if (0 <= score && score <= 3) {
				return true;
			}
		}
		// player draws 8 -> bankers draw when 0 - 2
		else if (playerCardValue == 8) {
			if (0 <= score && score <= 2) {
				return true;
			}
		}
		// player draws 2, 3 -> bankers draw when 0 - 4
		else if (playerCardValue == 2 || playerCardValue == 3) {
			if (0 <= score && score <= 4) {
				return true;
			}
		}
		// player draws 4, 5 -> bankers draw when 0 - 5
		else if (playerCardValue == 4 || playerCardValue == 5) {
			if (0 <= score && score <= 5) {
				return true;
			}
		}
		// player draws 6, 7 -> bankers draw when 0 - 6
		else {
			if (0 <= score && score <= 6) {
				return true;
			}
		}
		
		return false; // in other case, banker draws no card
	}
	
	// return whether player should draw a third card
	public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		int score = handTotal(hand);
		if (score <= 5) {
			return true;
		}
		return false;
	}
}
