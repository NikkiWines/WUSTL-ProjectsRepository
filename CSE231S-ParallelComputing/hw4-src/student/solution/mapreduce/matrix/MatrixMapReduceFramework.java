package student.solution.mapreduce.matrix;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.register;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import edu.wustl.cse231s.mapreduce.Mapper;
import edu.wustl.cse231s.mapreduce.Reducer;
import edu.wustl.cse231s.mapreduce.check.SolutionChecker;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.util.MultiWrapMap;
import instructor.solution.mapreduce.InstructorMatrixSolutionUtils;
import instructor.solution.mapreduce.InstructorSimpleSolutionUtils;
import student.solution.mapreduce.simple.IndividualMapContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class MatrixMapReduceFramework<E, K, V> implements MapReduceFramework<E, K, V> {
	public static class Builder<E, K, V> {
		public Builder<E, K, V> mapTaskCount(int mapTaskCount) {
			this.mapTaskCount = mapTaskCount;
			return this;
		}

		public Builder<E, K, V> reduceTaskCount(int reduceTaskCount) {
			this.reduceTaskCount = reduceTaskCount;
			return this;
		}


		public Builder<E, K, V> arrayListSizeHint(int arrayListSizeHint) {
			this.arrayListSizeHint = arrayListSizeHint;
			return this;
		}

		public MatrixMapReduceFramework<E, K, V> build() {
			assert this.mapTaskCount > 0;
			assert this.reduceTaskCount > 0;
			return new MatrixMapReduceFramework<>(this);
		}

		private int mapTaskCount;
		private int reduceTaskCount;
		private int arrayListSizeHint = -1;
	}

	private MatrixMapReduceFramework(Builder<E, K, V> builder) {
		this.mapTaskCount = builder.mapTaskCount;
		this.reduceTaskCount = builder.reduceTaskCount;
		this.arrayListSizeHint = builder.arrayListSizeHint;
	}

	private GroupStageSkippingMapContext<K, V>[] mapGroupAll(E[] S, Mapper<E, K, V> mapper) throws SuspendableException {
		final boolean IS_READY_TO_TEST_MY_REDUCE_ALL = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_REDUCE_ALL) {
			GroupStageSkippingMapContext<K, V>[] result = new GroupStageSkippingMapContext[this.mapTaskCount];
			// TODO: fill in parallel implementation of mapGroupAll
			// NOTE: a fine strategy would be to get it working sequentially first
			return result;
		} else {
			return InstructorMatrixSolutionUtils.mapGroupAll_fromStudent(S, mapper);
		}
	}
	
	private Map<K,V> reduceAll(GroupStageSkippingMapContext<K, V>[] grouped_f_of_S, Reducer<V> reducer) throws SuspendableException {
		final boolean IS_READY_TO_TEST_MY_REDUCE_ALL = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_REDUCE_ALL) {
			final Map<K, V>[] dictionaryArrayForReduce = new Map[this.reduceTaskCount];
			for (int i = 0; i < dictionaryArrayForReduce.length; i++) {
				dictionaryArrayForReduce[i] = new HashMap<>();
			}
			// TODO: fill in parallel implementation of reduceAll
			// NOTE: a fine strategy would be to get it working sequentially first
			Map<K, V> result = new MultiWrapMap<>(dictionaryArrayForReduce);
			return result;
		} else {
			return InstructorMatrixSolutionUtils.reduceAll_fromStudent(grouped_f_of_S, reducer);
		}
	}

	@Override
	public Map<K, V> mapReduceAll(E[] S, Mapper<E, K, V> mapper, Reducer<V> reducer) throws SuspendableException {
		GroupStageSkippingMapContext<K, V>[] grouped_f_of_S = this.mapGroupAll(S, mapper);
		Map<K, V> g_of_f_of_S = this.reduceAll(grouped_f_of_S, reducer);
		SolutionChecker.check(S, grouped_f_of_S, g_of_f_of_S, mapper, reducer);
		return g_of_f_of_S;
	}

	private final int mapTaskCount;
	private final int reduceTaskCount;
	private final int arrayListSizeHint;
}
