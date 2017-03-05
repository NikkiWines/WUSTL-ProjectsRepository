package student.solution.mapreduce.apps.wordcount;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.newIntegerFinishAccumulator;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.FinishAccumulatorBasedReducer;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class WordCountReducer implements FinishAccumulatorBasedReducer<Integer> {
	@Override
	public FinishAccumulator<Integer> createAccumulator() {
		FinishAccumulator<Integer> acc = newIntegerFinishAccumulator(NumberReductionOperator.SUM);
		return acc;
	}
}
