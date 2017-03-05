package student.studio.quicksort;

import edu.rice.hj.api.HjPoint;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class PartitionLocation {
	public PartitionLocation( HjPoint pt ) {
		this.leftSidesUpperInclusive = pt.get(1);
		this.rightSidesLowerInclusive = pt.get(0);
	}
	
	public int getLeftSidesUpperInclusive() {
		return this.leftSidesUpperInclusive;
	}
	
	public int getRightSidesLowerInclusive() {
		return this.rightSidesLowerInclusive;
	}
	
	private final int leftSidesUpperInclusive;
	private final int rightSidesLowerInclusive;
}
