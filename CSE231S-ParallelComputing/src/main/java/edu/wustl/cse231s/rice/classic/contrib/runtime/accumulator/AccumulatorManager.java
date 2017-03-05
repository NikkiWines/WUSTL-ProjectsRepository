package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.util.Arrays;
import java.util.List;

import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
//Based on the implementation by Jun Shirako (shirako@rice.edu) and Vivek Sarkar (vsarkar@rice.edu)
public final class AccumulatorManager {
	public AccumulatorManager(FinishAccumulator<?>[] finishAccumulators) {
		this.accumulators = Arrays.asList(finishAccumulators);
	}

	public void openFinishAccumulators() {
		for (FinishAccumulator<?> a : this.accumulators) {
			Accumulator<?> acc = (Accumulator<?>) a;
			acc.open();
		}
	}

	public void closeFinishAccumulators() {
		for (FinishAccumulator<?> a : this.accumulators) {
			Accumulator<?> acc = (Accumulator<?>) a;
			acc.close();
		}
	}

	private final List<FinishAccumulator<?>> accumulators;
}
