package student.solution.mapreduce.matrix;

import java.util.List;
import java.util.Map;

import edu.wustl.cse231s.mapreduce.MapContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface GroupStageSkippingMapContext<K,V> extends MapContext<K, V> {
	Map<K, List<V>> getResult(int reduceIndex);
}
