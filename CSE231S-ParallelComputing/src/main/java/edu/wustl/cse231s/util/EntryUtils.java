package edu.wustl.cse231s.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class EntryUtils {
	private EntryUtils() {
		throw new Error();
	}
	
	public static <K, V> Entry<K, V> createEntry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}

	public static <K, V> Entry<K, V>[] createEntryArray(int length) {
		return new Entry[length];
	}

	private static <K, V> Map<K, V> createMapFromArrayOfEntries(Map<K, V> result, Entry<K, V>[] entries) {
		for (Entry<K, V> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	public static <K, V> Map<K, V> createHashMapFromArrayOfEntries(Entry<K, V>[] entries) {
		return createMapFromArrayOfEntries( new HashMap<>(), entries );
	}
	public static <K, V> Map<K, V> createTreeMapFromArrayOfEntries(Entry<K, V>[] entries) {
		return createMapFromArrayOfEntries( new TreeMap<>(), entries );
	}
}
