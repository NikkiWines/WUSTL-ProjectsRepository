package edu.wustl.cse231s.raytrace;

import org.sunflow.image.Color;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface RayTraceTaskContext {
	public static final int UNKNOWN_TASK_ID = -1;
	Color castRay( int x, int y );
	void emit( Section section, Color[] colors, int id );
}
