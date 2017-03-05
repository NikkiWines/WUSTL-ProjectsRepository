package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
//Based on "Finish Accumulators: a Deterministic Reduction Construct for Dynamic Task Parallelism"
//Jun Shirako, Vincent Cave', Jisheng Zhao, and Vivek Sarkar
//https://wiki.rice.edu/confluence/download/attachments/4425835/wodet2013-shirako-sarkar.pdf
public class LazyIntegerAccumulator extends IntegerAccumulator {
	public LazyIntegerAccumulator(NumberReductionOperator operator) {
		super(operator);
	}

	@Override
	/*package-private*/ void open() {
		super.open();
		int arrayLength = LazyUtils.getPoolSize();
		this.array = new int[arrayLength];
		for (int i = 0; i < this.array.length; i++) {
			this.array[i] = this.operator.getInitialIntValue();
		}
	}
	@Override
	/*package-private*/ void close() {
		super.close();
		this.array = null;
	}

	@Override
	protected void putAccessible(int value) {
		int index = LazyUtils.getPoolIndex();
		switch (this.operator) {
		case SUM:
			this.array[index] += value;
			break;
		case PRODUCT:
			this.array[index] *= value;
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

	@Override
	protected void calculateAccum() {
		switch (this.operator) {
		case SUM:
			for (int value : this.array) {
				this.resultVal += value;
			}
			break;
		case PRODUCT:
			for (int value : this.array) {
				this.resultVal *= value;
			}
			break;
		case MIN:
			for (int value : this.array) {
				if (value < this.resultVal) {
					this.resultVal = value;
				}
			}
			break;
		case MAX:
			for (int value : this.array) {
				if (value > this.resultVal) {
					this.resultVal = value;
				}
			}
			break;
		}
	}

	private int[] array;
}
