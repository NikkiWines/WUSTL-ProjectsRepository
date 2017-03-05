package student.assignment.nqueens;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.dumpStatistics;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.HashMap;
import java.util.Map;

import edu.rice.hj.runtime.config.HjSystemProperty;

public class TestNQueens {
	public static void main(String[] args) {
		Map<Integer, Integer> mapQueenCountToSolutionCount = new HashMap<>();
		mapQueenCountToSolutionCount.put(2, 0);
		mapQueenCountToSolutionCount.put(3, 0);
		mapQueenCountToSolutionCount.put(4, 2);
		mapQueenCountToSolutionCount.put(5, 10);
		mapQueenCountToSolutionCount.put(6, 4);
		mapQueenCountToSolutionCount.put(7, 40);
		mapQueenCountToSolutionCount.put(8, 92);
		mapQueenCountToSolutionCount.put(9, 352);
		mapQueenCountToSolutionCount.put(10, 724);
		mapQueenCountToSolutionCount.put(11, 2680);
		int queenCount = 8;
		int solutionCount = mapQueenCountToSolutionCount.get(queenCount);

		HjSystemProperty.abstractMetrics.set(true);
		launchHabaneroApp(() -> {
			int seq_solutionCount = SequentialNQueens.countSolutions(queenCount);
			if (seq_solutionCount != solutionCount) {
				throw new RuntimeException(seq_solutionCount + " != " + solutionCount);
			} else {
				System.out.println(seq_solutionCount);
			}
		}, () -> {
			dumpStatistics();
		});

		launchHabaneroApp(() -> {
			int par_solutionCount = ParallelNQueens.countSolutions(queenCount);
			if (par_solutionCount != solutionCount) {
				throw new RuntimeException(par_solutionCount + " != " + solutionCount);
			} else {
				System.out.println(par_solutionCount);
			}
		}, () -> {
			dumpStatistics();
		});
	}
}
