package edu.wustl.cse231s.mapreduce;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface Mapper<E,K,V> {
	void map(MapContext<K, V> context, E item);
}
