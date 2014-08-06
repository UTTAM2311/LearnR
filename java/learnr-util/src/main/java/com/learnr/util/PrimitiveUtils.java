package com.learnr.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * Odd ball utilities that are just convenient to have.
 */
public class PrimitiveUtils {

	/* --- toPrimitive Utilities --- */

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with default value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @return Array of corresponding primitive class type
	 */
	public static long[] toPrimitive(Long[] inArray) {
		return toPrimitive(inArray, 0L);
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with default value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @return Array of corresponding primitive class type
	 */
	public static int[] toPrimitive(Integer[] inArray) {
		return toPrimitive(inArray, 0);
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with default value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @return Array of corresponding primitive class type
	 */
	public static short[] toPrimitive(Short[] inArray) {
		return toPrimitive(inArray, (short) 0);
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with default value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @return Array of corresponding primitive class type
	 */
	public static char[] toPrimitive(Character[] inArray) {
		return toPrimitive(inArray, (char) 0);
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with default value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @return Array of corresponding primitive class type
	 */
	public static double[] toPrimitive(Double[] inArray) {
		return toPrimitive(inArray, 0.0);
	}

	/* --- toPrimitive Utilities with valueForNull --- */

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with the passed
	 * value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @param valueForNull
	 *            Value to use as replacement for null.
	 * @return Array of corresponding primitive class type
	 */
	public static long[] toPrimitive(Long[] inArray, long valueForNull) {
		if (inArray == null)
			return null;

		final long[] outArray = new long[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			Long ov = inArray[i];
			outArray[i] = (ov == null ? valueForNull : ov);
		}
		return outArray;
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with the passed
	 * value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @param valueForNull
	 *            Value to use as replacement for null.
	 * @return Array of corresponding primitive class type
	 */
	public static int[] toPrimitive(Integer[] inArray, int valueForNull) {
		if (inArray == null)
			return null;

		final int[] outArray = new int[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			Integer ov = inArray[i];
			outArray[i] = (ov == null ? valueForNull : ov);
		}
		return outArray;
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with the passed
	 * value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @param valueForNull
	 *            Value to use as replacement for null.
	 * @return Array of corresponding primitive class type
	 */
	public static short[] toPrimitive(Short[] inArray, short valueForNull) {
		if (inArray == null)
			return null;

		final short[] outArray = new short[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			Short ov = inArray[i];
			outArray[i] = (ov == null ? valueForNull : ov);
		}
		return outArray;
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with the passed
	 * value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @param valueForNull
	 *            Value to use as replacement for null.
	 * @return Array of corresponding primitive class type
	 */
	public static char[] toPrimitive(Character[] inArray, char valueForNull) {
		if (inArray == null)
			return null;

		final char[] outArray = new char[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			Character ov = inArray[i];
			outArray[i] = (ov == null ? valueForNull : ov);
		}
		return outArray;
	}

	/**
	 * Converts to array of corresponding primitive class type. All the null values will be replaced with the passed
	 * value.
	 * 
	 * @param inArray
	 *            Array of wrapper class type
	 * @param valueForNull
	 *            Value to use as replacement for null.
	 * @return Array of corresponding primitive class type
	 */
	public static double[] toPrimitive(Double[] inArray, double valueForNull) {
		if (inArray == null)
			return null;

		final double[] outArray = new double[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			Double ov = inArray[i];
			outArray[i] = (ov == null ? valueForNull : ov);
		}
		return outArray;
	}
	
	
	/* --- Double related utilities --- */
	/* --- TODO: extend them for other ClassTypes --- */

	/**
	 * Get the Collection of {@code Double} as an array of primitive {@code double}.
	 * 
	 * @param collection
	 *            any {@link Collection} of {@link Double}
	 * @return Array of primitive {@code double}
	 */
	public static double[] asPrimitiveDoubleArray(Collection<Double> collection) {
		if (collection == null)
			return null;

		double[] dArray = new double[collection.size()];
		int i = 0;
		for (Double d : collection) {
			dArray[i++] = d;
		}
		return dArray;
	}

	/**
	 * Get double[] from String[] array
	 * 
	 * @param in
	 *            array of Strings
	 * @return array of doubles
	 */
	public static double[] asPrimitiveDoubleArray(String[] inArray) {
		double[] ret = new double[inArray.length];
		for (int i = 0; i < inArray.length; i++)
			ret[i] = Double.parseDouble(inArray[i]);

		return ret;
	}

	/**
	 * toString for 2 dimensional double array.
	 * 
	 * @param dArray
	 *            2 dimensional array of double
	 * @return the string representation
	 */
	public static String toString(double[][] dArray) {
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < dArray.length; i++) {
			result.append(Arrays.toString(dArray[i]));
			if (i < dArray.length - 1)
				result.append(',');
		}
		result.append(']');
		return result.toString();
	}

	/**
	 * 
	 * @param inArray
	 * @return
	 */
	public static Double[] toWrapper(double[] inArray) {
		if (inArray == null)
			return null;

		final Double[] outArray = new Double[inArray.length];
		for (int i = 0; i < inArray.length; i++) {
			Double ov = inArray[i];
			outArray[i] = ov;
		}
		return outArray;
	}

}
