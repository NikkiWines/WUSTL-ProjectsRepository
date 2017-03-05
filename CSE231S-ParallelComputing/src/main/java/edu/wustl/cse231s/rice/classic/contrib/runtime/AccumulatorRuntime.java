package edu.wustl.cse231s.rice.classic.contrib.runtime;


import edu.rice.hj.api.HjSuspendable;
import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.rice.classic.HabaneroClassic;
import edu.wustl.cse231s.rice.classic.contrib.api.ContentionLevel;
import edu.wustl.cse231s.rice.classic.contrib.api.DoubleAccumulationDeterminismPolicy;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;
import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.AccumulatorManager;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.EagerBigDecimalAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.EagerDoubleAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.EagerIntegerAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.EagerReducerAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.LazyBigDecimalAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.LazyDoubleAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.LazyIntegerAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator.LazyReducerAccumulator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum AccumulatorRuntime {
	;
	public static FinishAccumulator<Integer> newIntegerFinishAccumulator(NumberReductionOperator operator, ContentionLevel contentionLevel) {
		if (contentionLevel == ContentionLevel.HIGH) {
			return new LazyIntegerAccumulator(operator);
		} else {
			return new EagerIntegerAccumulator(operator);
		}
	}

	public static FinishAccumulator<Double> newDoubleFinishAccumulator(NumberReductionOperator operator, ContentionLevel contentionLevel, DoubleAccumulationDeterminismPolicy determinismPolicy) {
		if( determinismPolicy == DoubleAccumulationDeterminismPolicy.DETERMINISTIC ){
			switch( operator ) {
			case SUM:
			case PRODUCT:
				if (contentionLevel == ContentionLevel.HIGH) {
					return new LazyBigDecimalAccumulator(operator);
				} else {
					return new EagerBigDecimalAccumulator(operator);
				}
			}
		}
		if (contentionLevel == ContentionLevel.HIGH) {
			return new LazyDoubleAccumulator(operator);
		} else {
			return new EagerDoubleAccumulator(operator);
		}
	}

	public static <T> FinishAccumulator<T> newReducerFinishAccumulator(Reducer<T> reducer, ContentionLevel contentionLevel) {
		if (contentionLevel == ContentionLevel.HIGH) {
			return new LazyReducerAccumulator<>(reducer);
		} else {
			return new EagerReducerAccumulator<>(reducer);
		}
	}

	public static void finish(FinishAccumulator<?>[] accs, HjSuspendable body) throws SuspendableException {
		AccumulatorManager accumulatorManager = new AccumulatorManager(accs);
		accumulatorManager.openFinishAccumulators();
		try {
			HabaneroClassic.finish(body);
		} finally {
			accumulatorManager.closeFinishAccumulators();
		}
	}
}
