package student.studio.mapreduce.concrete.wordcount;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.mapreduce.check.SolutionChecker;
import edu.wustl.cse231s.util.EntryUtils;
import instructor.solution.mapreduce.InstructorSimpleSolutionUtils;
import student.solution.mapreduce.apps.wordcount.WordCountMapper;
import student.solution.mapreduce.apps.wordcount.WordCountReducer;
import student.solution.mapreduce.simple.IndividualMapContext;
import student.solution.mapreduce.simple.StudentIndividualMapContext;

public class WordCountConcreteStaticMapReduce {
	private static IndividualMapContext<String, Integer>[] mapAll(String[][] S, WordCountMapper mapper) throws SuspendableException {
		final boolean IS_READY_TO_TEST_MY_MAP = true; // TODO: set to true
		if (IS_READY_TO_TEST_MY_MAP) {
			IndividualMapContext<String, Integer>[] f_of_S = new IndividualMapContext[S.length];
			// TODO: fill in parallel implementation of mapAll
			// NOTE: a fine strategy would be to get it working sequentially first
			
			return f_of_S;
		} else {
			return InstructorSimpleSolutionUtils.mapAll_fromStudent(S, mapper);
		}
	}

	private static Map<String, List<Integer>> groupAll(IndividualMapContext<String, Integer>[] f_of_S) {
		final boolean IS_READY_TO_TEST_MY_GROUP = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_GROUP) {
			Map<String, List<Integer>> grouped_f_of_S = new HashMap<>();
			// TODO: fill in sequential implementation of groupAll
			// NOTE: no need to ever parallelize
			return grouped_f_of_S;
		} else {
			return InstructorSimpleSolutionUtils.groupAll_fromStudent(f_of_S);
		}
	}

	private static Map<String, Integer> reduceAll(Map<String, List<Integer>> grouped_f_of_S, WordCountReducer reducer) throws SuspendableException {
		final boolean IS_READY_TO_TEST_MY_REDUCE = false; // TODO: set to true
		if (IS_READY_TO_TEST_MY_REDUCE) {
			Entry<String, Integer> g_of_f_of_S[] = EntryUtils.createEntryArray(grouped_f_of_S.size());
			// TODO: fill in parallel implementation of reduceAll
			// NOTE: a fine strategy would be to get it working sequentially first
			return EntryUtils.createHashMapFromArrayOfEntries(g_of_f_of_S);
		} else {
			return InstructorSimpleSolutionUtils.reduceAll_fromStudent(grouped_f_of_S, reducer);
		}
	}
	
	public static Map<String, Integer> mapReduceAll(String[][] S, WordCountMapper mapper, WordCountReducer reducer) throws SuspendableException {
		IndividualMapContext<String, Integer>[] f_of_S = mapAll(S, mapper);
		Map<String, List<Integer>> grouped_f_of_S = groupAll(f_of_S);
		Map<String, Integer> g_of_f_of_S = reduceAll(grouped_f_of_S, reducer);
		SolutionChecker.check(S, f_of_S, grouped_f_of_S, g_of_f_of_S, mapper, reducer);
		return g_of_f_of_S;
	}	
}
