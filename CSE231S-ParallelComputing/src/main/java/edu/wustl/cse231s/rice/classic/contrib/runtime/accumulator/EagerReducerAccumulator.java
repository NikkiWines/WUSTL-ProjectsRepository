package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
// Based on "Finish Accumulators: a Deterministic Reduction Construct for Dynamic Task Parallelism"
// Jun Shirako, Vincent Cave', Jisheng Zhao, and Vivek Sarkar
// https://wiki.rice.edu/confluence/download/attachments/4425835/wodet2013-shirako-sarkar.pdf
public class EagerReducerAccumulator<T> extends ReducerAccumulator<T> {
	public EagerReducerAccumulator(Reducer<T> reducer) {
		super(reducer);
		this.transitionState = reducer.identity();
	}

	@Override
	protected void putAccessible(T value) {
		lock.lock();
		try {
			this.transitionState = this.reducer.reduce(this.transitionState, value);
		} finally {
			lock.unlock();
		}
	}

	@Override
	protected void calculateAccum() {
		this.resultVal = this.reducer.reduce(this.resultVal, this.transitionState);
		this.transitionState = this.reducer.identity();
	}

	//TODO: investigate.
	private final Lock lock = new ReentrantLock();

	//TODO: rename?
	private T transitionState;
}
