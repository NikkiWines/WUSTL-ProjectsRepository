package edu.wustl.cse231s.rice.classic.contrib.runtime.accumulator;

import java.util.concurrent.ForkJoinWorkerThread;

import edu.wustl.cse231s.rice.classic.HabaneroClassic;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
/* package-private */ enum LazyUtils {
	;
	/* package-private */ static int getPoolSize() {
		// return BaseTask.getPool().getPoolSize();
		return HabaneroClassic.numThreads();
	}

	/* package-private */ static int getPoolIndex() {
		Thread thread = Thread.currentThread();
		if (thread instanceof ForkJoinWorkerThread) {
			ForkJoinWorkerThread forkJoinWorkerThread = (ForkJoinWorkerThread) thread;
			return forkJoinWorkerThread.getPoolIndex();
		} else {
			throw new RuntimeException(
					"TODO: getPoolIndex() equivalent for thread " + thread + " " + thread.getClass());
		}
	}

}
