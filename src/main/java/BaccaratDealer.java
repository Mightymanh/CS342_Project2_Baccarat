import java.util.ArrayList;
import java.util.Random;
public class BaccaratDealer {

    ArrayList<Card> deck;


    public BaccaratDealer() {
        deck = new ArrayList<>();
    }
    private void generateSuite(String theSuite) {

        String[] nameList = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9"};

        for(int i = 0; i < nameList.length;i++) {
            Card newCard = new Card(theSuite, i+1, nameList[i]);

            deck.add(newCard);
        }

        Card newCard = new Card(theSuite, 0, "Jack");
        deck.add(newCard);
        newCard = new Card(theSuite, 0, "King");
        deck.add(newCard);
        newCard = new Card(theSuite, 0, "Queen");
        deck.add(newCard);
        newCard = new Card(theSuite, 0, "10");
        deck.add(newCard);
    }
    public void generateDeck() {

        // heart
        this.deck.clear();

        generateSuite("Heart");
        generateSuite("Spade");
        generateSuite("Diamond");
        generateSuite("Club");

    }


    public ArrayList<Card> dealHand() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));
        return cards;
    }


    public Card drawOne() {
        return deck.remove(deck.size()-1);
    }
    public void shuffleDeck() {
        Random randomNumber = new Random();

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
