import java.util.ArrayList;
import java.util.Random;
public class BaccaratDealer {

    ArrayList<Card> deck;


    public BaccaratDealer() {
        this.deck = new ArrayList<>();
    }
    private void generateSuite(String theSuite) {

        String[] nameList = {"ace", "2", "3", "4", "5", "6", "7", "8", "9"};

        for(int i = 0; i < nameList.length;i++) {
            Card newCard = new Card(theSuite, i+1, nameList[i]);

            this.deck.add(newCard);
        }

        Card newCard = new Card(theSuite, 0, "jack");
        this.deck.add(newCard);
        newCard = new Card(theSuite, 0, "king");
        this.deck.add(newCard);
        newCard = new Card(theSuite, 0, "queen");
        this.deck.add(newCard);
        newCard = new Card(theSuite, 0, "10");
        this.deck.add(newCard);
    }
    public void generateDeck() {

        // heart
        this.deck.clear();

        generateSuite("heart");
        generateSuite("spade");
        generateSuite("diamond");
        generateSuite("club");

    }


    public ArrayList<Card> dealHand() {
        if(deck.size() < 2) {
            return null;
        }
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));
        return cards;
    }


    public Card drawOne() {
        if(deckSize() < 1) {
            return null;
        }
        return deck.remove(deck.size()-1
        );
    }
    public void shuffleDeck() {
        Random randomNumber = new Random();
        randomNumber.setSeed(System.currentTimeMillis());
        for(int i = deck.size()-1;  i > 0; i--) {
            int randIndex =  randomNumber.nextInt(i);

            Card temp = deck.get(i);
            deck.set(i, deck.get(randIndex));
            deck.set(randIndex, temp);
        }

    }
    public int deckSize() {
        return deck.size();
    }
}
