package edu.wustl.cse231s.mapreduce.apps.friends;

import java.util.LinkedList;
import java.util.List;

import edu.wustl.cse231s.rice.classic.contrib.api.Reducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public final class ListIntersectionReducer<T> implements Reducer<List<T>> {
	@Override
	public Class<List<T>> getReduceClass() {
		return (Class)List.class;
	}
	@Override
	public List<T> identity() {
		return null;
	}
	public List<T> reduce(List<T> prevValue, List<T> value) {
		if( prevValue != null ) {
			List<T> list = new LinkedList<>();
			list.addAll(prevValue);
			list.retainAll(value);
			return list;
		} else {
			return value;
		}
	}
}
