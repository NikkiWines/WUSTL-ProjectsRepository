package edu.wustl.cse231s.rice.classic.options;

import java.util.ArrayList;
import java.util.List;

import edu.rice.hj.api.HjFuture;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class AwaitFuturesOption {
	public AwaitFuturesOption(HjFuture<?> futureA, HjFuture<?>[] futuresBtoZ) {
		this.futures = new ArrayList<>(1 + futuresBtoZ.length);
		this.futures.add(futureA);
		for( HjFuture<?> future : futuresBtoZ ) {
			this.futures.add(future);
		}
	}

	public List<HjFuture<?>> getFutures() {
		return this.futures;
	}

	private final List<HjFuture<?>> futures;
}
