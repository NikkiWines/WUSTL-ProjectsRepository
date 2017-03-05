package edu.wustl.cse231s;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class NotYetImplementedException extends RuntimeException {
	public NotYetImplementedException() {
		super();
	}
	public NotYetImplementedException(String detail) {
		super(detail);
	}
}
