package com.learnr.core;

import java.util.Map;

import com.learnr.core.ds.LabeledRealVector;

/**
 * A representation of an ordered data point (in a way you can call it a Vector).
 * <p>
 * An algebraic data point (x1, x2, ....,xn) is stored as an array of n elements, where all n elements of the array are
 * expected to of same data type
 * </p>
 * 
 * @param <T>
 *            the data type of point data.
 */
public interface DataPoint<T, L> {

	/**
	 * Returns the algebraic dimension of the data point.
	 * 
	 * @return the length of the array
	 * 
	 * @see DataPoint#point()
	 */
	Integer dimension();

	/**
	 * Get the point as a Labelled vector
	 * 
	 * @return
	 */
	LabeledRealVector<L> asVector();

	/**
	 * Any associated metadata.
	 * 
	 * @return a map of meta information
	 */
	Map<String, Object> getMetadata();

}
