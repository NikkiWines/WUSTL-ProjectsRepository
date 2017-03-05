package student.solution.mapreduce.simple;

import java.util.Map;

import edu.wustl.cse231s.mapreduce.MapContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface IndividualMapContext<K,V> extends MapContext<K, V>{
	Map<K, V> getResult();
}
