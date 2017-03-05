package edu.wustl.cse231s.raytrace.runtime;

import org.sunflow.core.Display;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;

import edu.wustl.cse231s.raytrace.RayTracer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class RtImageSampler implements ImageSampler {

	public RtImageSampler(RayTracer rayTracer) {
		this.rayTracer = rayTracer;
	}

	@Override
	public boolean prepare(Options options, Scene scene, int w, int h) {
		this.options = options;
		this.scene = scene;
		this.w = w;
		this.h = h;
		return true;
	}

	@Override
	public void render(Display display) {
		display.imageBegin(this.w, this.h, 1);
		try {
			RtContext context = new RtContext(this.options, this.scene, this.w, this.h, display);
			this.rayTracer.rayTrace(context);
		} finally {
			display.imageEnd();
		}
	}

	private final RayTracer rayTracer;
	private Options options;
	private Scene scene;
	private int w;
	private int h;
};
