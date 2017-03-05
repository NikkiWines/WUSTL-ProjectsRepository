package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.util.concurrent.atomic.AtomicLong;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class EagerDoubleAccumulator extends DoubleAccumulator {
	public EagerDoubleAccumulator(NumberReductionOperator operator) {
		super(operator);
		this.atom = new AtomicLong(Double.doubleToRawLongBits(this.resultVal));
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
		case MIN:
			this.atomicMin(value);
			break;
		case MAX:
			this.atomicMax(value);
			break;
		}
	}

	@Override
	protected void calculateAccum() {
		double value = Double.longBitsToDouble(this.atom.get());
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

		this.atom.set(Double.doubleToRawLongBits(this.operator.getInitialDoubleValue()));
	}

	private void atomicAdd(double val) {
		if (val == 0.0) {
			return;
		}

		while (true) {
			long expect = this.atom.get();
			double curr = Double.longBitsToDouble(expect);
			double next = curr + val;
			long update = Double.doubleToRawLongBits(next);
			if (this.atom.compareAndSet(expect, update)) {
				return;
			}
		}
	}

	private void atomicMul(double val) {
		if (val == 1.0) {
			return;
		}

		while (true) {
			long expect = this.atom.get();
			double curr = Double.longBitsToDouble(expect);
			double next = curr * val;
			long update = Double.doubleToRawLongBits(next);
			if (this.atom.compareAndSet(expect, update)) {
				return;
			}
		}
	}

	private void atomicMin(double val) {
		while (true) {
			long expect = this.atom.get();
			double curr = Double.longBitsToDouble(expect);
			if (val >= curr) {
				return;
			}
			long update = Double.doubleToRawLongBits(val);
			if (this.atom.compareAndSet(expect, update)) {
				return;
			}
		}
	}

	private void atomicMax(double val) {
		while (true) {
			long expect = this.atom.get();
			double curr = Double.longBitsToDouble(expect);
			if (val <= curr) {
				return;
			}
			long update = Double.doubleToRawLongBits(val);
			if (this.atom.compareAndSet(expect, update)) {
				return;
			}
		}
	}

	private final AtomicLong atom;
}
