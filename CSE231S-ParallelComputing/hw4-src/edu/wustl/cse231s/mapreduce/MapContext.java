package edu.wustl.cse231s.mapreduce;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface MapContext<K,V> {
	void emit( K key, V value );
}
