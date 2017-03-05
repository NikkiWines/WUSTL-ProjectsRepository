package edu.wustl.cse231s.timing;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class ImmutableTimer {
	public ImmutableTimer( String prefix ) {
		this.prefix = prefix;
		this.t0 = System.nanoTime();
	}
	
	public long markAndPrintResults( Object... args) {
		long dt = System.nanoTime() - this.t0;
		System.out.printf("%s; time:%8.3f msec;", this.prefix, dt*0.000001);
		for( Object arg : args ) {
			System.out.print(arg);
			System.out.print(" ");
		}
		System.out.println();
		return dt;
	}
	
	private final String prefix;
	private final long t0;
}
