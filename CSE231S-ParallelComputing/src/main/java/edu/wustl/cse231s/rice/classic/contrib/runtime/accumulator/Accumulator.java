package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import edu.rice.hj.runtime.baseruntime.HabaneroActivity;
import edu.rice.hj.runtime.forkjoin.ForkJoinRuntime;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
//Based on the implementation by Jun Shirako (shirako@rice.edu) and Vivek Sarkar (vsarkar@rice.edu)
public abstract class Accumulator<T> implements FinishAccumulator<T> {
	protected static enum CheckOwnershipId {
		REGISTRATION("Registration by non-parent task is not allowed."),
		GET("Send (put) by non-parent task is not allowed outside registered finish scope."),
		PUT("Result (get) by non-parent task is not allowed outside registered finish scope.");
		private CheckOwnershipId(String message) {
			this.message = message;
		}

		public String getMessage() {
			return this.message;
		}
		
		private final String message;
	};
	
	public Accumulator() {
		this.parentTask = ForkJoinRuntime.currentHabaneroActivity();
	}
	
	/*package-private*/void open() {
		this.setAccessible(true);
	}
	/*package-private*/void close() {
		this.calculateAccum();
		this.setAccessible(false);
	}
	
	protected boolean isAccessible() {
		return this.isAccessible;
	}
	
	private void setAccessible(boolean isAccessible) {
        if (isAccessible) {
            // Registration to finish; needs ownership check
            this.checkOwnership(CheckOwnershipId.REGISTRATION);

            if (this.isAccessible) {
                throw new FinishAccumulatorException("Nested (double) registration is not allowed.");
            }
        }
        this.isAccessible = isAccessible;
	}
	
	protected abstract void calculateAccum();
	
    protected void checkOwnership(CheckOwnershipId id) {
    	final HabaneroActivity task = ForkJoinRuntime.currentHabaneroActivity();
        if (task != parentTask) {
            throw new FinishAccumulatorException(id.getMessage());
        }
    }

    private final HabaneroActivity parentTask;
	private boolean isAccessible;
}
