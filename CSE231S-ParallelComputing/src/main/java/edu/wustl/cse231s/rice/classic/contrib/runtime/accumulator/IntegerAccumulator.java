package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
// Based on the implementation by Jun Shirako (shirako@rice.edu) and Vivek Sarkar (vsarkar@rice.edu)
public abstract class IntegerAccumulator extends Accumulator<Integer> {
	public IntegerAccumulator(NumberReductionOperator operator) {
		this.operator = operator;
		this.resultVal = this.operator.getInitialIntValue();
	}

	@Override
	public final Integer get() {
		if (this.isAccessible()) {
			//pass
		} else {
			this.checkOwnership(CheckOwnershipId.GET);
		}
		return this.resultVal;
	}

	protected abstract void putAccessible(int value);

	@Override
	public final void put(Integer v) {
		int value = v.intValue();
		if (this.isAccessible()) {
			this.putAccessible(value);
		} else {
			this.checkOwnership(CheckOwnershipId.PUT);
			switch (this.operator) {
			case SUM:
				this.resultVal += value;
				break;
			case PRODUCT:
				this.resultVal *= value;
				break;
			case MIN:
				if (value < this.resultVal) {
					this.resultVal = value;
				}
				break;
			case MAX:
				if (value > this.resultVal) {
					this.resultVal = value;
				}
				break;
			}
		}
	}

	protected final NumberReductionOperator operator;
	protected int resultVal;
}
