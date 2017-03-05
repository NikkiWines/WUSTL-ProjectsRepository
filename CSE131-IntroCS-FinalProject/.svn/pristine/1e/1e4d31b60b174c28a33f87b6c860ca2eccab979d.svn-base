package lab10;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sedgewick.StdDraw;

public class Game {

	public static void main(String[] args) {
		final int showPauseTime = 20; // milliseconds
		//
		// Want to be able to continually iterate through a list of
		//    things to draw on the screen.  Each of those things
		//    should be able to draw itself, and also tell me when
		//    it is done.
		//
		List<Anim> scene1 = new LinkedList<Anim>();
		FadingWigglingMessage welcome = new FadingWigglingMessage(showPauseTime, "Welcome to agar!", 5000, 0.005, 0.5, 0.5);
		FadingWigglingMessage start = new FadingWigglingMessage(showPauseTime, "Click to Start Level 1", 5000, 0.005, 0.5, 0.4);
		scene1.add(welcome);
		scene1.add(start);
		//
		// The HorzBalls and VertBalls are just for show.  In your game you
		//   won't need them
		//
		HorzBall hball = new HorzBall(0.3);
		VertBall vball = new VertBall(0.2, 3000, showPauseTime);
		Blob     blob  = new Blob(); // you can use WigglyBlob if you want
		blob.setColor(Color.ORANGE);
		//		scene1.add(hball);
		scene1.add(vball);
		for (int i=0; i < 11; ++i) {
			scene1.add(new VertBall(i/10.0, 3000, showPauseTime));
		}
		scene1.add(blob);
		List<Anim> scene2 = new LinkedList<Anim>();
		LinkedList<Food> foodToEat= new LinkedList<Food>();
		LinkedList<Bombs> bomb= new LinkedList<Bombs>();

		//
		// Stay on this level until scene1 has nothing to animate
		//
		while (!scene1.isEmpty()) {
			StdDraw.clear();
			// draw everything
			for (Anim a : scene1) {
				a.draw();
			}
			if (StdDraw.mousePressed()) {
				while (StdDraw.mousePressed()) {
					StdDraw.pause(50);;
				}
				scene1.clear();
			}
			Iterator<Anim> iter = scene1.iterator();
			while (iter.hasNext()) {
				Anim a = iter.next();
				if (a.isDone()) {
					iter.remove();   // removes the current object safely
				}
			}
			StdDraw.show(showPauseTime);
		}
		StdDraw.clear();
		StdDraw.show(); 

		int k=1000;
		while (blob.mass < k) { 
			while ( foodToEat.size() < 5) {
				Food f = new Food();
				foodToEat.add(f);
				scene2.add(f);
			}
			scene2.add(blob);
			for (Anim a: scene2) {
				a.draw();
			}
			StdDraw.show(10);
			StdDraw.clear();

			Iterator<Food> iter2= foodToEat.iterator();
			while (iter2.hasNext()) { 
				Food f = iter2.next();
				if (f.intersect(blob)) {
					iter2.remove();
					f.killMe();
					blob.mass= blob.mass + f.mass; 
					blob.radius = blob.radius + (Math.pow(blob.mass, 0.0005)-1) ;
				}
			}

			Iterator<Anim> iter3= scene2.iterator();
			while(iter3.hasNext()) {
				Anim a = iter3.next();
				if (a.isDone()) {
					iter3.remove();
				}	
			}
		}
		StdDraw.clear();
		StdDraw.show();

//		//level 2
//
//
		List<Anim> scene3 = new LinkedList<Anim>();
		FadingWigglingMessage level2 = new FadingWigglingMessage(showPauseTime, "Level Two", 5000, 0.01, 0.5, 0.5);
		FadingWigglingMessage bombsName = new FadingWigglingMessage(showPauseTime, "Avoid the Bombs", 5000, 0.01, 0.5, 0.4);
		FadingWigglingMessage clicking = new FadingWigglingMessage(showPauseTime, "Click to Start", 5000, 0.01, 0.5, 0.3);
		scene3.add(level2);
		scene3.add(bombsName);
		scene3.add(clicking);

		while (!scene3.isEmpty()) {
			StdDraw.clear();
			// draw everything
			for (Anim a : scene3) {
				a.draw();
			}
			if (StdDraw.mousePressed()) {
				while (StdDraw.mousePressed()) {
					StdDraw.pause(50);;
				}
				scene3.clear();
			}
			StdDraw.show(10);
		}


		blob.mass = 0;
		blob.radius= 0.025;
		int j=1000;
		while (blob.mass < j) { 
			StdDraw.clear();

			while ( foodToEat.size() < 10) {
				Food f = new Food();
				foodToEat.add(f);
				scene3.add(f);
			}
			while (bomb.size() < 10) {
				Bombs b= new Bombs(0, 0);
				bomb.add(b);
				scene3.add(b);
			}
			scene3.add(blob);
			for (Anim a: scene3) {
				a.draw();
			}
			StdDraw.show(10);


			if(blob.mass < 0 ){
				StdDraw.clear();
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
				StdDraw.text(0.5, 0.5, "Game Over!");
				StdDraw.show(showPauseTime);
				scene3.clear();
				break;
			}

			Iterator<Food> iter4= foodToEat.iterator();
			while (iter4.hasNext()) { 
				Food f = iter4.next();
				if (f.intersect(blob)) {
					iter4.remove();
					f.killMe();
					blob.mass= blob.mass + f.mass; 
					blob.radius = blob.radius + (Math.pow(blob.mass, 0.0003)-1) ;
				}
			}

			Iterator<Bombs> iter5= bomb.iterator();
			while (iter5.hasNext()) { 
				Bombs b = iter5.next();
				if (b.intersect(blob)) {
					iter5.remove();
					b.killMe();
					blob.mass= blob.mass - 50; 
					blob.radius = blob.radius - (Math.pow(blob.mass, 0.001)-1) ;
				}
			}
			Iterator<Anim> iter6= scene3.iterator();
			while(iter6.hasNext()) {
				Anim a = iter6.next();
				if (a.isDone()) {
					iter6.remove();
				}	
			}
		}
		StdDraw.clear();
		StdDraw.show();

		// level 3
		List<Anim> scene4 = new LinkedList<Anim>();
		FadingWigglingMessage level3 = new FadingWigglingMessage(showPauseTime, "Level Three", 5000, 0.01, 0.5, 0.5);
		FadingWigglingMessage bbombs = new FadingWigglingMessage(showPauseTime, "Bouncing Bombs", 5000, 0.01, 0.5, 0.4);
		FadingWigglingMessage clicking2 = new FadingWigglingMessage(showPauseTime, "Click to Start", 5000, 0.01, 0.5, 0.3);
		scene4.add(level3);
		scene4.add(bbombs);
		scene4.add(clicking2);

		while (!scene4.isEmpty()) {
			StdDraw.clear();
			// draw everything
			for (Anim a : scene4) {
				a.draw();
			}
			if (StdDraw.mousePressed()) {
				while (StdDraw.mousePressed()) {
					StdDraw.pause(50);;
				}
				scene4.clear();
			}
			StdDraw.show(10);
		}

		blob.mass = 0;
		blob.radius= 0.025;
		int l=1000;
		foodToEat.clear();
		bomb.clear();
		while (blob.mass < l) {
			StdDraw.clear();
			while ( foodToEat.size() < 10) {
				Food f = new Food();
				foodToEat.add(f);
				scene4.add(f);
			}
			while (bomb.size() < 7) {
				Bombs b= new Bombs(Math.random()/75.0, Math.random()/75.0);
				bomb.add(b);
				scene4.add(b);

			}

			scene4.add(blob);
			for (Anim a: scene4) {
				a.draw();
			}
			StdDraw.show(10);

			if(blob.mass < 0){
				StdDraw.clear();
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
				StdDraw.text(0.5, 0.5, "Game Over!");
				StdDraw.show(showPauseTime);
				scene4.clear();
			}

			Iterator<Food> iter7= foodToEat.iterator();
			while (iter7.hasNext()) { 
				Food f = iter7.next();
				if (f.intersect(blob)) {
					iter7.remove();
					f.killMe();
					blob.mass= blob.mass + f.mass; 
					blob.radius = blob.radius + (Math.pow(blob.mass, 0.0003)-1) ;
				}
			}

			Iterator<Bombs> iter8= bomb.iterator();
			while (iter8.hasNext()) { 
				Bombs b = iter8.next();
				if (b.intersect(blob)) {
					iter8.remove();
					b.killMe();
					blob.mass= blob.mass - 100; 
					blob.radius = blob.radius - (Math.pow(blob.mass, 0.001)-1); 
				}
				if(b.x - b.radius <= 0 || b.x + b.radius >= 1) { 
					b.vx = -b.vx;
				}
				if(b.y - b.radius < 0 || b.y + b.radius > 1) { 
					b.vy = -b.vy;
				}

			}
			Iterator<Anim> iter9= scene4.iterator();
			while(iter9.hasNext()) {
				Anim a = iter9.next();
				if (a.isDone()) {
					iter9.remove();
				}	
			}
		}
		if (blob.mass >= 1000) {
			StdDraw.clear();
			StdDraw.setPenColor(Color.CYAN);
			StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			StdDraw.text(0.5, 0.5, "YOU WIN!!!!!!!");
			StdDraw.show(showPauseTime);
		}
	}

}
