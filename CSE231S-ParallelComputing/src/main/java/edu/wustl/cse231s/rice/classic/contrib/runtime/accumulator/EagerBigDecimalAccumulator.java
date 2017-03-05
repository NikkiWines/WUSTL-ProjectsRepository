package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class EagerBigDecimalAccumulator extends BigDecimalAccumulator {
	public EagerBigDecimalAccumulator(NumberReductionOperator operator) {
		super(operator);
		this.atom = new AtomicReference<>(this.resultVal);
	}

	@Override
	protected void putAccessible(double value) {
		switch (this.operator) {
		case SUM:
			this.atomicAdd(value);
			break;
		case PRODUCT:
			this.atomicMul(value);
			break;
		}
	}

	@Override
	protected void calculateAccum() {
		BigDecimal value = this.atom.get();
		switch (this.operator) {
		case SUM:
			this.resultVal = this.resultVal.add(value);
			break;
		case PRODUCT:
			this.resultVal = this.resultVal.multiply(value);
			break;
		}

		this.atom.set(new BigDecimal(this.operator.getInitialDoubleValue()));
	}

	private void atomicAdd(double val) {
		if (val == 0.0) {
			return;
		}

		BigDecimal v = new BigDecimal(val);
		while (true) {
			BigDecimal expect = this.atom.get();
			BigDecimal update = expect.add(v);
			if (this.atom.compareAndSet(expect, update)) {
				return;
			}
		}
	}

	private void atomicMul(double val) {
		if (val == 1.0) {
			return;
		}

		BigDecimal v = new BigDecimal(val);
		while (true) {
			BigDecimal expect = this.atom.get();
			BigDecimal update = expect.multiply(v);
			if (this.atom.compareAndSet(expect, update)) {
				return;
			}
		}
	}

	private final AtomicReference<BigDecimal> atom;
}
