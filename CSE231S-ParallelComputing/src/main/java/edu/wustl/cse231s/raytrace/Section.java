package edu.wustl.cse231s.raytrace;

import org.sunflow.image.Color;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class Section {
	public Section(int xMin, int yMin, int xMaxExclusive, int yMaxExclusive) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMaxExclusive = xMaxExclusive;
		this.yMaxExclusive = yMaxExclusive;
	}

	public int getXMin() {
		return this.xMin;
	}

	public int getYMin() {
		return this.yMin;
	}

	public int getXMaxExclusive() {
		return this.xMaxExclusive;
	}

	public int getYMaxExclusive() {
		return this.yMaxExclusive;
	}
	
	public int getWidth() {
		return this.xMaxExclusive-this.xMin;
	}

	public int getHeight() {
		return this.yMaxExclusive-this.yMin;
	}
	
	public void render( RayTraceTaskContext taskContext, Color[] colors, int id ) {
		int index = 0;
		for (int y = this.getYMin(); y < this.getYMaxExclusive(); y++) {
			for (int x = this.getXMin(); x < this.getXMaxExclusive(); x++) {
				colors[index] = taskContext.castRay(x, y);
				index ++;
			}
		}
		taskContext.emit(this, colors, id);
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append("[xMin:");
		sb.append(this.xMin);
		sb.append(";yMin:");
		sb.append(this.yMin);
		sb.append(";xMaxExclusive:");
		sb.append(this.xMaxExclusive);
		sb.append(";yMaxExclusive:");
		sb.append(this.yMaxExclusive);
		sb.append("]");
		return sb.toString();
	}

	private final int xMin;
	private final int yMin;
	private final int xMaxExclusive;
	private final int yMaxExclusive;
}
