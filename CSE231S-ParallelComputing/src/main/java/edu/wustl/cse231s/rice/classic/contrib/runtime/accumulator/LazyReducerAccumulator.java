package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.lang.reflect.Array;

import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
// Based on "Finish Accumulators: a Deterministic Reduction Construct for
// Dynamic Task Parallelism"
// Jun Shirako, Vincent Cave', Jisheng Zhao, and Vivek Sarkar
// https://wiki.rice.edu/confluence/download/attachments/4425835/wodet2013-shirako-sarkar.pdf
public class LazyReducerAccumulator<T> extends ReducerAccumulator<T> {
	public LazyReducerAccumulator(Reducer<T> reducer) {
		super(reducer);
	}

	@Override
	/* package-private */ void open() {
		super.open();
		int arrayLength = LazyUtils.getPoolSize();
		this.array = (T[]) Array.newInstance(this.reducer.getReduceClass(), arrayLength);
		for (int i = 0; i < array.length; i++) {
			array[i] = this.reducer.identity();
		}
	}

	@Override
	/* package-private */ void close() {
		super.close();
		this.array = null;
	}

	@Override
	protected void putAccessible(T value) {
		int index = LazyUtils.getPoolIndex();
		if (array[index] != null) {
			array[index] = this.reducer.reduce(array[index], value);
		} else {
			array[index] = value;
		}
	}

	@Override
	protected void calculateAccum() {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				if (this.resultVal != null) {
					this.resultVal = this.reducer.reduce(this.resultVal, array[i]);
				} else {
					this.resultVal = array[i];
				}
			}
		}
	}

	private T[] array;
}
