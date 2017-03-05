package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public abstract class ReducerAccumulator<T> extends Accumulator<T> {
	public ReducerAccumulator(Reducer<T> reducer) {
		this.reducer = reducer;
		this.resultVal = reducer.identity();
	}

	@Override
	public final T get() {
		if (this.isAccessible()) {
			//pass
		} else {
			this.checkOwnership(CheckOwnershipId.GET);
		}
		return this.resultVal;
	}

	protected abstract void putAccessible(T value);

	@Override
	public final void put(T value) {
		if (this.isAccessible()) {
			this.putAccessible(value);
		} else {
			this.checkOwnership(CheckOwnershipId.PUT);
			if (this.resultVal != null) {
				this.resultVal = this.reducer.reduce(this.resultVal, value);
			} else {
				this.resultVal = value;
			}
		}
	}

	protected final Reducer<T> reducer;
	protected T resultVal;
}
