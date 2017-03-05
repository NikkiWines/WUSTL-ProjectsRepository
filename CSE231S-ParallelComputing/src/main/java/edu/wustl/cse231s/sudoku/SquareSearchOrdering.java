package edu.wustl.cse231s.sudoku;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface SquareSearchOrdering {
	Square next(SudokuPuzzle puzzle);
}
