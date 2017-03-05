package edu.wustl.cse231s.raytrace;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface RayTraceContext {
	int getWidth();
	int getHeight();
	RayTraceTaskContext createTaskContext();
}
