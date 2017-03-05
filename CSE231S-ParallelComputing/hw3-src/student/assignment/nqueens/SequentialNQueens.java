package student.assignment.nqueens;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.doWork;

import edu.wustl.cse231s.NotYetImplementedException;

public class SequentialNQueens {

	/**
	 * For sequential n-queens, implement the method placeQueenInRow. Note that
	 * countSolutions contains a call to placeQueenInRow(at_count,
	 * queenLocations, 0), and countSolutions is called in TestNQueens. This
	 * method is recursive; that is, it should place a queen in the given row
	 * and then call itself on the next row.
	 * 
	 * @param at_count
	 *            An array of size one to keep track of how many solutions you
	 *            find. Increment it every time you find a solution.
	 * 
	 * @param queenLocations
	 *            A representation of the chess board containing the queens. For
	 *            more information about the MutableQueenLocations class,
	 *            ctrl-click or command-click on "MutableQueenLocations" below.
	 * 
	 * @param row
	 *            The index of the row you are placing a queen into.
	 * 
	 */
	private static void placeQueenInRow(int[] at_count, MutableQueenLocations queenLocations, int row) {
		doWork(1);
		// TODO implement placeQueenInRow
		int boardSize = queenLocations.getBoardSize();
		for (int cal = 0; cal < boardSize; cal ++ ) {  // iterate through columns
			if(queenLocations.isThreatFree(row, cal)) {  // don't do anything unless we're threat free
				MutableQueenLocations secondQueenLocations = queenLocations;
				secondQueenLocations.setColumnOfQueenInRow(row, cal);
				int secondQueenBoardSize = secondQueenLocations.getBoardSize();
				if (row < (secondQueenBoardSize -1)) { // if you're within the board
					placeQueenInRow(at_count, secondQueenLocations, (row+1)); //  call kernel
				}
				else { //reached end of board and found solution! Increment solution counter
					at_count[0] ++; 
				}
			}
		}
	}

	public static int countSolutions(int boardSize) {
		MutableQueenLocations queenLocations = new MutableQueenLocations(boardSize);
		int[] at_count = { 0 };
		placeQueenInRow(at_count, queenLocations, 0);
		return at_count[0];
	}
}
