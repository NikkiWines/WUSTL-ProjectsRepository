package student.studio.wordscore;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
/*package-private*/ enum EntryUtils {
	;
	/*package-private*/ static <K, V> Entry<K, V> createEntry(K key, V value) {
		return new AbstractMap.SimpleEntry<>(key, value);
	}

	/*package-private*/ static <K, V> Entry<K, V>[] createEntryArray(int length) {
		return new Entry[length];
	}

	/*package-private*/ static <K, V> Map<K, V> createMapFromArrayOfEntries(Entry<K, V>[] entries) {
		Map<K, V> result = new HashMap<>();
		for (Entry<K, V> entry : entries) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
