package student.solution.mapreduce.simple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import edu.wustl.cse231s.mapreduce.Mapper;
import edu.wustl.cse231s.mapreduce.Reducer;
import edu.wustl.cse231s.mapreduce.check.SolutionChecker;
import edu.wustl.cse231s.util.EntryUtils;
import instructor.solution.mapreduce.InstructorSimpleSolutionUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class SimpleMapReduceFramework<E,K,V> implements MapReduceFramework<E, K, V> {
	private IndividualMapContext<K,V>[] mapAll(E[] S, Mapper<E,K,V> mapper) throws SuspendableException {
		final boolean IS_READY_TO_TEST_MY_MAP_ALL = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_MAP_ALL) {
			IndividualMapContext<K,V>[] f_of_S = new IndividualMapContext[ S.length ];
			// TODO: fill in parallel implementation of mapAll
			// NOTE: a fine strategy would be to get it working sequentially first
			return f_of_S;
		} else {
			return InstructorSimpleSolutionUtils.mapAll_fromStudent(S, mapper);
		}
	}

	private Map<K, List<V>> groupAll(IndividualMapContext<K,V>[] f_of_S) {
		final boolean IS_READY_TO_TEST_MY_GROUP_ALL = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_GROUP_ALL) {
			Map<K, List<V>> grouped_f_of_S = new HashMap<>();
			// TODO: fill in sequential implementation of groupAll
			// NOTE: no need to ever parallelize
			return grouped_f_of_S;
		} else {
			return InstructorSimpleSolutionUtils.groupAll_fromStudent(f_of_S);
		}
	}
	
	private Map<K, V> reduceAll(Map<K, List<V>> grouped_f_of_S, Reducer<V> reducer) throws SuspendableException {
		final boolean IS_READY_TO_TEST_MY_REDUCE_ALL = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_REDUCE_ALL) {
			Entry<K, V> g_of_f_of_S[] = EntryUtils.createEntryArray(grouped_f_of_S.size());
			// TODO: fill in parallel implementation of reduceAll
			// NOTE: a fine strategy would be to get it working sequentially first
			return EntryUtils.createHashMapFromArrayOfEntries(g_of_f_of_S);
		} else {
			return InstructorSimpleSolutionUtils.reduceAll_fromStudent(grouped_f_of_S, reducer);
		}
	}

	@Override
	public Map<K, V> mapReduceAll(E[] S, Mapper<E,K,V> mapper, Reducer<V> reducer) throws SuspendableException {
		IndividualMapContext<K,V>[] f_of_S = this.mapAll(S, mapper);
		Map<K, List<V>> grouped_f_of_S = this.groupAll(f_of_S);
		Map<K, V> g_of_f_of_S = this.reduceAll(grouped_f_of_S, reducer);
		SolutionChecker.check(S, f_of_S, grouped_f_of_S, g_of_f_of_S, mapper, reducer);
		return g_of_f_of_S;
	}
}
