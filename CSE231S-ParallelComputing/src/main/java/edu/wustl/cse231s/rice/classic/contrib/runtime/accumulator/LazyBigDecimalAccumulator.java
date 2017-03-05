package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.math.BigDecimal;

import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
//Based on "Finish Accumulators: a Deterministic Reduction Construct for Dynamic Task Parallelism"
//Jun Shirako, Vincent Cave', Jisheng Zhao, and Vivek Sarkar
//https://wiki.rice.edu/confluence/download/attachments/4425835/wodet2013-shirako-sarkar.pdf
public class LazyBigDecimalAccumulator extends BigDecimalAccumulator {
	public LazyBigDecimalAccumulator(NumberReductionOperator operator) {
		super(operator);
	}

	@Override
	/*package-private*/ void open() {
		super.open();
		int arrayLength = LazyUtils.getPoolSize();
		this.array = new BigDecimal[arrayLength];
	}

	@Override
	/*package-private*/ void close() {
		super.close();
		this.array = null;
	}

	@Override
	protected void putAccessible(double value) {
		int index = LazyUtils.getPoolIndex();
		BigDecimal val = new BigDecimal(value);
		if (this.array[index] != null) {
			switch (this.operator) {
			case SUM:
				this.array[index] = this.array[index].add(val);
				break;
			case PRODUCT:
				this.array[index] = this.array[index].multiply(val);
				break;
			}
		} else {
			this.array[index] = val;
		}
	}

	@Override
	protected void calculateAccum() {
		for (BigDecimal value : this.array) {
			if (value != null) {
				switch (this.operator) {
				case SUM:
					this.resultVal = this.resultVal.add(value);
					break;
				case PRODUCT:
					this.resultVal = this.resultVal.multiply(value);
					break;
				}
			}
		}
	}

	private BigDecimal[] array;
}
