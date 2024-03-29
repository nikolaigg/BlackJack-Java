import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private final ArrayList<Card> deck;	
	
	public Deck(int numOfDecks) {
		deck = new ArrayList<>();
		
        String[] suits = {"♥", "♦", "♣", "♠"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int i = 0; i < numOfDecks; i++) {
        	
            for (String suit : suits) {
            	
                for (String value : values) {
                	
                	deck.add(new Card(suit, value));
                }
            }
        }
    }
	
	public void shuffle() {
		System.out.println("Shuffling cards\n");
		Collections.shuffle(deck);
	}
	
	public Card drawCard() {
		if(deck.size() <= 10) {
			shuffle();
		}
		
		if(deck.isEmpty()) {
			return null;
		}
		
		else {
			Card card = deck.remove(0);
			return card;
		}
	}
	
	public void printDeck() {
		for (Card card : deck) {
			System.out.println(card);
		}
	}
}
