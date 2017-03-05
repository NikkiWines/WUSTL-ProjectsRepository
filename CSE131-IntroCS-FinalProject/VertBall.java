package lab10;

import java.awt.Color;
import java.util.Random;

import sedgewick.StdDraw;

public class VertBall implements Anim {
	
	private double x, y;   // current location of this ball
	private final Color color;
	private final int msecs, showPauseTime;

	/**
	 * 
	 * @param x  horizontal placement of this VertBall
	 * @param msecs number of milliseconds to move from the bottom to top of the screen
	 * @param showPauseTime number of milliseconds between calls to draw()
	 */
	public VertBall(double x, int msecs, int showPauseTime) {
		this.x = x;
		this.y = 0;  // start at bottom
		this.color = genRandomColor();
		this.msecs = msecs;
		this.showPauseTime = showPauseTime;
	}

	@Override
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x, y, .08);
		//
		// How many calls before we've sepent msecs time?
		//
		int numCallsToThisMethod = msecs / showPauseTime;
		//
		// How much should we move so that by the end of msecs,
		//    we have moved 1.0 unit?
		//
		double movePerCall = 1.0 / numCallsToThisMethod;
		//
		// move that much
		//
		this.y = this.y + movePerCall;
		if (this.y >= 1.0) 
			this.y = 0.0;  // start over at bottom
	}

	@Override
	public boolean isDone() {
		return false; // lives forever
	}
	
	/**
	 * Generates a random Color
	 * @return a Color with a random amount of red, green, blue
	 */
	private static Color genRandomColor() {
		Random r = new Random();
		int red = r.nextInt(200);
		int green = r.nextInt(256);
		int blue = r.nextInt(256);
		return new Color(red, green, blue);
	}

}
