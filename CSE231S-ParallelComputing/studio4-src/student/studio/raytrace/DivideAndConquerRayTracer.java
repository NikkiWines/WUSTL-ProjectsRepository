package student.studio.raytrace;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.net.URL;

import org.sunflow.image.Color;

import edu.wustl.cse231s.raytrace.RayTraceContext;
import edu.wustl.cse231s.raytrace.RayTraceTaskContext;
import edu.wustl.cse231s.raytrace.RayTraceUtils;
import edu.wustl.cse231s.raytrace.RayTracer;
import edu.wustl.cse231s.raytrace.Section;
import instructor.demo.raytrace.SplitFourWayRayTracer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class DivideAndConquerRayTracer implements RayTracer {
	// invoke this method when you get to the base case
	private void renderSection(RayTraceContext context, int xMin, int yMin, int xMax, int yMax) {
		int xLength = xMax - xMin;
		int yLength = yMax - yMin;
		int arrayLength = xLength * yLength;
		Color[] colors = new Color[arrayLength];
		Section section = new Section(xMin, yMin, xMax, yMax);
		section.render(context.createTaskContext(), colors, RayTraceTaskContext.UNKNOWN_TASK_ID);
	}

	private void rayTraceKernel(RayTraceContext context, int xMin, int yMin, int xMax, int yMax, int areaThreshold) {
		int xLength = xMax - xMin; 
		int yLength = yMax - yMin;

		int xMid = xMin + xLength/2; 
		int yMid = yMin + yLength/2;

		if (xLength*yLength > areaThreshold) { 
			async(() -> { // split into quadrants
				rayTraceKernel(context, xMin, yMin, xMid, yMid, areaThreshold);
				rayTraceKernel(context, xMin, yMid, xMid, yMax, areaThreshold);
				rayTraceKernel(context, xMid, yMin, xMax, yMid, areaThreshold);
				rayTraceKernel(context, xMid, yMid, xMax, yMax, areaThreshold);

			});
		} 
		else { 
			renderSection(context, xMin, yMin, xMax, yMax);
		}
	}

	@Override
	public void rayTrace(RayTraceContext context) {
		final int AREA_THRESHOLD = 400;
		launchHabaneroApp(() -> {
			finish(() -> {
				rayTraceKernel(context, 0, 0, context.getWidth(), context.getHeight(), AREA_THRESHOLD);
			});
		});
	}

	public static void main(String[] args) {
		URL url = SplitFourWayRayTracer.class.getResource("bunny.sc");
		RayTraceUtils.launch(new DivideAndConquerRayTracer(), url);
	}
}
