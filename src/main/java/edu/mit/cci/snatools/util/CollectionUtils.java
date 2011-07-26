package edu.mit.cci.snatools.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {

	public static <T> Collection<T> filter(Collection<T> collection, Filter<T> filter) {
		for (Iterator<T> i = collection.iterator(); i.hasNext();) {
			if (!filter.accept(i.next())) {
				i.remove();
			}
			
		}
		return collection;
	}

	public static <F, T> Collection<T> transmute(Collection<F> source,
			Transmuter<F, T> tx) {
		Collection<T> target = getEmpty(source);
		for (F from : source) {
			target.add(tx.transmute(from));
		}
		return target;
	}
	
	public static <F,T>  T process(Collection<F> col, Accumulator<F,T> p) {
		for (F obj:col) {
			p.accumulate(obj);
		}
		return p.result();
	}

	private static Collection getEmpty(Collection collection) {
		try {
			return (Collection) collection.getClass().newInstance();
		} catch (Exception e) {
			if (collection instanceof List) {
				return new ArrayList();
			} else {
				return new HashSet();
			}
		}
	}
	
	public static <K,V> void putAsCollection(Map<K,Collection<V>> map, K key, V val) {
		Collection<V> col = map.get(key);
		if (col == null) {
			col = new HashSet<V>();
			map.put(key, col);
		}
		col.add(val);
	}
	
	public static<K,V> void putAsCollection(Map<K, Collection<V>>map, K key, Collection<V> vals) {
		Collection<V> col = map.get(key);
		if (col == null) {
			col = new HashSet<V>();
			map.put(key, col);
		}
		col.addAll(vals);
		
	}
	
	public static<K,V> Collection<V> multiMapValues(Map<K, Collection<V>> collection) {
		Set<V> result = new HashSet<V> ();
		for (Collection<V> elts: collection.values()) {
			result.addAll(elts);
		}
		return result;
	}
	
	public static<K,V> void removeFromMultiMap(Map<K, Collection<V>>map, K key, V val) {
		Collection<V> col = map.get(key);
		if (col == null) return;			
		col.remove(val);
		
	}

	public static <T> List<T> filterND(List<T> collection, Filter<T> filter) {
		List<T> result = null;
		// try to use the underlying class

		try {
			result = (List<T>) collection.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result != null) {
			try {
				for (T obj : collection) {
					if (filter.accept(obj)) {
						result.add(obj);
					}
				}
				return result;
			} catch (Throwable t) {
				// ok, could be unmodifiable
			}
		}
		result = new ArrayList<T>();
		for (T obj : collection) {
			if (filter.accept(obj)) {
				result.add(obj);
			}
		}
		return result;
	}

	public static <T> Set<T> filterND(Set<T> collection, Filter<T> filter) {
		Set<T> result = null;
		// try to use the underlying class

		try {
			result = (Set<T>) collection.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result != null) {
			try {
				for (T obj : collection) {
					if (filter.accept(obj)) {
						result.add(obj);
					}
				}
				return result;
			} catch (Throwable t) {
				// ok, could be unmodifiable
			}
		}
		result = new HashSet<T>();
		for (T obj : collection) {
			if (filter.accept(obj)) {
				result.add(obj);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> from = new LinkedList<String>(Arrays.asList(new String[] {"1","2","3"}));
		List<Integer> to = (List<Integer>)CollectionUtils.transmute(from,new Transmuter<String,Integer>() {public Integer transmute(String obj) {return Integer.decode(obj);}});
		System.err.println(to);
	}

	

	

}
