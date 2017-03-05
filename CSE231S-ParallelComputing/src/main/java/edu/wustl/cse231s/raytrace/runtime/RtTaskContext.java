package edu.wustl.cse231s.raytrace.runtime;

import org.sunflow.core.IntersectionState;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;

import edu.wustl.cse231s.raytrace.RayTraceTaskContext;
import edu.wustl.cse231s.raytrace.Section;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class RtTaskContext implements RayTraceTaskContext {
	public RtTaskContext(RtContext context) {
		this.context = context;
	}

	@Override
	public void emit( Section section, Color[] colors, int id ) {
		int x = section.getXMin();
		int y = section.getYMin();
		int width = section.getWidth();
		int height = section.getHeight();
		this.context.getDisplay().imagePrepare(x, y, width, height, id);
		this.context.getDisplay().imageUpdate(x, y, width, height, colors);
	}
	
	@Override
	public Color castRay(int x, int y) {
		ShadingState state = this.context.getScene().getRadiance(intersectionState, x, this.context.getHeight() - 1 - y, 0.0, 0.0, 0.0, 0);
		return (state != null) ? state.getResult() : Color.BLACK;
	}

	private final RtContext context;
	private final IntersectionState intersectionState = new IntersectionState();
}
