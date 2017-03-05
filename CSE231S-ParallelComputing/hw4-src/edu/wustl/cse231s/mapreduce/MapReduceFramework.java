package edu.wustl.cse231s.mapreduce;

import java.util.Map;

import edu.rice.hj.api.SuspendableException;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface MapReduceFramework<E,K,V> {
	Map<K, V> mapReduceAll(E[] S, Mapper<E,K,V> mapper, Reducer<V> reducer) throws SuspendableException;
}
