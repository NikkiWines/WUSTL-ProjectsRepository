package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FinishAccumulatorException extends RuntimeException {
	public FinishAccumulatorException(String detail) {
		super(detail);
	}
}
