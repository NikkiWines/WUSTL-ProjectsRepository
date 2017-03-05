package edu.wustl.cse231s.sudoku;

import java.util.List;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface SudokuPuzzle {
	default boolean isAlreadySet(Square square) {
		return this.getValue(square) != 0;
	}

	int getValue(Square square);
	List<Integer> getOptions(Square square);
}
