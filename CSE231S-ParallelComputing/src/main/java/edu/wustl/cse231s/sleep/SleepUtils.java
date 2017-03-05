package edu.wustl.cse231s.sleep;

import java.util.Random;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum SleepUtils {
	;
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	public static void sleep(long millis, int nanos) {
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	public static void sleepRandom(int boundMillis) {
		Random random = new Random();
		sleep(random.nextInt(boundMillis));
	}
}
