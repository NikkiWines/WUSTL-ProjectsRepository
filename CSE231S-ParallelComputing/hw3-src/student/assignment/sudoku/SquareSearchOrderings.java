package student.assignment.sudoku;

import java.util.List;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.SquareSearchOrdering;
import edu.wustl.cse231s.sudoku.SudokuPuzzle;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum SquareSearchOrderings implements SquareSearchOrdering {
	ROW_MAJOR {
		@Override
		public Square next(SudokuPuzzle puzzle) {
			Square square = Square.A1;
			while( square != null ) {
				if( puzzle.isAlreadySet(square)) {
					//pass
				} else {
					break;
				}
				square = square.next();
			}
			return square;
		}
	},
	FEWEST_OPTIONS_FIRST{
		@Override
		public Square next(SudokuPuzzle puzzle) {
			Square currentSquare = Square.A1; // first square 
			Square leastSquare = Square.A1;
			int leastNumOptions = 10; // maximum number of options per square is 10, start with max and then change as we move through squares
			

			while( currentSquare != null) {  // go until we've run through all of our squares
				if (!puzzle.isAlreadySet(currentSquare))  {// we haven't set out puzzle yet, so there is work to do.
					if (leastNumOptions > puzzle.getOptions(currentSquare).size()) { // if we have fewer options than the current least number of options then update
						leastSquare = currentSquare; // update the square with the least number of options
						leastNumOptions = puzzle.getOptions(currentSquare).size(); //update options count
					}
					
				}
				currentSquare = currentSquare.next();
			}

			if (!puzzle.isAlreadySet(leastSquare)){ // if we havent set the puzzle, return square with least options
					return leastSquare;
			}
			else { 
				return null; // we have already set it, so return null
				//
			}
		}
	};
}
