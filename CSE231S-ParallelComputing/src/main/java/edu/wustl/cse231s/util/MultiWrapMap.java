package edu.wustl.cse231s.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.codehaus.janino.util.MultiIterator;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class MultiWrapMap<K, V> extends AbstractMap<K, V> {
	public MultiWrapMap(Map<K, V>[] maps) {
		this.maps = maps;
	}

	private int getReduceIndex(Object key) {
		return Math.floorMod(key.hashCode(), this.maps.length);
	}

	private Map<K, V> getMap(Object key) {
		return this.maps[this.getReduceIndex(key)];
	}

	@Override
	public boolean containsKey(Object key) {
		return this.getMap(key).containsKey(key);
	}

	@Override
	public V get(Object key) {
		return this.getMap(key).get(key);
	}

	@Override
	public V put(K key, V value) {
		return this.getMap(key).put(key,value);
	}

	@Override
	public V remove(Object key) {
		return this.getMap(key).remove(key);
	}

	@Override
	public void clear() {
		for (Map<K, V> map : this.maps) {
			map.clear();
		}
	}

	@Override
	public int size() {
		int result = 0;
		for (Map<K, V> map : this.maps) {
			result += map.size();
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		for (Map<K, V> map : this.maps) {
			if (map.isEmpty()) {
				// pass
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return new AbstractSet<java.util.Map.Entry<K, V>>() {
			@Override
			public Iterator<Entry<K, V>> iterator() {
				Set<Entry<K,V>>[] sets = new Set[MultiWrapMap.this.maps.length];
				for( int i=0; i<sets.length; i++ ) {
					sets[i] = MultiWrapMap.this.maps[i].entrySet();
				}
				return new MultiIterator(sets);
			}
			@Override
			public int size() {
				return MultiWrapMap.this.size();
			}
		};
	}

	private final Map<K, V>[] maps;
}
