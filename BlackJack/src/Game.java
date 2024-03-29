import java.math.BigDecimal;
import java.util.Scanner;

public class Game {
	
	private Deck deck;
	private Player player;
	private Dealer dealer;
	
	public Game(int numOfDecks) {
		deck = new Deck(numOfDecks);
		player = new Player();
		dealer = new Dealer();
	}
	
	void start() {
		Scanner sc = new Scanner(System.in);
		
		// shuffle decks
		deck.shuffle();
		
		while (true) {
			System.out.println("How much would you like to bet? (0 to exit): ");
			BigDecimal bet = sc.nextBigDecimal();
		
			
			if(bet.compareTo(BigDecimal.ZERO) == 0) {
				System.out.println("Exiting game");
				break;
			}
			
			// distribute cards
			dealer.hit(deck.drawCard());
			dealer.hit(deck.drawCard());
			
			player.hit(deck.drawCard());
			player.hit(deck.drawCard());
			
			sleep();
			// print hands
			System.out.println("Dealer's hand: " + dealer.getHand().get(0) + " X"); // print only 1 card 
			
			sleep();
			player.printHand();
			System.out.println("| Your total "+ player.getCardTotal()); 
			
			sleep();
			if (dealer.getCardTotal() == 21) {
				System.out.println("Dealer has BlackJack! You lose your " + bet + "$");
			}
			else if (player.getCardTotal() == 21) {
				System.out.println("BlackJack! You win " +  bet.multiply(BigDecimal.valueOf(2.5)) + "$");
			}
			else {
				playerTurn(bet);
			}
			
			resetGame();
			sleep();
		}
	
		sc.close();
	}

	
	public void playerTurn(BigDecimal bet) {
	    Scanner sc = new Scanner(System.in);

	    int hitCnt = 0;
	    while (true) {
	   
	    	System.out.print("\nPress H to hit, S to stand ");
	    	
	    	// check if player has hit so far
	    	if ( hitCnt == 0) {
	    		System.out.print(", D to double: ");
	    	}
	    	
	    	System.out.println();
	        char choice = sc.next().charAt(0);

	        // Hit
	        if (Character.toUpperCase(choice) == 'H') {
	        	hitCnt++;
	            Hit(bet);
	            if(isPlayerBust(bet)) {
	            	return;
	            }
	        } 
	        
	        // Stand
	        else if (Character.toUpperCase(choice) == 'S') {
	        	Stand(bet);
	        	dealerTurn(bet);
	            return;
	        }
	        
	        // Double
	        else if (Character.toUpperCase(choice) == 'D' && hitCnt == 0) {
	            Double(bet);
	            if(isPlayerBust(bet)) {
	            	return;
	            }
	            dealerTurn(bet);
	            return;
	        } 
	        else {
	            System.out.println("Invalid input, please try again: ");
	        }
	    }
	}

	private void Hit(BigDecimal bet) {
	    System.out.println("You chose to hit");
	    player.hit(deck.drawCard());
	    
	    sleep();
	    player.printHand();
	    System.out.println("| Your total "+ player.getCardTotal());
	}

	private void Stand(BigDecimal bet) {
	    System.out.println("You chose to stand");
	    
	    sleep();
	    dealer.printHand();
	    System.out.println("| Dealer's total "+ dealer.getCardTotal());
	}

	private void Double(BigDecimal bet) {
	    System.out.println("You chose to double your bet");
	    bet = bet.multiply(BigDecimal.valueOf(2));

	    player.hit(deck.drawCard());
	    
	    sleep();
	    player.printHand();
	    System.out.println("| Your total "+ player.getCardTotal()); 
	}
	
	private boolean isPlayerBust(BigDecimal bet) {
		if(player.getCardTotal() > 21) {
			sleep();
	        System.out.println("Bust! Dealer wins");
	        System.out.println("You lose your " + bet + "$");
	        return true;
	    }
		else return false;
	}
	
	public void dealerTurn(BigDecimal bet) {
		
		if(dealer.getCardTotal() >= 17) { // dealer hits till 17
			if (dealer.getCardTotal() > 21) {
				System.out.println("Dealer busts, you win!");
				System.out.println("You win " + bet.multiply(BigDecimal.valueOf(2)) + "$");
			}
			else if(dealer.getCardTotal() == player.getCardTotal()) {
				System.out.println("Push!");
				System.out.println("You get your " + bet + "$ back");
			}
			else if(dealer.getCardTotal() > player.getCardTotal()) {
				System.out.println("Dealer wins!");
				System.out.println("You lose your " + bet + "$");
			}
			else if(dealer.getCardTotal() < player.getCardTotal()) {
				System.out.println("You win " +  bet.multiply(BigDecimal.valueOf(2)) + "$");
			}
		}
		else {
			sleep();
			dealer.hit(deck.drawCard());
			System.out.println("Dealer hits: ");
			
			sleep();
			dealer.printHand();
			System.out.println("| Dealer's total "+ dealer.getCardTotal()); 
			
			dealerTurn(bet);
		}
	}
	public void resetGame() {
		
		player.setCardTotal(0);
		player.getHand().clear();
		
		dealer.setCardTotal(0);
		dealer.getHand().clear();
	}
	
	public static void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


