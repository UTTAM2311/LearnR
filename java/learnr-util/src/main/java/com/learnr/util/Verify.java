package com.learnr.util;


import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Assertion/Verification utility class that assists in validating arguments.
 *
 * <p>
 * This class is similar to JUnit's assertion library. If an argument value is deemed invalid, an
 * {@link IllegalArgumentException} is thrown (typically).
 * </p>
 */
public class Verify {

	/**
	 * Verifies a boolean expression, throwing {@code IllegalArgumentException} if the test result is {@code false}.
	 * 
	 * @param expression
	 *            a boolean expression
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if expression is {@code false}
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verifies a boolean expression, throwing {@code IllegalArgumentException} if the test result is {@code false}.
	 * 
	 * @param expression
	 *            a boolean expression
	 * @throws IllegalArgumentException
	 *             if expression is {@code false}
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[failed] - this expression must be true");
	}

	/**
	 * Verify that an object is {@code null} .
	 * 
	 * @param object
	 *            the object to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the object is not {@code null}
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verify that an object is {@code null} .
	 * 
	 * @param object
	 *            the object to check
	 * @throws IllegalArgumentException
	 *             if the object is not {@code null}
	 */
	public static void isNull(Object object) {
		isNull(object, "[failed] - the object argument must be null");
	}

	/**
	 * Verify that an object is not {@code null} .
	 * 
	 * @param object
	 *            the object to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the object is {@code null}
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verify that an object is not {@code null} .
	 * 
	 * @param object
	 *            the object to check
	 * @throws IllegalArgumentException
	 *             if the object is {@code null}
	 */
	public static void notNull(Object object) {
		notNull(object, "[failed] - this argument is required; it must not be null");
	}
	
	/**
	 * Verify that all passed objects are not {@code null} .
	 * 
	 * @param args
	 *            the array of objects to be verified.
	 * 
	 * @throws IllegalArgumentException
	 *             if any one of the objects is {@code null}
	 */
	public static void notNull(Object... args) {
		if (args == null)
			return;

		for (Object object : args) {
			notNull(object, "[failed] - this argument is required; it must not be null");
		}
	}

	/**
	 * Verify that the given String is not empty; that is, it must not be {@code null} and not the empty String.
	 * 
	 * @param text
	 *            the String to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 */
	public static void hasLength(String text, String message) {
		if (text == null || !(text.length() > 0)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verify that the given String is not empty; that is, it must not be {@code null} and not the empty String.
	 * 
	 * @param text
	 *            the String to check
	 */
	public static void hasLength(String text) {
		hasLength(text, "[failed] - this String argument must have length; it must not be null or empty");
	}

	/**
	 * Verify that the given String has valid text content; that is, it must not be {@code null} and must contain at
	 * least one non-whitespace character.
	 * 
	 * @param text
	 *            the String to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 */
	public static void hasText(String text, String message) {
		hasLength(text, message);

		// Check white spaces ...
		int tLen = text.length();
		for (int i = 0; i < tLen; i++) {
			if (!Character.isWhitespace(text.charAt(i))) {
				return;
			}
		}

		throw new IllegalArgumentException(message);
	}

	/**
	 * Verify that the given String has valid text content; that is, it must not be {@code null} and must contain at
	 * least one non-whitespace character.
	 * 
	 * @param text
	 *            the String to check
	 */
	public static void hasText(String text) {
		hasText(text, "[failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	/**
	 * Verify that an array has elements; that is, it must not be {@code null} and must have at least one element.
	 * 
	 * @param array
	 *            the array to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the object array is {@code null} or has no elements
	 */
	public static void notEmpty(Object[] array, String message) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verify that an array has elements; that is, it must not be {@code null} and must have at least one element.
	 * 
	 * @param array
	 *            the array to check
	 * @throws IllegalArgumentException
	 *             if the object array is {@code null} or has no elements
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(array, "[failed] - this array must not be empty: it must contain at least 1 element");
	}

	/**
	 * Verify that an array has no null elements. Note: Does not complain if the array is empty!
	 * 
	 * @param array
	 *            the array to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the object array contains a {@code null} element
	 */
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Verify that an array has no null elements. Note: Does not complain if the array is empty!
	 * 
	 * @param array
	 *            the array to check
	 * @throws IllegalArgumentException
	 *             if the object array contains a {@code null} element
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array, "[failed] - this array must not contain any null elements");
	}

	/**
	 * Verify that a collection has elements; that is, it must not be {@code null} and must have at least one element.
	 * 
	 * @param collection
	 *            the collection to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the collection is {@code null} or has no elements
	 */
	public static void notEmpty(Collection<? extends Object> collection, String message) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verify that a collection has elements; that is, it must not be {@code null} and must have at least one element.
	 * 
	 * @param collection
	 *            the collection to check
	 * @throws IllegalArgumentException
	 *             if the collection is {@code null} or has no elements
	 */
	public static void notEmpty(Collection<? extends Object> collection) {
		notEmpty(collection,
				"[failed] - this collection must not be empty: it must contain at least 1 element");
	}

	/**
	 * Verify that a Map has entries; that is, it must not be {@code null} and must have at least one entry.
	 * 
	 * @param map
	 *            the map to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the map is {@code null} or has no entries
	 */
	public static void notEmpty(Map<? extends Object, ? extends Object> map, String message) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Verify that a Map has entries; that is, it must not be {@code null} and must have at least one entry.
	 * 
	 * @param map
	 *            the map to check
	 * @throws IllegalArgumentException
	 *             if the map is {@code null} or has no entries
	 */
	public static void notEmpty(Map<? extends Object, ? extends Object> map) {
		notEmpty(map, "[failed] - this map must not be empty; it must contain at least one entry");
	}

	/**
	 * Verify that the provided object is an instance of the provided class.
	 * 
	 * @param clazz
	 *            the required class
	 * @param obj
	 *            the object to check
	 * @throws IllegalArgumentException
	 *             if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class<?> clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	/**
	 * Verify that the provided object is an instance of the provided class.
	 * 
	 * @param type
	 *            the type to check against
	 * @param obj
	 *            the object to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the object is not an instance of class
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class<?> type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message + " " + "Object of class ["
					+ (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
		}
	}

	/**
	 * Verify that {@code superType.isAssignableFrom(subType)} is {@code true}.
	 * 
	 * @param superType
	 *            the super type to check
	 * @param subType
	 *            the sub type to check
	 * @throws IllegalArgumentException
	 *             if the classes are not assignable
	 */
	public static void isAssignable(Class<?> superType, Class<?> subType) {
		isAssignable(superType, subType, "");
	}

	/**
	 * Verify that {@code superType.isAssignableFrom(subType)} is {@code true}.
	 * 
	 * @param superType
	 *            the super type to check against
	 * @param subType
	 *            the sub type to check
	 * @param message
	 *            the exception message to use if the assertion fails
	 * @throws IllegalArgumentException
	 *             if the classes are not assignable
	 */
	public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
		}
	}
	
	
	
	/**
	 * TODO Complete ...
	 * 
	 * @param values
	 * @param value
	 * @throws IllegalArgumentException
	 */
	public static void containsValue(Collection<? extends Object> values, Object value) {
		if (values != null) {
			for (Object val : values) {
				if (val != null && val.equals(value)) {
					return;
				}
			}
		}
		
		throw new IllegalArgumentException("[failed] - the collection does not contain the passed entry");
	}

}
