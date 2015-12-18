/**
 *
 * @author Ali Murtaza Sharif
 */
public class Card {
	private Suit suit;
	private Value value;

	public Card(Suit theSuit, Value theValue) {
		this.suit = theSuit;
		this.value = theValue;
	}
	// I don't think this class even needs any explaining
	public Suit getSuit() { return this.suit; }
	public Value getValue() { return this.value; }
	public String toString() { return this.value + " of " + this.suit; }
}
