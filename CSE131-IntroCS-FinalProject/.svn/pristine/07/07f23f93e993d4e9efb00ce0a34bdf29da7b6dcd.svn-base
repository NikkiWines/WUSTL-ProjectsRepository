package lab10;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sedgewick.StdDraw;

/**
 * This is the Game I developed on the videos.  I include it here
 * for completeness, but disable the main method because you really
 * should use the Game class, which I cleaned up from this version, as the
 * basis of your game
 * 
 * @author roncytron
 *
 */
public class GameOnVideo {

	public static void main(String[] args) {
		if (true)
			throw new Error("Don't use this version, use Game instead!");
		final int showPauseTime = 5; // milliseconds
		//
		// Want to be able to continually iterate through a list of
		//    things to draw on the screen.  Each of those things
		//    should be able to draw itself, and also tell me when
		//    it is done.
		//
		List<Anim> scene1 = new LinkedList<Anim>();
		HorzBall hball = new HorzBall(0.3);
		VertBall vball = new VertBall(0.2, 3000, showPauseTime);
//		Blob     blob  = new WigglyBlob(.05);
//		blob.setColor(Color.PINK);
		scene1.add(hball);
		scene1.add(vball);
		for (int i=0; i < 10; ++i) {
			scene1.add(new VertBall(i/10.0, 3000, showPauseTime));
		}
//		scene1.add(blob);
		//
		// List because order matters:  I will will draw the scene in
		//   the order of the list.  So something that comes after something
		//   else may appear to be "over" that something else.  Above,
		//   anotherthing will be on top of onething, because I draw
		//   onething first, and then anotherthing
		//
		
		while (!scene1.isEmpty()) {
			StdDraw.clear();
			// draw everything
			for (Anim a : scene1) {
				a.draw();
			}
			//
			//  check the list for anything that is done
			//    and if it is done, remove it from the list
			//
			// This way of removing done objects will lead to
			//   a concurrentmodificationexception, because
			//   we're removing something while the iteration is happening,
			//   and that's not an safe (approved) way of doing that.
			//
//			for (Anim a : scene1) {
//				if (a.isDone()) {
//					scene1.remove(a);  // removes a "from under" the iteration -- not OK
//				}
//			}
			// Instead, generate a bona fide iterator
			Iterator<Anim> iter = scene1.iterator();
			while (iter.hasNext()) {
				Anim a = iter.next();
				if (a.isDone()) {
					// use safe way of removing
					iter.remove();   // removes the current object safely
				}
			}
			StdDraw.show(showPauseTime);
		}
		
		// game over
		// clear the screen
		StdDraw.clear();
		StdDraw.show(showPauseTime);
		

	}

}
