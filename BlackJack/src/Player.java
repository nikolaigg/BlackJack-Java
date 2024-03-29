import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand;
	private int cardTotal;

	public Player() {
		hand = new ArrayList<>();
		cardTotal = 0;
	}

	public int getCardTotal() {
		return cardTotal;
	}
	
	
	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setCardTotal(int cardTotal) {
		this.cardTotal = cardTotal;
	}
	
	public void hit(Card card) {
		hand.add(card);	
		cardTotal = card.checkAce(card, cardTotal);
		cardTotal += card.getNumericValue();
		
	}
	
	public void printHand() {
		System.out.print("Your hand: ");
		for( Card card : hand) {
			System.out.print(card + " ");
		}
	}
	
	
}
