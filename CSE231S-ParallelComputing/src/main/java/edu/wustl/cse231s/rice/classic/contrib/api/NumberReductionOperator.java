package edu.wustl.cse231s.rice.classic.contrib.api;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
//Based on the implementation by Jun Shirako (shirako@rice.edu) and Vivek Sarkar (vsarkar@rice.edu)
public enum NumberReductionOperator {
	SUM(0, 0.0), 
	PRODUCT(1, 1.0), 
	MIN(Integer.MAX_VALUE, Double.MAX_VALUE), 
	MAX(Integer.MIN_VALUE, -Double.MAX_VALUE);

	private NumberReductionOperator(int initialIntValue, double initialDoubleValue) {
		this.initialIntValue = initialIntValue;
		this.initialDoubleValue = initialDoubleValue;
	}

	public int getInitialIntValue() {
		return this.initialIntValue;
	}

	public double getInitialDoubleValue() {
		return this.initialDoubleValue;
	}

	private final int initialIntValue;
	private final double initialDoubleValue;
}
