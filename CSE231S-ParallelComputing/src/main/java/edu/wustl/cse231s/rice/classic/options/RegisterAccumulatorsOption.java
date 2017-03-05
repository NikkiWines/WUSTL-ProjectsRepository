package edu.wustl.cse231s.rice.classic.options;

import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;

/**
 * @author Dennis Cosgrove (cosgroved@wustl.edu)
 */
public final class RegisterAccumulatorsOption {
	public RegisterAccumulatorsOption(FinishAccumulator<?> accumulatorA, FinishAccumulator<?>[] accumulatorBtoZ) {
		this.accumulators = new FinishAccumulator[1 + accumulatorBtoZ.length];
		this.accumulators[0] = accumulatorA;
		System.arraycopy(accumulatorBtoZ, 0, this.accumulators, 1, accumulatorBtoZ.length);
	}

	public FinishAccumulator<?>[] getAccumulators() {
		return accumulators;
	}

	private final FinishAccumulator<?>[] accumulators;
}
