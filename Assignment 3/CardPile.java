/**
 *
 * @author Ali Murtaza Sharif
 */
public class CardPile {
	private Card[] cards;
	private int numCards;

	public CardPile() {
		this.cards = new Card[52];
		this.numCards = 0;
	}

	// numCards refers to element right after the last card
	// That is why I add a card at index numCards
	public void addToBottom(Card c) {
		this.cards[numCards] = c;
		this.numCards++;
	}

	public boolean isEmpty() {
		if(this.numCards == 0) return true;
		return false;
	}

	public Card get(int i) {
		// Check if there is a card object at index i
		// Since, we aren't actually required to check for invalid 'i', I'm not going to check for i > 51
		// The second statement will throw an error if it occurs
		if(i >= this.numCards && i < this.cards.length) throw new ArrayIndexOutOfBoundsException();
		return this.cards[i];
	}

	// There are no cards from the index numCards to 52
	// Hence, accessing a card there should throw an ArrayIndexOutOfBoundsException
	public Card remove(int i) {
		// Check if there is a card object at index i
		if(i >= this.numCards && i < this.cards.length) throw new ArrayIndexOutOfBoundsException();
		Card removedCard = this.cards[i];
		// For the specific case where i refers to the last card in the pile
		if(i == this.numCards - 1) {
			this.cards[i] = null;
			this.numCards--;
			return removedCard;
		}
		Card[] newCardArray = new Card[52];
		this.cards[i] = null;
		// To make sure there aren't any 'null' gaps in the card pile
		// Iterate through each element in the cards array
		// If the element is not null then store it in newCardArray
		// j is used to make sure that cards are not overwritten in the new array
		int j = 0;
		for(Card card : this.cards) {
			if(card != null) {
				newCardArray[j++] = card;
			}
		}
		this.cards = newCardArray;
		this.numCards--;
		return removedCard;
	}

	public int find(Suit s, Value v) {
		for(int i = 0;i < this.numCards;i++) {
			if(this.cards[i].getSuit() == s && this.cards[i].getValue() == v) return i;
		}
		return -1;
	}

	public String toString() {
		String deckString = "";
		for(int i = 0;i < this.numCards;i++) {
			deckString = deckString + i + "." + this.cards[i].toString() + " ";
		}
		return deckString;
	}

	// I'm assuming that the method return type is CardPile
	// If this method returned Card[] then there would be no way to initialize a CardPile using this method
	public static CardPile makeFullDeck() {
		// Deck initialization
		Card[] deck = new Card[52];
		int i = 0;
		for(Suit s : Suit.values()) {
			for(Value v : Value.values()) {
				// Add every single card type to the deck
				deck[i] = new Card(s, v);
				i++;
			}
		}
		UtilityCode.shuffle(deck, deck.length);
		CardPile shuffledStack = new CardPile();
		// Copy every card into a new card pile using the addToBottom method
		for(int j = 0;j < deck.length;j++) {
			shuffledStack.addToBottom(deck[j]);
		}
		return shuffledStack;
	}
}
