package edu.wustl.cse231s.rice.classic.options;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class SingleOption {
	public SingleOption( Runnable runnable ) {
		this.runnable = runnable;
	}
	public Runnable getRunnable() {
		return this.runnable;
	}
	private final Runnable runnable;
}
