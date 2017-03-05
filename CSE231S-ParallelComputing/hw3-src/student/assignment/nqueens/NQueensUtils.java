package student.assignment.nqueens;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class NQueensUtils {

	private NQueensUtils() {
		throw new AssertionError();
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
	public static boolean isThreatFreeWithQueensInPreviousRows(QueenLocations queenLocations, int row, int column) {
		for (int i = 0; i < row; i++) {
			int columnOfQueenInRowI = queenLocations.getColumnOfQueenInRow(i);
			// is in same column
			if (columnOfQueenInRowI == column) {
				return false;
			}
			// is in same diagonal A
			if (row - i == columnOfQueenInRowI - column) {
				return false;
			}
			// is in same diagonal B
			if (row - i == column - columnOfQueenInRowI) {
				return false;
			}
		}
		return true;
	}

	public static void printBoard(int[] queens) {
		System.out.println(toString(queens));
	}

	public static String toString(int[] queens) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < queens.length; i++) {
			for (int j = 0; j < queens.length; j++) {
				if (queens[i] == j) {
					result.append("Q ");
				} else {
					result.append("* ");
				}
			}
			result.append('\n');
		}

		return result.toString();
	}
}
