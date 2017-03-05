package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.math.BigDecimal;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public abstract class BigDecimalAccumulator extends Accumulator<Double> {
	public BigDecimalAccumulator(NumberReductionOperator operator) {
		this.operator = operator;
		this.resultVal = new BigDecimal(this.operator.getInitialDoubleValue());
	}

	@Override
	public final Double get() {
		if (this.isAccessible()) {
			//pass
		} else {
			this.checkOwnership(CheckOwnershipId.GET);
		}
		return this.resultVal.doubleValue();
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
				this.resultVal = this.resultVal.add(new BigDecimal(value));
				break;
			case PRODUCT:
				this.resultVal = this.resultVal.multiply(new BigDecimal(value));
				break;
			}
		}
	}

	protected final NumberReductionOperator operator;
	protected BigDecimal resultVal;

}
