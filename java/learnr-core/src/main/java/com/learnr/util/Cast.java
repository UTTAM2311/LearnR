package com.learnr.util;


import java.util.List;
import java.util.Map;

/**
 * Cast to given type. Minimize "Unchecked cast" warnings.
 */
public class Cast {

	/**
	 * Cast object to passed type.
	 * 
	 * @param obj
	 *            object to be casted
	 * @return the casted object output
	 */
	public static <T> T cast(Object obj) {
		@SuppressWarnings("unchecked")
		T result = (T) obj;
		return result;
	}

	/**
	 * Cast object to passed type.
	 * 
	 * @param list
	 *            of objects to casted
	 * @param clazz
	 *            the class/type of the object
	 * @return the casted objects List
	 */
	public static <T> List<T> cast(List<?> list, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) list;
		return result;
	}

	/**
	 * Cast object to passed type.
	 * 
	 * @param list
	 *            of objects to casted
	 * @param clazz
	 *            the class/type of the object
	 * @return the casted objects List
	 */
	public static <T> List<T> castToList(Object list, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) list;
		return result;
	}

	/**
	 * Cast object to passed type.
	 * 
	 * @param map
	 *            the map of the objects to be casted.
	 * @param key
	 *            the class/type of the key
	 * @param value
	 *            the class/type of the value
	 * @return the casted Map output
	 */
	public static <K, V> Map<K, V> cast(Map<?, ?> map, Class<K> key, Class<V> value) {
		@SuppressWarnings("unchecked")
		Map<K, V> result = (Map<K, V>) map;
		return result;
	}

	/**
	 * Cast object to passed type.
	 * 
	 * @param map
	 *            the map of the objects to be casted.
	 * @param key
	 *            the class/type of the key
	 * @param value
	 *            the class/type of the value
	 * @return the casted Map output
	 */
	public static <K, V> Map<K, V> castToMap(Object map, Class<K> key, Class<V> value) {
		@SuppressWarnings("unchecked")
		Map<K, V> result = (Map<K, V>) map;
		return result;
	}

}
