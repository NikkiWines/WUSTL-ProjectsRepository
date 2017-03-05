package lab10;

import java.awt.Color;

import sedgewick.StdDraw;

/**
 * The HorzBall moves from the left to the right.
 * It dies (is done) halfway across at 0.5.
 * It does not reason about real time:  it always moves .01 to the
 *    right, no matter the showPauseTime between calls to draw()
 *    
 * @author roncytron
 *
 */
public class HorzBall implements Anim {
	
	private double x, y;   // current location of this ball

	public HorzBall(double y) {
		this.x = 0;  // start at left
		this.y = y;
	}

	@Override
	public void draw() {
		StdDraw.setPenColor(Color.CYAN);
		StdDraw.filledCircle(x, y, .08);
		// now move to the right a bit;
		this.x = this.x + .01;
	}

	@Override
	public boolean isDone() {
		if (this.x >= 0.5) { // halfway across, we are done
			return true;
		}
		else {
			return false;
		}
	}

}
