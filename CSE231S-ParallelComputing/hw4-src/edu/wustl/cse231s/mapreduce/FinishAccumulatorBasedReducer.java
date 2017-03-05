package edu.wustl.cse231s.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.register;

import java.util.List;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import instructor.solution.mapreduce.InstructorReduceSolution;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface FinishAccumulatorBasedReducer<V> extends Reducer<V> {
	FinishAccumulator<V> createAccumulator();
	default V reduce( List<V> list ) throws SuspendableException {
		FinishAccumulator<V> accumulator = this.createAccumulator();
		final boolean IS_READY_TO_TEST_MY_REDUCE = true; 
		if (IS_READY_TO_TEST_MY_REDUCE) {
			finish(register(accumulator), () -> {
				for (V value : list) { 
					async(() -> {
						accumulator.put(value);
					});
				}

			}); 
		} else {
			InstructorReduceSolution.reduce(list, accumulator);
		}
		return accumulator.get();
	};
}
