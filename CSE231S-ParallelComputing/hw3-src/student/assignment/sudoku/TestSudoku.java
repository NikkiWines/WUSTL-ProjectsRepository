package student.assignment.sudoku;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.wustl.cse231s.sudoku.SudokuUtils;
import edu.wustl.cse231s.sudoku.io.PuzzlesResource;
import edu.wustl.cse231s.sudoku.io.PuzzlesResourceUtils;
import edu.wustl.cse231s.timing.ImmutableTimer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestSudoku {
	public static void main(String[] args) throws Exception {
		final String orderingFormat = "%1$20s"; 
			for( PuzzlesResource puzzlesResource : PuzzlesResource.values() ) {
				List<String> givensList = PuzzlesResourceUtils.readGivens(puzzlesResource);

				launchHabaneroApp(() -> {

					Map<SquareSearchOrderings,List<Integer>> mapOrderingToIndicesToSkip = new HashMap<>();
					mapOrderingToIndicesToSkip.put(SquareSearchOrderings.ROW_MAJOR, puzzlesResource == PuzzlesResource.TOP95 ? Arrays.asList(0,4,7,22,35,40,70,94) : Collections.emptyList() );
					mapOrderingToIndicesToSkip.put(SquareSearchOrderings.FEWEST_OPTIONS_FIRST, Collections.emptyList());
	
					Map<SquareSearchOrderings,List<Long>> mapOrderingToNanos = new HashMap<>();
					
					int index = 0;
					for (String givens : givensList) {
						for( SquareSearchOrderings squareOrdering : SquareSearchOrderings.values()) {
							List<Integer> indicesToSkip = mapOrderingToIndicesToSkip.get(squareOrdering);
							long nanos;
							if( indicesToSkip.contains(index) ) {
								System.out.println( index + " " + String.format(orderingFormat, squareOrdering) + " SKIPPED" );
								nanos = -1L;
							} else {
								
								ImmutableSudokuPuzzle puzzle = new ImmutableSudokuPuzzle(givens);
								ImmutableTimer t = new ImmutableTimer(index + " " + String.format(orderingFormat, squareOrdering) + " " + puzzle);
								ImmutableSudokuPuzzle solution = SudokuSolution.solve(puzzle, squareOrdering);
								
								if( SudokuUtils.isValid(solution) ) {
									nanos = t.markAndPrintResults( solution );
								} else {
									throw new RuntimeException( solution.toString() );
								}
							}
							mapOrderingToNanos.compute(squareOrdering, (unusedSquareOrdering, list)->{
								if( list != null ) {
									//pass
								} else {
									list = new LinkedList<>();
								}
								list.add(nanos);
								return list;
							});
						}
						index ++;
						System.out.println();
						System.gc();
					}
					List<Long> rowMajorNanos = mapOrderingToNanos.get(SquareSearchOrderings.ROW_MAJOR);
					List<Long> fewestOptionsNanos = mapOrderingToNanos.get(SquareSearchOrderings.FEWEST_OPTIONS_FIRST);
					
					for( int i=0; i<rowMajorNanos.size(); i++ ) {
						long a = rowMajorNanos.get(i);
						long b = fewestOptionsNanos.get(i);
						if( a != -1L ) {
							System.out.printf("%d,%8.3f,%8.3f\n", i,a*0.000001, b*0.000001 );
						}
					}
				});
			
			}
			
	}
}
