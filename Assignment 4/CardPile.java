/**
 *
 * @author Ali Murtaza Sharif
 */
import java.util.ArrayList;
import java.util.Collections;
public class CardPile {
	private ArrayList<Card> cards;

	public CardPile() {
		this.cards = new ArrayList<Card>();
	}

	public void addToBottom(Card c) {
		this.cards.add(new Card(c.getSuit(), c.getValue()));
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public Card get(int i) {
		return this.cards.get(i);
	}

	public Card remove(int i) {
		return this.cards.remove(i);
	}

	public int find(Suit s, Value v) {
		// indexOf() was being really weird and kept returning -1
		// Probably because the addresses weren't the same
		// I'm going to compare the results of the toString() method becuase we only care about the suit and value of a card
		int i = 0;
		for(Card element : cards) {
			if(element.toString().equals((new Card(s, v).toString()))) return i;
			i++;
		}
		return -1;
	}

	public String toString() {
		String deckString = "";
		for(int i = 0;i < this.cards.size();i++) {
			deckString = deckString + i + "." + this.cards.get(i).toString() + " ";
		}
		return deckString;
	}

	public int getNumCards() {
		return this.cards.size();
	}

	public static CardPile makeFullDeck() {
		// Deck initialization
		ArrayList<Card> shuffledStack = new ArrayList<Card>();
		for(Suit s : Suit.values()) {
			for(Value v : Value.values()) {
				shuffledStack.add(new Card(s, v));
			}
		}
		Collections.shuffle(shuffledStack);
		CardPile returnedCardPile = new CardPile();
		returnedCardPile.cards = shuffledStack;
		return returnedCardPile;
	}

	public static CardPile makeFullDeck(int n) {
		ArrayList<Card> deck = new ArrayList<Card>();
		for(int i = 0;i < n;i++) {
			deck.addAll(makeFullDeck().cards);
		}
		Collections.shuffle(deck);
		CardPile shuffledStack = new CardPile();
		shuffledStack.cards = deck;
		return shuffledStack;
	}
}
