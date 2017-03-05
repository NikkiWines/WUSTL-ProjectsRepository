package edu.wustl.cse231s.rice.classic.options;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class ChunkedOption {
	public ChunkedOption() {
		this.size = -1;
	}
	public ChunkedOption( int size ) {
		this.size = size;
	}
	public boolean isSizeDecidedBySystem() {
		return this.size == -1;
	}
	public int getSize() {
		return size;
	}
	private final int size;
}
