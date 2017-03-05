package edu.wustl.cse231s.fx;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum FxUtils {
	;
	public static void runAndWait( Runnable runnable ) {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			runnable.run();;
			countDownLatch.countDown();
		});
		try {
			countDownLatch.await();
		} catch (InterruptedException ie) {
			throw new RuntimeException( ie );
		}
	}
}
