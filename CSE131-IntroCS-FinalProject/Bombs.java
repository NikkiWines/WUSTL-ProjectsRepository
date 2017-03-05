package lab10;

import java.awt.Color;

import sedgewick.StdDraw;

public class Bombs implements Anim {

	protected double x;
	protected double y;
	protected  double radius;   
	protected Color color;
	protected boolean dead;
	protected double vx;
	protected double vy;
	
	
	public Bombs(double vx, double vy) {
		this.radius = 0.025;
		this.color  = Color.RED;
		this.x      = Math.random();
		this.y      = Math.random();
		this.vx = vx;
		this.vy = vy;
		
	}

	@Override
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x, y, radius);
		this.x = this.x + this.vx;
		this.y = this.y + this.vy;
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
	
	

	public static void main(String[] args) {


	}

}
