package edu.wustl.cse231s.rice.classic.contrib.api;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface Reducer<T> {
	Class<T> getReduceClass();
	T identity();
	T reduce(T prevValue, T value);
}
