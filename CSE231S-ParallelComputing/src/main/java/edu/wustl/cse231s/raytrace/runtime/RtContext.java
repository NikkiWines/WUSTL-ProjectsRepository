package edu.wustl.cse231s.raytrace.runtime;

import org.sunflow.core.Display;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;

import edu.wustl.cse231s.raytrace.RayTraceContext;
import edu.wustl.cse231s.raytrace.RayTraceTaskContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class RtContext implements RayTraceContext {
	public RtContext(Options options, Scene scene, int width, int height, Display display) {
		this.options = options;
		this.scene = scene;
		this.display = display;
		this.width = width;
		this.height = height;
	}

	/*package-private*/ Options getOptions() {
		return this.options;
	}
	/*package-private*/ Scene getScene() {
		return this.scene;
	}
	/*package-private*/ Display getDisplay() {
		return this.display;
	}
	
	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public RayTraceTaskContext createTaskContext() {
		return new RtTaskContext(this);
	}

	private final Options options;
	private final Scene scene;
	private final Display display;
	private final int width;
	private final int height;
}
