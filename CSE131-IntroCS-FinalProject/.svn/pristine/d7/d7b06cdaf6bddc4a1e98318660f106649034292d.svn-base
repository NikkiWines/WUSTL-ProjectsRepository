package lab10;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import sedgewick.StdDraw;

public class Food implements Anim {
	
	protected double mass; 
	protected double radius;
	protected boolean dead;
	protected double x, y; // current location of this ball
	protected Color color;
	protected String value;

	public Food() {
		this.x = Math.random();  // start at random
		this.y = Math.random();  // start at random
		this.color= genRandomColor();
		this.mass= 10 + (int) (Math.random()*40);
		this.radius= ((double)(this.mass))/500;
		this.value= (int)(this.mass) + ""; 
	}

	@Override
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x, y, radius);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		StdDraw.text(x, y, value);
	}
		

	public static void main(String[] args) {
		// FIXME Auto-generated method stub

	}
	
	private static Color genRandomColor() {
		Random r = new Random();
		int red = r.nextInt(200);
		int green = r.nextInt(256);
		int blue = r.nextInt(256);
		return new Color(red, green, blue);
	}


	@Override
	public boolean isDone() {
		return this.dead;
	}
	
	public void killMe(){
		this.dead = true;
	}
	
	public boolean intersect(Blob b) { 
		double dx= Math.pow(this.x - StdDraw.mouseX(), 2);
		double dy= Math.pow(this.y - StdDraw.mouseY(), 2);
		double distance= Math.sqrt(dx + dy);
		if (distance < (this.radius + b.radius)) { 
			return true;
		}
		else {
			return false;
		}
	}
	

}
