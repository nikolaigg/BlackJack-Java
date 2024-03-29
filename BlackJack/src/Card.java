
public class Card {

	private final String suit;
	private final String value;
	
	public Card(String suit, String val) {
		this.suit = suit;
		this.value = val;
	}

	public int getNumericValue() {
		
		int numValue = switch(value) {
			case "Ace" -> 1;
			case "2" -> 2;
			case "3" -> 3;
			case "4" -> 4;
			case "5" -> 5;
			case "6" -> 6;
			case "7" -> 7;
			case "8" -> 8;
			case "9" -> 9;
			case "10", "J", "Q", "K" -> 10;
			default -> 0;
		};
		
		return numValue;
	}
	public int checkAce(Card card, int total) {
		if( card.value.equals("Ace") && !((total + 10) > 21)) {
			return total + 10;
		}else {
			return total;
		}
	}
	@Override
	public String toString() {
		return value + "" + suit;
	}
	
	
}
