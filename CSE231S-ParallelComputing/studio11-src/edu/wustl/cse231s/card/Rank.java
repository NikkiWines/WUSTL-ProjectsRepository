package edu.wustl.cse231s.card;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum Rank {
	ACE, KING, QUEEN, JACK, TEN(10), NINE(9), EIGHT(8), SEVEN(7), SIX(6), FIVE(5), FOUR(4), THREE(3), TWO(2);
	
	private Rank() {
		this( -1 );
	}
	
	private Rank( int numericValue ) {
		this.numericValue = numericValue;
	}
	
	public boolean isNumeric() {
		return this.numericValue != -1;
	}
	
	public int getNumericValue() {
		return this.numericValue;
	}
	
	private final int numericValue;
}
