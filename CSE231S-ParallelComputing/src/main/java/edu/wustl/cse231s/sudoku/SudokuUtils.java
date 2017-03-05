package edu.wustl.cse231s.sudoku;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum SudokuUtils {
	;
	public static boolean isValidSquareValue(SudokuPuzzle puzzle, Square square, int value) {
		for( Square peer : square.getPeers() ) {
			if( puzzle.getValue(peer) == value ) {
				return false;
			}
		}
		return true;
	}
	public static boolean isValid(SudokuPuzzle puzzle) {
		for( Square square : Square.values()) {
			int value = puzzle.getValue(square);
			if( value != 0 ) {
				if( isValidSquareValue(puzzle, square, value)) {
					//pass
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
}
