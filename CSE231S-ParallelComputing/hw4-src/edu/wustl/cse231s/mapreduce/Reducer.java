package edu.wustl.cse231s.mapreduce;

import java.util.List;

import edu.rice.hj.api.SuspendableException;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface Reducer<V> {
	V reduce( List<V> list ) throws SuspendableException;
}
