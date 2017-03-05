package student.studio.mapreduce.apps.cards;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.newIntegerFinishAccumulator;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.FinishAccumulatorBasedReducer;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class NumericValueReducer implements FinishAccumulatorBasedReducer<Integer> {
	@Override
	public FinishAccumulator<Integer> createAccumulator() {
		return newIntegerFinishAccumulator(NumberReductionOperator.SUM);
		}
}
