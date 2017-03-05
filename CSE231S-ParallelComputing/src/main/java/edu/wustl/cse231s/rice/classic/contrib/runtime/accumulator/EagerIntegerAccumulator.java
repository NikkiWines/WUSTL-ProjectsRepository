package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.util.concurrent.atomic.AtomicInteger;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
// Based on the implementation by Jun Shirako (shirako@rice.edu) and Vivek Sarkar (vsarkar@rice.edu)
public class EagerIntegerAccumulator extends IntegerAccumulator {
	public EagerIntegerAccumulator(NumberReductionOperator operator) {
		super(operator);
		this.atom = new AtomicInteger(this.resultVal);
	}

	@Override
	protected void putAccessible(int value) {
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
		int atomValue = this.atom.get();
		switch (this.operator) {
		case SUM:
			this.resultVal += atomValue;
			break;
		case PRODUCT:
			this.resultVal *= atomValue;
			break;
		case MIN:
			if (atomValue < this.resultVal) {
				this.resultVal = atomValue;
			}
			break;
		case MAX:
			if (atomValue > this.resultVal) {
				this.resultVal = atomValue;
			}
			break;
		}

		this.atom.set(this.operator.getInitialIntValue());
	}

	private void atomicAdd(int val) {
		if (val == 0) {
			//pass
		} else {
			this.atom.addAndGet(val);
		}
	}

	private void atomicMul(int val) {
		if (val == 1) {
			return;
		} else {
			while (true) {
				int expect = this.atom.get();
				int update = expect * val;
				if (this.atom.compareAndSet(expect, update)) {
					return;
				}
			}
		}
	}

	private void atomicMin(int val) {
		while (true) {
			int expect = this.atom.get();
			if (val >= expect) {
				return;
			}

			if (this.atom.compareAndSet(expect, val)) {
				return;
			}
		}
	}

	private void atomicMax(int val) {
		while (true) {
			int expect = this.atom.get();
			if (val <= expect) {
				return;
			}

			if (this.atom.compareAndSet(expect, val)) {
				return;
			}
		}
	}

	private final AtomicInteger atom;
}
