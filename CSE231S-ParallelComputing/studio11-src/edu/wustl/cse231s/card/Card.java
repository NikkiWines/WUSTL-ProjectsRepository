package edu.wustl.cse231s.card;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class Card {
	public Card( Suit suit, Rank rank ) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	private final Suit suit;
	private final Rank rank;
}
