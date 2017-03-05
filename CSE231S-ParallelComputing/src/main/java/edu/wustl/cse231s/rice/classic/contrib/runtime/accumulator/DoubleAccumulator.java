package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public abstract class DoubleAccumulator extends Accumulator<Double> {
	public DoubleAccumulator(NumberReductionOperator operator) {
		this.operator = operator;
		this.resultVal = this.operator.getInitialDoubleValue();
	}

	@Override
	public final Double get() {
		if (this.isAccessible()) {
			//pass
		} else {
			this.checkOwnership(CheckOwnershipId.GET);
		}
		return this.resultVal;
	}

	protected abstract void putAccessible(double value);

	@Override
	public final void put(Double v) {
		double value = v.doubleValue();
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
	protected double resultVal;
}
