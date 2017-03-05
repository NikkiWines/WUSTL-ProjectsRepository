package student.solution.mapreduce.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.MapContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class StudentGroupStageSkippingMapContext<K, V> implements GroupStageSkippingMapContext<K, V> {
	public StudentGroupStageSkippingMapContext(int reduceTaskCount, int arrayListSizeHint) {
		this.dictionaryArray = new Map[reduceTaskCount];
		for (int i = 0; i < this.dictionaryArray.length; i++) {
			this.dictionaryArray[i] = new HashMap<>();
		}
		this.arrayListSizeHint = arrayListSizeHint;
	}

	private int getReduceIndex(K key) {
		int reduceIndex = key.hashCode() % this.dictionaryArray.length;
		if (reduceIndex < 0) {
			reduceIndex += this.dictionaryArray.length;
		}
		return reduceIndex;
	}

	@Override
	public void emit(K key, V value) {
		int reduceIndex = getReduceIndex(key);
		throw new NotYetImplementedException();
	}

	@Override
	public Map<K, List<V>> getResult(int reduceIndex) {
		return this.dictionaryArray[reduceIndex];
	}

	private final Map<K, List<V>>[] dictionaryArray;
	private final int arrayListSizeHint;
}
