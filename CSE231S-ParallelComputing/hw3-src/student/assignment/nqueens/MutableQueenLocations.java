package student.assignment.nqueens;

/**
 * A board setup that can be changed. Each row on the board can contain at most
 * one queen in a single column.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class MutableQueenLocations implements QueenLocations {

	public MutableQueenLocations(int boardSize) {
		this.locations = new int[boardSize];
	}

	@Override
	public int getColumnOfQueenInRow(int row) {
		return this.locations[row];
	}

	/**
	 * Sets the queen position in a given row. Each row can contain at most a
	 * single queen in a single column, so by setting this value, you are
	 * overwriting any queens already in the given row.
	 * 
	 * @param row
	 *            The row in which to place the queen.
	 * @param column
	 *            The column in which to place the queen.
	 */
	public void setColumnOfQueenInRow(int row, int column) {
		if (!isThreatFree(row, column))
			throw new IllegalArgumentException("Unable to place queen at position (row=" + row + ", column=" + column
					+ ")\nCurrent board:\n" + toString());
		this.locations[row] = column;
	}

	/**
	 * Checks to see whether the given square on the board is threat free. Note
	 * that this method only checks rows above the given one, so the queens in
	 * rows below this one will not interfere.
	 * 
	 * @param row
	 *            The row of the square to check.
	 * @param column
	 *            The column of the square to check.
	 * @return false if there are queens on the board preventing placement of a
	 *         queen on this square, otherwise true.
	 */
	public boolean isThreatFree(int row, int column) {
		return NQueensUtils.isThreatFreeWithQueensInPreviousRows(this, row, column);
	}

	/**
	 * Gets the size of the board. For a normal chessboard, this will be 8.
	 * 
	 * @return The number of rows in the board.
	 */
	public int getBoardSize() {
		return this.locations.length;
	}

	public void printBoard() {
		NQueensUtils.printBoard(this.locations);
	}

	@Override
	public String toString() {
		return NQueensUtils.toString(this.locations);
	}

	private final int[] locations;
}
