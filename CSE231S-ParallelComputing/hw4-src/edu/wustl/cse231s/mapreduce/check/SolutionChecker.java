package edu.wustl.cse231s.mapreduce.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.mapreduce.Mapper;
import edu.wustl.cse231s.mapreduce.Reducer;
import instructor.solution.mapreduce.InstructorMatrixSolutionUtils;
import instructor.solution.mapreduce.InstructorSimpleSolutionUtils;
import student.solution.mapreduce.matrix.GroupStageSkippingMapContext;
import student.solution.mapreduce.simple.IndividualMapContext;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class SolutionChecker {
	private SolutionChecker() {
		throw new Error();
	}

	private static <K,V> List<Map<K,V>> toList( IndividualMapContext<K,V>[] f_of_S ) {
		List<Map<K,V>> result = new ArrayList<>( f_of_S.length );
		for( IndividualMapContext<K, V> mapContext : f_of_S ) {
			result.add(mapContext.getResult());
		}
		return result;
	}
	
	public static <E, K, V> void check(E[] S, IndividualMapContext<K,V>[] f_of_S, Map<K, List<V>> grouped_f_of_S, Map<K, V> g_of_f_of_S, Mapper<E, K, V> mapper, Reducer<V> reducer ) throws SuspendableException {
		IndividualMapContext<K,V>[] f_of_S_instructor = InstructorSimpleSolutionUtils.mapAll_fromCheck(S, mapper);
		List<Map<K,V>> _f_of_S = toList(f_of_S);
		List<Map<K,V>> _f_of_S_instructor = toList(f_of_S_instructor);
		if (checkLists(_f_of_S, _f_of_S_instructor)) {
			Map<K, List<V>> grouped_f_of_S_instructor = InstructorSimpleSolutionUtils.groupAll_fromCheck(f_of_S);
			if (checkGroupedFofS(grouped_f_of_S, grouped_f_of_S_instructor)) {
				Map<K, V> g_of_f_of_S_instructor = InstructorSimpleSolutionUtils.reduceAll_fromCheck(grouped_f_of_S, reducer);
				if (g_of_f_of_S.equals(g_of_f_of_S_instructor)) {
					// pass
				} else {
					throw new RuntimeException(createGofFofSExceptionMessage(g_of_f_of_S, g_of_f_of_S_instructor));
				}
			} else {
				throw new RuntimeException(
						createGroupedFofSExceptionMessage(grouped_f_of_S, grouped_f_of_S_instructor));
			}
		} else {
			throw new RuntimeException(createFofSExceptionMessage(_f_of_S, _f_of_S_instructor));
		}
	}

	private static <E> boolean checkLists(List<E> a, List<E> b) {
		List<E> buffer = new ArrayList<>(a.size());
		buffer.addAll(a);
		for (int i = 0; i < 10; i++) {
			// go overboard, check the list shuffled 10 times
			Collections.shuffle(buffer);
			if (CollectionUtils.isEqualCollection(buffer, b)) {
				// pass
			} else {
				return false;
			}
		}
		return true;
	}

	private static <K, V> boolean checkGroupedFofS(Map<K, List<V>> grouped_f_of_S, Map<K, List<V>> grouped_f_of_S_instructor) {
		boolean[] array = { true };
		grouped_f_of_S_instructor.entrySet().forEach((entry) -> {
			List<V> studentValue = grouped_f_of_S.get(entry.getKey());
			if (studentValue != null) {
				if (checkLists(entry.getValue(), studentValue)) {
					// pass
				} else {
					array[0] = false;
				}
			} else {
				array[0] = false;
			}
		});
		return array[0];
	}

	private static <K, V> String createFofSExceptionMessage(List<Map<K, V>> f_of_S, List<Map<K, V>> f_of_S_instructor) {
		StringBuilder sb = new StringBuilder();
		sb.append("f_of_S does not match instructor's solution f_of_S\n");
		sb.append("\n");
		sb.append("your f_of_S = ");
		appendFofS(sb, f_of_S);
		sb.append("\n");
		sb.append("instructor's f_of_S = ");
		appendFofS(sb, f_of_S_instructor);
		sb.append("\n");
		return sb.toString();
	}

	private static <K, V> String createGroupedFofSExceptionMessage(Map<K, List<V>> grouped_f_of_S, Map<K, List<V>> grouped_f_of_S_instructor) {
		StringBuilder sb = new StringBuilder();
		sb.append("grouped_f_of_S does not match instructor's solution grouped_f_of_S\n");
		sb.append("\n");
		sb.append("your grouped_f_of_S = ");
		appendGroupedFofS(sb, grouped_f_of_S);
		sb.append("\n");
		sb.append("instructor's grouped_f_of_S = ");
		appendGroupedFofS(sb, grouped_f_of_S_instructor);
		sb.append("\n");
		return sb.toString();
	}

	private static <K, V> String createGofFofSExceptionMessage(Map<K, V> g_of_f_of_S, Map<K, V> g_of_f_of_S_instructor) {
		StringBuilder sb = new StringBuilder();
		sb.append("g_of_f_of_S does not match instructor's solution g_of_f_of_S\n");
		sb.append("\n");
		sb.append("your g_of_f_of_S = ");
		appendGofFofS(sb, g_of_f_of_S);
		sb.append("\n");
		sb.append("instructor's g_of_f_of_S = ");
		appendGofFofS(sb, g_of_f_of_S_instructor);
		sb.append("\n");
		return sb.toString();
	}

	private static <K, V> void appendGofFofS(StringBuilder sb, Map<K, V> g_of_f_of_S) {
		sb.append("{\n");
		g_of_f_of_S.entrySet().forEach((entry) -> {
			sb.append("\t" + entry.getKey() + "->" + entry.getValue() + "\n");
		});
		sb.append("}\n");
	}

	private static <K, V> void appendGroupedFofS(StringBuilder sb, Map<K, List<V>> grouped_f_of_S) {
		sb.append("{\n");
		grouped_f_of_S.entrySet().forEach((entry) -> {
			sb.append("\t" + entry.getKey() + "->" + entry.getValue() + "\n");
		});
		sb.append("}\n");
	}

	private static <K, V> void appendFofS(StringBuilder sb, List<Map<K, V>> f_of_S) {
		sb.append("[\n");
		f_of_S.forEach((item) -> {
			sb.append("\t{\n");
			item.entrySet().forEach((entry) -> {
				sb.append("\t" + entry.getKey() + "->" + entry.getValue() + "\n");
			});
			sb.append("\t},\n");
		});
		sb.append("]\n");
	}

	public static <E, K, V> void check(E[] S, GroupStageSkippingMapContext<K,V>[] grouped_f_of_S, Map<K, V> g_of_f_of_S, Mapper<E, K, V> mapper, Reducer<V> reducer ) throws SuspendableException {
		GroupStageSkippingMapContext<K,V>[] grouped_f_of_S_instructor = InstructorMatrixSolutionUtils.mapGroupAll_fromCheck(S, mapper);
		//TODO
	}
}
