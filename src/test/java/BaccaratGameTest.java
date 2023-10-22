import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BaccaratGameTest {
    static BaccaratGame game;

    @BeforeAll
    static void setup() {
        game = new BaccaratGame();
        game.theDealer = new BaccaratDealer();
        game.gameLogic = new BaccaratGameLogic();
        game.currentBet = 10;
    }

    // evaluateWinnings()
    @Test
    void testEvaluateWinnings1() {
        game.bankerHand = new ArrayList<>();
        game.bankerHand.add(new Card("Club", 0, "King"));
        game.bankerHand.add(new Card("Queen", 5, "5"));
        game.playerHand = new ArrayList<>();
        game.playerHand.add(new Card("Club", 0, "King"));
        game.playerHand.add(new Card("Queen", 5, "5"));
        game.userChoice = "Draw";
        assertEquals(20, game.evaluateWinnings(), "evaluateWinnings(): user guessed correct but return value is wrong");
    }

    @Test
    void testEvaluateWinnings2() {
        game.bankerHand = new ArrayList<>();
        game.bankerHand.add(new Card("Club", 0, "King"));
        game.bankerHand.add(new Card("Queen", 9, "9"));
        game.playerHand = new ArrayList<>();
        game.playerHand.add(new Card("Club", 0, "King"));
        game.playerHand.add(new Card("Queen", 5, "5"));
        game.userChoice = "Draw";
        assertEquals(0, game.evaluateWinnings(), "evaluateWinnings(): user guessed incorrect but return value is wrong");
    }

}
