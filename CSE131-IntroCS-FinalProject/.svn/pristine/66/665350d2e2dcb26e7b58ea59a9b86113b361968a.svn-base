package lab10;

import java.awt.Color;
import java.awt.Font;

import lab7.Point;
import lab7.Vector;
import sedgewick.StdDraw;

/**
 * The Blob is like a HorzBall or VertBall except that it moves to
 *   where the mouse currently is when draw() is called.
 *   
 * @author roncytron
 *
 */
public class Blob implements Anim {

	protected double x;
	protected double y;
	protected double radius;   // NOT final, so you can change it as it eats or vomits food
	protected Color color;
	protected double mass; 

	public Blob() {
		this.radius = 0.025;
		this.color  = Color.BLACK;
		this.x      = 0.5;
		this.y      = 0.5;
		this.mass	= 0;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getRadius() {
		return this.radius;
	}

	@Override
	public boolean isDone() {
		return false;
	}

	@Override
	public void draw() {
		//
		// Draw myself wherever the mouse happens to be now
		//
		x = StdDraw.mouseX();
		y = StdDraw.mouseY();
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x, y, radius);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		StdDraw.text(x, y, (int)mass+ "");
	
		
	}
	
}