/**
 *
 * @author Ali Murtaza Sharif
 */
public class CardGame {
	public static void main(String args[]) {
		// Construct the deck safely
		// I take safety very seriously, top kek
		if(args.length == 0) {
			System.out.println("Please enter the number of player present in the card game as such\njava CardGame n\nWhere 'n' represents the number of players.");
			System.exit(1);
		}
		CardPile dealersDeck = CardPile.makeFullDeck();
		CardPile[] playersDecks = null;
		try {
			playersDecks = new CardPile[Integer.parseInt(args[0])];
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid integer");
			System.exit(1);
		} catch(NegativeArraySizeException e) {
			System.out.println("Please enter a non-negative number");
			System.exit(1);
		}
		// This is another safety check
		// I don't think it needs to be here, but whatever
		if(playersDecks == null) System.exit(1);
		for(int i = 0;i < playersDecks.length;i++) playersDecks[i] = new CardPile();
		// Deck construction is over
		// Deal cards from the dealersDeck to the playersDecks
		// using j = 51 to deal cards from the top of the dealer's deck
		for(int i = 0;!dealersDeck.isEmpty();i++) {
			if(i == playersDecks.length) i = 0;
			playersDecks[i].addToBottom(dealersDeck.remove(0));
		}
		/*
		This is legacy code. Please ignore
		It was used to deal cards like the code above it
		int i = 0;
		while(!dealersDeck.isEmpty()) {
			playersDecks[i].addToBottom(dealersDeck.remove(0));
			i++;
			if(i == playersDecks.length) i = 0;
		}
		*/

		// Print out every player's deck
		// This is for testing puposes
		/*
		for(int i = 0;i < playersDecks.length;i++) {
			System.out.println("Player " + i + "'s deck: " + playersDecks[i].toString() + "\n");
		}
		*/
		// Look for the ace of spades in each player's deck
		// This is the actual code that checks the winner
		for(int i = 0;i < playersDecks.length;i++) {
			if(playersDecks[i].find(Suit.SPADES, Value.ACE) != -1) {
				System.out.println("Player " + i + " is the winner!!");
				break;
			}
		}
	}
}
