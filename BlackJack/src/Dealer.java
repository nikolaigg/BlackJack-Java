import java.util.ArrayList;

public class Dealer {

	private ArrayList<Card> hand;
	private int cardTotal;
	
	public Dealer() {
		hand = new ArrayList<>();
		cardTotal = 0;
	}
	
	public void hit(Card card) {
		hand.add(card);
		cardTotal = card.checkAce(card, cardTotal);
		cardTotal += card.getNumericValue();
	}
	

	public void setCardTotal(int cardTotal) {
		this.cardTotal = cardTotal;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getCardTotal() {
		return cardTotal;
	}

	public void printHand() {
		System.out.print("Dealer's hand: ");
		for( Card card : hand) {
			System.out.print(card + " ");
		}
	}
}
