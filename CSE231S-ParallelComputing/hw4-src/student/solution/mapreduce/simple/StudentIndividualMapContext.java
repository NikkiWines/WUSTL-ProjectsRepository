package student.solution.mapreduce.simple;

import java.util.HashMap;
import java.util.Map;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.MapContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class StudentIndividualMapContext<K, V> implements IndividualMapContext<K, V> {
	public StudentIndividualMapContext() {
		this.dictionary = new HashMap<>();
	}

	@Override
	public void emit(K key, V value) {
		if( this.dictionary.containsKey(key)) {
			throw new IllegalArgumentException();
		}
		this.dictionary.put(key, value); // put key and value into dictionary
	}

	@Override
	public Map<K, V> getResult() {
		return this.dictionary;
	}

	private final Map<K, V> dictionary;
}
