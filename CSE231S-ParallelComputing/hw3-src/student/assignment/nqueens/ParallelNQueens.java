package student.assignment.nqueens;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.doWork;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.newIntegerFinishAccumulator;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.register;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;
import edu.wustl.cse231s.rice.classic.options.RegisterAccumulatorsOption;

public class ParallelNQueens {

	/**
	 * Like SequentialNQueens, implement the method placeQueenInRow. Note that
	 * countSolutions is called in TestNQueens, but not implemented for you
	 * here. There is no param row for the immutable version. Why not? How will
	 * you get the row? (Hint: look at the methods in ImmutableQueenLocations)
	 * 
	 * @param acc
	 *            An accumulator used to keep track of the number of board
	 *            arrangements found so far. Why are we using an accumulator
	 *            instead of an array?
	 * @param queenLocations
	 *            A representation of the chess board containing the queens.
	 *            However, note that it is immutable in the parallel version.
	 *            Why is it immutable? You must implement some methods in
	 *            ImmutableQueenLocations.java
	 */
	private static void placeQueenInRow(FinishAccumulator<Integer> acc, ImmutableQueenLocations queenLocations) {
		doWork(1);
		async(() -> {
			int currRow = queenLocations.getRowCount();
			int boardSize = queenLocations.getBoardSize();
			for (int row = 0; row < boardSize; row ++ ) {  // iterate through columns
				if(queenLocations.isNextRowThreatFree(row)) {  // don't do anything unless we're threat free
					ImmutableQueenLocations secondQueenLocations = queenLocations.createNext(row); // create new board state
					int secondQueenBoardSize = secondQueenLocations.getBoardSize();
					if (currRow < (secondQueenBoardSize -1)) { // if you're within the board
						placeQueenInRow(acc, secondQueenLocations); 
					}
					else { //reached end of board and found solution!
						acc.put(1); // increment accumulator
					}
				}
			}
		});
	}

	/**
	 * This method should return the number of solutions. Like the sequential
	 * version, it will contain an initial call to placeQueenInRow. (Hint: What
	 * will you need to do differently because you are working in parallel?)
	 * 
	 * @param size
	 *            The size of chess board, i.e. the "n" in n-queens.
	 * @return The number of solutions found for the given size.
	 */
	public static int countSolutions(int size) throws SuspendableException {
		ImmutableQueenLocations queenLocations = new ImmutableQueenLocations(size); 
		FinishAccumulator<Integer> acc = newIntegerFinishAccumulator(NumberReductionOperator.SUM);// accumulator to keep track of solutions
		finish(register(acc), () -> {
				placeQueenInRow(acc, queenLocations);
		});
		return acc.get(); // return solution count
	} 
}
