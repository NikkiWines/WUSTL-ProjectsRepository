package edu.wustl.cse231s.rice.classic.contrib.consumer;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.concurrent.BlockingQueue;

import edu.rice.hj.api.HjSuspendable;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class HabaneroConsumerThread extends Thread {
	public HabaneroConsumerThread(BlockingQueue<HjSuspendable> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		super.run();
		launchHabaneroApp(() -> {
			while (true) {
				try {
					this.consume(queue.take());
				} catch (InterruptedException ie) {
					throw new RuntimeException(ie);
				}
			}
		});
	}

	private void consume(HjSuspendable body) {
		async(body);
	}

	private final BlockingQueue<HjSuspendable> queue;
}