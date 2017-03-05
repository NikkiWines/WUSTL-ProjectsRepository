package student.assignment.nqueens;

import edu.wustl.cse231s.NotYetImplementedException;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class ImmutableQueenLocations implements QueenLocations {

	/** The size of the board. This is equivalent to the number of columns. */
	private final int boardSize;
	/**
	 * The queen locations on the board, stored in an array. Each index in this
	 * array corresponds to a row on the board. The value at that index is the
	 * column of the queen in that row.
	 */
	private final int[] locations;

	/**
	 * Creates an ImmutableQueenLocations board with no queens on it. You will
	 * never need to call this method.
	 * 
	 * @param boardSize
	 *            The size of the new board. This will be the number of columns.
	 */
	public ImmutableQueenLocations(int boardSize) {
		this.boardSize = boardSize;
		this.locations = new int[0];
	}

	/**
	 * Creates a new ImmutableQueenLocations instance from an already existing
	 * one. The new instance will have an additional queen. This queen will be
	 * placed in the next row down, in the given column.
	 * 
	 * @param other
	 *            The original ImmutableQueenLocations object to add onto.
	 * @param column
	 *            The column in which to place the new queen.
	 */
	private ImmutableQueenLocations(ImmutableQueenLocations other, int column) {
		if (!other.isNextRowThreatFree(column))
			throw new IllegalArgumentException("Unable to place queen at position (row=" + other.locations.length
					+ ", column=" + column + ")\nCurrent board:\n" + other.toString());
		this.boardSize = other.boardSize;
		this.locations = new int[other.locations.length + 1];
		System.arraycopy(other.locations, 0, this.locations, 0, other.locations.length);
		this.locations[this.locations.length - 1] = column;
	}

	/**
	 * This method should create the next board state by placing a queen in the
	 * specified column. (Don't overthink this one.)
	 * 
	 * @param column
	 *            The column where the queen will be placed.
	 * @return A new ImmutableQueenLocations object built off of this one. It
	 *         should have a new queen in the next row down in the given column.
	 */
	public ImmutableQueenLocations createNext(int column) {
		return new ImmutableQueenLocations(this, column);
	}
	/**
	 * This method should tell you which column a queen is in for a given row.
	 * Note that each row can only have a single queen, so this method will
	 * return the column of that queen.
	 * 
	 * @param row
	 *            The row where you will determine what column the queen is in.
	 * @return The column of the queen in the given row.
	 */
	@Override
	public int getColumnOfQueenInRow(int row) {
		return locations[row]; // locations array gives you the row, value at specific row gives you column
	}

	/**
	 * This method should return how many rows there currently are in the
	 * object. (Hint: look at your instance variables.)
	 * 
	 * @return The number of rows on the board that are currently occupied by a
	 *         queen.
	 */
	public int getRowCount() {
		return locations.length; // number of entries in locations row give you num rows
	}

	/**
	 * This method should return the size of the board. (Hint: again, look at
	 * your instance variables.)
	 * 
	 * @return The size of the board. This is equivalent to the number of
	 *         columns on the board.
	 */
	public int getBoardSize() {
		return boardSize; //variable given
	}

	/**
	 * This method should check to see if a position on the chess board is being
	 * attacked by any other queens. (Hint: maybe NQueensUtils could be useful?)
	 * 
	 * @param column
	 *            The column where the next queen will be placed.
	 */
	public boolean isNextRowThreatFree(int column) { 
		//NQueenUtils has method to check
		// check for this instance, and get then row with previous method
		return NQueensUtils.isThreatFreeWithQueensInPreviousRows(this, getRowCount(), column);
	}

	public void printBoard() {
		NQueensUtils.printBoard(this.locations);
	}

	@Override
	public String toString() {
		return NQueensUtils.toString(this.locations);
	}

}
