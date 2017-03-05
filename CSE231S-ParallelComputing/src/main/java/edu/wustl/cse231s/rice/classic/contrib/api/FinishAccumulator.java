package edu.wustl.cse231s.rice.classic.contrib.api;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface FinishAccumulator<T> {
	T get();
	void put(T value);
}
