package student.assignment.nqueens;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * 
 * An interface so both Immutable and MutableNQueens will work with NQueensUtils.
 */
public interface QueenLocations {
	int getColumnOfQueenInRow( int row );
}
