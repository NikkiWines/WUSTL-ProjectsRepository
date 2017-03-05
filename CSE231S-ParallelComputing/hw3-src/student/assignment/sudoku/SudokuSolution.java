package student.assignment.sudoku;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;

import java.util.List;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.SquareSearchOrdering;
import edu.wustl.cse231s.sudoku.SudokuUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum SudokuSolution {
	;
	private static void solveKernel(ImmutableSudokuPuzzle[] at_solution, ImmutableSudokuPuzzle puzzle, SquareSearchOrdering squareIterator) {
		async(() -> {
			Square next = squareIterator.next(puzzle);
			if(next != null){ // if we haven't found a solution yet 
				List<Integer> ops = puzzle.getOptions(next); // list of options for this square.
				for(int i = 0; i < ops.size(); i++){ // check each option 
					ImmutableSudokuPuzzle secondPuzzle = puzzle.createNext(next, ops.get(i));
					//make recursive call for each option
					solveKernel(at_solution, secondPuzzle, squareIterator);
				}
			}
			else{ // found solution! place into at_solution
				at_solution[0] = puzzle;
			}
		});
	}

	public static ImmutableSudokuPuzzle solve(ImmutableSudokuPuzzle puzzle, SquareSearchOrdering squareIterator) throws SuspendableException {
		ImmutableSudokuPuzzle[] at_solution = { null };
		finish(() -> {
			solveKernel(at_solution, puzzle, squareIterator);
		});
		return at_solution[0];
	}
}
