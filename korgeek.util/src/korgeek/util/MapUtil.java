package korgeek.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import korgeek.util.ext.CaseInsensitiveHashMap;

public class MapUtil {

	public final static <K, V> Map<K, V> create() {
		return new HashMap<K, V>() ;
	}

	public static<K, T> Map<K, T> create(K key, T value) {
		Map<K, T> result = new HashMap<K, T>() ;
		result.put(key, value) ;
		return result;
	}

	public final static <K, V> Map<K, V> toSynchronized(Map<K, V> map) {
		return  Collections.synchronizedMap(map) ;
	}

	public final static <V> Map<String, V> createCaseInsensitiveMap(){
		return new CaseInsensitiveHashMap<V>() ;
	}
}
