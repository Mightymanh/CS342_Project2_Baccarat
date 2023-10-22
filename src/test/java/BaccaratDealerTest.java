import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
public class BaccaratDealerTest {
    BaccaratDealer newDealer;
    @Test
    void BaccaratDealerConstructor() {
        BaccaratDealer theDaler = new BaccaratDealer();

        assertNotNull(theDaler.deck);
    }

    @BeforeEach
    void init() {
        newDealer = new BaccaratDealer();
        newDealer.generateDeck();
        //System.out.println("hellow world");
    }

    @Test
    void generateDeckTest() {
        int heart = 0;
        int club = 0;
        int diamond = 0;
        int spade = 0;
        for(Card i : newDealer.deck) {

            if(i.suite.equals("Heart")){
                heart++;
            } else if(i.suite.equals("Diamond")) {
                diamond++;
            } else if(i.suite.equals("Spade")) {
                spade++;
            } else if(i.suite.equals("Club")) {
                club++;
            }
        }

        assertEquals(13, heart);
        assertEquals(13, diamond);
        assertEquals(13, spade);
        assertEquals(13, club);


    }
    @Test
    void generateDeckTest2() {
        newDealer.drawOne();
        newDealer.generateDeck();
        int[] arr = {1,2,3,4,5,6,7,8,9,0,0,0,0};

        for(int i =0; i < arr.length; i++) {
            assertEquals(arr[i], newDealer.deck.get(i).value);
        }



    }

    @Test
    void dealHandTest() {
        ArrayList<Card> arr = newDealer.dealHand();

        assertEquals(2, arr.size());
        assertEquals(50, newDealer.deck.size());
        arr = newDealer.dealHand();

        assertEquals(2, arr.size());
        assertEquals(48, newDealer.deck.size());
        assertNotEquals(arr.get(0), arr.get(1));


    }

    @Test
    void dealHandtest2() {
        int[] arr = {1,2,3,4,5,6,7,8,9,0,0,0,0};

        for(int i =0; i < arr.length; i++) {
            assertEquals(arr[i], newDealer.deck.get(i).value);
        }
    }

    @Test
    void drawOneTest(){
        Card oneCard = newDealer.drawOne();
        assertEquals(51, newDealer.deck.size());
        Card card2 = newDealer.drawOne();
        assertEquals(50, newDealer.deck.size());
        assertNotEquals(card2, oneCard);

    }

    @Test
    void drawOneTest2() {
        for(int i =0; i < 52; i++) {
            assertNotNull(newDealer.drawOne());
        }

        assertNull(newDealer.drawOne());
    }


    @Test
    void shuffleTest(){
        ArrayList<Card> squence1 = new ArrayList<>(newDealer.deck);

        newDealer.shuffleDeck();
        assertFalse(Arrays.equals(squence1.toArray(), newDealer.deck.toArray()));


        ArrayList<Card> seq2 = new ArrayList<>(newDealer.deck);

        newDealer.shuffleDeck();
        assertFalse(Arrays.equals(seq2.toArray(), newDealer.deck.toArray()));
        assertFalse(Arrays.equals(squence1.toArray(), newDealer.deck.toArray()));
    }

    @Test
    void shuffle2() {
        ArrayList<Card> seq1 = new ArrayList<>(newDealer.deck);
        newDealer.shuffleDeck();

        assertFalse(Arrays.equals(seq1.toArray(),newDealer.deck.toArray()));

    }

    @Test
    void deckSizeTest() {
        newDealer.drawOne();
        assertEquals(51,newDealer.deckSize());
        newDealer.dealHand();
        assertEquals(49,newDealer.deckSize());

        newDealer.shuffleDeck();
        assertEquals(49,newDealer.deckSize());

        newDealer.dealHand();
        assertEquals(47,newDealer.deckSize());


    }

    @Test
    void deckSize2() {
        for(int i = 0; i < 52; i++) {
            newDealer.drawOne();
        }
        assertEquals(0, newDealer.deckSize());
    }

}
